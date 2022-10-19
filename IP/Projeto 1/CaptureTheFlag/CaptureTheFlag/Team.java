public class Team {

	private static final String CAVALEIRO = "CAVALEIRO";
	private static final String LANCEIRO = "LANCEIRO";
	private static final String ESPADACHIM = "ESPADACHIM";
	private int xFlag, yFlag;
	Character cavaleiro;
	Character lanceiro;
	Character espadachim;
	private String teamName;

	public Team(String teamName, int xFlag, int yFlag) {
		this.xFlag = xFlag;
		this.yFlag = yFlag;
		this.teamName = teamName;
		cavaleiro = new Character(xFlag, yFlag + 1,"cavaleiro");
		lanceiro = new Character(xFlag, yFlag -1,"lanceiro");
		espadachim = new Character(xFlag +1 , yFlag,"espadachim");
	}

	public String getTeamName() {
		return teamName;
	}

	public int getxFlag() {
		return xFlag;
	}

	public int getyFlag() {
		return yFlag;
	}

	public Character getCharacter(String tipo){
		String type = tipo.toUpperCase();
		switch(type){
		case CAVALEIRO:
			return cavaleiro;
		case LANCEIRO:
			return lanceiro;
		case ESPADACHIM:
			return espadachim;
		default: 
			return cavaleiro;
		}
	}
}
