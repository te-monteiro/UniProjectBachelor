public class TwitterHappy {
	public static final int HAPPYNESS = 24;
	public static final int FULL_HAPPYNESS = 8;
	public static final int MORNING = 1;
	public static final int AFTERNOON = 2;
	public static final int NIGHT = 3;
	private Counter morning, afternoon, night;
	public TwitterHappy() {
	    morning = new Counter();
		afternoon = new Counter();
		night = new Counter();
	}
	public void reset() {
		morning.reset();
		afternoon.reset();
	    night.reset(); 
		}
	//Pre : dayPeriod == MORNING || dayPeriod == AFTERNOON || dayPeriod == NIGHT
	public void sendMessage (int dayPeriod) {
		switch (dayPeriod) {
		case MORNING: morning.inc(); break;
		case AFTERNOON: afternoon.inc(); break;
		case NIGHT: night.inc(); 
		}
	}
	public int getMessagesMorning() {
		return morning.status();
	}
	public int getMessagesAfternoon() {
		return afternoon.status();
	}
	public int getMessagesNight() {
		return night.status();
	}
	//Pre : dayPeriod == MORNING || dayPeriod == AFTERNOON || dayPeriod == NIGHT
	public int getNumberOfMessages (int dayPeriod) {
		return (morning.status() + afternoon.status() + night.status());
		
	}
	public boolean isHappy() {
		return (morning.status() + afternoon.status() + night.status()) >= 24;
	}
	public boolean isFullyHappy() {
		return morning.status() >= 8 && afternoon.status() >= 8 && night.status() >= 8;
	}
	
}
