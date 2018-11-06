package com.example.mayakerem.application1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class StarkPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stark_page);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycleview_stark);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(new String[]{"Eddard Stark", "Catelyn Stark",
                "Robb Stark", "Sansa Stark","Arya Stark", "Bran Stark", "Rickon Stark", "Jon " +
                "Snow"}));
    }
}
