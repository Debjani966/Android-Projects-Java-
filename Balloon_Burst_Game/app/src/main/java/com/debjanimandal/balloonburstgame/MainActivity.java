package com.debjanimandal.balloonburstgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.widget.GridLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textviewTime,textviewCountdown,textviewScore;
    private ImageView b1,b2,b3,b4,b5,b6,b7,b8,b9;
    private GridLayout gridLayout;
    int score_count=0;
    Runnable runnable;
    Handler handler;
    ImageView[] balloonArray;
    MediaPlayer mediaPlayer;
    boolean status=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviewTime=findViewById(R.id.textViewtTime);
        textviewCountdown=findViewById(R.id.textViewtCount);
        textviewScore=findViewById(R.id.textViewtScore);
        b1=findViewById(R.id.baloon1);
        b2=findViewById(R.id.baloon2);
        b3=findViewById(R.id.baloon3);
        b4=findViewById(R.id.baloon4);
        b5=findViewById(R.id.baloon5);
        b6=findViewById(R.id.baloon6);
        b7=findViewById(R.id.baloon7);
        b8=findViewById(R.id.baloon8);
        b9=findViewById(R.id.baloon9);
        gridLayout=findViewById(R.id.gridLayout);
        mediaPlayer=MediaPlayer.create(this,R.raw.ssound);
        balloonArray = new ImageView[]{b1,b2,b3,b4,b5,b6,b7,b8,b9};

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                textviewCountdown.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                balloonControl();

                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long l) {
                        textviewTime.setText("Remaining Time : "+l/1000);
                    }

                    @Override
                    public void onFinish() {
                        Intent intent=new Intent(MainActivity.this, ResultsActivity.class);
                        intent.putExtra("score",score_count);
                        startActivity(intent);
                        finish();
                    }
                }.start();
            }
        }.start();
    }
    public void increasescorebyone(View view)
    {
        score_count++;
        textviewScore.setText("Score : "+score_count);
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
        mediaPlayer.start();
        if(view.getId()==b1.getId())
        {
            b1.setImageResource(R.drawable.bomb);
        }
        if(view.getId()==b2.getId())
        {
            b2.setImageResource(R.drawable.bomb);
        }
        if(view.getId()==b3.getId())
        {
            b3.setImageResource(R.drawable.bomb);
        }
        if(view.getId()==b4.getId())
        {
            b4.setImageResource(R.drawable.bomb);
        }
        if(view.getId()==b5.getId())
        {
            b5.setImageResource(R.drawable.bomb);
        }
        if(view.getId()==b6.getId())
        {
            b6.setImageResource(R.drawable.bomb);
        }
        if(view.getId()==b7.getId())
        {
            b7.setImageResource(R.drawable.bomb);
        }
        if(view.getId()==b8.getId())
        {
            b8.setImageResource(R.drawable.bomb);
        }
        if(view.getId()==b9.getId())
        {
            b9.setImageResource(R.drawable.bomb);
        }
    }
    public  void balloonControl()
    {
        textviewCountdown.setVisibility(View.INVISIBLE);
        textviewTime.setVisibility(View.VISIBLE);
        textviewScore.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView balloons : balloonArray)
                {
                    balloons.setVisibility(View.INVISIBLE);
                    balloons.setImageResource(R.drawable.balloon);
                }
                gridLayout.setVisibility(View.VISIBLE);

                Random random = new Random();
                int i=random.nextInt(balloonArray.length);
                balloonArray[i].setVisibility(View.VISIBLE);
                if(score_count<=5) {
                    handler.postDelayed(runnable, 2000);
                } else if (score_count>5 && score_count<=10) {
                    handler.postDelayed(runnable, 1500);
                } else if (score_count>10 && score_count<=15) {
                    handler.postDelayed(runnable, 1000);
                } else if (score_count>15) {
                    handler.postDelayed(runnable, 500);
                }
            }
        };
        handler.post(runnable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.volume)
        {
            if(!status)
            {
                mediaPlayer.setVolume(0,0);
                item.setIcon(R.drawable.volumeoff);
                status=true;
            }
            else {
                mediaPlayer.setVolume(1,1);
                item.setIcon(R.drawable.volumeup);
                status=false;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}