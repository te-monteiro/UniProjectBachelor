import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		HealthyLife h = new HealthyLifeClass();

		String comm = getCommand(in);

		while (!comm.equals("XS")) {
			switch (comm) {
			case "ADDUSER":
				addUser(in, h);
				break;
			case "REMOVEUSER":
				removeUser(in, h);
				break;
			case "GETUSERINFO":
				getUserInfo(in, h);
				break;
			case "ADDPARK":
				addPark(in, h);
				break;
			case "ADDBIKE":
				addBike(in, h);
				break;
			case "CHARGEUSER":
				chargeUser(in, h);
				break;
			case "PICKUP":
				pickUp(in, h);
				break;
			case "PICKDOWN":
				pickDown(in, h);
				break;
			case "REMOVEBIKE":
				removeBike(in, h);
				break;
			case "GETPARKINFO":
				getParkInfo(in, h);
				break;
			case "BIKEPICKUPS":
				bikePickUps(in, h);
				break;
			case "USERPICKUPS":
				userPickUps(in, h);
				break;
			case "PARKEDBIKE":
				parkedBike(in, h);
				break;
			case "LISTDELAYED":
				listDelayed(h);
				break;
			case "FAVORITEPARKS":
				favoriteParks(h);
				break;
			default:
				System.out.println();
			}
			System.out.println();
			comm = getCommand(in);
		}
		System.out.println();
		in.close();

	}

	/**
	 * Method that lists all the favorite parks
	 * 
	 * @param h
	 */
	private static void favoriteParks(HealthyLife h) {

	}

	/**
	 * Method that lists all the delays
	 * 
	 * @param h
	 */
	private static void listDelayed(HealthyLife h) {

	}

	/**
	 * Method that lists the park the bikes in a the determinate park
	 * 
	 * @param in
	 * @param h
	 */
	private static void parkedBike(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that lists all the pickups of an user
	 * 
	 * @param in
	 * @param h
	 */

	private static void userPickUps(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that lists all the pick down of a bike
	 * 
	 * @param in
	 * @param h
	 */

	private static void bikePickUps(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that gets the information of the park
	 * 
	 * @param in
	 * @param h
	 */
	private static void getParkInfo(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that removes a bike
	 * 
	 * @param in
	 * @param h
	 */
	private static void removeBike(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that does a pick down
	 * 
	 * @param in
	 * @param h
	 */
	private static void pickDown(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that does a pickup
	 * 
	 * @param in
	 * @param h
	 */
	private static void pickUp(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that charges the user
	 * 
	 * @param in
	 * @param h
	 */
	private static void chargeUser(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that adds a bike
	 * 
	 * @param in
	 * @param h
	 */
	private static void addBike(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that adds a park
	 * 
	 * @param in
	 * @param h
	 */

	private static void addPark(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that lists all the information of the users
	 * 
	 * @param in
	 * @param h
	 */
	private static void getUserInfo(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that removes a user
	 * 
	 * @param in
	 * @param h
	 */
	private static void removeUser(Scanner in, HealthyLife h) {

	}

	/**
	 * Method that adds a user
	 * 
	 * @param in
	 * @param h
	 */
	private static void addUser(Scanner in, HealthyLife h) {

	}

	private static String getCommand(Scanner in) {
		String input = in.nextLine().toLowerCase();
		return input;
	}

}
