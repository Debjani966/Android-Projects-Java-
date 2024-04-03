package com.debjanimandal.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView result;
    Button again;
     Button exit;
     int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=findViewById(R.id.textViewResult);
        again=findViewById(R.id.buttonPlayAgain);
        exit=findViewById(R.id.buttonExit);

        Intent intent=getIntent();
        score=intent.getIntExtra("score",0);
        String tt=String.valueOf(score);
        result.setText("Your Total Score: "+tt);

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(Result.this,MainActivity.class);
            startActivity(intent);
            finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}