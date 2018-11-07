package com.example.mayakerem.application1;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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
        recyclerView.setAdapter(new ListAdapter(new String[]{"Tytos Lannister R.I.P",
                "Tywin Lannister R.I.P", "Joanna Lannister R.I.P", "Kevan Lannister R.I.P",
                "Dorna Lannister R.I.P", "Cersei Lannister", "Jaimie Lannister", "Tyrion Lannister",
                "Lancel Lannister R.I.P", "Willem Lannister R.I.P", "Martyn Lannister R.I.P",
                "Joffrey Baratheon R.I.P", "Myrcella Bartheon R.I.P", "Tommen Baratheon"},
                new Drawable[]{ContextCompat.getDrawable(this,R.drawable.lannister),
                        ContextCompat.getDrawable(this,R.drawable.lannister)}));
    }
}
