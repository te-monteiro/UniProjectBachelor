
public class Castle {

	private static final String FIRST_OWNER = "";
	private static final String WITHOUT_OWNER = "sem dono";

	private int numSoldiers;
	private int x, y;
	private int wealth;
	private String name;
	private String owner;

	public Castle(int x, int y, int wealth, String name) {
		this.x = x; 
		this.y = y;
		this.wealth = wealth;
		this.name = name;
		owner = FIRST_OWNER;
		numSoldiers = 0;
	}

	public int getXCastle() {
		return x;
	}

	public int getYCastle() {
		return y;
	}

	public int getWealth() {
		return wealth;
	}
	
	public void gainWealth() {
		wealth++;
	}

	public String getName() {
		return name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public boolean equals(Castle otherContact) {
		return name.equals(otherContact.getName());
	}

	public void abadonedCastle() {
		owner = WITHOUT_OWNER;
	}

	public boolean hasOwner() {
		boolean result = true;
		if (getOwner() == "")
			result = false;
		return result;
	}

	public int getCastleSoldiers() {
		return numSoldiers;
	}
	
	public void changeWealth(int valor) {
		wealth = valor;
	}

}
