
public class Character {
	private static final String SUL = "SUL";
	private static final String NORTE = "NORTE";
	private static final String OESTE = "OESTE";
	private static final String ESTE = "ESTE";
	private boolean life;
	private int x, y;
	private String tipo;

	// Construtor
	public Character(int x, int y,String tipo) {
		this.tipo = tipo;
		this.x = x;
		this.y = y;
		life = true;
	}

	public void movement(String direction) {
		String direcao = direction.toUpperCase();
		switch (direcao) {
		case ESTE:
			x++;
			break;
		case OESTE:
			x--;
			break;
		case NORTE:
			y++;
			break;
		case SUL:
			y--;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getLife() {
		return life;
	}

	public void matar() {
		life = false;
	}

	public String getType() {
		return tipo;
	}
	
}
