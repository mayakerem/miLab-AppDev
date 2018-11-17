package com.example.mayakerem.app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Notification Reciever", "button to initiate notification was instantiated");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
