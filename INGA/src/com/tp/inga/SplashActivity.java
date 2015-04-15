package com.tp.inga;

import com.common.Utilites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity {
	Thread thread;
	private Utilites utilites;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		finId();
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					thread.sleep(2 * 1000);
					Intent intent = new Intent(SplashActivity.this,
							HomeActivity.class);
					startActivity(intent);
					finish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();

	}

	private void finId() {
		utilites = new Utilites(SplashActivity.this);
		if (utilites.getLoggedId()) {
			Utilites.setIntent(SplashActivity.this, HomeActivity.class, true);
		} else {
			Utilites.setIntent(SplashActivity.this, LoginActivity.class, true);

		}
	}

}
