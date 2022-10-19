import java.util.Scanner;

public class Main {
	Scanner input = new Scanner (System.in);
	
	private static final String VALUE_TEMPERATURE = "RT";
	private static final String NUMBER_TEMPERATURES = "NT";
	private static final String AVERAGE_TEMPERATURE = "AT";
	private static final String MAX_TEMPERATURE = "GT";
	private static final String MIN_TEMPERATURE = "LT";
	private static final String SAIR = "S";
	
	private static String readOption(Scanner in) {
		String opcao = in.next().toUpperCase();
		return opcao;
		}
	
	private static void valueTemperature(Scanner input, WeatherStationMenus a) {
		double temp = input.nextDouble();
		a.sampleTemperature(temp);
		System.out.println("Registo efectuado com sucesso.");
		}
	//System.out.println("Ha" + a.numberTemperatures() + "temperaturas registadas");
	private static void processnumberTemperatures (WeatherStationMenus a) {
		if ( a!= null && a.numberTemperatures() > 0) {
			System.out.printf("Ha %d temperaturas registadas.\n" , a.numberTemperatures());
		} else
			System.out.println("Sem temperaturas.");
	}
	// 0.2f pois nos queremos duas casas decimais. n corresponde ao new line. %f corresponde ao double.
	private static void averageTemperature (WeatherStationMenus a) {
		if ( a!= null && a.numberTemperatures() > 0) {
			System.out.printf("Media: %.2f.\n" , a.getAverage());
		} else
		System.out.println("Sem temperaturas.");
	}
	
	private static void maxTemperature (WeatherStationMenus a) {
		if ( a!= null && a.numberTemperatures() > 0) {
			System.out.printf("Maxima: %.2f.\n" , a.getMaximum());
		} else
		System.out.println("Sem temperaturas.");
	}
	
	private static void minTemperature (WeatherStationMenus a) {
		if ( a!= null && a.numberTemperatures() > 0) {
			System.out.printf("Minima: %.2f.\n" , a.getMinimum());
		} else
		System.out.println("Sem temperaturas.");
	}
	
	private static void menuMain(Scanner in, WeatherStationMenus a) {

		String opcao = "";
		
		
		while (!opcao.equals ("S")) {
			opcao = readOption(in);
			
			switch (opcao) {
			case VALUE_TEMPERATURE:
				valueTemperature(in, a);
				break;
			case NUMBER_TEMPERATURES:
				processnumberTemperatures(a);
				break;
			case AVERAGE_TEMPERATURE:
				averageTemperature(a);
				break;
			case MAX_TEMPERATURE:
				maxTemperature(a);
				break;
			case MIN_TEMPERATURE:
				minTemperature(a);
				break;
			case SAIR:
				break;
			default: 
				System.out.println("Comando invalido.");
		   }	
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner (System.in);
		WeatherStationMenus w = new WeatherStationMenus();
		
		menuMain(input, w);
		input.close();
	}
}
