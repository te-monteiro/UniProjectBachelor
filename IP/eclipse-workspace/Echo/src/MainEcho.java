import java.util.Scanner;
public class MainEcho {
	private static int message (String message, Scanner input) {
		System.out.println(message);
		int rep = input.nextInt();
		input.nextLine();
		return rep;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Echo e1 = new Echo();
		System.out.println(e1.echo("Ola", 3));

	}

}
