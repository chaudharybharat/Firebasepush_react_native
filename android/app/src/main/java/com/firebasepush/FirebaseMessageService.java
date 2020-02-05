package com.firebasepush;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import io.invertase.firebase.Utils;

public class FirebaseMessageService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";
    public static final String MESSAGE_EVENT = "messaging-message";
    public static final String REMOTE_NOTIFICATION_EVENT = "notifications-remote-notification";
    int notificationId =1;
    private String ADMIN_CHANNEL_ID = "Android4Dev";
     Uri alarmSound;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // onMessageReceived will always be triggered for data only messages even in background
    // onMessageRecevied for FCM messages with notification body will only be triggered if app is in foreground


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "onMessageReceived");
        playNotificationSound();
        alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + this.getPackageName() + "/raw/ring");
        if (remoteMessage.getData().size() > 0) {
            try {

                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
                String title = remoteMessage.getData().get("title");
                String message = remoteMessage.getData().get("body");
                    generateNotification(title,message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String message= remoteMessage.getNotification().getBody();
                generateNotification("",message);

        }
    }
    public void generateNotification(String title,String messsge){


        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("com.jshealth.clinic", notificationId);
        PendingIntent pIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Setting up Notification channels for android O and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupNotificationChannels();
        }
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

       // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)  //a resource for your custom small icon
                .setContentTitle(title) //the "title" value you sent in your notification
                .setContentText(messsge) //ditto
                .setAutoCancel(true)
                .setSound(null)
                //dismisses the notification on click
                .setContentIntent(pIntent);
              //  .setSound(defaultSoundUri)


        notificationManager.notify(notificationId++ /* ID of notification */, notificationBuilder.build());

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupNotificationChannels() {
        CharSequence name = getString(R.string.default_notification_channel_id);
        String description = getString(R.string.default_notification_channel_id);
        int importance = NotificationManager.IMPORTANCE_MIN;
        NotificationChannel channel = new NotificationChannel(ADMIN_CHANNEL_ID, name, importance);
        channel.setSound(null, null);
        channel.enableVibration(false);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        notificationManager.createNotificationChannel(channel);
    }
    /*  @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
            // FCM messages having notification body will be processed here
            Intent notificationEvent = new Intent(REMOTE_NOTIFICATION_EVENT);
            notificationEvent.putExtra("notification", remoteMessage);

            // Broadcast it to the (foreground) RN Application
            LocalBroadcastManager
                    .getInstance(this)
                    .sendBroadcast(notificationEvent);
        } else if (remoteMessage.getData() != null) {
            // data only FCM messages will be processed here

            // put your logic that you want to perform on receiving data only FCM messages
            // this part is the key as you restart services, update shared preferences etc.
            try {
                Map<String, String> messageMap = remoteMessage.getData();

                // if app is in foreground.
                if (Utils.isAppInForeground(this.getApplicationContext())) {
                    Intent messagingEvent = new Intent(MESSAGE_EVENT);
                    messagingEvent.putExtra("message", remoteMessage);
                    // Broadcast it to RN Application
                    LocalBroadcastManager
                            .getInstance(this)
                            .sendBroadcast(messagingEvent);
                }
            } catch (Throwable t) {
                // handle error logic
            }
        }
    }*/

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    /**
     *
     * @param token The new token.
     */
    public void sendRegistrationToServer(String token) {
        // put your logic here to update fcm registration token
    }
    public void playNotificationSound() {
        try {
            //Ringtone r = RingtoneManager.getRingtone(mContext, alarmSound);
            // r.play();
            MediaPlayer mp;
            mp = MediaPlayer.create(this, R.raw.ring);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
