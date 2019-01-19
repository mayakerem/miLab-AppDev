package com.example.stockdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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
            Log.d("Debug", "Successful server set up");
        } catch (Exception e) {
            Log.d("Debug", "Failed to set up server");
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
                Log.d("Debug", "Collected data from user");
                //Send stock name and open server communication
                sendToSocket();
            }
        });
    }

    //Socket communication function
    private void sendToSocket() {
        try {
            //Connect to the server that was set up before
            mSocket.connect();
            if (mSocket != null){
                Log.d("Debug", "Socket successfully connected");
                //Send the socket name to the event "sendStockName"
                mSocket.emit("sendStockName", name );
                //Now start listening to the event from the server with an Event Listener
                mSocket.on("sendStockData", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //creating a JSON file
                                JSONObject json = (JSONObject) args[0];
                                Log.d("Debug", "Receiving JSON from server");
                                String symbol = "";
                                String price = "";
                                String time = "";
                                try {
                                    symbol = json.getString("symbol");
                                    price = json.getString("price");
                                    time = json.getString("time");
                                    Log.d("Debug", "Name: " + symbol);
                                    Log.d("Debug", "Price: " + price);
                                    Log.d("Debug", "Time: " + time);
                                } catch (JSONException e) {
                                    Log.d("Debug","Failed to acquire JSON information");
                                }
                                if (price.equals("No data")){
                                    Toast.makeText(MainActivity.this, symbol +
                                                    " is an invalid symbol", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Current price" +
                                                    " of " + symbol + " is " + price + "\n \n Time: " +
                                            time, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
            } else {
                Log.d("Debug", "Socket connection was unsuccessful");
            }
        } catch (Exception e) {
            Log.d("Debug", "Socket exception error");
        }
    }

}
