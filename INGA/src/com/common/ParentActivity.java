package com.common;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ParentActivity extends Activity {
	GoogleCloudMessaging cloudMessaging;  
	public static CustomProgressDialog customProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}
	public ParentActivity() {
		activity = this;

	}

	@Override
	protected void onStart() {
		super.onStart();
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();

		try {
			if (customProgressDialog != null) {
				customProgressDialog.setCancelable(true);
				customProgressDialog.dismiss();
				customProgressDialog = null;
			}
		} catch (Exception e) {

		}
	}

	public void showProgressBar() {
		try {
			Log.e("Inside Progress dialog", "wow");
			if (customProgressDialog == null) {
				customProgressDialog = new CustomProgressDialog(this);
				customProgressDialog.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showProgressBar(Boolean isCancelable) {
		try {
			if (customProgressDialog == null) {
				customProgressDialog = new CustomProgressDialog(this);
				customProgressDialog.show();
				customProgressDialog.setCancelable(isCancelable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dismissProgressBar() {
		try {
			if (customProgressDialog != null) {
				customProgressDialog.setCancelable(true);
				customProgressDialog.dismiss();
				customProgressDialog = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
