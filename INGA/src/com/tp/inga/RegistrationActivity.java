package com.tp.inga;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.common.AppConstant;
import com.common.ParentActivity;
import com.common.Utilites;
import com.network.RegisterationAsyncTask;

public class RegistrationActivity extends ParentActivity {
	private EditText edt_name, edt_email, edt_pasasword, edt_firstname,
			edt_lastname, edt_confirm_pass;
	private Button btn_submit;
	ParentActivity parentActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regitration);

		findId();
	}

	private void findId() {
		edt_name = (EditText) findViewById(R.id.edt_username);
		edt_firstname = (EditText) findViewById(R.id.edt_first_name);
		edt_lastname = (EditText) findViewById(R.id.edt_last_name);
		edt_pasasword = (EditText) findViewById(R.id.edt_password);
		edt_email = (EditText) findViewById(R.id.edt_email);
		edt_confirm_pass = (EditText) findViewById(R.id.edt_confirm_pass);
		btn_submit = (Button) findViewById(R.id.btn_submit);

		parentActivity = this;
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
	}

	/**
	 * author-:Manpreet Singh date-:14-4-15 return-:void description-:method
	 * call on click submit button
	 **/
	public void submitRequest(View view) {
		Utilites.hideKeyboard(view);
		if (edt_email.getText().toString().equals("")
				|| edt_name.getText().toString().equals("")

				|| edt_pasasword.getText().toString().equals("")) {
			Utilites.showToast(this, AppConstant.emptyValidation);
		} else if (!(Utilites.emailValidation(edt_email.getText().toString()))) {
			Utilites.setError(edt_email, AppConstant.emailValidation);
		} else if (edt_confirm_pass.getText().toString()
				.equals(edt_pasasword.getText().toString())) {
			Utilites.setError(edt_confirm_pass,
					AppConstant.confirmPasswordValidation);
		}

		else {
			String url = getResources().getString(R.string.app_url)
					+ "app_userregistration";
			new RegisterationAsyncTask(RegistrationActivity.this,
					parentActivity, url, edt_name.getText().toString(),
					edt_email.getText().toString(), edt_pasasword.getText()
							.toString(), edt_firstname.getText().toString(),
					edt_lastname.getText().toString(), "11234123123123121")
					.execute();
		}

	}

	/**
	 * author-:Manpreet Singh date-:14-4-15 return-:void description-:method
	 * call when get response from server
	 **/
	@Override
	public void onResponseRecieve(String msg) {

		super.onResponseRecieve(msg);
		Utilites.showToast(RegistrationActivity.this, msg);
		Utilites.setIntent(RegistrationActivity.this, LoginActivity.class, true);

	}

	/**
	 * author-:Manpreet Singh date-:14-4-15 return-:void description-:method
	 * call when get error in response
	 **/
	@Override
	public void onErrorRecieve(String msg) {

		super.onErrorRecieve(msg);
		Utilites.showToast(RegistrationActivity.this, msg);

	}

}
