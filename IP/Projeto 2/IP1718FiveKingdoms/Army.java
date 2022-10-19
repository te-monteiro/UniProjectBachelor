
public class Army {

	private static final String KNIGHT = "cavaleiro";
	private static final String LANCER = "lanceiro";
	private static final String SWORDSMAN = "espadachim";

	private static final int DEFAULT_SIZE = 50;
	private static final int GROWTH_FACTOR = 2;

	private Soldiers[] soldiers;
	private int numTotalSoldiers, currentSoldiers;

	public Army() {
		numTotalSoldiers = 0;
		soldiers = new Soldiers[DEFAULT_SIZE];
		currentSoldiers = -1;
	}

	public Soldiers getSoldier(int i) {
		return soldiers[i];
	}

	public void recruitSoldier(Soldiers soldier) {
		if (isFull())
			resize();
		soldiers[numTotalSoldiers++] = soldier;
	}

	private boolean isFull() {
		return numTotalSoldiers == soldiers.length;
	}

	public void resize() {
		Soldiers[] tmp = new Soldiers[GROWTH_FACTOR * soldiers.length];
		for (int i = 0; i < numTotalSoldiers; i++)
			tmp[i] = soldiers[i];
		soldiers = tmp;
	}

	public int getTotalSoldiers() {
		return numTotalSoldiers;
	}

	public void initializeIterator() {
		currentSoldiers = 0;
	}

	public Soldiers next() {
		return soldiers[currentSoldiers++];
	}

	public boolean hasNext() {
		return ((currentSoldiers >= 0) && (currentSoldiers < numTotalSoldiers));
	}

	public int soldierCost(String type) {
		int valor = 0;
		type = type.toLowerCase();
		switch (type) {
		case KNIGHT:
			valor = 4;
			break;
		case LANCER:
			valor = 2;
			break;
		case SWORDSMAN:
			valor = 2;
			break;
		}
		return valor;
	}

	public boolean invalidSoldier(String type) {
		boolean result = true;
		if (type.equals(KNIGHT) || type.equals(LANCER) || type.equals(SWORDSMAN))
			result = false;
		return result;
	}

	/**
	 * Metodo que ira procurar se existe um soldado no vetor com as coordenadas que
	 * indicamos
	 */
	public boolean statusSoldier(int x, int y) {
		int i = 0;
		boolean found = false;
		while (i < numTotalSoldiers && !found) {
			if (soldiers[i].getX() == x && soldiers[i].getY() == y)
				found = true;
			else
				i++;
		}
		return found;
	}

	/**
	 * Metodo que permite encontrar a posicao de um soldado no vetor atraves das
	 * suas coordenadas
	 */
	public int searchSoldier(int x, int y) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < numTotalSoldiers && !found) {
			if (soldiers[i].getX() == x && soldiers[i].getY() == y)
				found = true;
			else
				i++;
			if (found)
				result = i;
		}
		return result;
	}

	/**
	 * Metodo que permite verificar se para alem do soldado que foi movimentado para
	 * tal posicao, existe outro do mesmo reino nessa posicao
	 */
	public int samePosition(int x, int y) {
		int result = 0;
		int i = 0;
		while (i < numTotalSoldiers) {
			if (soldiers[i].getX() == x && soldiers[i].getY() == y) {
				result++;
				i++;
			} else
				i++;
		}
		return result;
	}

}
