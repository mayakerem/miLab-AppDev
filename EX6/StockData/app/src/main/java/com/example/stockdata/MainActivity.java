package com.example.stockdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class MainActivity extends AppCompatActivity {

    private String port = "8080";
    // Emulator IP
    private String host = "http://10.0.2.2:";
    private Socket mSocket;
    String name;

    //Socket Set Up
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
                EditText editTextField = (EditText) findViewById(R.id.editText);
                name = editTextField.getText().toString().trim();
                Log.d("Debug", "going to connect to server now");
                //Send stock name and open server communication
                sendToSocket();
            }
        });

        Button stop_btn = (Button) findViewById(R.id.StopReceiving);
        //Click on stop button to stop receiving data
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //When clicked, close the socket
                    mSocket.close();
                } catch (Exception e) {
                    Log.d("Debug", "error when closing socket");
                }
            }
        });
    }

    //Socket communication function
    private void sendToSocket() {
        try {
            //Connect to the server that was set up before
            mSocket.connect();
            if (mSocket != null){
                Log.d("Debug", "Socket is connected");
                //send the socket name to the event "sendStockName"
                mSocket.emit("sendStockName", name );
                //Now start listening to the event from the server with an Event Listener
                mSocket.on("sendStockData", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //creating a JSON file
                                JSONObject json = (JSONObject) args[0];
                                Log.d("Debug", "received JSON");
                                String symbol = "";
                                String price = "";
                                try {
                                    symbol = json.getString("data");
                                    price = json.getString("price");
                                    Log.d("Debug", "The provided name: " + symbol);
                                    Log.d("Debug", "The Price: " + price);
                                } catch (JSONException e) {
                                    Log.d("Debug","Didnt get the JSON");
                                }

                            }
                        });
                    }
                });
            } else {
                Log.d("Debug", "mSocket didn't connect");
            }
        } catch (Exception e) {
            Log.d("Debug", "Socket exception");
//        }
//        finally {
//            Log.d("Debug", "Closing the socket! ");
        }
    }

}
