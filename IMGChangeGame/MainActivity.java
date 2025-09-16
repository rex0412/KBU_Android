package com.example.imgchangegame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView scoreText, timerText;
    private final int[] animals = {R.drawable.cat, R.drawable.dog, R.drawable.rabbit};
    private final Random random = new Random();
    private int score = 0;
    private CountDownTimer timer;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.textView1);
        timerText = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            if (!gameOver) {
                startGame();
            }
        });

        imageView.setOnClickListener(v -> {
            if (gameOver) return;

            int index = random.nextInt(animals.length);
            imageView.setImageResource(animals[index]);

            if (animals[index] == R.drawable.rabbit) {
                score += 10;
                scoreText.setText(getString(R.string.score_format, score));

                AlphaAnimation blink = new AlphaAnimation(0.0f, 1.0f);
                blink.setDuration(200);
                blink.setRepeatMode(Animation.REVERSE);
                blink.setRepeatCount(3);
                imageView.startAnimation(blink);
            }
        });
    }

    private void startGame() {
        score = 0;
        gameOver = false;
        scoreText.setText(R.string.score);
        timerText.setText(R.string.timer);

        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onFinish() {
                gameOver = true;
                timerText.setText(R.string.game_over);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(getString(R.string.timer_format, (int) (millisUntilFinished / 1000)));
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}