
public class Castles {

	private Castle[] castles;
	private Castle castle;
	private int numTotalCastles;
	private int counter, currentCastles;

	public Castles(int numTotalCastles) {
		this.numTotalCastles = numTotalCastles; 
		castles = new Castle[numTotalCastles];
		counter = 0;
		currentCastles = -1;
	}

	public void addCastle(Castle castle) {
		castles[counter++] = castle;
	}

	public Castle getCastle(int i) {
		return castles[i];
	}

	public void setOwner(String castleName, String owner) {
		castles[searchIndex(castleName)].setOwner(owner);
	}

	public String getSpecifiedOwner(String castleName) {
		return castles[searchIndex(castleName)].getOwner();
	}

	public int getNumCastles() {
		return counter;
	}

	public int searchIndex(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < counter && !found) {

			if (castles[i].getName().equals(name))
				found = true;
			else
				i++;
			if (found)
				result = i;
		}
		return result;
	}

	public void initializeIterator() {
		currentCastles = 0;
	}

	public Castle next() {
		return castles[currentCastles++];
	}

	public boolean hasNext() {
		return ((currentCastles >= 0) && (currentCastles < counter));
	}

	public boolean hasCastle(String name) {
		return (searchIndex(name) >= 0);
	}

	public int getNumSoldiers() {
		return castle.getCastleSoldiers();
	}
//	
//	public void gainWealth() {
//		castle.gainWealth();
//	}

//	public int getWealth() {
//		return castle.getWealth();
//	}

//	public int getX() {
//		return castle.getXCastle();
//	}
//
//	public int getY() {
//		return castle.getYCastle();
//	}

}