import java.util.Scanner;
public class MainSafeBankAccountAdv3 {
	private static final int DEPOSITAR = 1;
	private static final int LEVANTAR = 2;
	private static final int CONSULTAR_SALDO = 3;
	private static final int CONSULTAR_JURO_ANUAL = 4;
	private static final int CREDITAR_JURO_ANUAL = 5;
	private static final int SAIR = 6;
	
	private static int readOption(Scanner in) {
		System.out.println("Escolha uma opcao");
		int opcao = in.nextInt();
		in.nextLine();
		
		return opcao;
	}
	
	private static void printMenu() {
		System.out.println(DEPOSITAR);
		System.out.println(LEVANTAR);
		System.out.println(CONSULTAR_SALDO);
		System.out.println(CONSULTAR_JURO_ANUAL);
		System.out.println(CREDITAR_JURO_ANUAL);
		System.out.println(SAIR);
	}
	private static void processDeposit(Scanner in, SafeBankAccountAdv3 b) {
		int ammount;
		System.out.println("Montante em centimos(valor nao negativo):");
		ammount = in.nextInt();
		in.nextLine();
		if (ammount >= 0){
			b.deposit(ammount);
			System.out.println("Deposito efectuado com sucesso");
			} else
			 System.out.println("Montante deve ser um valor nao negativo");
	}
	private static void processWithdraw(Scanner in, SafeBankAccountAdv3 b) {
		System.out.println("Montate em centimos(valor nao negativo)");
		int ammount;
		ammount = in.nextInt();
		in.nextLine();
		if (ammount >= 0) {
			b.withdraw(ammount);
			System.out.println("Levantamento efectuado com sucesso");
		} else
			System.out.println("Montante deve ser um valor nao negativo");
	}
	private static void processBalance(SafeBankAccountAdv3 b) {
		System.out.printf("Tem %d centimos na sua conta.\n" , b.getBalance());
	}
	private static void processInterest(SafeBankAccountAdv3 b) {
		b.computeInterest();
	}
	private static void processApply(SafeBankAccountAdv3 b) {
		b.applyInterest();
	}
	private static void menuMain(Scanner in, SafeBankAccountAdv3 b) {
		printMenu();
		
		int opcao = 0;
		
		
		while (opcao != SAIR) {
			opcao = readOption(in);
			
			switch (opcao) {
			case DEPOSITAR:
				processDeposit(in, b);
				break;
			case LEVANTAR:
				processWithdraw(in, b);
				break;
			case CONSULTAR_SALDO:
				processBalance(b);
				break;
			case CONSULTAR_JURO_ANUAL:
				processInterest(b);
				break;
			case CREDITAR_JURO_ANUAL:
				processApply(b);
				break;
			case SAIR:
				System.out.println("OPERACAO FINALIZADA");
				break;
			default: 
				System.out.println("Comando Invalido");
				break;
		   }	
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SafeBankAccountAdv3 conta;
		Scanner input = new Scanner (System.in);
		System.out.println("Valor Inicial");
		int valor = input.nextInt();
		    input.nextLine();
		if (valor >= 0) {
			conta = new SafeBankAccountAdv3(valor);
			System.out.println("Conta criada com sucesso");
			menuMain(input, conta);
			input.close();
		}
		else
			System.out.println("Saldo inicial invalido");
		    input.close();
	}

	}
