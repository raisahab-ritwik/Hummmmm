package com.seva60plus.hum.specialoffers;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
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
import com.seva60plus.hum.util.ConnectionDetector;
import com.seva60plus.hum.util.SocialNetworking;
import com.seva60plus.hum.util.Util;

public class OffersListActivity extends Activity {

	private LinearLayout ll_elderCare, ll_medicalCare, ll_funeralService;

	private RelativeLayout backBtn;
	private ImageView menuIcon;
	private ImageView backBtnSub;
	private Button whatsapp, fb, twitter;
	// ---by Dibyendu
	// flag for Internet connection status
	private Boolean isInternetPresent = false;

	// Connection detector class
	private ConnectionDetector cd;
	private LocationManager lm;

	// ------End

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offers_dashboard);

		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);// by Dibyendu

		whatsapp = (Button) findViewById(R.id.whatsapp_btn);
		fb = (Button) findViewById(R.id.facebook_btn);
		twitter = (Button) findViewById(R.id.twitter_btn);

		whatsapp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SocialNetworking.shareAppLinkViaWhatsApp(OffersListActivity.this);
			}
		});

		fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SocialNetworking.shareAppLinkViaFacebook(OffersListActivity.this);
			}
		});

		twitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SocialNetworking.shareAppLinkViaTwitter(OffersListActivity.this);
			}
		});

		backBtnSub = (ImageView) findViewById(R.id.iv_back);
		backBtnSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(0, 0);
			}
		});

		ll_elderCare = (LinearLayout) findViewById(R.id.ll_elderCare);
		ll_elderCare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(OffersListActivity.this, OfferElderActivity.class);
				startActivity(i);

			}
		});

		ll_medicalCare = (LinearLayout) findViewById(R.id.ll_medicalCare);
		ll_medicalCare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(OffersListActivity.this, OfferMedicalActivity.class);
				startActivity(i);

			}
		});

		ll_funeralService = (LinearLayout) findViewById(R.id.ll_funeralService);
		ll_funeralService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(OffersListActivity.this, OfferFuneralActivity.class);
				startActivity(i);
			}
		});

		findViewById(R.id.llMenu).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intObj = new Intent(OffersListActivity.this, MenuLay.class);
				startActivity(intObj);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

			}
		});

		backBtn = (RelativeLayout) findViewById(R.id.back_settings);

		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (Util.isInternetAvailable(OffersListActivity.this)) {

					Intent i = new Intent(OffersListActivity.this, HumDetailsView.class);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_leave_right);
				} else {
					Intent i = new Intent(OffersListActivity.this, NoInternetPage.class);
					startActivity(i);
					overridePendingTransition(0, 0);
				}
			}
		});

	}

	public void onHomeClick(View v) {
		startActivity(new Intent(OffersListActivity.this, DashboardActivity.class));
		finish();
	}
}