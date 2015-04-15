package com.tp.inga;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.common.AppConstant;
import com.common.ParentActivity;
import com.common.Utilites;
import com.network.LoginAsyncTask;

public class LoginActivity extends ParentActivity {
	ParentActivity parentActivity;
	private EditText edtEmail, edtPassword;
	private Utilites utilites;
	private Context context;
	private RelativeLayout relLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findId();
	}

	private void findId() {
		context = LoginActivity.this;
		utilites = new Utilites(context);
		edtEmail = (EditText) findViewById(R.id.edt_email);
		relLogin = (RelativeLayout) findViewById(R.id.rel_login);
		edtPassword = (EditText) findViewById(R.id.edt_password);
		parentActivity = this;
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		finish();
	}

	
	
	public void onClickSubmit(View view) {
		if (edtEmail.getText().toString().equals("")
				|| edtPassword.getText().toString().equals("")) {
			Utilites.showToast(context, AppConstant.emptyFields);
		} else if (!(Utilites.emailValidation(edtEmail.getText().toString()))) {
			Utilites.showToast(context, AppConstant.emailValidation);
		} else {
			new LoginAsyncTask(context, parentActivity, edtEmail.getText()
					.toString(), edtPassword.getText().toString(),
					getResources().getString(R.string.app_url)
							+ AppConstant.loginUrl).execute();
		}

	}

	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:void description-:method
	 * call when get response from server
	 **/
	@Override
	public void onResponseRecieve1(JSONObject msg) {

		super.onResponseRecieve1(msg);
		try {
			utilites.saveLoggedInfo(
					Integer.parseInt(msg.getJSONObject(AppConstant.user)
							.getString(AppConstant.loggedId)),
					msg.getJSONObject(AppConstant.user).getString(
							AppConstant.loggedUsername),
					msg.getJSONObject(AppConstant.user).getString(
							AppConstant.loggedEmail),
					msg.getJSONObject(AppConstant.user).getString(
							AppConstant.loggedDeviceToken));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Utilites.setIntent(LoginActivity.this, HomeActivity.class, true);

	}

	/**
	 * author-:Manpreet Singh date-:15-4-15 return-:void description-:method
	 * call when get error in response
	 **/
	@Override
	public void onErrorRecieve(String msg) {

		super.onErrorRecieve(msg);
		Utilites.showToast(LoginActivity.this, msg);

	}
}
