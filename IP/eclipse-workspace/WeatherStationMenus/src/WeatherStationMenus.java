
public class WeatherStationMenus {
	private double MaxTemp, MinTemp, TotalTemps;
	private int NumberOfTemp;
	public WeatherStationMenus() {
		MaxTemp = 0;
		MinTemp= 0;
		NumberOfTemp = 0;
		TotalTemps = 0;
	}
	public void sampleTemperature(double temp) { 
		if (NumberOfTemp == 0) {
			MaxTemp = MinTemp = temp;
		}
		else {
			MaxTemp = Math.max(MaxTemp , temp);
			MinTemp = Math.min(MinTemp , temp);
			}
		NumberOfTemp++;
		TotalTemps += temp;
		
	}
	//pre: numberTemperatures() > 0 
	public double getMaximum() {
		return (MaxTemp);
	}
	//pre: numberTemperatures() > 0 
	public double getMinimum() {
		return (MinTemp);
	}
	//pre: numberTemperatures() > 0 
	public double getAverage() {
		return (TotalTemps/NumberOfTemp);
	}
	public int numberTemperatures() { 
		return (NumberOfTemp);
	}

}
