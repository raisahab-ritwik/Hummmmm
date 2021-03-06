package com.seva60plus.hum.wellbeing;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.seva60plus.hum.R;
import com.seva60plus.hum.activity.DashboardActivity;
import com.seva60plus.hum.activity.HumDetailsView;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.custom.NoInternetPage;
import com.seva60plus.hum.staticconstants.ConstantVO;
import com.seva60plus.hum.util.Contact;
import com.seva60plus.hum.util.DatabaseHandler;
import com.seva60plus.hum.util.Util;

public class WellBeingStatisticsExercise extends Activity {

	ImageView backBtn, menuIcon;

	RelativeLayout backSetup;

	Button tab_task, tab_sta;

	RelativeLayout tab_water, tab_execise, tab_mood;

	Button whatsapp, fb, twitter;

	Button tab_water_btn, tab_execise_btn, tab_mood_btn;

	TableLayout table_layout;

	// ---------------DB

	DatabaseHandler db;
	Calendar c;
	String setTime = "00:00:01";
	String dDay, dMonth, dYear;
	Thread myThread = null;

	// ---------------

	RelativeLayout sync_btn;

	int exMood1 = 0, exMood2 = 0, exMood3 = 0;

	Dialog progress;
	private AnimationDrawable progressAnimation;

	String eMsg = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welbing_sta_activity_exercise);

		db = new DatabaseHandler(this);
		c = Calendar.getInstance();

		// ------START------------For Progress Spinner--------------

		progress = new Dialog(WellBeingStatisticsExercise.this);
		progress.getWindow().setBackgroundDrawableResource(R.drawable.spinner_dialog_backround);

		// Remove the Title
		progress.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		// progress.setTitle("");

		// Set the View of the Dialog - Custom
		progress.setContentView(R.layout.custom_progress_dialog);

		// Set the title of the Dialog
		// dialog.setTitle("Title...");

		ImageView progressSpinner = (ImageView) progress.findViewById(R.id.progressSpinner);

		// Set the background of the image - In this case an animation
		// (/res/anim folder)
		progressSpinner.setBackgroundResource(R.anim.spinner_progress_animation);

		// Get the image background and attach the AnimationDrawable to it.
		progressAnimation = (AnimationDrawable) progressSpinner.getBackground();

		// Start the animation after the dialog is displayed.
		progress.setOnShowListener(new OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
				progressAnimation.start();
			}

		});

		progress.setCanceledOnTouchOutside(false);
		// -------END-----------For Progress Spinner-------------- ​
		whatsapp = (Button) findViewById(R.id.whatsapp_btn);
		fb = (Button) findViewById(R.id.facebook_btn);
		twitter = (Button) findViewById(R.id.twitter_btn);

		whatsapp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareAppLinkViaWhatsApp();
			}
		});

		fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareAppLinkViaFacebook();
			}
		});

		twitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareAppLinkViaTwitter();
			}
		});

		backBtn = (ImageView) findViewById(R.id.iv_back);

		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();

			}
		});

		backSetup = (RelativeLayout) findViewById(R.id.back_settings);

		backSetup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (Util.isInternetAvailable(WellBeingStatisticsExercise.this)) {
					Intent i = new Intent(WellBeingStatisticsExercise.this, HumDetailsView.class);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_leave_right);
				} else {
					Intent i = new Intent(WellBeingStatisticsExercise.this, NoInternetPage.class);
					startActivity(i);
					overridePendingTransition(0, 0);
				}
			}
		});

		findViewById(R.id.llMenu).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intObj = new Intent(getApplicationContext(), MenuLay.class);
				startActivity(intObj);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

			}
		});

		tab_task = (Button) findViewById(R.id.task_btn);
		tab_task.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, WellBeingActivityExercise.class);
				startActivity(intObj);
				overridePendingTransition(0, 0);
				finish();

			}
		});
		tab_sta = (Button) findViewById(R.id.static_btn);
		tab_sta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, WellBeingStatisticsExercise.class);
				startActivity(intObj);
				overridePendingTransition(0, 0);
				finish();

			}
		});

		tab_water = (RelativeLayout) findViewById(R.id.wel_water_btn);
		tab_water.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, WellBeingStatisticsSleep.class);
				startActivity(intObj);
				overridePendingTransition(0, 0);
				finish();

			}
		});
		tab_water_btn = (Button) findViewById(R.id.btn_water_btn);
		tab_water_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, WellBeingStatisticsSleep.class);
				startActivity(intObj);
				overridePendingTransition(0, 0);
				finish();

			}
		});

		tab_execise = (RelativeLayout) findViewById(R.id.wel_ex_btn);
		tab_execise.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, WellBeingStatisticsExercise.class);
				startActivity(intObj);
				overridePendingTransition(0, 0);
				finish();

			}
		});
		tab_execise_btn = (Button) findViewById(R.id.btn_ex_btn);
		tab_execise_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, WellBeingStatisticsExercise.class);
				startActivity(intObj);
				overridePendingTransition(0, 0);
				finish();

			}
		});

		tab_mood = (RelativeLayout) findViewById(R.id.wel_mood_btn);
		tab_mood.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, WellBeingStatisticsMood.class);
				startActivity(intObj);
				overridePendingTransition(0, 0);
				finish();

			}
		});
		tab_mood_btn = (Button) findViewById(R.id.btn_mood_btn);
		tab_mood_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, WellBeingStatisticsMood.class);
				startActivity(intObj);
				overridePendingTransition(0, 0);
				finish();

			}
		});

		sync_btn = (RelativeLayout) findViewById(R.id.refreshLay);
		sync_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				float[] datas = new float[3];
				datas[0] = exMood1;
				datas[1] = exMood2;
				datas[2] = exMood3;

				String[] labels = new String[3];
				labels[0] = "YES";
				labels[1] = "NO";
				labels[2] = "NA";

				// ==================================

				Intent intObj = new Intent(WellBeingStatisticsExercise.this, PieChartActivity.class);
				intObj.putExtra("numbers", datas);
				intObj.putExtra("lables", labels);
				intObj.putExtra("chartLabel", ConstantVO.EXERCISE_HEADER);
				startActivity(intObj);

				// =========================

				// new GetDataOnPiChart().execute();
				// openChart();
				// MainActivity1.getStatusData();

			}
		});
		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		BuildTable();

		Runnable myRunnableThread = new CountDownRunner();
		myThread = new Thread(myRunnableThread);
		// myThread.start();

		List<Contact> contacts = db.getAllContacts();

		for (Contact cn : contacts) {
			String log = cn.getDate() + "--" + cn.getTime() + "--" + cn.getResult() + "--" + cn.getStaus() + "--" + cn.getMode() + "\n";

			Log.d("Name: ", log);

		}

	}

	// ----------Database----------------------

	public void inserData(String result, String status, String mode) {

		Date dt = new Date();
		int hours = dt.getHours();
		int minutes = dt.getMinutes();
		int seconds = dt.getSeconds();

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		int mlen = String.valueOf(month).length();
		int dlen = String.valueOf(day).length();

		if (mlen == 1) {
			dMonth = "0" + month;
		} else {
			dMonth = String.valueOf(month);
		}

		if (dlen == 1) {
			dDay = "0" + day;
		} else {
			dDay = String.valueOf(day);
		}

		String toDate = dDay + "." + dMonth + "." + year;
		String curTime = hours + ":" + minutes + ":" + seconds;

		db.addContact(new Contact(toDate, curTime, result, status, mode));
		Toast.makeText(getApplicationContext(), "Thank you", Toast.LENGTH_SHORT).show();

		List<Contact> contacts = db.getAllContacts();

		for (Contact cn : contacts) {
			String log = cn.getDate() + "--" + cn.getTime() + "--" + cn.getResult() + "--" + cn.getStaus() + "--" + cn.getMode() + "\n";
			// Writing Contacts to log

			Log.d("Name: ", log);

		}

	}

	public void doWork() {
		runOnUiThread(new Runnable() {
			public void run() {
				try {
					// TextView txtCurrentTime=
					// (TextView)findViewById(R.id.text_view);
					Date dt = new Date();
					int hours = dt.getHours();
					int minutes = dt.getMinutes();
					int seconds = dt.getSeconds();

					int year = c.get(Calendar.YEAR);
					int month = c.get(Calendar.MONTH) + 1;
					int day = c.get(Calendar.DAY_OF_MONTH);

					int mlen = String.valueOf(month).length();
					int dlen = String.valueOf(day).length();

					if (mlen == 1) {
						dMonth = "0" + month;
					} else {
						dMonth = String.valueOf(month);
					}

					if (dlen == 1) {
						dDay = "0" + day;
					} else {
						dDay = String.valueOf(day);
					}

					String toDate = dDay + "." + dMonth + "." + year;
					String curTime = hours + ":" + minutes + ":" + seconds;

					// txtCurrentTime.setText(curTime+"  DATE : "+toDate);

					// String getTime = txtCurrentTime.getText().toString();

					if (curTime.equals("0:1:0")) {

						System.out.println("*********** " + curTime);

						db.addContact(new Contact(toDate, curTime, "NA", "2", "Ex"));
						Toast.makeText(getApplicationContext(), "Thank you", Toast.LENGTH_SHORT).show();

					} else {

						System.out.println("*********** " + curTime);
					}

				} catch (Exception e) {
				}
			}
		});
	}

	class CountDownRunner implements Runnable {
		// @Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					doWork();
					Thread.sleep(1000); // Pause of 1 Second
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (Exception e) {
				}
			}
		}
	}

	private void BuildTable() {

		TableRow row1 = new TableRow(this);
		row1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		for (int j = 0; j < 5; j++) {

			if (j == 0) {

				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape_row);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
				tv.setTextColor(Color.parseColor("#FFFFFF"));
				tv.setPadding(0, 10, 0, 10);

				tv.setText("Date");

				row1.addView(tv);
			} else if (j == 1) {

				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape_row);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
				tv.setTextColor(Color.parseColor("#FFFFFF"));
				tv.setPadding(0, 10, 0, 10);

				tv.setText("Time");

				row1.addView(tv);
			} else if (j == 2) {

				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape_row);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setTextColor(Color.parseColor("#FFFFFF"));
				tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
				tv.setPadding(0, 10, 0, 10);

				tv.setText("Response");

				row1.addView(tv);
			} else {

			}

		}

		table_layout.addView(row1);

		// ---=======

		List<Contact> contacts = db.getAllContactsNeed("exercise");

		System.out.println("LENGTH " + contacts.size());

		for (Contact cn : contacts) {
			String log = cn.getDate() + "--" + cn.getTime() + "--" + cn.getResult() + "--" + cn.getStaus() + "--" + cn.getMode() + "\n";
			// Writing Contacts to log
			// detailsText.append(log);
			Log.d("Name: ", log + ":");

			final TableRow row = new TableRow(this);
			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			for (int j = 0; j < 5; j++) {

				if (j == 0) {

					final TextView tv = new TextView(this);
					tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					tv.setBackgroundResource(R.drawable.cell_shape);
					tv.setGravity(Gravity.CENTER);
					tv.setTextSize(18);
					tv.setTextColor(Color.parseColor("#295978"));
					tv.setPadding(0, 5, 0, 5);

					tv.setText(cn.getDate());

					row.addView(tv);
					row.setClickable(true);
					row.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							System.out.println("Row Click " + tv.getText().toString());
						}
					});

				} else if (j == 1) {

					TextView tv = new TextView(this);
					tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					tv.setBackgroundResource(R.drawable.cell_shape);
					tv.setGravity(Gravity.CENTER);
					tv.setTextSize(18);
					tv.setPadding(0, 5, 0, 5);
					tv.setTextColor(Color.parseColor("#295978"));
					tv.setText(cn.getTime());

					row.addView(tv);
				} else if (j == 2) {

					TextView tv = new TextView(this);
					tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					tv.setBackgroundResource(R.drawable.cell_shape);
					tv.setGravity(Gravity.CENTER);
					tv.setTextSize(18);
					tv.setPadding(0, 5, 0, 5);
					tv.setTextColor(Color.parseColor("#295978"));
					tv.setText(cn.getResult());

					if (cn.getResult().contains("yes")) {
						exMood1 = exMood1 + 1;
					} else if (cn.getResult().contains("no")) {
						exMood2 = exMood2 + 1;
					} else {
						exMood3 = exMood3 + 1;
					}
					row.addView(tv);
				} else {

				}

			}

			table_layout.addView(row);

		}

	}

	private void shareAppLinkViaFacebook() {

		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/Seva60Plus"));
		startActivity(i);

	}

	private void shareAppLinkViaTwitter() {
		String urlToShare = "Please spread the word : Seva60Plus HUM Download Link : https://play.google.com/store/apps/details?id=com.seva60plus.hum";

		try {
			Intent intent1 = new Intent();
			intent1.setClassName("com.twitter.android", "com.twitter.android.PostActivity");
			intent1.setAction("android.intent.action.SEND");
			intent1.setType("text/plain");
			intent1.putExtra("android.intent.extra.TEXT", urlToShare);
			startActivity(intent1);
		} catch (Exception e) {
			// If we failed (not native FB app installed), try share through
			// SEND
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			String sharerUrl = "https://twitter.com/intent/tweet?text=Please spread the word : Seva60Plus HUM";
			// String sharerUrl =
			// "https://www.facebook.com/sharer/sharer.php?u=http://wrctechnologies.com/";
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
			startActivity(intent);
		}

	}

	private void shareAppLinkViaWhatsApp() {
		Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
		whatsappIntent.setType("text/plain");
		whatsappIntent.setPackage("com.whatsapp");
		whatsappIntent.putExtra(Intent.EXTRA_TEXT,
				"Please spread the word : Seva60Plus HUM Download Link : https://play.google.com/store/apps/details?id=com.seva60plus.hum");
		try {
			startActivity(whatsappIntent);
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(getApplicationContext(), "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
			// ToastHelper.MakeShortText("Whatsapp have not been installed.");
		}
	}

	public void onHomeClick(View v) {
		startActivity(new Intent(WellBeingStatisticsExercise.this, DashboardActivity.class));
		finish();
	}
}