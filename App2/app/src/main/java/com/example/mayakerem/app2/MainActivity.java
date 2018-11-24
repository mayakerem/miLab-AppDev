package com.example.mayakerem.app2;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button)findViewById(R.id.start_button);
        final QuoteNotificationService notification = new QuoteNotificationService();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, QuoteNotificationService.class);
//                startService(intent);
//                QuoteNotificationService.doAction(notification);
                QuoteNotificationService.doAction(MainActivity.this);

            }
        });

        Button endButton = (Button)findViewById(R.id.end_button);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, QuoteNotificationService.class);
//                stopService(intent);
            }
        });
    }
}

