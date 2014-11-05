package db;

import java.io.Serializable;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class OnlineWoordenDB implements WoordenDB, Serializable {
	private OfflineWoordenDB offlineDB;

	public OnlineWoordenDB() {
		offlineDB = new OfflineWoordenDB();
		//offlineDB.addWord("Informatica", "test");
		//source: http://stackoverflow.com/questions/8939250/parsing-json-file-java
		String jsonURL = "https://u0047590.webontwerp.khleuven.be/php/fetchGuessWords.php";

		try {
			String JsonData = JsonReader.readUrl(jsonURL); //Put the content at the url in a String
			JSONArray rows = new JSONArray(JsonData); //Put each different entry in the array

			for (int i = 0; i < rows.length(); i++) { // Loop over each each row
				JSONObject row = rows.getJSONObject(i); // Get row object
				offlineDB.addWord(row.getString("type"), row.getString("guessword"));
				 
					
				}
		} catch (JSONException e) {
			// JSON Parsing error
			e.printStackTrace();
		} catch (IOException io){
			io.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public Set<String> getCategories() {
		return offlineDB.getCategories();
	}

	@Override
	public String getWord(String category) {
		return offlineDB.getWord(category);
	}

	@Override
	public void addCategory(String category) {
		offlineDB.addCategory(category);

	}

	@Override
	public void addWord(String category, String woord) {
		offlineDB.addWord(category, woord);

	}

}
