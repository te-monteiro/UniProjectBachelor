import java.util.Scanner;


public class Main {

	

	public static void main(String[] args) {
		
		Scanner in = new Scanner (System.in);
		Supermarket sp = new SupermarketClass();
		String comm = getCommand(in);
		
		while (!comm.equals("SAIR")) {
			switch(comm) {
			case "CRIA CARRINHO":
				createCar(in , sp);
				break;
			case "CRIA ARTIGO":
				createObject(in , sp);
				break;
			case "DEPOSITA":
				deposit(in , sp);
				break;
			case "REMOVE":
				remove( in , sp );
				break;
			default:
				System.out.println("ERRO");
			}
			System.out.println();
			comm = getCommand(in);
		}
		
	System.out.println("Volte sempre.");
	System.out.println();
	in.close();

	}

	
	private static String getCommand(Scanner in) {
		String input;
		input = in.next().toUpperCase();
		if(input.equals("CRIA")) {
			input += " " + in.next().toUpperCase();
		}
		return input;
	}
	
	private static void createCar (Scanner in, Supermarket sp)
	throws AlreadyExistsCarException {
		
		
		
		int capacity;
		
		try {
		String name = in.next().trim();
		capacity = in.nextInt();
		sp.addCar(name, capacity);
		System.out.println("Carrinho criado com sucesso.");
		
		}
		catch( AlreadyExistsCarException except ) {
			System.out.println("Carrinho existente!");
		}
		
	}
	
	private static void createObject (Scanner in, Supermarket sp) 
	throws AlreadyExistsObjectException {
		
		try {
		String name = in.next();
		int money = in.nextInt();
		int volume = in.nextInt();
		sp.addObject(name, money, volume);
		System.out.println("Artigo criado com sucesso.");
		}
		catch(AlreadyExistsObjectException excep) {
			System.out.println("Artigo existente!");
		}
	}
		
	private static void deposit ( Scanner in, Supermarket sp )
	throws CarDoesntExistsException, ObjectDoesntExistsException,
	ExceedsCapacityException {
		
		try {
		String objectName = in.next();
		String carName = in.next();
		sp.addObjectToCar(objectName, carName);
		System.out.println("Artigo adicionado com sucesso.");
		
		}
		
		catch(CarDoesntExistsException excp) {
			System.out.println("Carrinho inexistente!");
			
		}
		
		catch (ObjectDoesntExistsException excep) {
			System.out.println("Artigo inexistente!");
		}
		
		catch(ExceedsCapacityException excep) {
			System.out.println("Capacidade excedida!");
		}
		
		
		
	}
		
	}
	
	
	

