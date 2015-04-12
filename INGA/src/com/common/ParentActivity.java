package com.common;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ParentActivity extends Activity {
	GoogleCloudMessaging cloudMessaging;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	
}
