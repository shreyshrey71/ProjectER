package com.example.android.pianotile20;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ClassicActivityDifficultSel extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_set_1);
    }

    public void normalLaunch(View view){
        Intent intent = new Intent(this,ClassicNormal.class);
        startActivity(intent);
        finish();
    }
    public void invertLaunch(View view){
        Intent intent = new Intent(this,ClassicInvert.class);
        startActivity(intent);
        finish();
    }
    public void leaderboard(View view){
        Intent intent = new Intent(this,LeaderboardActivity.class);
        intent.putExtra("mode",0);
        startActivity(intent);
    }
    public void backButton(View view){
        onBackPressed();
    }
}
