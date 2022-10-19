import java.util.Scanner;
public class MainSafeBankAccountAdv3 {
	private static final int LEVANTAR = 1;
	private static final int DEPOSITAR = 2;
	private static final int CONSULTAR_SALDO = 3;
	private static final int CONSULTAR_JURO_ANUAL = 4;
	private static final int CREDITAR_JURO_ANUAL = 5;
	private static final int CALCULAR_POUPANÇA = 6;
	private static final int SAIR = 7;
	
	private static int readOption(Scanner in) {
		int op;
		System.out.println("1- Levantar");
		System.out.println("2- Depositar");
		System.out.println("3- Consultar Saldo");
		System.out.println("4- Consultar Juro Anual");
		System.out.println("5- Creditar Juro Anual");
		System.out.println("6- Calcular Poupança");
		System.out.println("7- Sair");
		op = in.nextInt();
		 in.nextLine();
		 return op;
	}
	private static void processDeposit(SafeBankAccountAdv3 b,Scanner in) {
		int ammount;
		System.out.println("Montante em centimos(valor nao negativo):");
		ammount = in.nextInt();
		in.nextLine();
		if (ammount >= 0){
			b.deposit(ammount);
			System.out.println("Depósitoh efectuado com sucesso");
			} else
			 System.out.println("Montante deve ser um valor não negativo");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SafeBankAccountAdv3 conta;
		Scanner input = new Scanner (System.in);
		System.out.println("Valor Inicial");
		int op;
		int valor = input.nextInt();
		    input.nextLine();
		if (valor >= 0) {
			conta = new SafeBankAccountAdv3(valor);
			System.out.println("Conta criada com sucesso");
			op = readOption(input);
			switch (op){
			    case LEVANTAR:  
			    	processDeposit(conta, input); 
			    	break;
			    case DEPOSITAR: 
			    	processDeposit(conta, input); 
			    	break;
			    case CONSULTAR_SALDO:
			    	processDeposit(conta, input); 
			    	break;
			    case CONSULTAR_JURO_ANUAL:
			    	processDeposit(conta, input); 
			    	break;
			    case CREDITAR_JURO_ANUAL: 
			    	processDeposit(conta, input); 
			    	break;
			    case CALCULAR_POUPANÇA: 
			    	processDeposit(conta, input); 
			    	break;
			    case SAIR: 
			    	processDeposit(conta, input); 
			    	break;
			    default: System.out.print("Opçãoh Inválida");
			System.out.println("Valor a levantar");
			valor = input.nextInt();
			input.nextLine();
			if ( valor <= conta.getBalance())
				System.out.println("Levantamento efetuado");
			else
				System.out.println("Não é possivel fazer o levantamento");
			    conta.withdraw(valor);
		}
		else
			System.out.println("Saldo inicial invalido");
		    input.close();
	}

	}

}
