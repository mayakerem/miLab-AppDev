package com.example.mayakerem.app2;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Broadcast reciever needs to hold the intent from the intent service and is
        //later passe on to the main activity in the XML
        QuoteNotificationService.doAction(context);
//    throw new UnsupportedOperationException("Not yet implemented");
    }
}
