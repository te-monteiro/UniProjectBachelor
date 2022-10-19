import java.util.ArrayList;
import java.util.List;

public class CarClass implements Car {
	
	String name;
	int capacity;
	int quantity;
	private List<Object> objects;
	
	

	public CarClass(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		quantity = 0;
		objects = new ArrayList<Object>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public boolean exceedsCapacity(int volume) {
		boolean result = false;
		if(capacity < quantity + volume) {
			result = true;
		}
		return result;
	}

	@Override
	public void addObject(Object o) {
		
		objects.add(o);
		
	}
	
	
}
