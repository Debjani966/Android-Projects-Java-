package com.debjanimandal.balloonburstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private TextView textViewInfo,textViewMyScore,textViewHighestScore;
    private Button buttonPlayAgain, buttonQuitGame;
    int myscore;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        textViewInfo=findViewById(R.id.textViewInfo);
        textViewMyScore=findViewById(R.id.textViewYourscore);
        textViewHighestScore=findViewById(R.id.textViewHighestscore);
        buttonPlayAgain=findViewById(R.id.buttonPlayagain);
        buttonQuitGame=findViewById(R.id.buttonQuit);

        myscore=getIntent().getIntExtra("score",0);
        textViewMyScore.setText("Your Score : "+myscore);

        sharedPreferences=this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore=sharedPreferences.getInt("highestScore",0);

        if(myscore>=highestScore)
        {
            sharedPreferences.edit().putInt("highestScore",myscore).apply();
            textViewHighestScore.setText("Highest Score : "+myscore);
            textViewInfo.setText("Congatulations. The new high Score. Do you want to get better scores?");
        }
        else {
            textViewHighestScore.setText("Highest Score : "+highestScore);
            if(highestScore-myscore>10) {
                textViewInfo.setText("You must get little faster!");
            }
            if(highestScore-myscore>3 && highestScore-myscore <= 10) {
                textViewInfo.setText("Good. How about getting a little faster?");
            }
            if(highestScore-myscore<=3) {
                textViewInfo.setText("Excellent. If you get a little faster, you can reach the high score.");
            }
        }

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonQuitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
    }
}