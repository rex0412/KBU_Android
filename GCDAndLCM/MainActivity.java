package com.example.gcdandlcm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar1, seekBar2;
    private TextView textViewLabel1, textViewLabel2, resultTextView;

    private int num1 = 1;
    private int num2 = 1;
    private boolean userHasInteracted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        textViewLabel1 = findViewById(R.id.textViewLabel1);
        textViewLabel2 = findViewById(R.id.textViewLabel2);
        resultTextView = findViewById(R.id.resultTextView);
        Button button = findViewById(R.id.calculateButton);

        setupSeekBarListeners();

        button.setOnClickListener(v -> {
            if (!userHasInteracted) {
                Toast.makeText(MainActivity.this, getString(R.string.input_prompt_toast), Toast.LENGTH_SHORT).show();
            } else {
                calculateAndDisplayResults();
            }
        });
    }

    private void setupSeekBarListeners() {
        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = progress + 1;
                int seekBarId = seekBar.getId();

                String labelText = getString(R.string.label_format, value);

                if (seekBarId == R.id.seekBar1) {
                    num1 = value;
                    textViewLabel1.setText(labelText);
                } else if (seekBarId == R.id.seekBar2) {
                    num2 = value;
                    textViewLabel2.setText(labelText);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                userHasInteracted = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        seekBar1.setOnSeekBarChangeListener(listener);
        seekBar2.setOnSeekBarChangeListener(listener);
    }
    private void calculateAndDisplayResults() {
        int resultGcd = gcd(num1, num2);
        long resultLcm = ((long) num1 * num2) / resultGcd;

        String gcdString = getString(R.string.gcd_result_format, num1, num2, resultGcd);
        String lcmString = getString(R.string.lcm_result_format, num1, num2, resultLcm);
        String fullResultText = getString(R.string.full_result_format, gcdString, lcmString);
        resultTextView.setText(fullResultText);
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}