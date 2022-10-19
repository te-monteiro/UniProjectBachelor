
public class MainSafeBankAccount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SafeBankAccount ba1 = new SafeBankAccount (1000);
		System.out.println(ba1.getBalance());
		ba1.withdraw(1500);
		System.out.println(ba1.getBalance());
		System.out.println(ba1.computeInterest());
		ba1.applyInterest();
		System.out.println(ba1.getBalance());

	}

}
