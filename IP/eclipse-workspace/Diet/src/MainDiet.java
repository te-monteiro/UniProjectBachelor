
public class MainDiet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Diet d = new Diet();
		System.out.println(d.balance());
		System.out.println(d.eatTimes());
		d.eat(50);
		d.eat(75);
		System.out.println(d.eatTimes());
		System.out.println(d.burnTimes());
		d.burn(20);
		System.out.println(d.balance());
		d.eat(75);
		d.eat(100);
		d.burn(40);
		d.burn(30);
		System.out.println(d.balance());
		System.out.println(d.eatTimes());
		System.out.println(d.burnTimes());
		System.out.println(d.averageEatenCallories());
		System.out.println(d.averageBurntCallories());
		System.out.println(d.isBalanceNegative());
		

	}

}
