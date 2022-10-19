
public class MainRestaurant {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OurRestaurant myRest = new OurRestaurant (4.5f, 5.0f);
		System.out.printf("%.2f\n",myRest.cash());
		System.out.printf("%.2f\n",myRest.iva());
		System.out.printf("%.2f\n",myRest.profit());
		System.out.println(myRest.hasProfit());
		System.out.println(myRest.normalMenus());
		System.out.println(myRest.reducedMenus());
		System.out.printf("%.2f\n",myRest.request(2,1));
		System.out.printf("%.2f\n",myRest.cash());
		System.out.printf("%.2f\n",myRest.iva());
		System.out.printf("%.2f\n",myRest.profit());
		System.out.println(myRest.hasProfit());
		System.out.println(myRest.normalMenus());
		System.out.println(myRest.reducedMenus());
		System.out.printf("%.2f\n",myRest.request(4,4));
		System.out.printf("%.2f\n",myRest.cash());
		System.out.printf("%.2f\n",myRest.iva());
		System.out.printf("%.2f\n",myRest.profit());
		System.out.println(myRest.hasProfit());
		System.out.println(myRest.normalMenus());
		System.out.println(myRest.reducedMenus());
		

	}

}
