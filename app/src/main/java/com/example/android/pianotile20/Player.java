package com.example.android.pianotile20;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Player extends AppCompatActivity {
    TextView textView;
    float reclen;
    String recording;
    DecimalFormat abc;
    SoundPool soundPool;
    int s1,s2,s3,s4;
    String ext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().setMaxStreams(5).build();
        }
        else{
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }
        s1 = soundPool.load(this,R.raw.a,1);
        s2= soundPool.load(this,R.raw.c,1);
        s3=soundPool.load(this,R.raw.e,1);
        s4= soundPool.load(this,R.raw.g,1);
        abc= new DecimalFormat("#.#");
        textView = findViewById(R.id.coutertext);
        textView.setVisibility(View.GONE);
        ext = getIntent().getStringExtra("ext");

        final String FILE_NAME = "recsave"+ext+".txt";

        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            recording=sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        reclen=recording.length()*10;
        play();
    }
    int t=0;
    void play()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(t<recording.length()){
                    if(recording.charAt(t)=='1')
                    {
                        soundPool.play(s1,1,1,0,0,1);
                    }
                    else if(recording.charAt(t)=='2')
                    {
                        soundPool.play(s2,1,1,0,0,1);
                    }
                    else if(recording.charAt(t)=='3')
                    {
                        soundPool.play(s3,1,1,0,0,1);
                    }
                    else if(recording.charAt(t)=='4')
                    {
                        soundPool.play(s4,1,1,0,0,1);
                    }
                    t++;
                play();}
                else
                {
                }
            }
        },100);
    }
    public void back(){
    }
}
