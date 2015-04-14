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

import com.common.AppConstant;
import com.common.ParentActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class RegisterationAsyncTask extends AsyncTask<Void, Void, Void> {
	String email, name, firstname, lastname, password, url, device_token,
			response;
	private Context context;
	private ParentActivity activity;

	public RegisterationAsyncTask(Context context,
			ParentActivity parentActivity, String url, String email,
			String username, String password, String first_name,
			String last_name, String deviceToken) {
		this.context = context;
		this.url = url;
		this.name = username;
		this.email = email;
		this.password = password;
		this.firstname = first_name;
		this.lastname = last_name;
		this.device_token = deviceToken;
		activity = parentActivity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// activity.showProgressBar();
	}

	@Override
	protected Void doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair(AppConstant.username, name));
			list.add(new BasicNameValuePair(AppConstant.email, email));
			list.add(new BasicNameValuePair(AppConstant.password, password));
			list.add(new BasicNameValuePair(AppConstant.firstName, firstname));
			list.add(new BasicNameValuePair(AppConstant.lastName, lastname));
			list.add(new BasicNameValuePair(AppConstant.deviceToken,
					device_token));
			httpPost.setEntity(new UrlEncodedFormEntity(list));
			HttpResponse httpResponse = client.execute(httpPost);
			response = EntityUtils.toString(httpResponse.getEntity());
			Log.e("TAG RESPONSE", response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		// activity.dismissProgressBar();
		try {
			JSONObject jsonObject = new JSONObject(response);
			String error = jsonObject.getString(AppConstant.error);
			if (error.equals("0")) {
				activity.onResponseRecieve(jsonObject
						.getString(AppConstant.msg));
			} else if (error.equals("1")) {
				activity.onErrorRecieve(jsonObject.getString(AppConstant.msg));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
