package com.example.mayakerem.application1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LannisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister_page);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview_lannister);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(new String[]{"Tyrion Lannister", "Tywin Lannister"
                ,"Jamie Lannister", "Cersei Lannister", "Lancel Lannister", "Tommen Baratheon"}));
    }
}
