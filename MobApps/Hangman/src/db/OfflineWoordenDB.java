package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OfflineWoordenDB implements WoordenDB, Serializable {
	
	private Map<String, List<String>> woorden;
	private XmlReader xmlReader;
	
	public OfflineWoordenDB(){
		//woorden = new HashMap<String, List<String>>();
		xmlReader = new XmlReader();
		woorden = xmlReader.getWords();
		/*this.addWord("dieren", "aap");
		this.addWord("dieren", "poes");
		this.addWord("dieren", "hondje");
		this.addWord("auto's", "lamborghini");
		this.addWord("auto's", "ford");
		this.addWord("auto's", "ferrari");
		this.addWord("auto's", "subaru");
		this.addWord("landen", "nederland");
		this.addWord("landen", "djibouti");
		this.addWord("landen", "canada");
		this.addWord("landen", "jamaica");*/
	}

	@Override
	public Set<String> getCategories() {
		return woorden.keySet();
	}

	@Override
	public String getWord(String category) {
		List<String> woordjes = woorden.get(category);
		Collections.shuffle(woordjes);
		return woordjes.get(0);
	}

	@Override
	public void addCategory(String category) {
		woorden.put(category, new ArrayList<String>());
	}

	@Override
	public void addWord(String category, String woord) {
		if(!woorden.containsKey(category)){
			addCategory(category);
		}
		woorden.get(category).add(woord);
	}

}
