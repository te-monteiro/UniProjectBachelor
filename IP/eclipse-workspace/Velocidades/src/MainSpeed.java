
public class MainSpeed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Speed caracol = new Speed();
		caracol.setInMS(0.001);
		System.out.println(caracol.getInMS());
		System.out.println(caracol.getInKmH());
		Speed obikwelu = new Speed();
		obikwelu.setInKmH(36.0);
		System.out.println(obikwelu.getInMS());

	}

}
