import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/** A really simple HTTP Client
 * 
 * @author David Pereira 54920
 * @author Pedro Belinha 47155
 *
 */

public class GetFile {
	private static final int BUF_SIZE = 512;
	private static final int MAX_RETRY = 3;
	
	private static Stats stat;


	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Usage: java GetFile url_to_access");
			System.exit(0);
		}
		String url = args[0];
		URL u = new URL(url);
		// Assuming URL of the form http://server-name:port/path ....
		int port = u.getPort() == -1 ? 80 : u.getPort();
		String path = u.getPath() == "" ? "/" : u.getPath();
		stat = new Stats();

		downloadFile(u.getHost(), port, path);

	}
		
    // Implement here to download the file requested in the URL
    // and write the file in the client side.
    // In the end print the required statistics
	private static void downloadFile(String host, int port, String path) 
			throws Exception {
				boolean finish = false;
				int minimo = 0;
				int maximo = minimo + 5000;
				OutputStream outputFile = new FileOutputStream("Pedro e David");
				String filename = path.substring(path.lastIndexOf('/')+1);
				if (filename.equals("")) 
					filename = "index.html";
		
					/*new Thread( () -> {

					}).start();*/
				while(!finish) {
					Socket sock = new Socket(host, port);
		
					OutputStream out = sock.getOutputStream();
					InputStream in = sock.getInputStream();
					String request = String.format(
						"GET %s HTTP/1.0\r\n"+
						"Host: %s\r\n"+
						"INTERVALO: bytes = " + String.valueOf("%d") + "-" + String.valueOf("%d") + "\r\n" + 
						"User-Agent: X-RC2020 GetFile\r\n\r\n", path, host, minimo, maximo);
		
					out.write(request.getBytes());
		
					System.out.println("\nSent Request:\n-------------\n"+request);		
					System.out.println("Got Reply:");
					System.out.println("\nReply Header:\n--------------");
				
					String answerLine = Http.readLine(in);  // first line is always present
					System.out.println(answerLine);
					String[] reply = Http.parseHttpReply(answerLine);
					long[] range = null;

					Integer x = 0;
					Integer contentRange = 0;
		
					answerLine = Http.readLine(in);
					while (!answerLine.equals("")) {
						//System.out.println(answerLine);
						String[] head = Http.parseHttpHeader(answerLine);
						answerLine = Http.readLine(in);
						/*if(answerLine.contains("Content-Length")) {
							Integer contentLength = Integer.parseInt(answerLine.substring(16));
							stat.newRequest(contentLength);
							System.out.println(contentLength);
						}*/
						System.out.println(answerLine + " ESTA AQUI TERESA");
						if(answerLine.contains("Content-Range")) {
							String[] al = answerLine.split(" ");
							String[] value = al[2].split("/");
							String[] val = value[0].split("-");
							contentRange = Integer.parseInt(value[1]);
							x = Integer.parseInt(val[1]);
							stat.newRequest(x);
							System.out.println("LENGTH " + x);
							System.out.println("CONTENT RANGE " + contentRange);
							
						}
					}
		
					if (reply[1].equals("200") || reply[1].equals("206")) {
						System.out.println("\nReply Body:\n--------------");
						//long time0 = System.currentTimeMillis();
						int n;
						byte[] buffer = new byte[BUF_SIZE];
						while((n = in.read(buffer)) >= 0) {
							outputFile.write(buffer, 0, n);
							System.out.println("APRECIA " + x);
							System.out.println("APRECIA UTRA BEZ " + contentRange);
							if(x >= contentRange)
								finish = true;
						}	
					}
					else
						System.out.println("Ooops, received status:" + reply[1]);
					
					minimo = maximo + 1;
					maximo = minimo + 4999;	

					sock.close();


					/*
					File fcopy = new File("copy_of_"+filename);
			FileOutputStream of = new FileOutputStream(fcopy);
			of.write(bytesLidos);
					*/
	    			stat.printReport();
				}
	}

        // can add other private stuff as needed
}


