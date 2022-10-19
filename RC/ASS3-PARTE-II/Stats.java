
public class Stats {

	private long totalBytes = 0L;
	private long startTime = 0L;
	private int totalRequests = 0;

	
	/**
	 * Create a Stats object to do some statistics and print it
	 */
	public Stats() {
		startTime = System.currentTimeMillis();		
	}

	/**
	 * count a new request sent and file's bytes received
	 * @param: bytes - number of bytes received
	 */
	public void newRequest( int bytes ) {
		totalRequests++;
		totalBytes += bytes;
	}

	
	/**
	 * Print statistics report
	 */
	public void printReport() {
		// compute time spent receiving bytes
		long milliSeconds = System.currentTimeMillis() - startTime;
		double speed = totalBytes / (double)milliSeconds; // K bytes/s
		
		System.out.println("\nTransfer stats: -----------------------------------------");
		System.out.println("Total time elapsed (s):\t\t" + (milliSeconds/1000.0));
		System.out.println("Download size (bytes):\t\t" + totalBytes);
		System.out.printf("End-to-end debit (Kbytes/s):\t%.3f\n", speed);
		System.out.println("Number of requests:\t\t" + totalRequests);
		System.out.println("==========================================================\n");

	}
}
