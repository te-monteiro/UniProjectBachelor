import java.util.Scanner;

public class MainSafeBankAccountPoupar {

	private static final String DEPOSITAR = "D";
	private static final String LEVANTAR = "L";
	private static final String BALANCE = "B";
	private static final String CALCULAR_JURO_ANUAL = "CJR";
	private static final String CREDITAR_JURO = "CJ";
	private static final String CALCULAR_OBJETIVO = "CA";
	private static final String SAIR = "S";
	
	private static String readOption(Scanner in) {
		System.out.println("Escolha uma opcao");
		String opcao = in.next();
		opcao = opcao.toUpperCase();
		in.nextLine();
		
		return opcao;
		}
	
	private static void printMenu() {
		System.out.println(DEPOSITAR);
		System.out.println(LEVANTAR);
		System.out.println(BALANCE);
		System.out.println(CALCULAR_JURO_ANUAL);
		System.out.println(CREDITAR_JURO);
		System.out.println(CALCULAR_OBJETIVO);
		System.out.println(SAIR);
	}
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		SafeBankAccountPoupar b1 = createAccount("valor", input);
		
		menuMain(input, b1);
		input.close();
		}
	
	private static void menuMain(Scanner in, SafeBankAccountPoupar a) {
		printMenu();
		
		String opcao = "";
		
		
		while (!opcao.equals ("SAIR")) {
			opcao = readOption(in);
			
			switch (opcao) {
			case DEPOSITAR:
				processDeposit(in, a);
				break;
			case LEVANTAR:
				processWithdraw(in, a);
				break;
			case BALANCE:
				processBalance(a);
				break;
			case CALCULAR_JURO_ANUAL:
				processInterest(a);
				break;
			case CREDITAR_JURO:
				processApply(a);
				break;
			case CALCULAR_OBJETIVO:
				processGoal(a, in);
				break;
			case SAIR:
				System.out.println("OPERACAO FINALIZADA");
		   }	
		}
	}
	
	
	private static void processDeposit(Scanner in, SafeBankAccountPoupar a) {
		System.out.println("Montante a depositar");
		int amount = in.nextInt();
		in.nextLine();
		if(amount>0) {
			System.out.println("Valor depositado");
			a.deposit(amount);
		}
		else {
			System.out.println("Valor nao depositado");
		}
	}
	
	private static void processWithdraw(Scanner in, SafeBankAccountPoupar a) {
		System.out.println("Montante a levantar");
		int amount = in.nextInt();
		in.nextLine();
		if(amount>0)
			if(amount <= a.getBalance()) {
				System.out.println("Valor levantado");
				a.withdraw(amount);
			}
			else {
				System.out.println("Aplicada multa");
				a.withdraw(amount);
			}
		else {
			System.out.println("Valor invalido");
		}
	}
	
	private static void processBalance(SafeBankAccountPoupar a) {
		System.out.println("Saldo conta:");
		System.out.println(a.getBalance());
	}
	
	private static void processInterest(SafeBankAccountPoupar a) {
		System.out.println("O juro tem o valor");
		System.out.println(a.computeInterest());
	}
	
	private static void processApply(SafeBankAccountPoupar a) {
		System.out.println("Os juros foram aplicados");
		a.applyInterest();
	}
	
	private static void processGoal(SafeBankAccountPoupar a, Scanner in) {
		System.out.println("Qual e o seu objetivo?");
		int goal = in.nextInt();
		in.nextLine();
		System.out.println(a.howManySavingYears(goal));
	}
	
	private static int printMessage(String message, Scanner input) {
		System.out.println(message);
		int amout = input.nextInt();
		input.nextLine();
		return amout;
	}
	
	private static SafeBankAccountPoupar createAccount(String message, Scanner input) {
		SafeBankAccountPoupar conta = null;
		int value = printMessage(message, input);
		if(value>0) {
			conta = new SafeBankAccountPoupar(value);
		}
		return conta;
	}

}