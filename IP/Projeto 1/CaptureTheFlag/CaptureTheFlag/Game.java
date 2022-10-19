//up
public class Game {
	// Constantes
	private static final String CAVALEIRO = "cavaleiro";
	private static final String ESPADACHIM = "espadachim";
	private static final String LANCEIRO = "lanceiro";
	private static final String NORTE = "NORTE";
	private static final String SUL = "SUL";
	private static final String OESTE = "OESTE";
	private static final String ESTE = "ESTE";

	// Variaveis
	private boolean on, isEquipa1;
	Team team1;
	Team team2;
	private int horizontal, vertical;

	// O Game vai ligar as classe personagens e a classe Teams com a main

	// Contrutor
	public Game() {
		on = false;
		team1 = null;
		team2 = null;
		horizontal = -1;
		vertical = -1;
		isEquipa1 = true;
	}

	// Metodos
	public int getHorizontal() {
		return horizontal;
	}

	public int getVertical() {
		return vertical;
	}

	public boolean gameIsRuning() {
		return on;
	}

	public boolean isEquipa1() {
		return isEquipa1;
	}

	public void setGameOn() {
		on = true;		
	}

	public void setGameOff() {
		on=false;		
	}

	public void criarMapa(int horizontal, int vertical) {
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

	public String getNameEquipa(int equipa) {
		if (equipa == 1)
			return team1.getTeamName();
		else
			return team2.getTeamName();
	}

	public Team getTeam(int equipa) {
		if (equipa == 1)
			return team1;
		else
			return team2;
	}

	public void criarEquipas(String nom1, String nome2, int xFlag1, int yFlag1, int xFlag2, int yFlag2) {
		team1 = new Team(nom1, xFlag1, yFlag1);
		team2 = new Team(nome2, xFlag2, yFlag2);
	}

	public boolean getStatus(String personagem, int equipa) {
		String type = personagem.toLowerCase();
		if (equipa == 1) {
			switch (type) {
			case CAVALEIRO:
				return team1.getCharacter(CAVALEIRO).getLife();
			case ESPADACHIM:
				return team1.getCharacter(ESPADACHIM).getLife();
			case LANCEIRO:
				return team1.getCharacter(LANCEIRO).getLife();
			default:
				return false;
			}
		} else {
			switch (type) {
			case CAVALEIRO:
				return team2.getCharacter(CAVALEIRO).getLife();
			case ESPADACHIM:
				return team2.getCharacter(ESPADACHIM).getLife();
			case LANCEIRO:
				return team2.getCharacter(LANCEIRO).getLife();
			default:
				return false;
			}
		}
	}

	public boolean moveAndDie(String move, String tipo, int equipa){
		String movimento = move.toUpperCase();
		Team team = getTeam(equipa);
		Character personagem = team.getCharacter(tipo);

		int xPos, yPos;
		xPos = personagem.getX();
		yPos = personagem.getY();

		int nextXPos, nextYPos;

		switch(movimento){
		case NORTE :
			nextYPos = yPos +1;
			nextXPos = xPos;
			break;
		case SUL:
			nextYPos  = yPos -1;
			nextXPos = xPos;
			break;
		case OESTE:
			nextXPos = xPos -1;
			nextYPos = yPos;
			break;
		case ESTE:
			nextXPos = xPos +1;
			nextYPos = yPos;
			break;
		default:
			nextXPos = xPos;
			nextYPos = yPos;
		}

		//		Para a mesma equipa
		String type = tipo.toLowerCase();
		switch(type){
		case CAVALEIRO:
			return lutaCavalo(equipa,nextXPos,nextYPos);
		case LANCEIRO:
			return lutaLanceiro(equipa, nextXPos, nextYPos);
		case ESPADACHIM:
			return lutaEspadachim(equipa, nextXPos, nextYPos);
		default: 
			return false;
		}
	}

	private boolean lutaEspadachim(int equipa, int nextXPos, int nextYPos) {
		Team outra;
		int outraEqp;
		if(equipa ==1 ){
			outraEqp=2;
			outra = team2;
		}else{
			outraEqp=1;
			outra = team1;
		}
		Character lanceiro, cavaleiro, espadachim;
		lanceiro = outra.getCharacter(LANCEIRO);
		cavaleiro = outra.getCharacter(CAVALEIRO);
		espadachim = outra.getCharacter(ESPADACHIM);

		if((nextXPos == lanceiro.getX() && nextYPos == lanceiro.getY()) && lanceiro.getLife()){
			mataPersonagem(LANCEIRO,outraEqp);
			return false;
		}
		else if((nextXPos == espadachim.getX() && nextYPos == espadachim.getY() && espadachim.getLife())){
			mataPersonagem(ESPADACHIM,outraEqp);
			return false;
		}else if((nextXPos == cavaleiro.getX() && nextYPos == cavaleiro.getY() && cavaleiro.getLife())){
			mataPersonagem(ESPADACHIM, equipa);
			return true;
		} else 
			return false;
	}

	private boolean lutaLanceiro(int equipa, int nextXPos, int nextYPos) {

		Team outra;
		int outraEqp;
		if(equipa ==1 ){
			outraEqp=2;
			outra = team2;
		}else{
			outraEqp=1;
			outra = team1;
		}
		Character lanceiro, cavaleiro, espadachim;
		lanceiro = outra.getCharacter(LANCEIRO);
		cavaleiro = outra.getCharacter(CAVALEIRO);
		espadachim = outra.getCharacter(ESPADACHIM);

		if((nextXPos == lanceiro.getX() && nextYPos == lanceiro.getY()) && lanceiro.getLife()){
			mataPersonagem(LANCEIRO,outraEqp);
			return false;
		}

		else if((nextXPos == espadachim.getX() && nextYPos == espadachim.getY() && espadachim.getLife())){
			mataPersonagem(LANCEIRO,equipa);
			return true;

		}else if((nextXPos == cavaleiro.getX() && nextYPos == cavaleiro.getY() && cavaleiro.getLife())){
			mataPersonagem(CAVALEIRO, outraEqp);
			return false;
		} else
			return false;
	}

	private boolean lutaCavalo(int equipa, int nextXPos, int nextYPos) {
		Team outra;
		int outraEqp;
		if(equipa ==1 ){
			outraEqp=2;
			outra = team2;
		}else{
			outraEqp=1;
			outra = team1;
		}
		Character lanceiro, cavaleiro, espadachim;
		lanceiro = outra.getCharacter(LANCEIRO);
		cavaleiro = outra.getCharacter(CAVALEIRO);
		espadachim = outra.getCharacter(ESPADACHIM);

		if((nextXPos == lanceiro.getX() && nextYPos == lanceiro.getY()) && lanceiro.getLife()){
			mataPersonagem(CAVALEIRO,equipa);
			return true;
		}
		else if((nextXPos == espadachim.getX() && nextYPos == espadachim.getY() && espadachim.getLife())){
			mataPersonagem(ESPADACHIM,outraEqp);
			return false;
		}else if((nextXPos == cavaleiro.getX() && nextYPos == cavaleiro.getY() && cavaleiro.getLife())){
			mataPersonagem(CAVALEIRO, outraEqp);
			return false;

		} else return false;




	}

	private void mataPersonagem(String tipo, int equipa) {

		if(equipa==1){
			team1.getCharacter(tipo).matar();
		} else {
			team2.getCharacter(tipo).matar();
		}
	}

	public void moveChar(String move, String tipo, int equipa) {
		String type = tipo.toLowerCase();
		if (equipa == 1) {
			switch (type) {
			case CAVALEIRO:
				team1.getCharacter(CAVALEIRO).movement(move);
				break;
			case ESPADACHIM:
				team1.getCharacter(ESPADACHIM).movement(move);
				break;
			case LANCEIRO:
				team1.getCharacter(LANCEIRO).movement(move);
				break;
			default:
			}
		} else {
			switch (type) {
			case CAVALEIRO:
				team2.getCharacter(CAVALEIRO).movement(move);
				break;
			case ESPADACHIM:
				team2.getCharacter(ESPADACHIM).movement(move);
				break;
			case LANCEIRO:
				team2.getCharacter(LANCEIRO).movement(move);
				break;
			default:
			}
		}
	}

	public void gerirMovimentos(){

	}

	public boolean canMove(String move,int equipa,String tipo,Game game){


		String movimento = move.toUpperCase();
		Team team = game.getTeam(equipa);
		Character character = team.getCharacter(tipo);
		int xPos  = character.getX();
		int yPos= character.getY();

		switch(movimento){
		case NORTE :
			return (yPos != game.getVertical());
		case SUL:
			return (yPos != 1);
		case OESTE:
			return (xPos != 1);
		case ESTE:
			return (xPos != game.getHorizontal()); 
		default:
			return false;
		}

	}

	public boolean checkSameTeam(String move, String tipo, int equipa){
		String movimento = move.toUpperCase();
		Team team = getTeam(equipa);
		Character personagem = team.getCharacter(tipo);

		int xPos, yPos;
		xPos = personagem.getX();
		yPos = personagem.getY();

		int nextXPos, nextYPos;

		switch(movimento){
		case NORTE :
			nextYPos = yPos +1;
			nextXPos = xPos;
			break;
		case SUL:
			nextYPos  = yPos -1;
			nextXPos = xPos;
			break;
		case OESTE:
			nextXPos = xPos -1;
			nextYPos = yPos;
			break;
		case ESTE:
			nextXPos = xPos +1;
			nextYPos = yPos;
			break;
		default:
			nextXPos = xPos;
			nextYPos = yPos;
		}


		//		Para a mesma equipa
		String type = tipo.toLowerCase();
		switch(type){
		case CAVALEIRO:
			return tratarCavaleiro(team,nextXPos,nextYPos);
		case LANCEIRO:
			return tratarLanceiro(team, nextXPos,nextYPos);
		case ESPADACHIM:
			return tratarEspadachim(team, nextXPos,nextYPos);
		default: 
			return false;
		}
	}

	public boolean win(String move, String tipo, int equipa){
		String movimento = move.toUpperCase();
		Team team = getTeam(equipa);
		Character personagem = team.getCharacter(tipo);

		int xPos, yPos;
		xPos = personagem.getX();
		yPos = personagem.getY();

		int nextXPos, nextYPos;

		switch(movimento){
		case NORTE :
			nextYPos = yPos +1;
			nextXPos = xPos;
			break;
		case SUL:
			nextYPos  = yPos -1;
			nextXPos = xPos;
			break;
		case OESTE:
			nextXPos = xPos -1;
			nextYPos = yPos;
			break;
		case ESTE:
			nextXPos = xPos +1;
			nextYPos = yPos;
			break;
		default:
			nextXPos = xPos;
			nextYPos = yPos;
		}

		int outraEquip;

		if(equipa == 1){
			outraEquip = 2;
		} else {
			outraEquip = 1;
		}

		Team outra =  getTeam(outraEquip);
		if(nextXPos == outra.getxFlag() && nextYPos == outra.getyFlag()){
			return true;
		}
		return false;
	}

	private boolean tratarLanceiro(Team team,int nextXPos,int nextYPos) {
		Character cavaleiro,espadachim;
		cavaleiro = team.getCharacter(CAVALEIRO);
		espadachim = team.getCharacter(ESPADACHIM);

		int xPosCav,yPosCav,xPosEsp,yPosEsp;
		xPosCav = cavaleiro.getX();
		yPosCav = cavaleiro.getY();
		xPosEsp = espadachim.getX();
		yPosEsp = espadachim.getY();


		if(((nextXPos == xPosCav && nextYPos == yPosCav) && cavaleiro.getLife()) || (nextXPos == team.getxFlag() && nextYPos == team.getyFlag()) || ((nextXPos == xPosEsp && nextYPos == yPosEsp) && espadachim.getLife())){
			return true;
		}
		return false;
	}

	private boolean tratarEspadachim(Team team, int nextXPos, int nextYPos) {
		Character lanceiro,cavaleiro;
		lanceiro = team.getCharacter(LANCEIRO);
		cavaleiro = team.getCharacter(CAVALEIRO);

		int xPosLan,yPosLan,xPosCav,yPosCav;
		xPosLan = lanceiro.getX();
		yPosLan = lanceiro.getY();
		xPosCav = cavaleiro.getX();
		yPosCav = cavaleiro.getY();

		if(((nextXPos == xPosLan && nextYPos == yPosLan) && lanceiro.getLife()) || (nextXPos == team.getxFlag() && nextYPos == team.getyFlag()) || ((nextXPos == xPosCav && nextYPos == yPosCav) && cavaleiro.getLife())){
			return true;
		}
		return false;
	}

	private boolean tratarCavaleiro(Team team, int nextXPos, int nextYPos) {

		Character lanceiro,espadachim;
		lanceiro = team.getCharacter(LANCEIRO);
		espadachim = team.getCharacter(ESPADACHIM);

		int xPosLan,yPosLan,xPosEsp,yPosEsp;
		xPosLan = lanceiro.getX();
		yPosLan = lanceiro.getY();
		xPosEsp = espadachim.getX();
		yPosEsp = espadachim.getY();

		if(((nextXPos == xPosLan && nextYPos == yPosLan) && lanceiro.getLife()) || (nextXPos == team.getxFlag() && nextYPos == team.getyFlag())|| ((nextXPos == xPosEsp && nextYPos == yPosEsp) && espadachim.getLife())){
			return true;
		}
		return false;
	}

	public void changeTeam() {
		isEquipa1 = !isEquipa1;

	}

	public String oneToKill(String move, String tipo, int equipa){
		String movimento = move.toUpperCase();
		Team team = getTeam(equipa);
		Character personagem = team.getCharacter(tipo);

		int xPos, yPos;
		xPos = personagem.getX();
		yPos = personagem.getY();

		int nextXPos, nextYPos;

		switch(movimento){
		case NORTE :
			nextYPos = yPos +1;
			nextXPos = xPos;
			break;
		case SUL:
			nextYPos  = yPos -1;
			nextXPos = xPos;
			break;
		case OESTE:
			nextXPos = xPos -1;
			nextYPos = yPos;
			break;
		case ESTE:
			nextXPos = xPos +1;
			nextYPos = yPos;
			break;
		default:
			nextXPos = xPos;
			nextYPos = yPos;
		}


		//		Para a mesma equipa
		String type = tipo.toLowerCase();
		switch(type){
		case CAVALEIRO:
			return isCavalo(equipa,nextXPos,nextYPos);
		case LANCEIRO:
			return isLanceiro(equipa, nextXPos, nextYPos);
		case ESPADACHIM:
			return isEspadachim(equipa, nextXPos, nextYPos);
		default: 
			return "";
		}
	}

	private String isEspadachim(int equipa, int nextXPos, int nextYPos) {

		Team outra;
		if(equipa ==1 ){
			outra = team2;
		}else{
			outra = team1;
		}
		Character lanceiro, espadachim;
		lanceiro = outra.getCharacter(LANCEIRO);
		espadachim = outra.getCharacter(ESPADACHIM);

		if((nextXPos == lanceiro.getX() && nextYPos == lanceiro.getY())){
			return "lanceiro";
		}
		else if((nextXPos == espadachim.getX() && nextYPos == espadachim.getY())){
			return "espadachim";
		} else
			return "";

	}

	private String isLanceiro(int equipa, int nextXPos, int nextYPos) {
		Team outra;
		if(equipa ==1 ){
			outra = team2;
		}else{
			outra = team1;
		}

		Character lanceiro, cavaleiro;
		lanceiro = outra.getCharacter(LANCEIRO);
		cavaleiro = outra.getCharacter(CAVALEIRO);

		if((nextXPos == lanceiro.getX() && nextYPos == lanceiro.getY())){
			return "lanceiro";
		}
		else if((nextXPos == cavaleiro.getX() && nextYPos == cavaleiro.getY())){
			return "cavaleiro";
		} else
			return "";
	}

	private String isCavalo(int equipa, int nextXPos, int nextYPos) {
		Team outra;
		if(equipa ==1 ){
			outra = team2;
		}else{
			outra = team1;
		}
		Character cavaleiro, espadachim;
		cavaleiro = outra.getCharacter(CAVALEIRO);
		espadachim = outra.getCharacter(ESPADACHIM);

		if((nextXPos == espadachim.getX() && nextYPos == espadachim.getY() )){
			return "espadachim";
		}
		else if((nextXPos == cavaleiro.getX() && nextYPos == cavaleiro.getY())){
			return "cavaleiro";

		} else
			return "";

	}
}
