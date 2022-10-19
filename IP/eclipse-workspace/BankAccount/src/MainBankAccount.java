
public class MainBankAccount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount b1 = new BankAccount (2000);
		b1.getBalance();
		System.out.println(b1.getBalance());
		b1.deposit(2);
		b1.deposit(8);
		b1.redZone();
		System.out.println(b1.redZone());
		System.out.println(b1.getBalance());
		b1.withdraw(3000);
		System.out.println(b1.getBalance());
		b1.redZone();
		System.out.println(b1.redZone());
		
	}

}
