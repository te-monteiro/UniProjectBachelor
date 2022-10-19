
public class BankAccount {
	private int balance;
    public BankAccount() {balance=0;}
    public BankAccount(int initial) {balance=initial;}
    public void deposit (int ammount) { balance= balance+ammount;}
    public void withdraw (int ammount) {balance= balance-ammount;}
    public int getBalance() {return balance;}
    public boolean redZone() {return (balance<0);}

}
