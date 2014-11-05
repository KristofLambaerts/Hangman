package db;

import java.util.List;
import java.util.Set;

public interface WoordenDB {
	public Set<String> getCategories();
	
	public String getWord(String category);
	
	public void addCategory(String category);
	
	public void addWord(String category, String woord);
}
