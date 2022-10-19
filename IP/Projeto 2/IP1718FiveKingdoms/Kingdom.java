
public class Kingdom {

	private String name;
	// private Castle[] castle;
	private Castles castles;
	// private Castle kingdomCastle;
	private Army soldiers;
	private int numCastles, totalWealth;

	public Kingdom(String name, int numTotalCastles, Castle kingdomCastle) {
		this.name = name;
		numCastles = 1;
		// castle = new Castle[numTotalCastles];
		castles = new Castles(numTotalCastles);
		castles.addCastle(kingdomCastle);
		castles.setOwner(kingdomCastle.getName(), name);
		// castle[numCastles++]=kingdomCastle;
		soldiers = new Army();

	}

	public void initializeIterator() {
		castles.initializeIterator();
	}

	public Castle next() {
		return castles.next();
	}

	public boolean hasNext() {
		return castles.hasNext();
	}

	public void initializeIteratorSoldiers() {
		soldiers.initializeIterator();
	}

	public Soldiers nextSoldier() {
		return soldiers.next();
	}

	public boolean hasNextSoldier() {
		return soldiers.hasNext();
	}

	// private void addCastle(Castle castle) {
	// castle[numCastles++]=castle;
	// }

	public String getKingdomName() {
		return name;
	}

	public boolean equals(Kingdom otherContact) {
		return name.equals(otherContact.getKingdomName());
	}

	public int getNumCastles() {
		return numCastles;
	}

	public void addNumCastles() {
		numCastles++;
	}

	public int getKingdomSoldiers() {
		return soldiers.getTotalSoldiers();
	}

	public int getTotalWealth() {
		return totalWealth;
	}

	public boolean greaterThan(Kingdom other) {
		return this.getKingdomName().compareTo(other.getKingdomName()) > 0;
	}

	// public int searchOwnCastles(String castleName) {
	// return castles.searchIndex(castleName);
	// }

	public int getSoldierCost(String type) {
		return soldiers.soldierCost(type);
	}

	// public int getWealth() {
	// return castles.getWealth();
	// }

	public void createSoldier(int x, int y, String type) {
		soldiers.recruitSoldier(new Soldiers(x, y, type));
	}

	public boolean invalidSoldier(String type) {
		return soldiers.invalidSoldier(type);
	}

	public boolean statusSoldier(int x, int y) {
		return soldiers.statusSoldier(x, y);
	}

	public int samePos(int x, int y) {
		return soldiers.samePosition(x, y);
	}

	public int searchSoldier(int x, int y) {
		return soldiers.searchSoldier(x, y);
	}

	public Soldiers getSoldier(int i) {
		return soldiers.getSoldier(i);
	}

	/** Metodo que da retorno da coordenada x, de um soldado em especifico */
	public int getSpecifiedSoldierX(int x, int y) {
		return soldiers.getSoldier(searchSoldier(x, y)).getX();
	}

	/** Metodo que da retorno da coordenada y, de um soldado em especifico */
	public int getSpecifiedSoldierY(int x, int y) {
		return soldiers.getSoldier(searchSoldier(x, y)).getY();
	}
}
