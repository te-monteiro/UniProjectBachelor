
import java.util.Scanner;
public class MainSafeBankAccountAdv {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1 - Criar a conta com saldo inicial dado pelo utilizador
		//1.1 - Pedir o valor para abrir a conta
		SafeBankAccountAdv conta;
		Scanner input = new Scanner (System.in);
		System.out.println("Valor Inicial");
		int valor = input.nextInt();
		    input.nextLine();
		 //1.2 - Criar a conta, se o valor for positivo
		if (valor > 0) {
			conta = new SafeBankAccountAdv(valor);
			System.out.println("Conta criada com sucesso");
			System.out.println("Valor a levantar");
			valor = input.nextInt();
			input.nextLine();
			if ( valor <= conta.getBalance())
				System.out.println("Levantamento efetuado");
			else
				System.out.println("Nao e possivel fazer o levantamento");
			    conta.withdraw(valor);
		}
		else
			System.out.println("Saldo inicial invalido");
		    input.close();
	}

}
