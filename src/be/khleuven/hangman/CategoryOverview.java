package be.khleuven.hangman;

import java.util.List;

import model.GameFacade;
import model.GameFacadeImpl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryOverview extends Activity {
	ListView listView ;
	GameFacade gameFacade;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        
        gameFacade = (GameFacade) getIntent().getSerializableExtra("facade");
        //source: http://androidexample.com/Create_A_Simple_Listview_-_Android_Example/index.php?view=article_discription&aid=65&aaid=90
        
        listView = (ListView) findViewById(R.id.list);
        
        String[] values = new String[gameFacade.getCategories().size()];
        int count = 0;
        for(String s: gameFacade.getCategories()){
        	values[count] = s;
        	count++;
        }
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
            	  String itemValue = (String) listView.getItemAtPosition(position);
            	  gameFacade.createGame(itemValue);
            	  Intent i = new Intent(CategoryOverview.this, MyActivityHangman.class);
            	  i.putExtra("facade", gameFacade);
                  startActivity(i);
             
              }

         }); 
    }

}

