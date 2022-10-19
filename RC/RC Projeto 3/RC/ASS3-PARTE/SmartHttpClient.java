import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Testes a verificar: - No caso a meio a coneccao(transmicao) ser partida ->
 * temos que fazer um novo pedido com o que ele falhou - Repeticao, conceito de
 * ranges pequenos
 */

public class SmartHttpClient {
	private static final int BUF_SIZE = 512;
	private static final int MAX_RETRY = 3;

	private static final int RANGE = 8000;/// 321 deve ser multiplo BUF_SIZE
	private static final String CONTENT_LENGTH = "Content-Length:";

	private static Stats stat;

	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java SmartHttpClient url_to_access");
			System.exit(0);
		}
		String url = args[0];
		URL u = new URL(url);
		// Assuming URL of the form http://server-name:port/path ....
		int port = u.getPort() == -1 ? 80 : u.getPort();
		String path = u.getPath() == "" ? "/" : u.getPath();
		// FileInputStream wkFile;
		// outFile = new RandomAccessFile(wkFile, "rw");

		downloadFile(u.getHost(), port, path);
	}

	private static void downloadFile(String host, int port, String path) throws UnknownHostException, IOException {

		stat = new Stats();

		int minRange = 0;
		int maxRange = RANGE;
		boolean fin = false;
		int fileSize = -1;
		int numBytes = 0;
		OutputStream wkFile = new FileOutputStream("tmp.out");

		String filename = path.substring(path.lastIndexOf('/') + 1);
		if (filename.equals("")) {
			filename = "index.html";
		}

		while (!fin) {
			Socket sock = new Socket(host, port);
			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();

			String request = String.format("GET " + path + " HTTP/1.0\r\n" + "Host: " + host + "\r\n" + "Range: bytes="
					+ String.valueOf(minRange) + "-" + String.valueOf(maxRange) + "\r\n"
					+ "User-Agent: X-RC2020 SmartHttpClient\r\n\r\n");

			out.write(request.getBytes());

			System.out.println("\nSent Request:\n-------------\n" + request);
			System.out.println("Got Reply:");
			System.out.println("\nReply Header:\n--------------");

			String answerLine = Http.readLine(in); // first line is always present
			System.out.println("first: " + answerLine);
			String[] reply = Http.parseHttpReply(answerLine);
			long[] range = null;

			answerLine = Http.readLine(in);
			while (!answerLine.equals("")) {
				String[] head = Http.parseHttpHeader(answerLine);

				answerLine = Http.readLine(in);
				/// System.out.println("get LINEEEE: " + answerLine.indexOf(":", )
				if (answerLine.contains(CONTENT_LENGTH)) {
					fileSize = Integer.parseInt(answerLine.substring(answerLine.lastIndexOf(":") + 2));
					// System.out.println("get LINEEEE: " + fileSize);
				}
			}

			if (reply[1].equals("206") || reply[1].equals("200")) {

				System.out.println("\nReply Body:\n--------------");
				long time0 = System.currentTimeMillis();
				int n;
				byte[] buffer = new byte[BUF_SIZE];

				while ((n = in.read(buffer)) >= 0) {

					wkFile.write(buffer, 0, n);
					// System.out.println("Range " + RANGE + " FileSize " + fileSize + " Max Range "
					// + maxRange + " Min "
					// + minRange);
					numBytes = numBytes + buffer.length;
					if (fileSize < RANGE) {
						fin = true;
					}
				}

			} else
				System.out.println("Ooops, received status:" + reply[1]);

			minRange = maxRange + 1; //// testar byte de apoio depois
			maxRange += RANGE;
			sock.close();
			stat.newRequest(numBytes);

		}

	}
}

// long time = System.currentTimeMillis()-time0;
// System.err.println("Time= "+time+"ms "+(f.length()-start)+"bytes Kbits/s= "+
// (8*(f.length()-start)/(double)time));
