package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import db.OfflineWoordenDB;
import db.OnlineWoordenDB;
import db.WoordenDB;

public class GameFacadeImpl implements GameFacade, Serializable {
	private HangmanGame game;
	private WoordenDB woordenDB;
	
	public GameFacadeImpl(boolean online){
		if(online){
			woordenDB = new OnlineWoordenDB();			
		} else {
			woordenDB = new OfflineWoordenDB();
		}
	}
	
	@Override
	public void createGame(String category){
		setGame(new HangmanGame(woordenDB.getWord(category)));
		game.setCategorie(category);
	}
	
	public void setGame(HangmanGame game){
		this.game = game;
	}

	@Override
	public int getAantalPogingen() {
		return game.getAantalPogingen();
	}

	@Override
	public List<Character> getGebruikteLetters() {
		return game.getGebruikteLetters();
	}

	@Override
	public String getWoord() {
		return game.getWoord();
	}

	@Override
	public String getVoortgang() {
		return game.getVoortgang();
	}
	
	@Override
	public void guess(char c){
		game.guess(c);
	}
	
	@Override
	public int getFout(){
		return game.getFout();
	}
	
	@Override
	public Set<String> getCategories(){
		return woordenDB.getCategories();
	}

	@Override
	public GameState getGameState() {
		return game.getGameState();
	}

	@Override
	public String getCategory() {
		return game.getCategorie();
	}
	
}
