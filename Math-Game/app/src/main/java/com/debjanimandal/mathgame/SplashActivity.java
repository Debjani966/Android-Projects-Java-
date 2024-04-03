package com.debjanimandal.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private ImageView image;
    private TextView text;
    Animation anImage, anText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image=findViewById(R.id.imageViewA);
        text=findViewById(R.id.textViewA);
        anImage= AnimationUtils.loadAnimation(this,R.anim.image_animation);
        anText= AnimationUtils.loadAnimation(this,R.anim.text_animation);
        image.setAnimation(anImage);
        text.setAnimation(anText);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}