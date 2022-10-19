import java.util.ArrayList;
import java.util.List;

public class SupermarketClass implements Supermarket {
	
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

	@Override
	public void addObjectToCar(String objectName, String carName) 
	throws CarDoesntExistsException, ObjectDoesntExistsException, ExceedsCapacityException {
		
		if(!this.alreadyExistsCar(carName)) {
			throw new CarDoesntExistsException();
		}
		
		else if(!this.alreadyExistsObject(objectName)) {
			throw new ObjectDoesntExistsException();
		}
		
		else if(this.alreadyExceededCapacity(carName, objectName)) {
			throw new ExceedsCapacityException();
		}
		
		else {
			
			Car c = this.getCar(carName);
			Object o = this.getObject(objectName);
			c.addObject(o);
		}
		
	}
	
	private boolean alreadyExceededCapacity(String carName, String objectName) {
		boolean result = false;
		int volume = this.getObject(objectName).getVolume();
		Car c = this.getCar(carName);
		if ( c.exceedsCapacity(volume) ) {
			result = true;
		}
		return result;
		
	}
	
	private Car getCar(String name) {
		Car c = null;
		for(int i = 0; i < cars.size() ; i++) {
			if (cars.get(i).getName().equals(name)) {
				c = cars.get(i);
			}
		}
		return c;
	}
	
	private Object getObject(String name) {
		Object c = null;
		for(int i = 0; i < objects.size() ; i++) {
			if (objects.get(i).getName().equals(name)) {
				c = objects.get(i);
			}
		}
		return c;
	}


	

	
	
	

}
