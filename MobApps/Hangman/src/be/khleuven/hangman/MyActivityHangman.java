package be.khleuven.hangman;



import model.GameState;
import model.HangmanGame;
import model.GameFacade;
import model.GameFacadeImpl;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivityHangman extends ActionBarActivity {
	
	private TextView opgaveVeld, gebruikteLettersVeld, categoryVeld;
	private ImageView imageView;
	private EditText inputVeld;
	private GameFacade gameFacade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_activity_hangman);
		gameFacade = (GameFacade)getIntent().getSerializableExtra("facade");
		inputVeld = (EditText) findViewById(R.id.invoer);
		opgaveVeld = (TextView) findViewById(R.id.Opgave);
		categoryVeld = (TextView) findViewById(R.id.Category);
		categoryVeld.setText(categoryVeld.getText()+ " " + gameFacade.getCategory());
		imageView = (ImageView) findViewById(R.id.Image);
		gebruikteLettersVeld =  (TextView) findViewById(R.id.GebruikteLetters);
		update();	
	}
	
	public void guess(View view){
		String inputText = inputVeld.getText().toString();
		if(!inputText.matches("")){
			char input = inputText.charAt(0);
			gameFacade.guess(input);
			update();
		}

	}
	
	private void setGebruikteLetters(){
		String gebruikteText = "Used characters: ";
		for(char c: gameFacade.getGebruikteLetters()){
			gebruikteText += " " + c;
		}
		gebruikteLettersVeld.setText(gebruikteText);
	}
	
	private void setImage(int aantalFout){
		if(aantalFout == 1){
			imageView.setImageResource(R.drawable.hang1);
		} else if(aantalFout == 2){
			imageView.setImageResource(R.drawable.hang2);
		} else if(aantalFout == 3){
			imageView.setImageResource(R.drawable.hang3);
		} else if(aantalFout == 4){
			imageView.setImageResource(R.drawable.hang4);
		} else if(aantalFout == 5){
			imageView.setImageResource(R.drawable.hang5);
		} else if(aantalFout == 6){
			imageView.setImageResource(R.drawable.hang6);
		} else {
			imageView.setImageResource(R.drawable.hang0);
		}
	}
	
	public void update(){
		opgaveVeld.setText(gameFacade.getVoortgang());
		setImage(gameFacade.getFout());
		setGebruikteLetters();
		inputVeld.setText("");
		checkIfGameOver();
	}
	
	public void checkIfGameOver(){
		if(gameFacade.getGameState() != GameState.STARTED){ 
			opgaveVeld.setText(gameFacade.getWoord());
			if(gameFacade.getGameState() == GameState.WON){
				imageView.setImageResource(R.drawable.winning);				
			} else{
				imageView.setImageResource(R.drawable.gameover);
			}
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {

					Intent i = new Intent(MyActivityHangman.this, CategoryOverview.class);
					i.putExtra("facade", gameFacade);
					startActivity(i);
					finish();
	 
				}
			}, 3000);
		}
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_activity_hangman, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
