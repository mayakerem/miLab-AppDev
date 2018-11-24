package com.example.mayakerem.app2;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class QuoteNotificationService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_QUOTE_NOTIFICATION = "com.example.mayakerem.app2.action.ACTION_QUOTE_NOTIFICATION";
    public static final String[] quotesArray = {"quote1", "qoute2", "quote3", "quote4", "quote5", "quote6"};
    public static AlarmManager alarmManager;
    public static final long TEN_SECONDS_IN_MILLISECONDS = 1000*10;

    public QuoteNotificationService() {
        super("QuoteNotificationService");
    }


    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void doAction(Context context) {
        //Initializing Action
        Intent intent = new Intent(context, QuoteNotificationService.class);
        intent.setAction(ACTION_QUOTE_NOTIFICATION);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_QUOTE_NOTIFICATION.equals(action)) {
                handleAction();
            } else {
                throw new RuntimeException("Unknown action provided");
            }
        }
    }

    private void handleAction() {
        //connecting the intent to the brodcast reciever
        Intent intent = new Intent(this, NotificationReceiver.class);
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), TEN_SECONDS_IN_MILLISECONDS,
                PendingIntent.getBroadcast(this, 0, intent, 0));

        String quote = quotesArray[(int) (Math.random() * quotesArray.length)];
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Random Quotes")
                .setContentText(quote).setAutoCancel(true)
                .setPriority(1)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int id = 1;
        notificationManager.notify(id ++, builder.build());
    }

//    // For API 26 and higher need to follow by channels
//    private void registerNotificationChannel() {
////        Log.i(ACTION_NOTIFY, "notification channel for API higher than 26");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
//            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH));
//        }
//    }
}