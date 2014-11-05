package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import khleuven.mobile.KHLeuvenMobileException;

public class HangmanGame implements Serializable {
	int aantalPogingen;
	int fout;
	int limietFout = 6;
	GameState gameState;
	String woord;
	String voortgang;
	String categorie;
	List<Character> gebruikteLetters;
	

	public HangmanGame(String woord) {
		setWoord(woord.toLowerCase());
		generateVoortgang(woord);
		aantalPogingen = 0;
		fout = 0;
		gebruikteLetters = new ArrayList<Character>();
		setGameState(GameState.STARTED);
	}

	public void generateVoortgang(String woord) {
		voortgang = "";
		for (int i = 0; i < woord.length(); i++) {
			voortgang += "*";
		}
	}

	public void guess(char c) {
		c = Character.toLowerCase(c);
		if (getGameState() == GameState.STARTED && !gebruikteLetters.contains(c)) {

			boolean gevonden = false;
			aantalPogingen++;			
			gebruikteLetters.add(c);
			char[] temp = voortgang.toCharArray();
			voortgang = "";
			for (int i = 0; i < woord.length(); i++) {
				if (woord.charAt(i) == c) {
					voortgang += c;
					gevonden = true;
				} else {
					voortgang += temp[i];
				}
			}

			if (!gevonden) {
				fout++;
				if (fout == limietFout) {
					setGameState(GameState.LOST);
				}
			} else if (voortgang.equals(woord)) {
				setGameState(GameState.WON);
			}
		}

	}

	public int getAantalPogingen() {
		return aantalPogingen;
	}

	public void setAantalPogingen(int aantalPogingen) {
		this.aantalPogingen = aantalPogingen;
	}

	public String getWoord() {
		return woord;
	}

	public void setWoord(String woord) {
		this.woord = woord;
	}

	public String getVoortgang() {
		return voortgang;
	}

	public void setVoortgang(String voortgang) {
		this.voortgang = voortgang;
	}

	public List<Character> getGebruikteLetters() {
		return gebruikteLetters;
	}

	public void setGebruikteLetters(List<Character> gebruikteLetters) {
		this.gebruikteLetters = gebruikteLetters;
	}

	public int getFout() {
		return fout;
	}

	public void setFout(int fout) {
		this.fout = fout;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public int getLimietFout() {
		return limietFout;
	}

	public void setLimietFout(int limietFout) {
		this.limietFout = limietFout;
	}
	
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


}
