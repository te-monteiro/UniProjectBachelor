
public class MainTreasureMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Treasure map = new Treasure(8,-4);
		map.walkNorth(1);
		map.walkWest(3);
		System.out.println(map.getYPos());
		System.out.println(map.getXPos());
		map.dig();
		System.out.println(map.dig());
		map.walkSouth(6);
		map.walkEast(10);
		map.walkNorth(1);
		map.dig();
		System.out.println(map.dig());
		map.walkEast(1);
		map.dig();
		System.out.println(map.dig());

	}

}
