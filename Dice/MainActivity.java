package com.example.dice;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private int before = -1;
    private final int[] dices = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4,
            R.drawable.dice5, R.drawable.dice6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(v -> dice());
    }

    private void dice() {
        Random random = new Random();
        while (true) {
            int newIndex = random.nextInt(dices.length);

            if (before != newIndex) {
                before = newIndex;

                imageView.setImageResource(dices[newIndex]);
                break;
            } else {
                Toast.makeText(getBaseContext(), "중복", LENGTH_SHORT).show();
            }
        }
    }
}