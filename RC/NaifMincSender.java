package assignment1;
import cnss.lib.*;
import cnss.simulator.*;

public class NaifMincSender extends AbstractApplicationAlgorithm {

	public static int BLOCKSIZE = 10000; // 10000*8 = 80000 bits
	public static int TOTAL_PACKETSIZE = BLOCKSIZE+Packet.HEADERSIZE; // 10000*8 = 80160 bits

	public NaifMincSender() {
		super(true, "naif-minc-sender");
	}

	int totSent;
	int totalBlocks;
	int startTime;
	int transferTime;
	int totBytesTransferred;
	int e2eTransferRate;
	double windowSize;
	int nAck;
	int nWindow;

	boolean mayProceed = false;

	public int initialise(int now, int node_id, Node self, String[] args) {
		super.initialise(now, node_id, self, args);
		if ( args.length != 1 ) {
			log(now, "ERROR: files-sender: missing argument totalBlocks");
			System.exit(-1);
		}
		nAck = 0;
		nWindow = 0;
		totalBlocks = Integer.parseInt(args[0]);
		windowSize = Math.pow(2, nWindow);

		log(0, "starting");
		totSent = 0;
		startTime = now;

		mayProceed = true;
		return 1;

	}

	public void on_clock_tick(int now) {
		//System.out.println("window no on clock: " + windowSize);
		if ( mayProceed && totSent < totalBlocks) {
			totSent++;
			byte[] pl = new byte[BLOCKSIZE];
			pl[0]= (byte) ( totSent & 0xff ); 
			self.send( self.createDataPacket( 1, pl ));
			log(now, "sent packet of size "+TOTAL_PACKETSIZE+" n. "+totSent);
			if(totSent >= windowSize){
				mayProceed = false;
			}
		}

	}

	public void on_receive(int now, DataPacket p) {
		boolean maxWindow = false;
		log(now, "ack packet: "+p+" pl: "+new String(p.getPayload()));

		
		nAck++; ///ack received
	//	System.out.println("nAck: " + nAck + "      windowSize: " + windowSize);

		
		if (Math.pow(2, (nWindow+1)) > totalBlocks) {
			transferTime = now - startTime;
			totBytesTransferred = TOTAL_PACKETSIZE*totalBlocks;
			float transferTimeInSeconds = (float)transferTime / 1000;
			e2eTransferRate = (int)(totBytesTransferred*8 / transferTimeInSeconds);
			log(now, totBytesTransferred+" bytes transferred in "+transferTime+" ms at "+e2eTransferRate+" bps e2e rate");
		}
		
		
		if(nAck==windowSize) {
			mayProceed = true;
			nWindow++;
			windowSize += Math.pow(2, nWindow);		
		}
	}

	public void showState(int now) {
		System.out.println(name + " sent " + totSent + " packets with blocks");
		System.out.println(name+" "+totBytesTransferred+" bytes transferred in "
				+transferTime+" ms at "+e2eTransferRate+" bps e2e rate");
	}
}

