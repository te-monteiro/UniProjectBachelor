
public class MainLamp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lamp lamp1 = new Lamp();
		System.out.println(lamp1.isOn());
		lamp1.on();
		System.out.println(lamp1.isOn());
		lamp1.on();
		System.out.println(lamp1.isOn());
		lamp1.off();
		System.out.println(lamp1.isOn());

	}

}
