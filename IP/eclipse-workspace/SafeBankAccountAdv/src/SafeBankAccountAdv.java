
public class SafeBankAccountAdv {
	private int balance;
	public static final int FINE=200;
	public SafeBankAccountAdv() {balance=0; }
	public SafeBankAccountAdv(int initial) {balance=initial; }
	public void deposit(int ammount) {balance=balance+ammount; }
	public void withdraw(int ammount) {
		if (ammount<=balance)
		    balance=balance-ammount;
		else
			balance=balance-FINE;
	}
	public int getBalance() {return balance; }
	public boolean RedZone() {return (balance<0); }
	public static final int CASA=1000000;
	public static final int CASA2=200000;
	public static final float SUPERCASA1=0.03f;
	public static final float SUPERCASA2=0.02f;
	public static final float SUPERCASA3=0.01f;
	public int computeInterest() {
		float interestRate;
		if (balance>CASA)
			interestRate=SUPERCASA1;
		else if (balance>CASA2)
			interestRate=SUPERCASA2;
		else interestRate=SUPERCASA3;
		return Math.round(balance*interestRate);
	}
	public void applyInterest() {
		balance=balance+this.computeInterest();
	}

}
