package com.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.common.AppConstant;
import com.common.ParentActivity;

public class RegisterationAsyncTask extends AsyncTask<Void, Void, Void> {
	private String url = null, username = null, password = null, email = null,
			firstname = null, lastname = null, device_token = null;
	private Context context;
	private ParentActivity activity;
	private String response;

	public RegisterationAsyncTask(Context context,ParentActivity parentactivity, String _url,
			String username, String email, String password, String first_name,
			String last_name, String device_token) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = first_name;
		this.lastname = device_token;
		this.device_token = username;

		this.url = _url;
		this.context = context;
		this.activity = parentactivity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		activity.showProgressBar();

	}

	@Override
	protected Void doInBackground(Void... params) {

		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair(AppConstant.username, username));
			list.add(new BasicNameValuePair(AppConstant.email, email));
			list.add(new BasicNameValuePair(AppConstant.password, password));
			list.add(new BasicNameValuePair(AppConstant.first_name, firstname));
			list.add(new BasicNameValuePair(AppConstant.last_name, lastname));
			list.add(new BasicNameValuePair(AppConstant.device_token,
					device_token));
			httpPost.setEntity(new UrlEncodedFormEntity(list));
			HttpResponse httpResponse=client.execute(httpPost);
			response=EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		activity.dismissProgressBar();
		try {
			JSONObject jsonObject = new JSONObject(response);
			String error = jsonObject.getString(AppConstant.error);
			String msg = jsonObject.getString(AppConstant.message);
			if (error.equals(AppConstant.errorCode)) {
				activity.onResponseRecieve(msg);

			} else {
				activity.onErrorRecieve(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
