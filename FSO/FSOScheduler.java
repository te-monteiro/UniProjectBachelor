package caoss.simulator.os.scheduling;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import caoss.simulator.Program;
import caoss.simulator.configuration.Hardware;
import caoss.simulator.hardware.DeviceId;
import caoss.simulator.hardware.Timer;
import caoss.simulator.os.Dispatcher;
import caoss.simulator.os.Logger;
import caoss.simulator.os.ProcessControlBlock;
import caoss.simulator.os.Scheduler;


public class FSOScheduler implements Scheduler<SchedulingState> {

	private static final int QUANTUM = 10;
	private static final int QUOTA = 20;
	private static final int PERIOD = 150;
	private static final int PRIOLEVELS = 4;


	private final Queue<ProcessControlBlock<SchedulingState>>[] readyQueues;
	private final Queue<ProcessControlBlock<SchedulingState>> blockedQueue;
	private ProcessControlBlock<SchedulingState> running; // just one CPU
			
	private final Timer timer = (Timer) Hardware.devices.get(DeviceId.TIMER);

	private long lastUpgradeTime = 0;  // last priority upgrade time
	
	
	@SuppressWarnings("unchecked")
	public FSOScheduler( ) {
		this.readyQueues = (Queue<ProcessControlBlock<SchedulingState>>[]) new LinkedBlockingQueue[PRIOLEVELS];
		this.blockedQueue = new LinkedBlockingQueue<ProcessControlBlock<SchedulingState>>();
		
		for (int i = 0; i < PRIOLEVELS; i++) {
			this.readyQueues[i] = new LinkedBlockingQueue<ProcessControlBlock<SchedulingState>>();
		}
		
		this.lastUpgradeTime = Hardware.clock.getTime(); 
	}
	
	
	@Override
	public synchronized void newProcess(Program program) {	
	
		ProcessControlBlock<SchedulingState> pcb =
				new ProcessControlBlock<SchedulingState>(program, 
						new SchedulingState(0, QUOTA));
		Logger.info("Create process " + pcb.pid + " to run program " + program.getFileName());
		
		if(running != null)
		{	
			readyQueues[0].add(pcb);
		}
		else
		{
			dispatch(pcb);
		}
		
		upgrade();
		logQueues();
	}

	@Override
	public synchronized void ioRequest(ProcessControlBlock<SchedulingState> pcb) {
		Logger.info("Process " + pcb.pid + ": IO request");
		
		int pcbQuota = pcb.getSchedulingState().getQuota();
		int time = (int) (Hardware.clock.getTime() - pcb.getSchedulingState().getSchedulingTime());
		if(pcbQuota < time)
		{
			Logger.info("Process" + pcb.pid + ": quota expired");
			int level = pcb.getSchedulingState().getLevel();
			if(level < 3)
			{
			pcb.getSchedulingState().setLevel(level + 1);
			}
			pcb.getSchedulingState().setQuota(QUOTA);
		}
		else
		{
			int quota = pcbQuota - time;
			pcb.getSchedulingState().setQuota(quota);
		}
		 
		blockedQueue.add(pcb);
		chooseNext();	
		
		upgrade();
		logQueues();
	}

	@Override
	public synchronized void ioConcluded(ProcessControlBlock<SchedulingState> pcb) {
		Logger.info("Process " + pcb.pid + ": IO concluded");
		
		blockedQueue.remove(pcb);
		
		if(running != null)
		{	
			int level = pcb.getSchedulingState().getLevel();
			readyQueues[level].add(pcb);
		}
		else
		{
			dispatch(pcb);
		}
		
		upgrade();
		logQueues();
	}

	@Override
	public synchronized void quantumExpired(ProcessControlBlock<SchedulingState> pcb) {
		Logger.info("Process " + pcb.pid + ": quantum expired");
		
		int pcbQuota = pcb.getSchedulingState().getQuota();
		
		if(pcbQuota <= QUANTUM)
		{
			Logger.info("Process" + pcb.pid + ": quota expired");
			int level = pcb.getSchedulingState().getLevel();
			if(level < 3)
			{
				pcb.getSchedulingState().setLevel(level + 1);
			}
			pcb.getSchedulingState().setQuota(QUOTA);
		}
		else
		{
			int quota = pcbQuota - QUANTUM;
			pcb.getSchedulingState().setQuota(quota);
		}
		readyQueues[pcb.getSchedulingState().getLevel()].add(pcb);
		chooseNext();	
		
		upgrade();
		logQueues();
	}

	@Override
	public synchronized void processConcluded(ProcessControlBlock<SchedulingState> pcb) {
		Logger.info("Process " + pcb.pid + ": execution concluded");
		
		long turnarround = Hardware.clock.getTime() - pcb.arrivalTime;
		Logger.info("Process " + pcb.pid + ": turnarround time: " + turnarround);		
		
		chooseNext();
		
		upgrade();
		logQueues();
	}
	


	private void upgrade() {
		long currentTime = Hardware.clock.getTime();
		long elapsed = currentTime - this.lastUpgradeTime;
		if (elapsed >= PERIOD) {
			Logger.info("Upgrade priorities");	
			
			ProcessControlBlock<SchedulingState> pcb;
			
			for(int i = 0; i < PRIOLEVELS; i++)
			{
				
				Iterator<ProcessControlBlock<SchedulingState>> it = readyQueues[i].iterator();
				
				while(it.hasNext())
				{
					pcb = it.next();
					
					
					if(i == 1)
					{
						readyQueues[i].remove(pcb);
						pcb.getSchedulingState().setLevel(i - 1);
						readyQueues[i-1].add(pcb);
						
					}
					else if(i >= 2)
						{
							readyQueues[i].remove(pcb);
							pcb.getSchedulingState().setLevel(i - 2);
							readyQueues[i-2].add(pcb);
						}
	
					
					pcb.getSchedulingState().setQuota(QUOTA);
				}
			
			}
			
			Iterator<ProcessControlBlock<SchedulingState>> ite = blockedQueue.iterator();
			
			while(ite.hasNext())
			{
				pcb = ite.next();
				
				int level = pcb.getSchedulingState().getLevel();
				
				if(level == 1)
				{

					pcb.getSchedulingState().setLevel(level - 1);

					
				}
				else 
					if(level >= 2)
					{

						pcb.getSchedulingState().setLevel(level - 2);
						
					}
				
				pcb.getSchedulingState().setQuota(QUOTA);
			}
			
			int lv = running.getSchedulingState().getLevel();
			if(lv == 1)
				running.getSchedulingState().setLevel(lv - 1);
			else if(lv >=2)
			{
				running.getSchedulingState().setLevel(lv - 2);
			}
			
			running.getSchedulingState().setQuota(QUOTA);
				
			this.lastUpgradeTime = currentTime;
		}
			
	}
	
	
	private void chooseNext() {
		for (Queue<ProcessControlBlock<SchedulingState>> queue : this.readyQueues) {
			if (queue.size() > 0) {
				dispatch(queue.poll());
				return;
			}
		}
			
		dispatch(null);
	}
	
	private void dispatch(ProcessControlBlock<SchedulingState> pcb) {
		Dispatcher.dispatch(pcb);
		running = pcb;
		
		if (pcb != null) {
			SchedulingState state = pcb.getSchedulingState();
			state.setSchedulingTime(Hardware.clock.getTime());
			timer.set(QUANTUM);
			Logger.info("Run process "+ pcb.pid +" (quantum="+ QUANTUM +", quota="+ state.getQuota()+")");
		}
		else
			timer.set(0);
	}
	
	
	private void logQueues() {
		int i = 0;
		for (Queue<ProcessControlBlock<SchedulingState>> queue : this.readyQueues) {
			Logger.info("Queue " + i + ": " + queue);
			i++;
		}
		Logger.info("Blocked " + blockedQueue);
	}
}
