
public class Kingdoms {

	private Castles c;
	private Kingdom k;
	private Kingdom[] kingdoms;
	private int counter, currentKingdom, numKingdoms, currentOrd;
	private Kingdom[] iteraOrd;

	public Kingdoms(int numKingdoms) {
		this.numKingdoms = numKingdoms;
		kingdoms = new Kingdom[numKingdoms];
		counter = 0;
		currentKingdom = -1;
		currentOrd = -1;
	}

	/** Metodo que adiciona reinos */
	public void addKingdom(String name, int numTotalCastles, Castle castle) {
		kingdoms[counter++] = new Kingdom(name, numTotalCastles, castle);
	}

	public int numKingdoms() {
		return counter;
	}
 
	public Kingdom getKingdom(int i) {
		return kingdoms[i];
	}

	private int searchKingdomName(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < counter && !found) {
			if (kingdoms[i].getKingdomName().equals(name))
				found = true;
			else
				i++;
			if (found)
				result = i;
		}
		return result;
	}

	public boolean hasKingdom(String name) {
		return (searchKingdomName(name) >= 0);
	}

	public void initializeIterator() {
		currentKingdom = 0;
	}

	public Kingdom next() { 
		return kingdoms[currentKingdom++];
	}

	public boolean hasNext() {
		return ((currentKingdom >= 0) && (currentKingdom < counter));
	}

	public int searchOwner(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < counter && !found) {
			if (c.getCastle(i).getOwner() == k.getKingdomName()) {
				found = true;
				k.addNumCastles();
			} else
				i++;
			if (found)
				result = i;
		}
		return result;
	}

	public void bubbleSort(Kingdom[] kingdoms) {
		for (int i = 1; i < counter; i++)
			for (int j = counter - 1; j >= i; j--)
				if (kingdoms[j - 1].greaterThan(kingdoms[j])) {
					Kingdom temp = kingdoms[j - 1];
					kingdoms[j - 1] = kingdoms[j];
					kingdoms[j] = temp;
				}
	}

	public void initializeOrdIterator() {
		// copia de vector
		iteraOrd = new Kingdom[counter];
		for (int i = 0; i < counter; i++)
			iteraOrd[i] = kingdoms[i];
		// ordenar contactos por nome
		bubbleSort(iteraOrd);
		currentOrd = 0;
	}

	public boolean hasNextOrd() {
		return ((currentOrd >= 0) && (currentOrd < counter));
	}

	public Kingdom nextOrd() {
		return iteraOrd[currentOrd++];
	}

}
