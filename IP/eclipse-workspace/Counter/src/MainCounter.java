
public class MainCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Counter c1= new Counter();
		System.out.println(c1.status());
		c1.dec();
		System.out.println(c1.status());
		c1.inc();
		System.out.println(c1.status());
		c1.reset();
		System.out.println(c1.status());
		

	}

}
