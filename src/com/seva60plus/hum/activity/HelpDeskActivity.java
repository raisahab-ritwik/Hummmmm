package com.seva60plus.hum.activity;

import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seva60plus.hum.R;

public class HelpDeskActivity extends Activity {

	Button close;

	TextView phoneText, phoneText2, webText, emailText, facebookText, twitterText, nameText;
	RelativeLayout webLay, emailLay, facebookLay;
	Button callBtn, call2Btn, visitBtn, sendBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help_desk);

		Typeface font = Typeface.createFromAsset(getAssets(), "openSansRegular.ttf");

		phoneText = (TextView) findViewById(R.id.phone_text);

		phoneText.setTypeface(font);

		phoneText2 = (TextView) findViewById(R.id.phone_text4);

		phoneText2.setTypeface(font);

		call2Btn = (Button) findViewById(R.id.call_icon4copy);

		call2Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String phNo = "+919836533649";

				try {
					Intent my_callIntent = new Intent(Intent.ACTION_CALL);
					my_callIntent.setData(Uri.parse("tel:" + phNo));
					//here the word 'tel' is important for making a call...
					startActivity(my_callIntent);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(getApplicationContext(), "Error in your phone call" + e.getMessage(), Toast.LENGTH_LONG).show();
				}

			}
		});

		webText = (TextView) findViewById(R.id.TextView01);
		webText.setTypeface(font);
		webLay = (RelativeLayout) findViewById(R.id.RelativeLayout01);
		visitBtn = (Button) findViewById(R.id.Button01);

		webLay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.seva60plus.co.in"));
				startActivity(i);

			}
		});
		visitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.seva60plus.co.in"));
				startActivity(i);

			}
		});

		emailText = (TextView) findViewById(R.id.web_text);
		emailText.setText("support@seva60plus.co.in");
		emailText.setTypeface(font);
		emailLay = (RelativeLayout) findViewById(R.id.website_text_lay);
		sendBtn = (Button) findViewById(R.id.web_text_icon2);

		emailLay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "support@seva60plus.co.in" });
				final PackageManager pm = getPackageManager();
				final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
				ResolveInfo best = null;
				for (final ResolveInfo info : matches)
					if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
						best = info;
				if (best != null)
					intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
				startActivity(intent);

			}
		});
		sendBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "support@seva60plus.co.in" });
				final PackageManager pm = getPackageManager();
				final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
				ResolveInfo best = null;
				for (final ResolveInfo info : matches)
					if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
						best = info;
				if (best != null)
					intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
				startActivity(intent);

			}
		});

		facebookText = (TextView) findViewById(R.id.distance_text);
		facebookText.setText("www.facebook.com/Seva60Plus");
		facebookText.setTypeface(font);
		facebookLay = (RelativeLayout) findViewById(R.id.distance_text_lay);

		facebookLay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/Seva60Plus"));
				startActivity(i);

			}
		});

		nameText = (TextView) findViewById(R.id.name_text);

		close = (Button) findViewById(R.id.close);
		close.setTypeface(font);
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Take action.
				finish();

			}
		});

	}

	public void onKitchenClick(View v) {

		String phNo = "+918230078523";

		try {
			Intent my_callIntent = new Intent(Intent.ACTION_CALL);
			my_callIntent.setData(Uri.parse("tel:" + phNo));
			//here the word 'tel' is important for making a call...
			startActivity(my_callIntent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(getApplicationContext(), "Error in your phone call" + e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	public void onSparshClick(View v) {

		String phNo = "+919836533649";

		try {
			Intent my_callIntent = new Intent(Intent.ACTION_CALL);
			my_callIntent.setData(Uri.parse("tel:" + phNo));
			//here the word 'tel' is important for making a call...
			startActivity(my_callIntent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(getApplicationContext(), "Error in your phone call" + e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	public void onCareClick(View v) {

		String phNo = "+918230078529";

		try {
			Intent my_callIntent = new Intent(Intent.ACTION_CALL);
			my_callIntent.setData(Uri.parse("tel:" + phNo));
			//here the word 'tel' is important for making a call...
			startActivity(my_callIntent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(getApplicationContext(), "Error in your phone call" + e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}