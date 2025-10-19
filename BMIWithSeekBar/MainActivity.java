package com.example.bmiwithseekbar;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar weightSeekBar, heightSeekBar;
    private TextView weightValueTextView, heightValueTextView;
    private TextView bmiResultTextView, bmiStatusTextView;

    private static final float MIN_WEIGHT = 35.0f;
    private static final float MIN_HEIGHT = 90.0f;
    private static final float STEP = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightSeekBar = findViewById(R.id.weightSeekBar);
        heightSeekBar = findViewById(R.id.heightSeekBar);
        weightValueTextView = findViewById(R.id.weightValueTextView);
        heightValueTextView = findViewById(R.id.heightValueTextView);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);
        bmiStatusTextView = findViewById(R.id.bmiStatusTextView);

        weightSeekBar.setMax(230);
        heightSeekBar.setMax(280);

        updateValueTextViews();

        SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateValueTextViews();

                if (fromUser) {
                    calculateAndDisplayBmi();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        weightSeekBar.setOnSeekBarChangeListener(seekBarListener);
        heightSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }
    private void updateValueTextViews() {
        float currentWeight = MIN_WEIGHT + (weightSeekBar.getProgress() * STEP);
        float currentHeight = MIN_HEIGHT + (heightSeekBar.getProgress() * STEP);

        weightValueTextView.setText(getString(R.string.weight_value_format, currentWeight));
        heightValueTextView.setText(getString(R.string.height_value_format, currentHeight));
    }
    private void calculateAndDisplayBmi() {
        if (bmiResultTextView.getVisibility() != View.VISIBLE) {
            bmiResultTextView.setVisibility(View.VISIBLE);
        }
        if (bmiStatusTextView.getVisibility() != View.VISIBLE) {
            bmiStatusTextView.setVisibility(View.VISIBLE);
        }

        float currentWeight = MIN_WEIGHT + (weightSeekBar.getProgress() * STEP);
        float currentHeight = MIN_HEIGHT + (heightSeekBar.getProgress() * STEP);

        if (currentHeight > 0) {
            float heightInMeters = currentHeight / 100.0f;
            float bmi = currentWeight / (heightInMeters * heightInMeters);

            bmiResultTextView.setText(getString(R.string.bmi_result_format, bmi));

            String status;
            if (bmi >= 25.0f) {
                status = getString(R.string.status_obese);
            } else if (bmi >= 23.0f) {
                status = getString(R.string.status_overweight);
            } else if (bmi >= 18.5f) {
                status = getString(R.string.status_normal);
            } else {
                status = getString(R.string.status_underweight);
            }
            bmiStatusTextView.setText(status);
        }
    }
}