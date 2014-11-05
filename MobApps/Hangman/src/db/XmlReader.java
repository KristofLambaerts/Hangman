package db;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlReader implements Serializable {

	public Document configureer(String path) {
		InputStream is = getClass().getResourceAsStream(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(is);
		} catch (SAXException | IOException ex) {
			ex.printStackTrace();
		}
		return doc;
	}

	public HashMap<String, List<String>> getWords() {
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		List<String> temp;
		Document doc = configureer("offlinewoorden.xml");
		NodeList nList = doc.getElementsByTagName("category");
		for (int i = 0; i < nList.getLength(); i++) {
			temp = new ArrayList<String>();
			Node nNode = nList.item(i);
			Element eElement = (Element) nNode;
			NodeList wList = eElement.getElementsByTagName("woord");
			for(int j = 0; j < wList.getLength(); j++){
				Node wNode = wList.item(j);
				Element wElement = (Element) wNode;
				temp.add(wElement.getTextContent());
			}
			result.put(eElement.getElementsByTagName("naam").item(0).getTextContent(), temp);
		}
		return result;
	}
}
