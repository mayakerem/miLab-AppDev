package com.example.mayakerem.app2;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

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
    private static final String ACTION_QUOTE_NOTIFICATION = "com.example.mayakerem.app2.action.ACTION_QUOTE_NOTIFICATION";
    private final String[] quotesArray = {"quote1", "qoute2", "quote3", "quote4"};
    public static AlarmManager alarmManager;

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
    public static void doAction (Context context, String quote) {
        //Action
        Intent intent = new Intent(context, QuoteNotificationService.class);
        intent.setAction(ACTION_QUOTE_NOTIFICATION);
        context.startService(intent);
        //Alarm
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), TimeUnit.SECONDS.toMinutes(3), PendingIntent.getService(context,0, intent, -1));

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String quote = quotesArray[(int) (Math.random()*quotesArray.length)];
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(quote);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        int id = 1;
        notificationManager.notify(id, builder.build());
    }
}