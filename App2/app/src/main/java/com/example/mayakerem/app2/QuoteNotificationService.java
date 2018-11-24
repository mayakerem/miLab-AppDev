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
 * helper methods.
 */
public class QuoteNotificationService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_QUOTE_NOTIFICATION = "com.example.mayakerem.app2.action.ACTION_QUOTE_NOTIFICATION";
    public static final String[] quotesArray =
            {"When you're alone, and life is making you lonely",
                    "You can always go Downtown",
                    "When you've got worries, all the noise and the hurry",
                    "Seems to help, I know Downtown",
                    "Just listen to the music of the traffic in the city",
                    "Linger on the sidewalk where the neon signs are pretty",
                    "How can you lose?",
                    "You can forget all your troubles, forget all your cares",
                    "So go downtown, things'll be great when you're",
                    "Downtown, no finer place for sure",
                    "Downtown everything's waiting for you",
                    "Don't hang around and let your problems surround you",
                    "There are movie shows Downtown",
                    "Maybe you know some little places to go to",
                    "Where they never close Downtown",
                    "Just listen to the rhythm of a gentle bossa nova",
                    "You'll be dancing with him too before the night is over, Happy again",
                    "The lights are much brighter there",
                    "You can forget all your troubles, forget all your cares",
                    "So go downtown, where all the lights are bright\n"};
    public static AlarmManager alarmManager;
    public static final long SECONDS = 1000 * 15;
    public static NotificationManagerCompat notificationManager;
    public static int id = 1;


    public QuoteNotificationService() {
        super("QuoteNotificationService");
    }


    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void doAction(Context context) {
        //Initializing Action
        Intent intent = new Intent(context, QuoteNotificationService.class);
        intent.setAction(ACTION_QUOTE_NOTIFICATION);
        context.startService(intent);
    }

//    public static void stopAction(Context context) {
//        notificationManager.cancelAll();
//    }

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
        //connecting the intent to the broadcast receiver
        Intent intent = new Intent(this, NotificationReceiver.class);
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), SECONDS,
                PendingIntent.getBroadcast(this, 0, intent, 0));

        String quote = quotesArray[(int) (Math.random() * quotesArray.length)];
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(id + ": " +quote)
               // .setContentText(id+ ": " + quote)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
                ;
        notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(id ++, builder.build());
    }
}