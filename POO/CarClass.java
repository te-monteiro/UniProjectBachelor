
public class CarClass implements Car {
	
	String name;
	int capacity;

	public CarClass(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}
	
	
}
