package com.example.android.pianotile20;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class Recordings extends AppCompatActivity {
    Database mydb;
    RecordsAdapter adapter;
    RecyclerView recyclerView;
    private List<String> division = new Vector<String>();
    private List<String> rate = new Vector<String>();
    private List<String> time = new Vector<String>();
    private List<String> counter = new Vector<String>();
    private List<String> mode = new Vector<String>();
    private List<String> recording = new Vector<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordings_layout);
        mydb=new Database(this);
        recyclerView = findViewById(R.id.recordings_recyclerviewparent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor res = mydb.getAllDataSno();
        if(res.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"No Recordings till Now. Play game to Record automatically",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext())
            {
                division.add(res.getString(1));
                rate.add(res.getString(2));
                time.add(res.getString(3));
                counter.add(res.getString(4));
                mode.add(res.getString(5));
                recording.add(res.getString(6));
            }
            adapter=new RecordsAdapter(this,division,rate,time,counter,mode,recording);
            recyclerView.setAdapter(adapter);
        }
        Toast.makeText(getApplicationContext(),recording.get(0),Toast.LENGTH_SHORT).show();
    }
}
