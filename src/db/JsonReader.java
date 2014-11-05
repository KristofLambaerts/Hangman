package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

//source: http://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
//source: http://stackoverflow.com/questions/8805802/parsing-json-string-from-url-restful-webservice-using-gson-libraries-android
public class JsonReader implements Serializable {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;

        try{
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader (url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[]chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }
}