package com.invsol.getfoody.gcm;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.invsol.getfoody.R;
import com.invsol.getfoody.constants.Constants;
import com.invsol.getfoody.controllers.AppEventsController;
import com.invsol.getfoody.view.HomeActivity;
import com.invsol.getfoody.view.OrdersActivity;

/**
 * This {@code WakefulBroadcastReceiver} takes care of creating and managing a
 * partial wake lock for your app. It passes off the work of processing the GCM
 * message to an {@code IntentService}, while ensuring that the device does not
 * go back to sleep in the transition. The {@code IntentService} calls
 * {@code GcmBroadcastReceiver.completeWakefulIntent()} when it is ready to
 * release the wake lock.
 */
public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
	
	private static final String TAG = "GcmBroadcastReceiver";
    private Context ctx;
    private Notification.InboxStyle inboxStyle;
    private String gcmMessage;
    //private int numOfMessages;
    //final static String GROUP_KEY_ORDERS = "group_key_orders";

    @Override
    public void onReceive(Context context, Intent intent) {
    	ctx = context;
    	PowerManager mPowerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        WakeLock mWakeLock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
        mWakeLock.acquire();
         
        try {
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
             
            String messageType = gcm.getMessageType(intent);
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification(0,"Send error", false);
                 
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification(1,"Deleted messages on server", false);
                 
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            	Log.i(TAG, "Received: " + intent.getExtras().toString());
            	 gcmMessage = intent.getExtras().getString("ORDER");
            	 //String orderMsg = new String();
            	 Log.i(TAG, "Received: " + gcmMessage);
            	 try {
            		 JSONObject json = new JSONObject(gcmMessage);
            		 //orderMsg = "FROM : " + json.getString(Constants.JSON_ADDRESS) + " INR : " + json.getInt(Constants.JSON_ORDERTOTAL);
            		 AppEventsController.getInstance().getModelFacade().getResModel().addPendingOrderItem(json);
            		 //numOfMessages += 1;
            		 inboxStyle = new Notification.InboxStyle();
            		 String[] events = new String[2];
            		 events[0] = "FROM : " + json.getString(Constants.JSON_ADDRESS);
            		 events[1] = "INR : " + json.getInt(Constants.JSON_ORDERTOTAL);
            		 // Sets a title for the Inbox in expanded layout
            		 inboxStyle.setBigContentTitle("Order details:");
            		 // Moves events into the expanded layout
            		 for (int i=0; i < events.length; i++) {
        			    inboxStyle.addLine(events[i]);
            		 }
            		 //inboxStyle.setSummaryText("this is sub text");
            		 sendNotification( json.getInt(Constants.JSON_ORDER_ID), "Received", true);
				} catch (JSONException e) {					
					e.printStackTrace();
				}
            	
            }
            setResultCode(Activity.RESULT_OK);
             
        } finally {
            mWakeLock.release();
        }
    }
    
    private void sendNotification(int id, String text, boolean launchApp) {
    	int icon = R.drawable.ic_launcher;
        NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(ctx, OrdersActivity.class);
        notificationIntent.putExtra("ORDER", gcmMessage);
		  PendingIntent intent = PendingIntent.getActivity(ctx, 0,
				    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		  Notification notification = new Notification.Builder(ctx)
			 .setContentIntent(intent)
			 .setContentTitle("New Order")
	         .setSmallIcon(icon)
	         .setStyle(inboxStyle)
	        // .setNumber(numOfMessages)
	         .build();
         
        if (launchApp) {
        	notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
        		    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		  notification.defaults |= Notification.DEFAULT_SOUND;
        		  // Vibrate if vibrate is enabled
        	      notification.defaults |= Notification.DEFAULT_VIBRATE;
        		  notification.flags |= Notification.FLAG_INSISTENT;
        		  notification.flags |= Notification.FLAG_AUTO_CANCEL;
        		  notification.flags |= Notification.FLAG_NO_CLEAR;
        		  mNotificationManager.notify(id, notification);
        }
    }
	
}