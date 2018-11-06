package com.example.mayakerem.application1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LannisterPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int[] image = {R.drawable.Homepage};
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister_page);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_lannister);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(new String[]{"Tyrion Lannister", "Title: Hand of the" +
                " Queen", "Origin: Casterly Rock", "Allegiance: House Lannister, House Targaryen",
                "Culture: Andal", "Religion: Faith of the Seven", "Parents: Tywin Lannister, Joanna " +
                "Lannister"}));
    }
}
