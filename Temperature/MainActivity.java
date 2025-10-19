package com.example.temperature;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarCelsius, seekBarFahrenheit;
    private TextView textViewCelsius, textViewFahrenheit, textViewResult;
    private RadioGroup radioGroup;
    private Button buttonConvert, buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        resetUI();
        setupListeners();
    }

    private void initializeViews() {
        seekBarCelsius = findViewById(R.id.seekBarCelsius);
        seekBarFahrenheit = findViewById(R.id.seekBarFahrenheit);
        textViewCelsius = findViewById(R.id.textViewCelsius);
        textViewFahrenheit = findViewById(R.id.textViewFahrenheit);
        textViewResult = findViewById(R.id.textViewResult);
        radioGroup = findViewById(R.id.radioGroup);
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonReset = findViewById(R.id.buttonReset);
    }

    private void setupListeners() {
        seekBarCelsius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double celsiusValue = progress - 100.0;
                textViewCelsius.setText(getString(R.string.celsius_label_format, celsiusValue));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarFahrenheit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double fahrenheitValue = progress - 100.0;
                textViewFahrenheit.setText(getString(R.string.fahrenheit_label_format, fahrenheitValue));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioCToF) {
                seekBarCelsius.setEnabled(true);
                seekBarFahrenheit.setEnabled(false);
            } else if (checkedId == R.id.radioFToC) {
                seekBarCelsius.setEnabled(false);
                seekBarFahrenheit.setEnabled(true);
            }
        });

        buttonConvert.setOnClickListener(v -> handleConversion());
        buttonReset.setOnClickListener(v -> resetUI());
    }

    private void handleConversion() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, getString(R.string.toast_select_option), Toast.LENGTH_SHORT).show();
            return;
        }

        double celsius, fahrenheit;

        if (selectedId == R.id.radioCToF) {
            celsius = seekBarCelsius.getProgress() - 100.0;
            fahrenheit = (celsius * 1.8) + 32;
        } else {
            fahrenheit = seekBarFahrenheit.getProgress() - 100.0;
            celsius = (fahrenheit - 32) / 1.8;
        }

        textViewResult.setText(getString(R.string.result_format, celsius, fahrenheit));
        textViewResult.setVisibility(View.VISIBLE);
    }

    private void resetUI() {
        seekBarCelsius.setEnabled(true);
        seekBarFahrenheit.setEnabled(true);

        seekBarCelsius.setProgress(100);
        seekBarFahrenheit.setProgress(100);

        textViewCelsius.setText(getString(R.string.celsius_label_format, 0.0));
        textViewFahrenheit.setText(getString(R.string.fahrenheit_label_format, 0.0));

        radioGroup.clearCheck();
        textViewResult.setVisibility(View.GONE);
    }
}