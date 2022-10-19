import java.util.Scanner;

public class Main {

	private static final String O_LANCEIRO_DA_ILUSTRE_CASA_DE = "O lanceiro da ilustre casa de ";
	private static final String O_ESPADACHIM_DA_ILUSTRE_CASA_DE = "O espadachim da ilustre casa de ";
	private static final String JA_NAO_ESTA_ENTRE_NOS = " ja nao esta entre nos.";
	private static final String E_UM_COBARDOLAS = " e um cobardolas.";
	private static final String DEVIA_TENTAR_IR_PARA_OUTRO_SITIO = " devia tentar ir para outro sitio.";
	private static final String O_CAVALEIRO_DA_ILUSTRE_CASA_DE = "O cavaleiro da ilustre casa de ";
	private static final String MAPA_MOSTRA_O_MAPA_DO_JOGO = "mapa - Mostra o mapa do jogo";
	private static final String NOVO_NOVO_JOGO = "novo - Novo jogo";
	private static final String OPCAO_INEXISTENTE = "Opcao inexistente.";
	private static final String SETINHA = "> ";
	private static final String OBRIGADA_POR_JOGAR_ATE_A_PROXIMA = "Obrigado por jogar. Ate a proxima.";
	private static final String ESPACO = " ";
	private static final String COMANDO_INACTIVO = "Comando inactivo.";
	private static final String CAVALEIRO = "cavaleiro(";
	private static final String LANCEIRO = "lanceiro(";
	private static final String ESPADACHIM = "espadachim(";
	private static final String LANCEIRO2 = "LANCEIRO";
	private static final String ESPADACHIM2 = "ESPADACHIM";
	private static final String CAVALEIRO2 = "CAVALEIRO";
	private static final String MAP_PEQUENO_DEMAIS_PARA_O_GAME = "Mapa pequeno demais para o jogo.";
	private static final String BANDEIRA_EM_POSICAO_INVALIDA = " bandeira em posicao invalida ";
	private static final String AS_TEAMS_NAO_PODEM_TER_O_MESMO_NOME = "As equipas nao podem ter o mesmo nome.";
	private static final String GAME_INICIADO_COMECA = "Jogo iniciado, comeca a equipa ";
	private static final String CAVALEIRO_MOVE_O_CAVALEIRO = "cavaleiro - Move o cavaleiro";
	private static final String LANCEIRO_MOVE_O_LANCEIRO = "lanceiro - Move o lanceiro";
	private static final String ESPADACHIM_MOVE_O_ESPADACHIM = "espadachim - Move o espadachim";
	private static final String AJUDA_MOSTRA_AJUDA = "ajuda - Mostra a ajuda";
	private static final String SAI_TERMINA_A_EXECUCAO_DO_PROGRAMA = "sai - Termina a execucao do programa";
	private static final String NOVO = "NOVO";
	private static final String AJUDA = "AJUDA";
	private static final String MAPA = "MAPA";
	private static final String SAI = "SAI";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Game game = new Game();
		comandosDisponiveis(game);
		String option = "";
		
		//Enquando nao for a opcao sair ele permanece a espera de comandos
		while (!option.equals(SAI)) {
			prompt(game);
			option = in.next().toUpperCase();
			switch (option) {
			case NOVO:
				New(in, game);
				break;
			case AJUDA:
				comandosDisponiveis(game);
				break;
			case MAPA:
				descreverMap(game);
				break;
			case CAVALEIRO2:
				movimentaCavaleiro(in, game);
				break;
			case LANCEIRO2:
				movimentaLanceiro(in, game);
				break;
			case ESPADACHIM2:
				movimentaEspadachim(in, game);
				break;
			case SAI:
				System.out.println(OBRIGADA_POR_JOGAR_ATE_A_PROXIMA);
				break;
			default:
				System.out.println(OPCAO_INEXISTENTE);
			}
		}
		in.close();
	}

	//Mensagem no inicio de cada linha consoante o jogo esta a decorrer ou nao
	private static void prompt(Game game){
		int equipa = getCurrentTeamNumber(game);
		if(game.gameIsRuning()){
			System.out.print(game.getNameEquipa(equipa) + " " + SETINHA);
		} else 
			System.out.print(SETINHA);
	}

	
	//Metodo de criar jogo inicializando o mapa, as equipas com os dados introduzidos pelo utilizador
	private static void New(Scanner in, Game game) {

		// Valores para o limite do Map
		int horizontal = in.nextInt();
		int vertical = in.nextInt();
		// Se os valores para o Map nao sao abaixo de 10
		in.nextLine();
		String teamName1 = in.next();
		int xFlag1 = in.nextInt();
		int yFlag1 = in.nextInt();
		in.nextLine();
		// Dados para a segunda team
		String teamName2 = in.next();
		int xFlag2 = in.nextInt();
		int yFlag2 = in.nextInt();
		in.nextLine();

		//Nao interessa se vai acabar bem ou mal o comando novo mete logo a off o jogo
		game.setGameOff();

		if (horizontal < 10 || vertical < 10){
			System.out.println(MAP_PEQUENO_DEMAIS_PARA_O_GAME);
			game.setGameOff();
		} else {
			if (xFlag1 >= horizontal || yFlag1 >= vertical || xFlag1 <= 1 || yFlag1 <= 1){
				
				System.out.println(teamName1 + BANDEIRA_EM_POSICAO_INVALIDA + xFlag1 + ESPACO + yFlag1 + ".");

				if (xFlag2 >= horizontal || yFlag2 >= vertical || xFlag2 <= 1 || yFlag2 <= 1){

					System.out.println(teamName2 + BANDEIRA_EM_POSICAO_INVALIDA + xFlag2 + ESPACO + yFlag2 + ".");
				}
			}else {
				if (xFlag2 >= horizontal || yFlag2 >= vertical || xFlag2 <= 1 || yFlag2 <= 1){
					System.out.println(teamName2 + BANDEIRA_EM_POSICAO_INVALIDA + xFlag2 + ESPACO + yFlag2 + ".");
				} else{
					if (xFlag2 >= horizontal || yFlag2 >= vertical){
						System.out.println(teamName2 + BANDEIRA_EM_POSICAO_INVALIDA + xFlag2 + ESPACO + yFlag2 + ".");
					}
					else {
						if (Math.abs(xFlag1 - xFlag2) + Math.abs(yFlag1 - yFlag2) <= 5)
							System.out.println(teamName2 + BANDEIRA_EM_POSICAO_INVALIDA + xFlag2 + ESPACO + yFlag2 + ".");
						else {
							if (teamName1.equals(teamName2))
								System.out.println(AS_TEAMS_NAO_PODEM_TER_O_MESMO_NOME);
							else {
								game.criarEquipas(teamName1, teamName2, xFlag1, yFlag1, xFlag2, yFlag2);
								game.criarMapa(horizontal, vertical);
								game.setGameOn();
								System.out.println(GAME_INICIADO_COMECA + teamName1 + ".");
							}
						}
					}
				}
			}
		}
	}

	//Lista de comandos disponiveis consoante o estado do jogo ( a decorrer ou nao )
	private static void comandosDisponiveis(Game game) {
		if (game.gameIsRuning()) {
			System.out.println(NOVO_NOVO_JOGO);
			System.out.println(MAPA_MOSTRA_O_MAPA_DO_JOGO);
			System.out.println(CAVALEIRO_MOVE_O_CAVALEIRO);
			System.out.println(ESPADACHIM_MOVE_O_ESPADACHIM);
			System.out.println(LANCEIRO_MOVE_O_LANCEIRO);
			System.out.println(AJUDA_MOSTRA_AJUDA);
			System.out.println(SAI_TERMINA_A_EXECUCAO_DO_PROGRAMA);
		} else {
			System.out.println(NOVO_NOVO_JOGO);
			System.out.println(AJUDA_MOSTRA_AJUDA);
			System.out.println(SAI_TERMINA_A_EXECUCAO_DO_PROGRAMA);
		}
	}

	//Mostra a informação dos dados do jogo que estao a decorrer
	private static void descreverMap(Game game) {
		String espadachim1 = "espadachim";
		String lanceiro1 = "lanceiro";
		String cavaleiro1 = "cavaleiro";
		if (game.gameIsRuning()) {
			System.out.println(game.getHorizontal() + ESPACO + game.getVertical());

			System.out.print(
					game.team1.getTeamName() + ESPACO + game.team1.getxFlag() + ESPACO + game.team1.getyFlag() + " ");

			System.out.print(CAVALEIRO + vivoOuMorto(game.getStatus(cavaleiro1, 1)) + ") " + game.team1.cavaleiro.getX() + ESPACO
					+ game.team1.cavaleiro.getY() + " ");

			System.out.print(ESPADACHIM + vivoOuMorto(game.getStatus(espadachim1, 1)) + ") " + game.team1.espadachim.getX()
			+ ESPACO + game.team1.espadachim.getY() + " ");

			System.out.println(LANCEIRO + vivoOuMorto(game.getStatus(lanceiro1, 1)) + ") " + game.team1.lanceiro.getX() + ESPACO
					+ game.team1.lanceiro.getY());

			System.out.print(
					game.team2.getTeamName() + ESPACO + game.team2.getxFlag() + ESPACO + game.team2.getyFlag()+ " ");

			System.out.print(CAVALEIRO + vivoOuMorto(game.getStatus(cavaleiro1, 2)) + ") " + game.team2.cavaleiro.getX() + ESPACO
					+ game.team2.cavaleiro.getY() + " ");

			System.out.print(ESPADACHIM + vivoOuMorto(game.getStatus(espadachim1, 2)) + ") " + game.team2.espadachim.getX()
			+ ESPACO + game.team2.espadachim.getY() + " ");

			System.out.println(LANCEIRO + vivoOuMorto(game.getStatus(lanceiro1, 2)) + ") " + game.team2.lanceiro.getX() + ESPACO
					+ game.team2.lanceiro.getY());
		} else
			System.out.println(COMANDO_INACTIVO);

	}

	//Retorna o numero da equipa que esta a jogar, 1 se for a equipa 1 e 2 se for a equipa 2
	private static int getCurrentTeamNumber(Game game) {
		if (game.isEquipa1())
			return 1;
		else
			return 2;
	}
	
	//Movimentacao do cavalo consoante os comandos do utilizador
	private static void movimentaCavaleiro(Scanner in, Game game) {
		int equipa = getCurrentTeamNumber(game);
		String tipo = "cavaleiro";

		int rival;
		if(equipa == 1){
			rival = 2;
		} else {
			rival = 1;
		}

		if (!game.gameIsRuning()) {
			System.out.println(COMANDO_INACTIVO);
		} else {
			if (game.getTeam(equipa).getCharacter(tipo).getLife()) {

				String move = in.next();
				String move1 = in.next();
				String move2 = in.next();
				in.nextLine();

				if (game.canMove(move,equipa,tipo,game)) {
					if(!game.checkSameTeam(move, tipo, equipa)){
						if(game.win(move, tipo, equipa)){
							System.out.println("Sou um heroi " + game.getNameEquipa(equipa) + "! A bandeira " + game.getNameEquipa(rival) + " e nossa! Vitoria gloriosa!");
							game.moveChar(move, tipo, equipa);
							System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
									+ game.getTeam(equipa).getCharacter(tipo).getX() 
									+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());

							game.setGameOff();
						} else {
							if(!game.moveAndDie(move, tipo, equipa)){

								if(game.oneToKill(move, tipo, equipa).equals("cavaleiro")){
									game.moveChar(move, tipo, equipa);

									System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "cavaleiro" + " me faz frente!");

									System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
											+ game.getTeam(equipa).getCharacter(tipo).getX() 
											+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());

								} else {
									if(game.oneToKill(move, tipo, equipa).equals("espadachim")){
										game.moveChar(move, tipo, equipa);

										System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "espadachim" + " me faz frente!");

										System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
												+ game.getTeam(equipa).getCharacter(tipo).getX() 
												+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
									} else {

										game.moveChar(move, tipo, equipa);
										System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
												+ game.getTeam(equipa).getCharacter(tipo).getX() 
												+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
									}
								}
							} else {

								System.out.println("Argh! A dor! Maldito sejas, lanceiro " + game.getNameEquipa(rival) +".");

								System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
										+ game.getTeam(equipa).getCharacter(tipo).getX() 
										+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());

							}
						}
					} else {
						System.out.println(O_CAVALEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) +DEVIA_TENTAR_IR_PARA_OUTRO_SITIO);

						System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
								+ game.getTeam(equipa).getCharacter(tipo).getX() 
								+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
					}
				}else{
					System.out.println(O_CAVALEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) +E_UM_COBARDOLAS);

					System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa))
					+ ") " + game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO
					+ game.getTeam(equipa).getCharacter(tipo).getY());
				}
				if(game.gameIsRuning()){


					//Se pode fazer o segundo move
					if (game.canMove(move1,equipa,tipo,game)){
						if(!game.checkSameTeam(move1, tipo, equipa)){
							if(game.win(move1, tipo, equipa)){

								System.out.println("Sou um heroi " + game.getNameEquipa(equipa) + "! A bandeira " + game.getNameEquipa(rival) + " e nossa! Vitoria gloriosa!");
								game.moveChar(move1, tipo, equipa);
								System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
										+ game.getTeam(equipa).getCharacter(tipo).getX() 
										+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
								game.setGameOff();
							} else {
								if(!game.moveAndDie(move1, tipo, equipa)){

									if(game.oneToKill(move1, tipo, equipa).equals("cavaleiro")){
										game.moveChar(move1, tipo, equipa);

										System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "cavaleiro" + " me faz frente!");

										System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
												+ game.getTeam(equipa).getCharacter(tipo).getX() 
												+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());

									} else {
										if(game.oneToKill(move1, tipo, equipa).equals("espadachim")){
											game.moveChar(move1, tipo, equipa);

											System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "espadachim" + " me faz frente!");

											System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
													+ game.getTeam(equipa).getCharacter(tipo).getX() 
													+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
										} else {

											game.moveChar(move1, tipo, equipa);
											System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
													+ game.getTeam(equipa).getCharacter(tipo).getX() 
													+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
										}
									}
								} else {

									System.out.println("Argh! A dor! Maldito sejas, lanceiro " + game.getNameEquipa(rival) +".");

									System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
											+ game.getTeam(equipa).getCharacter(tipo).getX() 
											+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());

								}
							}
						} else {
							System.out.println(O_CAVALEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) +DEVIA_TENTAR_IR_PARA_OUTRO_SITIO);
							System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" +vivoOuMorto( game.getStatus(tipo, equipa))
							+ ") " + game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO
							+ game.getTeam(equipa).getCharacter(tipo).getY());
						}
					}else{
						System.out.println(O_CAVALEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) +E_UM_COBARDOLAS);

						System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa))
						+ ") " + game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO
						+ game.getTeam(equipa).getCharacter(tipo).getY());
					}
				}
				//se pode fazer o 3 move
				if(game.gameIsRuning()){


					if (game.canMove(move2,equipa,tipo,game)){
						if(!game.checkSameTeam(move2, tipo, equipa)){
							if(game.win(move2, tipo, equipa)){

								System.out.println("Sou um heroi " + game.getNameEquipa(equipa) + "! A bandeira " + game.getNameEquipa(rival) + " e nossa! Vitoria gloriosa!");
								game.moveChar(move2, tipo, equipa);
								System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
										+ game.getTeam(equipa).getCharacter(tipo).getX() 
										+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
								game.setGameOff();
							} else {
								if(!game.moveAndDie(move2, tipo, equipa)){

									if(game.oneToKill(move2, tipo, equipa).equals("cavaleiro")){
										game.moveChar(move2, tipo, equipa);

										System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "cavaleiro" + " me faz frente!");

										System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
												+ game.getTeam(equipa).getCharacter(tipo).getX() 
												+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
										game.changeTeam();

									} else {
										if(game.oneToKill(move2, tipo, equipa).equals("espadachim")){
											game.moveChar(move2, tipo, equipa);

											System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "espadachim" + " me faz frente!");

											System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
													+ game.getTeam(equipa).getCharacter(tipo).getX() 
													+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
											game.changeTeam();
										} else {

											game.moveChar(move2, tipo, equipa);
											System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
													+ game.getTeam(equipa).getCharacter(tipo).getX() 
													+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
											game.changeTeam();
										}
									}
								} else {

									System.out.println("Argh! A dor! Maldito sejas, lanceiro " + game.getNameEquipa(rival) +".");

									System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
											+ game.getTeam(equipa).getCharacter(tipo).getX() 
											+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
									game.changeTeam();
								}
							}
						}else{
							game.changeTeam();
							System.out.println(O_CAVALEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) +DEVIA_TENTAR_IR_PARA_OUTRO_SITIO);

							System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" +vivoOuMorto( game.getStatus(tipo, equipa))
							+ ") " + game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO
							+ game.getTeam(equipa).getCharacter(tipo).getY());
						}
					}else{

						System.out.println(O_CAVALEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) +E_UM_COBARDOLAS);

						System.out.println(game.getNameEquipa(equipa) + ESPACO + "cavaleiro(" + vivoOuMorto(game.getStatus(tipo, equipa))
						+ ") " + game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO
						+ game.getTeam(equipa).getCharacter(tipo).getY());

						game.changeTeam();

					}
				} 
			}else
				System.out.println(
						O_CAVALEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) + JA_NAO_ESTA_ENTRE_NOS);
		}
	}

	private static String vivoOuMorto(Boolean status){
		if(status)
			return "vivo";
		else
			return "morto";
	}

	private static void movimentaLanceiro(Scanner in, Game game) {

		int equipa = getCurrentTeamNumber(game);
		int rival;
		if(equipa == 1){
			rival = 2;
		} else {
			rival = 1;
		}
		String tipo = "lanceiro";
		if (!game.gameIsRuning()) {
			System.out.println(COMANDO_INACTIVO);
		} else {
			if (game.getTeam(equipa).getCharacter(tipo).getLife()) {

				String move = in.next();
				in.nextLine();

				if (game.canMove(move,equipa,tipo,game)){
					if(!game.checkSameTeam(move, tipo, equipa)){
						if(game.win(move, tipo, equipa)){
							System.out.println("Sou um heroi " + game.getNameEquipa(equipa) + "! A bandeira " + game.getNameEquipa(rival) + " e nossa! Vitoria gloriosa!");
							game.moveChar(move, tipo, equipa);
							System.out.println(game.getNameEquipa(equipa) + ESPACO + "lanceiro(" + vivoOuMorto(game.getStatus(tipo, equipa))+ ") "
									+ game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
							game.setGameOff();
						} else {
							if(!game.moveAndDie(move, tipo, equipa)){
								if(game.oneToKill(move, tipo, equipa).equals("cavaleiro")){
									game.moveChar(move, tipo, equipa);

									System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "cavaleiro" + " me faz frente!");

									System.out.println(game.getNameEquipa(equipa) + ESPACO + "lanceiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
											+ game.getTeam(equipa).getCharacter(tipo).getX() 
											+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
									game.changeTeam();

								} else {
									if(game.oneToKill(move, tipo, equipa).equals("lanceiro")){
										game.moveChar(move, tipo, equipa);

										System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "lanceiro" + " me faz frente!");

										System.out.println(game.getNameEquipa(equipa) + ESPACO + "lanceiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
												+ game.getTeam(equipa).getCharacter(tipo).getX() 
												+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
										game.changeTeam();
									} else {

										game.moveChar(move, tipo, equipa);
										System.out.println(game.getNameEquipa(equipa) + ESPACO + "lanceiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
												+ game.getTeam(equipa).getCharacter(tipo).getX() 
												+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
										game.changeTeam();
									}
								}
							} else {

								System.out.println("Argh! A dor! Maldito sejas, espadachim " + game.getNameEquipa(rival) +".");
								System.out.println(game.getNameEquipa(equipa) + ESPACO + "lanceiro(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
										+ game.getTeam(equipa).getCharacter(tipo).getX() 
										+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
								game.changeTeam();
							}
						}
					}else {
						System.out.println(O_LANCEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) +DEVIA_TENTAR_IR_PARA_OUTRO_SITIO);
						System.out.println(game.getNameEquipa(equipa) + ESPACO + "lanceiro(" + vivoOuMorto(game.getStatus(tipo, equipa))+ ") "
								+ game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
						game.changeTeam();
					}

				}else{

					System.out.println(O_LANCEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) + E_UM_COBARDOLAS);

					System.out.println(game.getNameEquipa(equipa) + ESPACO + "lanceiro(" + vivoOuMorto(game.getStatus(tipo, equipa))+ ") "
							+ game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());

					game.changeTeam();

				}
			} else System.out.println(O_LANCEIRO_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) + JA_NAO_ESTA_ENTRE_NOS);
		}
	}

	private static void movimentaEspadachim(Scanner in, Game game) {

		String tipo = "espadachim";
		int equipa = getCurrentTeamNumber(game);

		int rival;
		if(equipa == 1){
			rival = 2;
		} else {
			rival = 1;
		}

		if (!game.gameIsRuning()) {
			System.out.println(COMANDO_INACTIVO);
		} else {
			if (game.getTeam(equipa).getCharacter(tipo).getLife()) {

				String move = in.next();
				in.nextLine();

				if (game.canMove(move,equipa,tipo,game)){
					if(!game.checkSameTeam(move, tipo, equipa)){
						if(game.win(move, tipo, equipa)){
							System.out.println("Sou um heroi " + game.getNameEquipa(equipa) + "! A bandeira " + game.getNameEquipa(rival) + " e nossa! Vitoria gloriosa!");
							game.moveChar(move, tipo, equipa);
							System.out.println(game.getNameEquipa(equipa) + ESPACO + "espadachim(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") "
									+ game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
							game.setGameOff();

						} else {
							if(!game.moveAndDie(move, tipo, equipa)){

								if(game.oneToKill(move, tipo, equipa).equals("espadachim")){
									game.moveChar(move, tipo, equipa);

									System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "espadachim" + " me faz frente!");

									System.out.println(game.getNameEquipa(equipa) + ESPACO + "espadachim(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
											+ game.getTeam(equipa).getCharacter(tipo).getX() 
											+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
									game.changeTeam();

								} else {
									if(game.oneToKill(move, tipo, equipa).equals("lanceiro")){
										game.moveChar(move, tipo, equipa);

										System.out.println("Muhahah, sou um "+ game.getNameEquipa(equipa) + "! Sou invencivel! Nenhum "+ "lanceiro" + " me faz frente!");

										System.out.println(game.getNameEquipa(equipa) + ESPACO + "espadachim(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
												+ game.getTeam(equipa).getCharacter(tipo).getX() 
												+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
										game.changeTeam();
									} else {

										game.moveChar(move, tipo, equipa);
										System.out.println(game.getNameEquipa(equipa) + ESPACO + "espadachim(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
												+ game.getTeam(equipa).getCharacter(tipo).getX() 
												+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
										game.changeTeam();
									}
								}
							} else {

								System.out.println("Argh! A dor! Maldito sejas, cavaleiro " + game.getNameEquipa(rival) +".");
								System.out.println(game.getNameEquipa(equipa) + ESPACO + "espadachim(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") " 
										+ game.getTeam(equipa).getCharacter(tipo).getX() 
										+ ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
								game.changeTeam();
							}
						}
					}else {
						System.out.println(O_ESPADACHIM_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) +DEVIA_TENTAR_IR_PARA_OUTRO_SITIO);

						System.out.println(game.getNameEquipa(equipa) + ESPACO + "espadachim(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") "
								+ game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());

						game.changeTeam();
					}

				}else{
					System.out.println(O_ESPADACHIM_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) + E_UM_COBARDOLAS);
					System.out.println(game.getNameEquipa(equipa) + ESPACO + "espadachim(" + vivoOuMorto(game.getStatus(tipo, equipa)) + ") "
							+ game.getTeam(equipa).getCharacter(tipo).getX() + ESPACO + game.getTeam(equipa).getCharacter(tipo).getY());
					game.changeTeam();
				}

			} else System.out.println(O_ESPADACHIM_DA_ILUSTRE_CASA_DE + game.getNameEquipa(equipa) + JA_NAO_ESTA_ENTRE_NOS);

		}
	}

}
