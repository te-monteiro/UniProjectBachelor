import java.util.Scanner;

public class MainDietAdv {
	Scanner input = new Scanner (System.in);
	
	private static final String REFEICAO = "D";
	private static final String EXERCICIO = "E";
	private static final String CALORIAS_RETIDAS = "CR";
	private static final String CALORIAS_INGERIDAS = "CI";
	private static final String CALORIAS_ELIMINADAS = "CE";
	private static final String MAXIMO_CALORIAS_INGERIDAS = "MCI";
	private static final String MAXIMO_CALORIAS_ELIMINADAS = "MCE";
	private static final String SAIR = "S";
	
	private static String readOption(Scanner in) {
		System.out.println("Escolha uma opcao");
		String opcao = in.next().toUpperCase();
		return opcao;
	}
	private static void printMenu() {
		System.out.println(REFEICAO);
		System.out.println(EXERCICIO);
		System.out.println(CALORIAS_RETIDAS);
		System.out.println(CALORIAS_INGERIDAS);
		System.out.println(CALORIAS_ELIMINADAS);
		System.out.println(MAXIMO_CALORIAS_INGERIDAS);
		System.out.println(MAXIMO_CALORIAS_ELIMINADAS);
		System.out.println(SAIR);
	}
	private static void processEat(Scanner in, DietAdv d) {
		int c = in.nextInt();
		d.eat(c);
		System.out.println("Calorias ingeridas com sucesso");
	}
	private static void processBurn(Scanner in, DietAdv d) {
		int c = in.nextInt();
		d.burn(c);
		System.out.println("Calorias eliminadas com sucesso");
	}
	private static void processCR(DietAdv d) {
		System.out.println(d.balance());
	}
	private static void processCI(DietAdv d) {
		
	}
	private static void processCE(DietAdv d) {
		System.out.println(d.burnTimes());
	}
	private static void processMCI(DietAdv d) {
		
	}
	private static void processMCE(DietAdv d) {
		System.out.println(d.averageBurntCallories());
	}
	private static void menuMain(Scanner in, DietAdv d) {
		printMenu();
		String opcao = "";
		
		while (!opcao.equals ("Sair")) {
			opcao = readOption(in);
			
			switch (opcao) {
			case REFEICAO:
				processEat(in, d);
				break;
			case EXERCICIO:
				processBurn(in, d);
				break;
			case CALORIAS_RETIDAS:
				processCR(d);
				break;
			case CALORIAS_INGERIDAS:
				processCI(d);
				break;
			case CALORIAS_ELIMINADAS:
				processCE(d);
				break;
			case MAXIMO_CALORIAS_INGERIDAS:
				processMCI(d);
				break;
			case MAXIMO_CALORIAS_ELIMINADAS:
				processMCE(d);
				break;
			case SAIR:
				System.out.println("Obrigado por utilizar os nossos sevicos");
				break;
			default: 
				System.out.println("Comando Invalido");
				break;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner (System.in);
		DietAdv d = new DietAdv();
		
		menuMain(input, d);
		input.close();
	}

}
