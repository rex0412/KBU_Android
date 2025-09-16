package com.example.yutnori;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final int[] yutImages = {R.drawable.image1, R.drawable.image2};
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView resultTextView = findViewById(R.id.textView);
        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        Button throwButton = findViewById(R.id.button);

        throwButton.setOnClickListener(v -> {
            int n1 = random.nextInt(2); // 0 또는 1
            int n2 = random.nextInt(2); // 0 또는 1
            int n3 = random.nextInt(2); // 0 또는 1
            int n4 = random.nextInt(2); // 0 또는 1

            imageView1.setImageResource(yutImages[n1]);
            imageView2.setImageResource(yutImages[n2]);
            imageView3.setImageResource(yutImages[n3]);
            imageView4.setImageResource(yutImages[n4]);

            int backCount = n1 + n2 + n3 + n4;

            String resultText;
            switch (backCount) {
                case 1:
                    resultText = getString(R.string.yut_do);
                    break;
                case 2:
                    resultText = getString(R.string.yut_gae);
                    break;
                case 3:
                    resultText = getString(R.string.yut_geol);
                    break;
                case 4:
                    resultText = getString(R.string.yut_yut);
                    break;
                case 0:
                    resultText = getString(R.string.yut_mo);
                    break;
                default:
                    resultText = "";
                    break;
            }
            resultTextView.setText(getString(R.string.result_format, resultText));
        });
    }
}