package com.example.android.pianotile20;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {
    public static int M=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.into_lay);
    }
    public void setToArcade(View view){
        Intent intent = new Intent(this,ArcadeActivityDifficultySel.class);
        startActivity(intent);
    }
    public void setToClassic(View view){
        Intent intent = new Intent(this,ClassicActivityDifficultSel.class);
        startActivity(intent);
    }
    public void setToZen(View view){
        Intent intent = new Intent(this,ZenDifficultySet.class);
        startActivity(intent);
    }
    public void setToBinary(View view){
        Intent intent = new Intent(this,BinaryDifficultySet.class);
        startActivity(intent);
    }
    public void setToRelay(View view){
        Intent intent = new Intent(this,RelayDifficultySet.class);
        startActivity(intent);
    }
    public void music(View view){
        Button b = findViewById(R.id.musicset);
        if(M==1)
        {
            M=0;
            b.setText("Music : Off");
        }
        else
        {
            M=1;
            b.setText("Music : On");
        }
    }
    public void listOfRecordings(View view){
    }
}
