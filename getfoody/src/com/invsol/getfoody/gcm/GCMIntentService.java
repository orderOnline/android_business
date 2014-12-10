package com.invsol.getfoody.gcm;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.invsol.getfoody.R;
import com.invsol.getfoody.view.HomeActivity;

public class GCMIntentService extends IntentService {
	
	Context context = this;
	public static final int NOTIFICATION_ID = 1;
    NotificationCompat.Builder builder;
    public static final String TAG = "GCM Intent Service";
    
	public GCMIntentService() {
		super("GCMIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		 if (!extras.isEmpty()) {

			 if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
	                Log.i(TAG,"Send error: " + extras.toString());
	            } else if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_DELETED.equals(messageType)) {
	            	Log.i(TAG,"Deleted messages on server: " +
	                        extras.toString());
	            // If it's a regular GCM message, do some work.
	            } else if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
	            	 Log.i(TAG, "Received: " + extras.toString());
	            	 String message = intent.getExtras().getString("message");
	            	 generateNotification(context, message);
	            }
	        }
		 GCMBroadcastReceiver.completeWakefulIntent(intent);
	}
	
	/**
	  * Issues a notification to inform the user that server has sent a message.
	  */
	 private static void generateNotification(Context context, String message) {
	  int icon = R.drawable.ic_launcher;
	  NotificationManager notificationManager = (NotificationManager) context
	    .getSystemService(Context.NOTIFICATION_SERVICE);
	  Intent notificationIntent = new Intent(context, HomeActivity.class);
	  PendingIntent intent = PendingIntent.getActivity(context, 0,
			    notificationIntent, 0);
	  Notification notification = new Notification.Builder(context)
	  			.setContentIntent(intent)
	  			 .setContentTitle("New Order")
		         .setContentText(message)
		         .setSmallIcon(icon)
		         .build();
	  notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
	    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	  notification.defaults |= Notification.DEFAULT_SOUND;
	  // Vibrate if vibrate is enabled
      notification.defaults |= Notification.DEFAULT_VIBRATE;
	  notification.flags |= Notification.FLAG_INSISTENT;
	  notification.flags |= Notification.FLAG_NO_CLEAR;
	  notificationManager.notify(0, notification);
	 }

}