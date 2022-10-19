
import java.util.Scanner;

public class MainSafeBankAccount2 {
	
	private static int printMessage (String message,Scanner input) {
		System.out.println(message);
		int ammount = input.nextInt();
		input.nextLine();
		return ammount;
	}
	private static SafeBankAccountAdv2 createAccount (String message,Scanner in) {
		SafeBankAccountAdv2 account = null;
		int value = printMessage (message, in);
		if (value > 0)
			account = new SafeBankAccountAdv2(value);
		return account;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		SafeBankAccountAdv2 conta1 = createAccount("Saldo conta1", input);
		if (conta1 != null) {
			SafeBankAccountAdv2 conta2 = createAccount("Saldo conta2", input);
		  if (conta2 != null) {
			int value = printMessage ( "Valor a transferir", input);
			if (value <= conta1.getBalance() && value > 0) {
				conta1.withdraw(value);
				conta2.deposit(value);
				System.out.println(conta2.getBalance()); }
			else 
				System.out.println("Nao e possivel transferir o valor referido");
			}else 
				System.out.println("Conta2 invalida");
	}else
		System.out.println("Conta1 invalida");
		input.close();
	} 
}

