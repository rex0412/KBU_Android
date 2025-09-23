package com.example.bmi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private EditText heightEdit, weightEdit;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightEdit = findViewById(R.id.heightEdit);
        weightEdit = findViewById(R.id.weightEdit);
        Button button = findViewById(R.id.button);
        result = findViewById(R.id.result);

        button.setOnClickListener(v -> {
            String heightStr = heightEdit.getText().toString();
            String weightStr = weightEdit.getText().toString();

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(this, "키와 몸무게를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            float height = Float.parseFloat(heightStr);
            float weight = Float.parseFloat(weightStr);

            float heightInMeters = height / 100.0f;

            float bmi = weight / (heightInMeters * heightInMeters);

            if (bmi < 16.0f) {
                result.setText("체중 부족 (심한 저체중)입니다");
                result.setTextColor(ContextCompat.getColor(this, R.color.blue));
            } else if (bmi < 18.5f) {
                result.setText("체중 부족 (저체중)입니다");
                result.setTextColor(ContextCompat.getColor(this, R.color.blue));
            } else if (bmi < 25.0f) {
                result.setText("정상 체중입니다");
                result.setTextColor(ContextCompat.getColor(this, R.color.black));
            } else if (bmi < 30.0f) {
                result.setText("과체중입니다");
                result.setTextColor(ContextCompat.getColor(this, R.color.blue));
            } else {
                result.setText("비만입니다!!!");
                result.setTextColor(ContextCompat.getColor(this, R.color.red));
            }
        });
    }
}