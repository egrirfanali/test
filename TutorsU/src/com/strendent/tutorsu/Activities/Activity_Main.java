package com.strendent.tutorsu.Activities;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.Settings;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.TutorsUApplication;

public class Activity_Main extends Activity {

	private Button btnPhoneNo;
	private Button btnFb;
	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__main);

		findViewByIds();
		
		String  string=Settings.getApplicationSignature(getApplicationContext());
//		
//		ParseObject testObject = new ParseObject("TestObject");
//		testObject.put("foo", "bar");
//		testObject.saveInBackground();

		btnPhoneNo.setOnClickListener(btnClickListener);
		btnFb.setOnClickListener(btnClickListener);

		ParseUser currentUser = ParseUser.getCurrentUser();
		if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
			// Go to the user info activity
			showUserDetailsActivity();
		}
	}


	public void onFbClick() {
		progressDialog = ProgressDialog.show(Activity_Main.this, "", "Logging in...", true);

		List<String> permissions = Arrays.asList("public_profile", "email", "user_friends");
		// NOTE: for extended permissions, like "user_about_me", your app must be reviewed by the Facebook team
		// (https://developers.facebook.com/docs/facebook-login/permissions/)

		ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException err) {
				progressDialog.dismiss();
				if (user == null) {
					Log.d(TutorsUApplication.TAG, "Uh oh. The user cancelled the Facebook login.");
				} else if (user.isNew()) {
					
//					ParseObject testObject = new ParseObject("User");
//					testObject.put("foo", "bar");
//					testObject.saveInBackground();
					Log.d(TutorsUApplication.TAG, "User signed up and logged in through Facebook!");
					showUserDetailsActivity();
				} else {
					
					Log.d(TutorsUApplication.TAG, "User logged in through Facebook!");
					showUserDetailsActivity();
				}
			}
		});
	}

	private void showUserDetailsActivity() {
		 Intent intent = new Intent(this, UserDetailsActivity.class);
		startActivity(intent);
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}


	OnClickListener btnClickListener=new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.btnPhoneNo:


				break;
			case R.id.btnFb:

				onFbClick();

				break;

			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__main, menu);
		return true;
	}
	
	


	/* 
	 * Setting references for the views
	 * */
	private void findViewByIds(){
		btnPhoneNo=(Button)findViewById(R.id.btnPhoneNo);
		btnFb=(Button)findViewById(R.id.btnFb);
	}



}
