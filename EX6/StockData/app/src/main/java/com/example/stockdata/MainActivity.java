package com.example.stockdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class MainActivity extends AppCompatActivity {

    private String port = "8080";
    private String host = "http://10.0.2.2:";
    private Socket mSocket;
    String name;

    {
        try {
            mSocket = IO.socket(host + port);
            Log.d("Debug", "has connected");
        } catch (Exception e) {
            Log.d("Debug", "not connected");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fetchData = (Button) findViewById(R.id.fetch_btn);
        //Click on button to ask for the stock value
        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve stock name
                EditText nameField = (EditText) findViewById(R.id.editText);
                name = nameField.getText().toString().trim();
                //Send stock name and open server communication
                Log.d("Debug", "going to connect to server now");
                sendToSocket();
            }
        });

        Button stop_btn = (Button) findViewById(R.id.StopReceiving);
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mSocket.close();
                } catch (Exception e) {
                    Log.d("Debug", "error when closing socket");
                }
            }
        });
    }

    private void sendToSocket() {
        try {
            //open socket with
//            mSocket = IO.socket(host + port);
            mSocket.connect();
//            Log.d("Debug", "has connected");
            if (mSocket != null){
                Log.d("Debug", "Socket is connected");
                //send
                mSocket.emit("sendStockName", name );


                //listining
                mSocket.on("sendStockName", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //creating a JSON file
                                JSONObject data = (JSONObject) args[0];
                                Log.d("Debug", "recieved JSON");

                            }
                        });
                    }
                });
            } else {
                Log.d("Debug", "mSocket didnt connect");
            }
        } catch (Exception e) {
            Log.d("Debug", "Socket exception");
        }
        finally {
            Log.d("Debug", "Closing the socket! ");
        }
    }

}
