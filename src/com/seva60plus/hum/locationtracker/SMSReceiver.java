package com.seva60plus.hum.locationtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
	LocationManager lm;
	LocationListener locationListener;
	String senderTel = "";
	// GPSTracker class
	GPSTracker gps;

	@Override
	public void onReceive(Context context, Intent intent) {
		// ---get the SMS message that was received---
		Log.v("SMS Receiver", "On Receive");

		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		String str = "";
		if (bundle != null) {

			// ---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i = 0; i < msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				if (i == 0) {
					// ---get the sender address/phone number---
					senderTel = msgs[i].getOriginatingAddress();
				}
				// ---get the message body---
				str += msgs[i].getMessageBody().toString();
				Log.v("SMS Receiver", "" + str);
				//Toast.makeText(context, "" + str, Toast.LENGTH_LONG).show();

			}
			if (str.contains("GeoLocation_Test Hum")) {
				gps = new GPSTracker(context);

				Log.v("SMS Receiver", "------------>> get location<<------------------");
				// check if GPS enabled
				if (gps.canGetLocation()) {

					double latitude = gps.getLatitude();
					double longitude = gps.getLongitude();
					System.out.println("Telephone Number: " + senderTel);
					System.out.println("Message:\n" + "http://maps.google.com/maps?q=" + latitude + "," + longitude);
					//					Toast.makeText(context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
					SmsManager sms = SmsManager.getDefault();
					sms.sendTextMessage(senderTel, null, "Click to see the hum's current location\n http://maps.google.com/maps?q=" + latitude + ","
							+ longitude, null, null);

				} else {
					// can't get location
					// GPS or Network is not enabled
					// Ask user to enable GPS/network in settings
					//gps.showSettingsAlert();
					lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

					//---request location updates---
					locationListener = new MyLocationListener();
					lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 100, locationListener);
				}

				// ---abort the broadcast; SMS messages won’t be broadcasted---
				abortBroadcast();
				this.abortBroadcast();
			}
		}
	}

	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {
			if (loc != null) {
				Log.v("NEtwork Location", "" + "Your Location is - \nLat: " + loc.getLatitude() + "\nLong: " + loc.getLongitude());
				;
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(senderTel, null,
						"Click to see the hum's current location\n http://maps.google.com/maps?q=" + loc.getLatitude() + "," + loc.getLongitude(), null, null);
				//---stop listening for location changes---
				lm.removeUpdates(locationListener);
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
}
