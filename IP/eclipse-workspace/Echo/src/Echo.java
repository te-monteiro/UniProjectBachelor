
public class Echo {
	private int numEchos;
	public Echo() { numEchos = 0; }
	public String echo (String data, int rep ) {
		int count = 0;
		String copies = "";
		while (count < rep) {
			copies += " " + data;
			count++;
		}
		numEchos = numEchos + rep;
		return copies;
	}
	public int numTotalEchos() {
		return numEchos;
	}
}
