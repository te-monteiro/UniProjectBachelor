
public class Diet {
	private int eatTimes, burnTimes;
	private int eatCallories, burnCallories;
	public void eat(int c) { eatCallories+=c; eatTimes++; }
	public void burn(int c) { burnCallories+=c; burnTimes++; }
	public int eatTimes() {return eatTimes; }
	public int burnTimes() {return burnTimes; }
	public int balance() {return eatCallories - burnCallories; }
	public boolean isBalanceNegative() {return balance()<0; }
	public float averageEatenCallories() {return eatCallories/eatTimes; }
	public float averageBurntCallories() {return burnCallories/burnTimes; }
	

}
