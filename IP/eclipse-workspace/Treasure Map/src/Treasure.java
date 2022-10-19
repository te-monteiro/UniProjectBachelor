
public class Treasure {
	private int tx, ty;
	private int px, py;
	public Treasure(int tx, int ty) { 
		px=0; 
		py=0; 
		this.tx = tx;
		this.ty=  ty;
	}
	public void walkNorth (int steps) {
		py = py+steps;
	}
	public void walkSouth (int steps) {
		py = py-steps;
	}
	public void walkEast (int steps) {
		px = px+steps;
	}
	public void walkWest (int steps) {
		px= px-steps;
	}
	public boolean dig() {return (tx==px) & (ty==py); }
	public int getXPos() {return (px);}
	public int getYPos() {return (py);}
}
