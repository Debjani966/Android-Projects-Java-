package com.debjanimandal.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    MediaPlayer song;
    int activePlayer=0;
    String st;
    int gameactive=1;
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    int winstate[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img=(ImageView) view;
        int index=Integer.parseInt(img.getTag().toString());

        TextView tt=findViewById(R.id.status);
        if(gameactive==0)
        {
            gamereset(view);
        }
        //playing condition
        if(gameactive==1)
        {
        if(gamestate[index]==2) {
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                gamestate[index] = 0;
                activePlayer = 1;
                st = "O's turn to tap";
            }
            else {
                img.setImageResource(R.drawable.o);
                gamestate[index] = 1;
                activePlayer = 0;
                st = "X's turn to tap";
            }
        }
        }
        //win condition
        int f=0;
        for(int i[]:winstate)
        {
            if(gamestate[i[0]]==gamestate[i[1]] && gamestate[i[1]]==gamestate[i[2]] && gamestate[i[0]]!=2)
            {
                if(activePlayer==0)
                {
                    st="O's has won";
                    gameactive=0;
                    f=1;
                }
                else {
                    st="X's has won";
                    gameactive=0;
                    f=1;
                }
            }
        }
        //draw condition
        int c=0;
        for(int i=0;i<9;i++)
        {
            if(gamestate[i]!=2)
            {
                c++;
            }
        }

        if(c==9 && f==0)
        {
            st="Draw Nobody has won";
            gameactive=0;
        }
        tt.setText(st);
    }
    //new game
    public void gamereset(View view)
    {
        activePlayer=0;
        gameactive=1;
        for(int i=0;i<9;i++)
        {
            gamestate[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
    public void playit(View view)
    {
        song.start();
    }
    public  void pause(View view)
    {
        song.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        song=MediaPlayer.create(MainActivity.this,R.raw.music);
    }
    @Override
    protected void onPause() {
        super.onPause();
        song.release();
    }
}
