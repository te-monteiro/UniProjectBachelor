import java.util.ArrayList;
import java.util.List;

public class SupermarketClass implements Supermarket throws CarDoesntExistsException, ObjectDoesntExistsException, ExceedCapacityException {
	
	private List<Car> cars;
	private List<Object> objects;
	
	public SupermarketClass() {
		
		cars = new ArrayList<Car>();
		objects = new ArrayList<Object>();
	}

	@Override
	public void addCar(String name, int capacity) throws AlreadyExistsCarException {
		if(this.alreadyExistsCar(name)) {
			throw new AlreadyExistsCarException();
		}
		else {
			Car c = new CarClass (name, capacity);
			cars.add(c);
		}
	}
	
	private boolean alreadyExistsCar(String name) {
		boolean result = false;
		for ( int i = 0; i < cars.size() ; i++) {
			if(cars.get(i).getName().equals(name)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void addObject(String name, int money, int volume)
	throws AlreadyExistsObjectException{
		if(this.alreadyExistsObject(name)) {
			throw new AlreadyExistsObjectException();
		}
		
		else {
			Object o = new ObjectClass (name, money, volume);
			objects.add(o);
		}
	
	}
	
	private boolean alreadyExistsObject(String name) {
		boolean result = false;
		for(int i =0; i< objects.size(); i++) {
			if(objects.get(i).getName().equals(name)) {
			result = true;
			}
		}
		
		return result;
	}
	
	
	
	

}
