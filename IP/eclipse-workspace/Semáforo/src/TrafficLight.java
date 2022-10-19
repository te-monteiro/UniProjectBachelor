
public class TrafficLight {
	private int Light;
	public static final int RED = 1;
	public static final int GREEN = 2;
	public static final int YELLOW = 3;
	public TrafficLight() {Light = RED; }
	public boolean isRed() {return  (Light == RED); }
	public boolean isGreen() {return (Light == GREEN); }
	public boolean isYellow() {return (Light == YELLOW); }
	public boolean pass() {return (Light == GREEN || Light == YELLOW); }
	public boolean stop() {return (Light < GREEN); }
	public void changeColor() {
		if (Light == RED) {
				Light = GREEN;
	}
		else if (Light == GREEN) {
			    Light = YELLOW;
		}
		else {
			Light = RED;
		}
	}

}
