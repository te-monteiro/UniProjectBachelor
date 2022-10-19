
public class Game {

	private Kingdoms k;
	private Castles c;
	private int width, height;
	private int numTotalKingdoms, numTotalCastles;
	private int numKingdoms, numCastles;
	private boolean isRunning, result;
	private int teamPlaying;

	public Game() {
		k = new Kingdoms(numTotalKingdoms);
		c = new Castles(numTotalCastles);
		isRunning = false;
		teamPlaying = 0;
		result = false;
	}

	/** Dimensoes do mapa */
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/** Metodo que permite criacao do jogo */
	public void createGame(int x, int y, int numTotalKingdoms, int numTotalCastles) {
		width = x;
		height = y;
		this.numTotalKingdoms = numTotalKingdoms;
		this.numTotalCastles = numTotalCastles;
		k = new Kingdoms(numTotalKingdoms);
		c = new Castles(numTotalCastles);

	}

	/** Metodo que permite criacao de um reino */
	public void createKingdom(String name, Castle castle) {
		k.addKingdom(name, numTotalCastles, castle);

	}

	/** Metodo que permite a criacao de um castelo */
	public void createCastle(int x, int y, int wealth, String name) {
		c.addCastle(new Castle(x, y, wealth, name));
	}

	/** Metodo que verifica se o jogo esta a decorrer ou nao */
	public boolean gameStatus() {
		return isRunning;
	}

	public void gameOn() {
		isRunning = true;
	}

	public void gameOff() {
		isRunning = false;
		teamPlaying = 0;
	}

	/** Metodo que permite a mudanca de turno para o reino seguinte */
	public void turnChange() {
		if (teamPlaying + 1 == numTotalKingdoms)
			teamPlaying = 0;
		else {
			teamPlaying++;
		}
	}

	/**
	 * Metodo que permite identificar o reino a jogar
	 * 
	 * @return do reino que esta a jogar
	 */
	public Kingdom getTeamPlaying() {
		return k.getKingdom(teamPlaying);
	}

	/**
	 * Metodo que permite identificar o nome do reino que esta a jogar
	 * 
	 * @return do nome do reino que se econtra a jogar
	 */
	public String getKingdomName() {
		return k.getKingdom(teamPlaying).getKingdomName();
	}

	public String getCastleOwner(int i) {
		return c.getCastle(i).getOwner();
	}

	public boolean nameExistsKingdoms(String name) {
		return k.hasKingdom(name);
	}

	public boolean nameExistsCastles(String name) {
		return c.hasCastle(name);
	}

	public int getNumKingdoms() {
		return k.numKingdoms();
	}

	public int getKingdomCastles() {
		return c.getNumCastles();
	}

	public Castle getCastle(String castleName) {
		return c.getCastle(c.searchIndex(castleName));
	}

	public void setOwner(String castleName, String owner) {
		c.setOwner(castleName, owner);
	}

	/** Metodos que retornam a variavel owner */
	/** Metodo que da retorno da variavel owner atraves da sua procura */
	public String getOwner(String castleName) {
		return c.getSpecifiedOwner(castleName);
	}

	public void initialIteratorCastle() {
		c.initializeIterator();
	}

	public Castle nextCastle() {
		return c.next();
	}

	public boolean hasNextCastle() {
		return c.hasNext();
	}

	public void initialIteratorKingdom() {
		k.initializeIterator();
	}

	public Kingdom nextKingdom() {
		return k.next();
	}

	public boolean hasNextKingdom() {
		return k.hasNext();
	}

	/** Metodo que da retorno do numero de castelos de um reino em especifico */
	public int getSpecifiedKingdomNumCastles() {
		return k.getKingdom(teamPlaying).getNumCastles();
	}

	public int getNumSoldiers() {
		return c.getNumSoldiers();
	}

	public int getSpecifiedNumSoldiers() {
		return c.getCastle(getKingdomCastles()).getCastleSoldiers();
	}

	public void initializeOrdIterator() {
		k.initializeOrdIterator();
	}

	public boolean hasNextOrd() {
		return k.hasNextOrd();
	}

	public Kingdom nextOrd() {
		return k.nextOrd();
	}

	public int getSpecifiedWealth(String castleName) {
		return c.getCastle(c.searchIndex(castleName)).getWealth();
	}

	public int getSpecifiedCastleX(String castleName) {
		return c.getCastle(c.searchIndex(castleName)).getXCastle();
	}

	public int getSpecifiedCastleY(String castleName) {
		return c.getCastle(c.searchIndex(castleName)).getYCastle();
	}

	/**
	 * Metodo que permite verificar se o soldado se dirige para fora do mapa ou nao
	 */
	public boolean outsidemap(int x, int y) {
		boolean result = true;
		if (k.getKingdom(teamPlaying).getSpecifiedSoldierX(x, y) >= 1
				&& k.getKingdom(teamPlaying).getSpecifiedSoldierX(x, y) <= width
				&& k.getKingdom(teamPlaying).getSpecifiedSoldierY(x, y) >= 1
				&& k.getKingdom(teamPlaying).getSpecifiedSoldierY(x, y) <= height)
			result = false;
		return result;
	}

}
