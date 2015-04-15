package com.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class Utilites {
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private Context context;

	public Utilites(Context context) {
		this.context = context;
	}

	
	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:void description-:method
	 * for show toast
	 **/
	public static void showToast(Context context, String msg) {

		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:void description-:method
	 *for hide keyboard
	 **/
	public static void hideKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) view.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

	}
	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:void description-:method
	 * for check email validation format
	 **/
	public static boolean emailValidation(String str) {

		if (str.indexOf("@") > 0 && str.indexOf(".") > 0) {

			return true;

		} else {
			return false;
		}

	}
	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:void description-:method
	 * for save logged user information in sharedprefrence 
	 **/
	public void saveLoggedInfo(int id, String username, String email,
			String deviceToken) {
		preferences = context.getSharedPreferences(AppConstant.prefName, 1);
		editor = preferences.edit();
		editor.putInt(AppConstant.loggedId, id);
		editor.putString(AppConstant.loggedUsername, username);
		editor.putString(AppConstant.loggedDeviceToken, deviceToken);
		editor.putString(AppConstant.loggedEmail, email);
		editor.commit();

	}

	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:boolean description-:method
	 * for check loggedUser id
	 **/
	public boolean getLoggedId() {
		int id = preferences.getInt(AppConstant.loggedId, 0);
		if (id == 0) {
			return false;
		} else {
			return true;
		}

	}
	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:void description-:method
	 * for setError on editText
	 **/
	public static void setError(EditText editText, String msg) {
		editText.setText("");
		editText.setError(msg);
		editText.requestFocus();

	}
	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:void description-:method
	 * for intent
	 **/
	public static final void setIntent(Activity activity, Class class1,
			boolean flag) {
		Intent intent = new Intent(activity, class1);
		activity.startActivity(intent);
		if (flag) {
			activity.finish();
		}

	}

}
