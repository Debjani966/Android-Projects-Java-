package com.debjanimandal.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    Animation image;
    Animation text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=findViewById(R.id.imageViewSplash);
        textView=findViewById(R.id.textViewGuessGame);

        image= AnimationUtils.loadAnimation(this,R.anim.image_animation);
        text=AnimationUtils.loadAnimation(this,R.anim.text_animation);
        imageView.setAnimation(image);
        textView.setAnimation(text);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}