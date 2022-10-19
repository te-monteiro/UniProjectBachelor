import java.util.Scanner;

public class MainWeatherStation {
	private static void registerTemp (Scanner input, WeatherStation w) {
		double temp = input.nextDouble();
		input.nextLine();
		w.sampleTemperature(temp);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner (System.in);
		WeatherStation w = new WeatherStation();
		registerTemp(input, w);
		registerTemp(input, w);
		registerTemp(input, w);
		registerTemp(input, w);
		registerTemp(input, w);
		System.out.printf("%.2f\n", w.getAverage());
		System.out.printf("%.2f\n", w.getMaximum());
		System.out.printf("%.2f\n", w.getMinimum());
		
	}

}
