
public class MainSemaforo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrafficLight t1 = new TrafficLight();
		System.out.println(t1.isGreen());
		System.out.println(t1.isRed());
		System.out.println(t1.pass());
		System.out.println(t1.stop());
		t1.changeColor();
		System.out.println(t1.isYellow());
		System.out.println(t1.pass());
		t1.changeColor();
		System.out.println(t1.isGreen());
		System.out.println(t1.isYellow());
		System.out.println(t1.pass());
		t1.changeColor();
		System.out.println(t1.pass());
	}

}
