package com.debjanimandal.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast, textViewRight,textViewHint;
    private Button buttonConfirm;
    private EditText editTextGuess;
    boolean two,three,four;
    Random r=new Random();
    int random;
    int remains=10;

    ArrayList<Integer> guessList=new ArrayList<>();
    int userAttempts=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewLast=findViewById(R.id.textViewLast);
        textViewRight=findViewById(R.id.textViewRight);
        textViewHint=findViewById(R.id.textViewhint);
        buttonConfirm=findViewById(R.id.buttonconfirm);
        editTextGuess=findViewById(R.id.editTextGuess);

        two=getIntent().getBooleanExtra("one",false);
        three=getIntent().getBooleanExtra("two",false);
        four=getIntent().getBooleanExtra("three",false);

        if(two)
        {
            random=r.nextInt(90)+10;
        }
        if(three)
        {
            random=r.nextInt(900)+10;
        }
        if(four)
        {
            random=r.nextInt(9000)+10;
        }
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess=editTextGuess.getText().toString();
                if(guess.equals(""))
                {
                    Toast.makeText(GameActivity.this, "Please enter a guess.", Toast.LENGTH_SHORT).show();
                }
                else {
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);
                    userAttempts++;
                    remains--;

                    int userGuess=Integer.parseInt(guess);
                    guessList.add(userGuess);
                    textViewLast.setText("Your last guess : "+guess);
                    textViewRight.setText("Your remaining right : "+remains);

                    if(random==userGuess)
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Congatulations, My guess was "+random+"\n\nYou know my number in "+userAttempts
                        +" attempts. \n\n Your guesses : "+guessList
                        +"\n\n Would you like to play again?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                    }
                    if(random < userGuess)
                    {
                        textViewHint.setText("Decrease your guess");
                    }
                    if(random >userGuess)
                    {
                        textViewHint.setText("Increase your guess");
                    }
                    if(remains==0)
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry, Your right to guess is over"+"\n\n My guess was "+random
                                +"\n\n Your guesses : "+guessList
                                +"\n\n Would you like to play again?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();

                    }
                    editTextGuess.setText("");
                }
            }
        });
    }
}