
public class OurRestaurant {
	public static final float MENU = 10f;
	public static final float IVA = 0.23f;
	public static final float DESCONTO = 0.20f;
	private float menuFish, menuMeat;
	private int numberofFish, numberofMeat, reducedMenus, normalMenus;
	private float cash, fishCost, meatCost, profit;
	public OurRestaurant (float menuFish, float menuMeat) {
		this.menuFish = menuFish;
		this.menuMeat = menuMeat;
		fishCost = menuFish;
		meatCost = menuMeat;
		menuFish = MENU;
		menuMeat = MENU;
	}
	public float request (int numberofFish, int numberofMeat) {
		this.numberofFish += numberofFish;
		this.numberofMeat += numberofMeat;
		float value = 0;
		if (numberofFish > 2 && numberofMeat > 2) {
			cash =  (cash + (numberofFish+numberofMeat)*MENU*(1-DESCONTO));
		    value = (numberofFish * MENU * (1-DESCONTO)) + (numberofMeat * MENU * (1-DESCONTO));
		    reducedMenus += numberofFish + numberofMeat;
	}
		else {
			cash = cash + (numberofFish+numberofMeat) * MENU;
		    value = (numberofFish * MENU) + (numberofMeat * MENU);
		    normalMenus += numberofFish + numberofMeat;
}
		return (value);
	}
	public int normalMenus() {
		return (normalMenus);
	}
    public int reducedMenus() {
    	return (reducedMenus);
    }
    public float cash() {
    	return cash;
}
    public float iva() {
		return (cash * IVA);
	}
    public float profit() {
    	return (cash - (numberofFish *this.menuFish + numberofMeat *this.menuMeat+ iva()));
    }
    public boolean hasProfit() { 
    	profit = (cash / IVA) - (meatCost * menuMeat) - (fishCost * menuFish);
    	return (profit > 0); 
    	}
}