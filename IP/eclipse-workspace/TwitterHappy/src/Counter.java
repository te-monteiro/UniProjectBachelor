
public class Counter {
	private int x;
	public Counter() {x=0; }
	public void inc() {x=x+1; }
	public void dec() {x=x-1; }
	public void reset() {x=0; }
	public int status() {return x; }

}
