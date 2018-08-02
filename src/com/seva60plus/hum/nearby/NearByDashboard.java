package com.seva60plus.hum.nearby;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.seva60plus.hum.R;
import com.seva60plus.hum.activity.DashboardActivity;
import com.seva60plus.hum.activity.HumDetailsView;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.custom.NoInternetPage;
import com.seva60plus.hum.custom.ProgressHUD;
import com.seva60plus.hum.custom.SlowInternetPage;
import com.seva60plus.hum.util.GPSTracker;
import com.seva60plus.hum.util.SocialNetworking;
import com.seva60plus.hum.util.Util;

public class NearByDashboard extends Activity implements OnCancelListener {

	private LinearLayout atm_btn, hospital_btn, bank_btn, police_btn, restaurant_btn, shopping_btn;
	private RelativeLayout backBtn;
	private Button whatsapp, fb, twitter;
	GPSTracker gpsTracker;
	ProgressHUD mProgressHUD;
	private Double myLate, myLang;
	public static final String MY_PREFS_NAME = "MyPrefsFile";
	private RelativeLayout helpLay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby_dashboard);

		whatsapp = (Button) findViewById(R.id.whatsapp_btn);
		fb = (Button) findViewById(R.id.facebook_btn);
		twitter = (Button) findViewById(R.id.twitter_btn);

		whatsapp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SocialNetworking.shareAppLinkViaWhatsApp(NearByDashboard.this);
			}
		});

		fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SocialNetworking.shareAppLinkViaFacebook(NearByDashboard.this);
			}
		});

		twitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SocialNetworking.shareAppLinkViaTwitter(NearByDashboard.this);
			}
		});

		// ------START------------For Progress Spinner--------------
		mProgressHUD = ProgressHUD.show(NearByDashboard.this, "Please wait...", true, false, this);

		gpsTracker = new GPSTracker(this);

		if (gpsTracker.getLatitude() == 0.0 || gpsTracker.getLongitude() == 0.0) {

		} else {

			myLate = gpsTracker.getLatitude();
			myLang = gpsTracker.getLongitude();

			mProgressHUD.dismiss();
			System.out.println("My Location:" + myLate + " : " + myLang);

		}

		// ----------------Loder timing Check-------26.08.15

		if (mProgressHUD.isShowing()) {
			// is running
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {

					// not getting-----!
					if (mProgressHUD.isShowing()) {
						System.out.println("Pro=====1");
						mProgressHUD.dismiss();
						// Toast.makeText(getApplicationContext(),
						// "Please try again", Toast.LENGTH_SHORT).show();

						Intent intObj = new Intent(NearByDashboard.this, SlowInternetPage.class);
						startActivity(intObj);
						overridePendingTransition(0, 0);
					} else {

					}

				}
			}, 15000);
		} else {
			// ----Getit Done
			System.out.println("Pro=====2");
		}

		// =============== -------end Loader timing ====26.08.15

		findViewById(R.id.llMenu).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intObj = new Intent(NearByDashboard.this, MenuLay.class);
				startActivity(intObj);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

				// finish();
			}
		});

		backBtn = (RelativeLayout) findViewById(R.id.back_settings);

		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (Util.isInternetAvailable(NearByDashboard.this)) {
					Intent i = new Intent(NearByDashboard.this, HumDetailsView.class);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_leave_right);
				} else {
					Intent i = new Intent(NearByDashboard.this, NoInternetPage.class);
					startActivity(i);
					overridePendingTransition(0, 0);
				}

			}
		});

		ImageView backBtnSub = (ImageView) findViewById(R.id.iv_back);
		backBtnSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(0, 0);
			}
		});

		helpLay = (RelativeLayout) findViewById(R.id.helpLay);
		helpLay.setVisibility(View.VISIBLE);
		helpLay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intObj = new Intent(NearByDashboard.this, HelpNearbyPage.class);
				startActivity(intObj);
			}
		});

		SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
		editor.putString("prefMyLate", String.valueOf(myLate));
		editor.putString("prefMyLang", String.valueOf(myLang));
		editor.commit();

		// -------------------------------------------------------------------------------
		atm_btn = (LinearLayout) findViewById(R.id.atm_btn);
		atm_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("My Location:" + myLate + " : " + myLang);

				Intent intObj = new Intent(NearByDashboard.this, MainActivityMap.class);
				intObj.putExtra("myLate", String.valueOf(myLate));
				intObj.putExtra("myLang", String.valueOf(myLang));
				intObj.putExtra("iChoice", "atm");
				startActivity(intObj);
				mProgressHUD.dismiss();

			}
		});

		bank_btn = (LinearLayout) findViewById(R.id.bank_btn);
		bank_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("My Location:" + myLate + " : " + myLang);

				Intent intObj = new Intent(NearByDashboard.this, MainActivityMap.class);
				intObj.putExtra("myLate", String.valueOf(myLate));
				intObj.putExtra("myLang", String.valueOf(myLang));
				intObj.putExtra("iChoice", "bank");
				startActivity(intObj);
				mProgressHUD.dismiss();

			}
		});

		hospital_btn = (LinearLayout) findViewById(R.id.hospital_btn);
		hospital_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("My Location:" + myLate + " : " + myLang);

				Intent intObj = new Intent(NearByDashboard.this, MainActivityMap.class);
				intObj.putExtra("myLate", String.valueOf(myLate));
				intObj.putExtra("myLang", String.valueOf(myLang));
				intObj.putExtra("iChoice", "hospital");
				startActivity(intObj);
				mProgressHUD.dismiss();

			}
		});

		police_btn = (LinearLayout) findViewById(R.id.police_btn);
		police_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("My Location:" + myLate + " : " + myLang);

				Intent intObj = new Intent(NearByDashboard.this, MainActivityMap.class);
				intObj.putExtra("myLate", String.valueOf(myLate));
				intObj.putExtra("myLang", String.valueOf(myLang));
				intObj.putExtra("iChoice", "police");
				startActivity(intObj);
				mProgressHUD.dismiss();

			}
		});

		restaurant_btn = (LinearLayout) findViewById(R.id.resturant_btn);
		restaurant_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("My Location:" + myLate + " : " + myLang);

				Intent intObj = new Intent(NearByDashboard.this, MainActivityMap.class);
				intObj.putExtra("myLate", String.valueOf(myLate));
				intObj.putExtra("myLang", String.valueOf(myLang));
				intObj.putExtra("iChoice", "food");
				startActivity(intObj);
				mProgressHUD.dismiss();

			}
		});

		shopping_btn = (LinearLayout) findViewById(R.id.shopping_btn);
		shopping_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("My Location:" + myLate + " : " + myLang);

				Intent intObj = new Intent(NearByDashboard.this, MainActivityMap.class);
				intObj.putExtra("myLate", String.valueOf(myLate));
				intObj.putExtra("myLang", String.valueOf(myLang));
				intObj.putExtra("iChoice", "shopping_mall");
				startActivity(intObj);
				mProgressHUD.dismiss();

			}
		});

	}

	public void onHomeClick(View v) {
		startActivity(new Intent(NearByDashboard.this, DashboardActivity.class));
		finish();
	}

	@Override
	public void onCancel(DialogInterface arg0) {
		// TODO Auto-generated method stub

	}
}
