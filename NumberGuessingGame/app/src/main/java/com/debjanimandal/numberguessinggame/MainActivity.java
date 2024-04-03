package com.debjanimandal.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButton1,radioButton2,radioButton3;
    private Button buttonstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioButton1=findViewById(R.id.radioButton1);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton3=findViewById(R.id.radioButton3);
        buttonstart=findViewById(R.id.buttonStart);



        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, GameActivity.class);
                if(!radioButton1.isChecked() && !radioButton2.isChecked() && !radioButton3.isChecked())
                {
                    Snackbar.make(view,"Please select a number of digits.",Snackbar.LENGTH_LONG).show();
                }
                else{
                    if(radioButton1.isChecked())
                    {
                        intent.putExtra("one",true);
                    }
                    if (radioButton2.isChecked()) {
                        intent.putExtra("two",true);
                    }
                    if(radioButton3.isChecked()) {
                        intent.putExtra("three",true);
                    }
                    startActivity(intent);
                }
            }
        });

    }
}