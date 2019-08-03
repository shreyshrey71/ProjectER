package com.example.android.pianotile20;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ClassicNormal extends AppCompatActivity {

    float height, rowHeight = 152.0f;
    int numberOfRows = 0, numberOfRowsVisible = 0;
    int bottomPos = 0, topPos;
    float hitSpeed = 0, hitFrequency = 0;
    float almostInfinteLength;
    TextView textView;
    int ratio;
    SoundPool soundPool;
    int s1,s2,s3,s4;
    int recVal=0;
    String recording="";
    float elapsed = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.coutertext);
        final RelativeLayout main = (RelativeLayout) findViewById(R.id.gameparent);
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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                height = main.getMeasuredHeight() / getResources().getDisplayMetrics().density;
                almostInfinteLength = 10000 * height;
                ratio = Math.round(rowHeight / height);
                hitFrequency = 20 / 8;
                hitSpeed = hitFrequency * rowHeight * getResources().getDisplayMetrics().density;
                numberOfRows = (int) Math.floor(height / rowHeight);
                numberOfRowsVisible = (int) Math.ceil(height / rowHeight);
                numberOfRowsVisible += 10;
                createfirstRow();
            }
        }, 10);
    }
    public void counterfunc()
    {

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(wrong==0&&counter!=26){
                    recording+=recVal;
                    recVal = 0;
                    elapsed+=0.1;
                    counterfunc();
                }
                else {
                    recording+=recVal;
                    recVal = 0;
                    Toast.makeText(getApplicationContext(),recording,Toast.LENGTH_SHORT).show();
                    resultdisp();
                    wrong=1;
                }

            }
        },100);
    }

    public void resultdisp()
    {
        Database mydb = new Database(this);
        mydb.insertData("0",""+((counter-1)/elapsed),""+elapsed,""+(counter-1),"0");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),Result.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
    public void createfirstRow() {
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final RelativeLayout main = (RelativeLayout) findViewById(R.id.gameparent);
        RelativeLayout.LayoutParams rp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view0 = inflater.inflate(R.layout.recyclerview, null);
        view0.setId(0);
        view0.setLayoutParams(rp1);
        main.addView(view0);
        findViewById(0).setY((height - rowHeight) * getResources().getDisplayMetrics().density);
        creatStartingRows();
    }

    public void creatStartingRows() {
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final RelativeLayout main = (RelativeLayout) findViewById(R.id.gameparent);
        RelativeLayout.LayoutParams rp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 1; i <= 25; i++) {
            View view = inflater.inflate(R.layout.recyclerview, null);
            topPos = i;
            view.setId(i);
            view.setLayoutParams(rp1);
            Button button0 = view.findViewById(R.id.b0);
            Button button1 = view.findViewById(R.id.b1);
            Button button2 = view.findViewById(R.id.b2);
            Button button3 = view.findViewById(R.id.b3);
            final View viewpar = view;
            Random random = new Random();
            int r = random.nextInt(4);
            if(i==1){
                switch (r) {
                    case 0: {
                        button0.setText("Start");
                        button0.setBackgroundColor(Color.BLACK);
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewpar.getId() == counter && wrong == 0) {
                                    counterfunc();
                                    counter++;
                                    recVal=1;
                                    if(IntroActivity.M==1)
                                        soundPool.play(s1,1,1,0,0,1);

                                    textView.setText(""+(counter-1));
                                    v.setBackgroundColor(Color.parseColor("#999999"));
                                    if (wrong == 0)
                                        animateInitialRows();
                                }
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        break;
                    }
                    case 1: {
                        button1.setText("Start");
                        button1.setBackgroundColor(Color.BLACK);
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewpar.getId() == counter && wrong == 0) {
                                    counterfunc();
                                    counter++;
                                    recVal=2;
                                    if(IntroActivity.M==1)
                                        soundPool.play(s2,1,1,0,0,1);
                                    textView.setText(""+(counter-1));
                                    v.setBackgroundColor(Color.parseColor("#999999"));
                                    if (wrong == 0)
                                        animateInitialRows();
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        break;
                    }
                    case 2: {
                        button2.setText("Start");
                        button2.setBackgroundColor(Color.BLACK);
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewpar.getId() == counter && wrong == 0) {
                                    counterfunc();
                                    counter++;
                                    recVal=3;
                                    if(IntroActivity.M==1)
                                        soundPool.play(s3,1,1,0,0,1);
                                    textView.setText(""+(counter-1));
                                    v.setBackgroundColor(Color.parseColor("#999999"));
                                    if (wrong == 0)
                                        animateInitialRows();
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        break;
                    }
                    case 3: {
                        button3.setText("Start");
                        button3.setBackgroundColor(Color.BLACK);
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewpar.getId() == counter && wrong == 0) {
                                    counterfunc();
                                    counter++;
                                    recVal=4;
                                    if(IntroActivity.M==1)
                                        soundPool.play(s4,1,1,0,0,1);

                                    textView.setText(""+(counter-1));
                                    v.setBackgroundColor(Color.parseColor("#999999"));
                                    if (wrong == 0)
                                        animateInitialRows();
                                }
                            }
                        });
                        break;
                    }
                }}
            else{
                switch (r) {
                    case 0: {
                        button0.setBackgroundColor(Color.BLACK);
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewpar.getId() == counter && wrong == 0) {
                                    counter++;
                                    recVal=1;
                                    if(IntroActivity.M==1)
                                    soundPool.play(s1,1,1,0,0,1);

                                    textView.setText(""+(counter-1));
                                    v.setBackgroundColor(Color.parseColor("#999999"));
                                    if (wrong == 0)
                                        animateInitialRows();
                                }

                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        break;
                    }
                    case 1: {
                        button1.setBackgroundColor(Color.BLACK);
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewpar.getId() == counter && wrong == 0) {
                                    counter++;
                                    recVal=2;
                                    if(IntroActivity.M==1)
                                    soundPool.play(s2,1,1,0,0,1);

                                    textView.setText(""+(counter-1));
                                    v.setBackgroundColor(Color.parseColor("#999999"));
                                    if (wrong == 0)
                                        animateInitialRows();
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        break;
                    }
                    case 2: {
                        button2.setBackgroundColor(Color.BLACK);
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewpar.getId() == counter && wrong == 0) {
                                    counter++;
                                    recVal=3;
                                    if(IntroActivity.M==1)
                                    soundPool.play(s3,1,1,0,0,1);

                                    textView.setText(""+(counter-1));
                                    v.setBackgroundColor(Color.parseColor("#999999"));
                                    if (wrong == 0)
                                        animateInitialRows();
                                    ;
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        break;
                    }
                    case 3: {
                        button3.setBackgroundColor(Color.BLACK);
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (wrong == 0) {
                                    wrong = 1;
                                    v.setBackgroundColor(Color.RED);
                                    if(counter==bottomPos)
                                        for (int i = 0; i <= numberOfRowsVisible; i++) {
                                            findViewById(i + bottomPos).animate().translationY((height-(i+1)*rowHeight) * getResources().getDisplayMetrics().density);
                                            findViewById(i + bottomPos).animate().setDuration(300);}
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewpar.getId() == counter && wrong == 0) {
                                    counter++;
                                    recVal=4;
                                    if(IntroActivity.M==1)
                                    soundPool.play(s4,1,1,0,0,1);

                                    textView.setText(""+(counter-1));
                                    v.setBackgroundColor(Color.parseColor("#999999"));
                                    if (wrong == 0)
                                        animateInitialRows();
                                }
                            }
                        });
                        break;
                    }
                }}
            main.addView(view);
            findViewById(i).setY((height - (i + 1) * rowHeight) * getResources().getDisplayMetrics().density);
            ytop = (height - (i + 1) * rowHeight) * getResources().getDisplayMetrics().density;
        }
    }

    float ytop;
    int counter = 1;
    long time=150;
    public void animateInitialRows() {
        if (wrong == 0) {
            for (int i = 0; i <=25; i++) {
                findViewById(i).animate().translationYBy(rowHeight* getResources().getDisplayMetrics().density);
                findViewById(i).animate().setDuration(time);
            }
        }
    }

    int wrong = 0;
}
