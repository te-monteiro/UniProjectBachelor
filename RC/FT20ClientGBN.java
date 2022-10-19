
import java.io.File;
import java.io.RandomAccessFile;

import cnss.simulator.Node;
import cnss.simulator.Simulator;
import ft20.FT20AbstractApplication;
import ft20.FT20_AckPacket;
import ft20.FT20_DataPacket;
import ft20.FT20_FinPacket;
import ft20.FT20_PacketHandler;
import ft20.FT20_UploadPacket;

public class FT20ClientGBN extends FT20AbstractApplication implements FT20_PacketHandler {

	static int SERVER = 1;

	enum State {
		BEGINNING, UPLOADING, FINISHING
	};

	static int DEFAULT_TIMEOUT = 1000;

	private File file;
	private RandomAccessFile raf;
	private int BlockSize;
	private int windowSize;
	private int nextPacketSeqN, lastPacketSeqN;
	private int lastAck; // ultimo ack recebido
	private State state;
	private boolean mayProceed;

	public FT20ClientGBN() {
		super(true, "FT20-ClientGBN");
	}

	public int initialise(int now, int node_id, Node nodeObj, String[] args) {
		super.initialise(now, node_id, nodeObj, args, this);

		raf = null;
		file = new File(args[0]);
		BlockSize = Integer.parseInt(args[1]);
		windowSize = Integer.parseInt(args[2]);

		state = State.BEGINNING;
		lastPacketSeqN = (int) Math.ceil(file.length() / (double)BlockSize);

		mayProceed = false;

		sendNextPacket(now);
		return 1;
	}

	/*public void on_clock_tick(int now) {
		// mandar um pacote caso ele caiba na janela e o estado seja uploading
		if(nextPacketSeqN < windowSize && state == State.UPLOADING && mayProceed)
			sendNextPacket(now);
	}*/

	private void sendNextPacket(int now) {
		switch (state) {
		case BEGINNING:
			super.sendPacket(now, SERVER, new FT20_UploadPacket(file.getName(), now));
			break;
		case UPLOADING:
			super.sendPacket(now, SERVER, readDataPacket(file, nextPacketSeqN, now));
			break;
		case FINISHING:
			super.sendPacket(now, SERVER, new FT20_FinPacket(nextPacketSeqN, now));
			break;
		}
		self.set_timeout(DEFAULT_TIMEOUT);
	}

	public void on_timeout(int now) {
		super.on_timeout(now);
		if(state == State.UPLOADING)
			mayProceed = true;
		/* se o estado for uploading e houver um timeout 
		e preciso fazer gbn e continuar a enviar pacotes de dados */
		//sendNextPacket(now);
	}

	@Override
	public void on_receive_ack(int now, int client, FT20_AckPacket ack) {
		lastAck = ack.cSeqN;
		switch (state) {
		case BEGINNING:
			state = State.UPLOADING;
			nextPacketSeqN = 1;
			sendNextPacket(now);
			break;
		case UPLOADING:
			//nextPacketSeqN = ack.cSeqN + 1;
			if (lastAck > lastPacketSeqN)
				state = State.FINISHING;
			nextPacketSeqN++;
			sendNextPacket(now);
			break;
		case FINISHING:
			super.log(now, "All Done. Transfer complete...");
			super.printReport( now );
			return;
		}
		//sendNextPacket(now);
	}
	
	private FT20_DataPacket readDataPacket(File file, int seqN, int timestamp) {
		try {
			if (raf == null)
				raf = new RandomAccessFile(file, "r");

			raf.seek(BlockSize * (seqN - 1));
			byte[] data = new byte[BlockSize];
			int nbytes = raf.read(data);
			return new FT20_DataPacket(seqN, timestamp, data, nbytes);
		} catch (Exception x) {
			throw new Error("Fatal Error: " + x.getMessage());
		}
	}
}
