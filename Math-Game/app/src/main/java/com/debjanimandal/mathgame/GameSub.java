package com.debjanimandal.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameSub extends AppCompatActivity {
    TextView score;
    TextView life;
    TextView time;
    TextView ques;
    EditText ans;
    Button ok;
    Button next;
    Random random=new Random();
    int num1;
    int num2;
    int userAns;
    int realAns;
    int sc=0;
    int li=3;
    CountDownTimer timer;
    public static final long Start_Timer_In_Milis=60000;
    Boolean timer_running;
    long time_let_in_milis=Start_Timer_In_Milis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_add);
        score = findViewById(R.id.textViewScore);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);
        ques = findViewById(R.id.textViewQues);
        ans = findViewById(R.id.editTextAns);
        ok = findViewById(R.id.buttonOK);
        next = findViewById(R.id.buttonNext);
        gamecontinue();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAns = Integer.valueOf(ans.getText().toString());
                pauseTimer();
                if (userAns == realAns) {
                    sc = sc + 10;
                    score.setText("" + sc);
                    ques.setText("Congratulation!! your answer is true!!!");
                } else {
                    li = li - 1;
                    life.setText("" + li);
                    ques.setText("Oh No!! your answer is wrong!!!");
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
                resetTimer();

                ans.setText("");
                if (li <= 0) {
                    Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(GameSub.this,Result.class);
                    intent.putExtra("score",sc);
                    startActivity(intent);
                    finish();
                }
                else {
                    gamecontinue();
                }
            }
        });
    }
    public void gamecontinue()
    {
        startTimer();
        num1=random.nextInt(100);
        num2=random.nextInt(100);
        realAns=num1-num2;
        ques.setText(num1+ "-" +num2);

    }
    public void startTimer()
    {
        timer=new CountDownTimer(time_let_in_milis,1000) {
            @Override
            public void onTick(long l) {
                time_let_in_milis= l;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running=false;
                ques.setText("Times Up!!");
                ans.setText("");
                li=li-1;
                life.setText(""+li);
                pauseTimer();
                resetTimer();
                updateText();
            }
        }.start();
        timer_running=true;
    }
    public void updateText()
    {
        int second=(int)(time_let_in_milis/1000)%60;
        String timeleft=String.format(Locale.getDefault(),"%02d",second);
        time.setText(timeleft);
    }
    public void pauseTimer()
    {
        timer.cancel();
        timer_running=false;
    }
    public void resetTimer()
    {
        time_let_in_milis=Start_Timer_In_Milis;
        updateText();
    }
}