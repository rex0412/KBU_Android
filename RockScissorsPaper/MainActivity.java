package com.example.rockscissorspaper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int[] imageResourceIds = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3};
    private final int[] resultStrings = {R.string.result1, R.string.result2, R.string.result3};
    private final int[] score = {0, 0, 0};

    private TextView textViewResult, textViewYou, textViewPhone, textViewGame;
    private ImageView imageViewYou, imageViewPhone;
    private final Random random = new Random();

    private Bitmap[] originalBitmaps;
    private Bitmap[] flippedBitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeBitmaps();

        textViewResult = findViewById(R.id.textViewResult);
        textViewYou = findViewById(R.id.textView1);
        textViewPhone = findViewById(R.id.textView2);
        textViewGame = findViewById(R.id.textView3);

        imageViewYou = findViewById(R.id.imageView1);
        imageViewPhone = findViewById(R.id.imageView2);

        imageViewYou.setImageBitmap(originalBitmaps[0]);
        imageViewPhone.setImageBitmap(flippedBitmaps[2]);

        Button buttonScissors = findViewById(R.id.button1);
        Button buttonRock = findViewById(R.id.button2);
        Button buttonPaper = findViewById(R.id.button3);

        buttonScissors.setOnClickListener(v -> test(0));
        buttonRock.setOnClickListener(v -> test(1));
        buttonPaper.setOnClickListener(v -> test(2));
    }

    private void initializeBitmaps() {
        int imageCount = imageResourceIds.length;
        originalBitmaps = new Bitmap[imageCount];
        flippedBitmaps = new Bitmap[imageCount];

        Matrix sideInversionMatrix = new Matrix();
        sideInversionMatrix.setScale(-1f, 1f);

        for (int i = 0; i < imageCount; i++) {
            Bitmap original = BitmapFactory.decodeResource(getResources(), imageResourceIds[i]);
            originalBitmaps[i] = original;

            Bitmap flipped = Bitmap.createBitmap(
                    original, 0, 0,
                    original.getWidth(), original.getHeight(),
                    sideInversionMatrix, false
            );
            flippedBitmaps[i] = flipped;
        }
    }

    private void test(int you) {
        int phone = random.nextInt(3);

        imageViewYou.setImageBitmap(originalBitmaps[you]);
        imageViewPhone.setImageBitmap(flippedBitmaps[phone]);

        if (you == phone) {
            score[1]++;
            textViewResult.setText(resultStrings[1]);
        } else if ((you == 0 && phone == 2) || (you == 1 && phone == 0) || (you == 2 && phone == 1)) {
            score[0]++;
            textViewResult.setText(resultStrings[0]);
        } else {
            score[2]++;
            textViewResult.setText(resultStrings[2]);
        }
        textViewYou.setText(getString(R.string.you, score[0]));
        textViewPhone.setText(getString(R.string.phone, score[2]));
        textViewGame.setText(getString(R.string.game, score[1]));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < imageResourceIds.length; i++) {
            if (originalBitmaps[i] != null) {
                originalBitmaps[i].recycle();
            }
            if (flippedBitmaps[i] != null) {
                flippedBitmaps[i].recycle();
            }
        }
    }
}