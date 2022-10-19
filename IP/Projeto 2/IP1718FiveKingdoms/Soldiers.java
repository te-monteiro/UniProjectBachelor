
public class Soldiers {

	private static final String KNIGHT = "cavaleiro";
	private static final String LANCER = "lanceiro";
	private static final String SWORDSMAN = "espadachim";

	private static final String SOUTH = "sul";
	private static final String NORTH = "norte";
	private static final String WEST = "oeste";
	private static final String EAST = "este";
	private int x, y;
	private String type;

	/**
	 * Construtor recebe as posicoes na qual a personagem se vai encontrar e o seu
	 * tipo
	 */
	public Soldiers(int x, int y, String type) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	/**
	 * Consoanto o argumento que recebe a personagem vai se deslocar num dos 4
	 * pontos cardeais
	 */
	public void movement(String move) {
		String direction = move.toLowerCase();
		switch (direction) {
		case EAST:
			x++;
			break;
		case WEST:
			x--;
			break;
		case NORTH:
			y++;
			break;
		case SOUTH:
			y--;
			break;
		}
	}

	/**
	 * Metodo que permite o soldado voltar para a posicao anterior caso haja algum
	 * obstaculo
	 */
	public void moveBack(String move) {
		String direction = move.toLowerCase();
		switch (direction) {
		case EAST:
			x--;
			break;
		case WEST:
			x++;
			break;
		case NORTH:
			y--;
			break;
		case SOUTH:
			y++;
			break;
		}
	}

	/** retorna a posicao x da personagem */
	public int getX() {
		return x;
	}

	/** retorna a posicao y da personagem */
	public int getY() {
		return y;
	}

	/** retorna o tipo da personagem */
	public String getType() { 
		return type;
	}

	/**
	 * Metodo que permite identificar se o soldado é do tipo cavaleiro ou nao, o que
	 * vai permitir verificar se o soldado move-se uma ou tres vezes
	 */
	public boolean isKnight() {
		boolean result = true;
		if (type.equals(LANCER) || type.equals(SWORDSMAN))
			result = false;
		return result;
	}

}
