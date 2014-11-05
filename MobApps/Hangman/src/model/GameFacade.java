package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.Serializable;

public interface GameFacade extends Serializable {
	
	public int getAantalPogingen();
	
	public List<Character> getGebruikteLetters();
	
	public String getWoord();
	
	public String getVoortgang();
	
	public int getFout();
	
	public void guess(char c);
	
	public String getCategory();
	
	public Set<String> getCategories();
	
	public GameState getGameState();
	
	public void createGame(String category);
}
