package be.khleuven.hangman;

import model.GameFacade;
import model.GameFacadeImpl;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {
	GameFacade gameFacade;

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		boolean isConnected = false;
		if(ni != null){
			isConnected = ni.isConnected();			
		}

		gameFacade = new GameFacadeImpl(isConnected);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent i = new Intent(SplashScreen.this, CategoryOverview.class);
				i.putExtra("facade", gameFacade);
				startActivity(i);
				finish();
 
			}
		}, SPLASH_TIME_OUT);
	}

}



