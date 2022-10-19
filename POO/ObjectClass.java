
public class ObjectClass implements Object {

	String name;
	int money;
	int volume;

	public ObjectClass(String name, int money, int volume) {
		this.name = name;
		this.money = money;
		this.volume = volume;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getMoney() {
		return money;
	}

	@Override
	public int getVolume() {
		return volume;
	}

}
