package com.example.android.pianotile20;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BinaryDifficultySet extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_set_2);
    }

    public void normalEasyLaunch(View view){
        Intent intent = new Intent(this,BinaryNormalEasy.class);
        startActivity(intent);
        finish();
    }
    public void invertEasyLaunch(View view){
        Intent intent = new Intent(this,BinaryInvertEasy.class);
        startActivity(intent);
        finish();
    }
    public void leaderboardBinary(View view){
        onBackPressed();
    }

    public void normalDifficultLaunch(View view){
        Intent intent = new Intent(this,BinaryNormalDifficult.class);
        startActivity(intent);
        finish();
    }
    public void unstableLaunch(View view){
        Intent intent = new Intent(this,BinaryInvertDifficult.class);
        startActivity(intent);
        finish();
    }
    public void backButton(View view){
        onBackPressed();
    }
}
