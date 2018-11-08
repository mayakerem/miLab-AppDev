package com.example.mayakerem.application1;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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
        recyclerView.setAdapter(new ListAdapter(new String[]{"Rickard Stark R.I.P", "Brandon Stark R.I.P", "Eddard Stark R.I.P", "Catelyn Tully R.I.P", "Benjen Stark", "Lyanna Stark R.I.P","Rhaegar Targaryen R.I.P", "Rob Stark R.I.P", "Sansa Stark", "Arya Stark", "Bran Stark", "Rickson Stark R.I.P", "Jon Snow"}, new Drawable[]{ContextCompat.getDrawable(this,R.drawable.rickardstark), ContextCompat.getDrawable(this,R.drawable.unknownstark), ContextCompat.getDrawable(this,R.drawable.eddardnedstark), ContextCompat.getDrawable(this,R.drawable.catelyntullystark), ContextCompat.getDrawable(this,R.drawable.brandonstark), ContextCompat.getDrawable(this,R.drawable.lyannastark), ContextCompat.getDrawable(this,R.drawable.rhaegartargaryen), ContextCompat.getDrawable(this,R.drawable.robertstark), ContextCompat.getDrawable(this,R.drawable.sansastark), ContextCompat.getDrawable(this,R.drawable.aryastark), ContextCompat.getDrawable(this,R.drawable.branstark), ContextCompat.getDrawable(this,R.drawable.rikonstark), ContextCompat.getDrawable(this,R.drawable.jonsnow)}));
    }
}
