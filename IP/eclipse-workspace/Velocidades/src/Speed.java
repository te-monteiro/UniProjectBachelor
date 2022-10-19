
public class Speed {
	private double Ms, Kmh, Mph;
	public Speed() {
		Ms = 0;
		Kmh = 0;
		Mph = 0;
	}	
	public double getInMS() {return Ms; }
	public void setInMS(double speed) {
		Ms = speed + Ms;
		
	}
	public double getInKmH() {return Kmh; }
	public void setInKmH(double speed) {
		Kmh = speed + Kmh;
		Kmh = (0.277778 * (speed + Ms));
		}
	public double getInMpH() {return Mph; }
	public void setInMph(double speed) {
		Mph = speed + Kmh;
		Mph = 0.44704 * Ms;
		Mph = 1.609344 * Kmh;
	}

}
