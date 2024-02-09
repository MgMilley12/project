package com.example.historydates;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.historydates.R;

public class MainActivity extends AppCompatActivity {

    private AnimatorSet front_animat;
    private AnimatorSet back_animat;
    private boolean isFront = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView cardFront = findViewById(R.id.card_front);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView cardBack = findViewById(R.id.card_back);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float scale = displayMetrics.density;

        cardFront.setCameraDistance(8000 * scale);
        cardBack.setCameraDistance(8000 * scale);

        front_animat = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_anim);
        back_animat = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_anim);

        cardFront.setOnClickListener(v -> flipCard());
        cardBack.setOnClickListener(v -> flipCard());
    }

    private void flipCard() {
        TextView cardFront = findViewById(R.id.card_front);
        TextView cardBack = findViewById(R.id.card_back);

        if (!front_animat.isRunning() && !back_animat.isRunning()) {
            if (isFront) {
                front_animat.setTarget(cardFront);
                back_animat.setTarget(cardBack);
                front_animat.start();
                back_animat.start();
                isFront = false;
            } else {
                front_animat.setTarget(cardBack);
                back_animat.setTarget(cardFront);
                back_animat.start();
                front_animat.start();
                isFront = true;
            }
        }
    }
}
