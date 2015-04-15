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

public class LoginAsyncTask extends AsyncTask<Void, Void, Void> {
	private ParentActivity activity;
	private String email, password, url,response;
	private Context context;

	public LoginAsyncTask(Context context, ParentActivity parentActivity,
			String email, String password, String url) {
		this.context = context;
		this.activity = parentActivity;
		this.email = email;
		this.password = password;
		this.url = url;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		activity.showProgressBar();
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			
			list.add(new BasicNameValuePair(AppConstant.username, email));
			list.add(new BasicNameValuePair(AppConstant.password, password));
			list.add(new BasicNameValuePair(AppConstant.device_token, "12313123134431243432"));
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
				JSONObject jsonObject2=jsonObject.getJSONObject("list");
				
				activity.onResponseRecieve1(jsonObject2);

			} else {
				activity.onErrorRecieve("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
