import java.util.Scanner;

public class Main {

	/** Constantes que representam os comandos */
	private static final String NEW_GAME = "novo";
	private static final String SOLDIER = "soldado";
	private static final String RECRUIT = "recruta";
	private static final String MAP = "mapa";
	private static final String CASTLES = "castelos";
	private static final String ARMY = "exercito";
	private static final String KINGDOMS = "reinos";
	private static final String HELP = "ajuda";
	private static final String QUIT = "sai";

	/** Constantes que representam as mensagens do comando Ajuda */
	private static final String NEW_GAME_HELP = "novo - Novo jogo";
	private static final String SOLDIER_HELP = "soldado - Move o soldado";
	private static final String RECRUIT_HELP = "recruta - Recruta um soldado num castelo";
	private static final String MAP_HELP = "mapa - Lista todos os castelos do mapa, incluindo os abandonados, pela ordem de criacao no jogo e todos os reinos ainda em jogo, pela ordem de jogada";
	private static final String CASTLES_HELP = "castelos - Lista os castelos do jogador activo, pela ordem pela qual foram conquistados";
	private static final String ARMY_HELP = "exercito - Lista os soldados vivos do jogador activo, pela ordem de recrutamento";
	private static final String KINGDOMS_HELP = "reinos - Lista os varios reinos ainda em jogo, ordenados por nome do reino";
	private static final String HELP_HELP = "ajuda - Mostra a ajuda";
	private static final String QUIT_HELP = "sai - Termina a execucao do programa";

	/** Constantes que representam mensagens de erro */
	private static final String INACTIVE_COMMAND = "Comando inactivo.";
	private static final String INEXISTENT_OPTION = "Opcao inexistente.";
	private static final String FATAL_ERROR = "Erro fatal, jogo nao inicializado.";
	private static final String SMALL_MAP_ERROR = "Mapa pequeno demais para o jogo.";
	private static final String INVALID_NUMBER_KINGDOMS = "Numero de reinos invalido.";
	private static final String INVALID_NUMBER_CASTLES = "Numero de castelos invalido.";
	private static final String INVALID_CASTLE_POSITION = "Castelo em posicao invalida.";
	private static final String INVALID_WEALTH = "Castelo com riqueza invalida.";
	private static final String INSUFFICIENT_CASTLES = "Numero insuficiente de castelos criados.";
	private static final String INSUFFICIENT_KINGDOMS = "Numero insuficiente de reinos criados.";
	private static final String CASTLE_MISSING = "Castelo nao existe.";
	private static final String CASTLES_NAMES = "Os castelos nao podem ter nomes duplicados.";
	private static final String KINGDOMS_NAMES = "Os reinos nao podem ter nomes duplicados.";
	private static final String OCCUPIED_CASTLE = "Castelo ja ocupado.";
	private static final String INVALID_SOLDIER = "Tipo de soldado inexistente.";
	private static final String ILLEGAL_INVASION = "Castelo invadido ilegalmente.";
	private static final String INSUFFICIENT_WEALTH = "Riqueza insuficiente para recrutamento.";
	private static final String NOT_FREE = "Castelo nao livre.";
	private static final String NO_SOLDIER = "Nao existe nenhum soldado da ilustre casa de";
	private static final String NO_ILLUSTRIOUS_SOLDIER = "Nao existe nenhum soldado ilustre da casa de";
	private static final String ILLUSTRIOUS_HOUSE = "da ilustre casa de";
	private static final String IS_COWARD = "e um cobardolas.";
	private static final String OTHER_PLACE = "devia tentar ir para outro sitio.";

	/** Constantes que representam as mensagens */
	private static final String GAME_INITIATED = "Jogo iniciado, comeca o reino ";
	private static final String BYE = "Obrigado por jogar. Ate a proxima.";
	private static final String NO_ARMY = "Sem exercito.";
	private static final String KINGDOM = " reinos:";
	private static final String CASTLE = " castelos:";
	private static final String CASTLES_PRINT = "castelos";
	private static final String SOLDIERS = " soldados:";
	private static final String SOLDIERS_PRINT = "soldados";
	private static final String WITH_RICHES = "com riqueza";
	private static final String OF_RICHES = "de riqueza";
	private static final String ON_POSITION = "na posicao";
	private static final String RECRUITED_ON = "recrutado no";

	/** Constantes que representam os sinais necessarios para as strings */
	private static final String DOT = ".";
	private static final String SEP = " ";
	private static final String LEFT_PARENTHESES = "(";
	private static final String RIGHT_PARENTHESES = ")";
	private static final String SEMICOLON = ";";
	private static final String COMMA = ",";

	/**
	 * Constantes que representam limites superiores ou inferiores a serem cumpridos
	 */
	private static final int MAP_LIMIT = 10;
	private static final int MAP_MIN_LIMIT = 1;
	private static final int MIN_KINGDOMS = 2;
	private static final int MAX_KINGDOMS = 8;

	/** Metodo para obter o comando do Scanner */
	private static String getCommand(Scanner in, Game gamePlay) {
		if (!gamePlay.gameStatus())
			System.out.print("> ");
		else {
			System.out.print(gamePlay.getKingdomName() + " > ");
		}
		String input = in.next().toLowerCase();
		return input;

	}

	private static void gainWealth(Game gamePlay) {
		gamePlay.initialIteratorCastle();
		while (gamePlay.hasNextCastle()) {
			Castle c = gamePlay.nextCastle();
			c.gainWealth();
		}
	}

	private static void processNewGame(Scanner in, Game gamePlay) {

		int width = in.nextInt();
		int height = in.nextInt();
		int numTotalKingdoms = in.nextInt();
		int numTotalCastles = in.nextInt();
		in.nextLine();
		if (width < MAP_LIMIT || height < MAP_LIMIT) {
			System.out.println(SMALL_MAP_ERROR);
			System.out.println(FATAL_ERROR);
			gamePlay.gameOff();
		} else if (numTotalKingdoms < MIN_KINGDOMS || numTotalKingdoms > MAX_KINGDOMS) {
			System.out.println(INVALID_NUMBER_KINGDOMS);
			System.out.println(FATAL_ERROR);
			gamePlay.gameOff();
		} else if (numTotalCastles > width * height || numTotalCastles < numTotalKingdoms) {
			System.out.println(INVALID_NUMBER_CASTLES);
			System.out.println(FATAL_ERROR);
			gamePlay.gameOff();
		} else {
			gamePlay.createGame(width, height, numTotalKingdoms, numTotalCastles);
			gamePlay.gameOn();
			System.out.println(numTotalCastles + CASTLE);
			createCastles(in, numTotalCastles, gamePlay, width, height);

			if (numTotalCastles < numTotalKingdoms) {
				System.out.println(INSUFFICIENT_CASTLES);
				System.out.println(FATAL_ERROR);
				gamePlay.gameOff();
			}
			if (gamePlay.getKingdomCastles() < numTotalKingdoms) {
				System.out.println(INSUFFICIENT_CASTLES);
				System.out.println(FATAL_ERROR);
				gamePlay.gameOff();
			} else {
				System.out.println(numTotalKingdoms + KINGDOM);
				createKingdoms(in, numTotalKingdoms, gamePlay);
				if (gamePlay.getNumKingdoms() < MIN_KINGDOMS) {
					System.out.println(INSUFFICIENT_KINGDOMS);
					System.out.println(FATAL_ERROR);
					gamePlay.gameOff();
				} else
					System.out.println(GAME_INITIATED + gamePlay.getKingdomName() + DOT);
			}
		}

	}

	private static void createCastles(Scanner in, int numTotalCastles, Game gamePlay, int width, int height) {
		for (int i = 0; i < numTotalCastles; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int wealth = in.nextInt();
			String name = in.nextLine().trim();
			if (x < MAP_MIN_LIMIT || x > width || y < MAP_MIN_LIMIT || y > height) {
				System.out.println(INVALID_CASTLE_POSITION);
			} else if (wealth < 0) {
				System.out.println(INVALID_WEALTH);
			} else if (gamePlay.nameExistsCastles(name))
				System.out.println(CASTLES_NAMES);
			else
				gamePlay.createCastle(x, y, wealth, name);

		}

	}

	private static void createKingdoms(Scanner in, int numTotalKingdoms, Game gamePlay) {
		for (int i = 0; i < numTotalKingdoms; i++) {
			String kingdomName = in.next().trim();
			String castleName = in.nextLine().trim();
			if (gamePlay.nameExistsKingdoms(kingdomName)) {
				System.out.println(KINGDOMS_NAMES);
			} else if (!gamePlay.nameExistsCastles(castleName)) {
				System.out.println(CASTLE_MISSING);
			} else if (gamePlay.getOwner(castleName) != "")
				System.out.println(OCCUPIED_CASTLE);
			else
				gamePlay.createKingdom(kingdomName, gamePlay.getCastle(castleName));
		}

	}

	/** Metodo que representa o menu, quando nao se enontra nenhum jogo iniciado */
	private static void listMenu() {
		System.out.println(NEW_GAME_HELP);
		System.out.println(HELP_HELP);
		System.out.println(QUIT_HELP);
	}

	private static void processHelp(Game gamePlay) {
		if (gamePlay.gameStatus() == false)
			listMenu();
		else {
			System.out.println(NEW_GAME_HELP);
			System.out.println(SOLDIER_HELP);
			System.out.println(RECRUIT_HELP);
			System.out.println(MAP_HELP);
			System.out.println(CASTLES_HELP);
			System.out.println(ARMY_HELP);
			System.out.println(KINGDOMS_HELP);
			System.out.println(HELP_HELP);
			System.out.println(QUIT_HELP);
		}

	}

	private static void processSoldier(Scanner in, Game gamePlay) {
		int n = 0;
		Kingdom k = gamePlay.getTeamPlaying();
		int x = in.nextInt();
		int y = in.nextInt();
		if (!k.statusSoldier(x, y)) {
			System.out.println(NO_ILLUSTRIOUS_SOLDIER + SEP + k.getKingdomName() + SEP + ON_POSITION + SEP
					+ LEFT_PARENTHESES + x + COMMA + y + RIGHT_PARENTHESES + DOT);
			in.nextLine();
			gamePlay.turnChange();
			gainWealth(gamePlay);
		} else {
			Soldiers s = k.getSoldier(k.searchSoldier(x, y));
			if (s.isKnight()) {
				while (n < 3) {
					Soldiers soldier = k.getSoldier(k.searchSoldier(x, y));
					String move = in.next();
					soldier.movement(move);
					x = soldier.getX();
					y = soldier.getY();
					if (gamePlay.outsidemap(soldier.getX(), soldier.getY())) { /** Se o soldado for para fora do mapa */
						soldier.moveBack(move);
						System.out.println("O" + SEP + k.getKingdomName() + SEP + soldier.getType() + SEP
								+ LEFT_PARENTHESES + s.getX() + COMMA + s.getY() + RIGHT_PARENTHESES + SEP
								+ ILLUSTRIOUS_HOUSE + SEP + k.getKingdomName() + SEP + IS_COWARD);
						System.out.println(k.getKingdomName() + SEP + soldier.getType() + SEP + LEFT_PARENTHESES
								+ soldier.getX() + COMMA + soldier.getY() + RIGHT_PARENTHESES);
						n++;
					} else if (k.samePos(x,
							y) > 1) { /** Se houver um soldado do mesmo reino no lugar para o qual queremos ir */
						System.out.println("O" + SEP + soldier.getType() + SEP + ILLUSTRIOUS_HOUSE + SEP
								+ k.getKingdomName() + SEP + OTHER_PLACE);
						soldier.moveBack(move);
						System.out.println(k.getKingdomName() + SEP + soldier.getType() + SEP + LEFT_PARENTHESES
								+ soldier.getX() + COMMA + soldier.getY() + RIGHT_PARENTHESES);
						n++;
					} else {
						System.out.println(k.getKingdomName() + SEP + soldier.getType() + SEP + LEFT_PARENTHESES
								+ soldier.getX() + COMMA + soldier.getY() + RIGHT_PARENTHESES);
						n++;
					}
					x = soldier.getX();
					y = soldier.getY();
				}
				gamePlay.turnChange();
				gainWealth(gamePlay);

			} else {
				String move = in.next();
				in.nextLine();
				s.movement(move);
				x = s.getX();
				y = s.getY();
				if (gamePlay.outsidemap(s.getX(), s.getY())) { /** Se o soldado for para fora do mapa */
					s.moveBack(move);
					System.out.println("O" + SEP + k.getKingdomName() + SEP + s.getType() + SEP + LEFT_PARENTHESES
							+ s.getX() + COMMA + s.getY() + RIGHT_PARENTHESES + SEP + ILLUSTRIOUS_HOUSE + SEP
							+ k.getKingdomName() + SEP + IS_COWARD);
					System.out.println(k.getKingdomName() + SEP + s.getType() + SEP + LEFT_PARENTHESES + s.getX()
							+ COMMA + s.getY() + RIGHT_PARENTHESES);
				} else if (k.samePos(x,
						y) > 1) {/** Se houver um soldado do mesmo reino no lugar para o qual queremos ir */
					System.out.println("O" + SEP + s.getType() + SEP + ILLUSTRIOUS_HOUSE + SEP + k.getKingdomName()
							+ SEP + OTHER_PLACE);
					s.moveBack(move);
					System.out.println(k.getKingdomName() + SEP + s.getType() + SEP + LEFT_PARENTHESES + s.getX()
							+ COMMA + s.getY() + RIGHT_PARENTHESES);
				} else {
					System.out.println(k.getKingdomName() + SEP + s.getType() + SEP + LEFT_PARENTHESES + s.getX()
							+ COMMA + s.getY() + RIGHT_PARENTHESES);
				}
				gamePlay.turnChange();
				gainWealth(gamePlay);
			}

		}

	}

	private static void processRecruit(Scanner in, Game gamePlay) {
		Kingdom k = gamePlay.getTeamPlaying();
		String type = in.next();
		String castleName = in.nextLine().trim();
		if (k.invalidSoldier(type))
			System.out.println(INVALID_SOLDIER);
		else if (!gamePlay.getOwner(castleName).equals(k.getKingdomName()))
			System.out.println(ILLEGAL_INVASION);
		else {
			Castle c = gamePlay.getCastle(castleName);
			int valor = c.getWealth();
			valor -= k.getSoldierCost(type);
			if (valor < 0)
				System.out.println(INSUFFICIENT_WEALTH);
			else if (k.statusSoldier(c.getXCastle(), c.getYCastle()))
				System.out.println(NOT_FREE);
			else {

				k.createSoldier(c.getXCastle(), c.getYCastle(), type);
				System.out.println(type + SEP + RECRUITED_ON + SEP + castleName + SEP + "do reino" + SEP
						+ k.getKingdomName() + SEP + "por" + SEP + k.getSoldierCost(type) + SEP + "moedas" + DOT);
				c.changeWealth(valor);

			}
		}
		gamePlay.turnChange();
		gainWealth(gamePlay);
	}

	private static void processMap(Game gamePlay) {
		System.out.println(gamePlay.getWidth() + SEP + gamePlay.getHeight());
		System.out.println(gamePlay.getKingdomCastles() + CASTLE);

		gamePlay.initialIteratorCastle();
		while (gamePlay.hasNextCastle()) {
			Castle c = gamePlay.nextCastle();
			if (c.getOwner() == "")
				c.abadonedCastle();
			System.out.println(c.getName() + SEP + LEFT_PARENTHESES + c.getOwner() + RIGHT_PARENTHESES);
		}

		System.out.println(gamePlay.getNumKingdoms() + KINGDOM);

		gamePlay.initialIteratorKingdom();
		Kingdom k = gamePlay.nextKingdom();
		System.out.print(k.getKingdomName());
		while (gamePlay.hasNextKingdom()) {
			k = gamePlay.nextKingdom();
			System.out.print(SEMICOLON + SEP + k.getKingdomName());
		}
		System.out.println();

	}

	private static void processCastles(Game gamePlay) {
		Kingdom k = gamePlay.getTeamPlaying();
		System.out.println(k.getNumCastles() + CASTLE);

		k.initializeIterator();
		while (k.hasNext()) {
			Castle c = k.next();
			System.out.println(c.getName() + SEP + WITH_RICHES + SEP + c.getWealth() + SEP + ON_POSITION + SEP
					+ LEFT_PARENTHESES + c.getXCastle() + COMMA + c.getYCastle() + RIGHT_PARENTHESES);
		}

	}

	private static void processArmy(Game gamePlay) {
		Kingdom k = gamePlay.getTeamPlaying();
		if (k.getKingdomSoldiers() > 0) {
			System.out.println(k.getKingdomSoldiers() + SOLDIERS);

			k.initializeIteratorSoldiers();
			while (k.hasNextSoldier()) {
				Soldiers s = k.nextSoldier();
				System.out.println(s.getType() + SEP + ON_POSITION + SEP + LEFT_PARENTHESES + s.getX() + COMMA
						+ s.getY() + RIGHT_PARENTHESES);
			}
		} else
			System.out.println(NO_ARMY);

	}

	private static void processKingdoms(Game gamePlay) {
		int totalSum = 0;

		System.out.println(gamePlay.getNumKingdoms() + KINGDOM);

		gamePlay.initializeOrdIterator();
		while (gamePlay.hasNextOrd()) {
			Kingdom k = gamePlay.nextOrd();

			k.initializeIterator();
			while (k.hasNext()) {
				Castle c = k.next();
				totalSum += c.getWealth();
			}

			System.out.println(k.getKingdomName() + COMMA + SEP + k.getNumCastles() + SEP + CASTLES_PRINT + COMMA + SEP
					+ k.getKingdomSoldiers() + SEP + SOLDIERS_PRINT + COMMA + SEP + totalSum + SEP + OF_RICHES);

			totalSum = 0;
		}
	}

	// private static void processKingdomsAux(Game gamePlay, int totalSum) {
	// Kingdom k = gamePlay.getTeamPlaying();
	//
	// k.initializeIterator();
	// while (k.hasNext()) {
	// Castle c = k.next();
	// totalSum += c.getWealth();
	// }
	// }

	private static void menu(Scanner in, Game gamePlay) {
		String comm = "";
		listMenu();
		do {
			comm = getCommand(in, gamePlay);
			if (!comm.equals(QUIT) && gamePlay.gameStatus() == false)
				switch (comm) {
				case NEW_GAME:
					processNewGame(in, gamePlay);
					break;
				case HELP:
					processHelp(gamePlay);
					break;
				case SOLDIER:
					System.out.println(INACTIVE_COMMAND);
					break;
				case RECRUIT:
					System.out.println(INACTIVE_COMMAND);
					in.nextLine();
					break;
				case MAP:
					System.out.println(INACTIVE_COMMAND);
					break;
				case CASTLES:
					System.out.println(INACTIVE_COMMAND);
					break;
				case ARMY:
					System.out.println(INACTIVE_COMMAND);
					break;
				case KINGDOMS:
					System.out.println(INACTIVE_COMMAND);
					break;
				case QUIT:
					break;
				default:
					System.out.println(INEXISTENT_OPTION);
					in.nextLine();
					break;
				}
			else
				// if (!comm.equals(QUIT) && !gamePlay.gameStatus()) {
				switch (comm) {
				case NEW_GAME:
					processNewGame(in, gamePlay);
					break;
				case SOLDIER:
					processSoldier(in, gamePlay);

					break;
				case RECRUIT:
					processRecruit(in, gamePlay);
					break;
				case MAP:
					processMap(gamePlay);
					break;
				case CASTLES:
					processCastles(gamePlay);
					break;
				case ARMY:
					processArmy(gamePlay);
					in.nextLine();
					break;
				case KINGDOMS:
					processKingdoms(gamePlay);
					break;
				case HELP:
					processHelp(gamePlay);
					break;
				case QUIT:
					break;
				default:
					System.out.println(INEXISTENT_OPTION);
					in.nextLine();
					break;
				// }
				}
		} while (!comm.equals(QUIT));
		System.out.println(BYE);
		in.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Game gamePlay = new Game();
		menu(in, gamePlay);

	}

}
