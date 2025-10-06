package com.example.birthday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnShowCalculator, btnCalculateAge, btnCalculateBirthYear;
    LinearLayout layoutCalculateAge, layoutCalculateBirthYear;
    EditText editBirthYear, editAge;
    TextView textResult;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radioGroup = findViewById(R.id.radioGroup);
        btnShowCalculator = findViewById(R.id.btn_show_calculator);
        btnCalculateAge = findViewById(R.id.btn_calculate_age);
        btnCalculateBirthYear = findViewById(R.id.btn_calculate_birth_year);
        layoutCalculateAge = findViewById(R.id.layout_calculate_age);
        layoutCalculateBirthYear = findViewById(R.id.layout_calculate_birth_year);
        editBirthYear = findViewById(R.id.edit_birth_year);
        editAge = findViewById(R.id.edit_age);
        textResult = findViewById(R.id.text_result);

        btnShowCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textResult.setVisibility(View.INVISIBLE);
                editBirthYear.setText("");
                editAge.setText("");

                int checkedId = radioGroup.getCheckedRadioButtonId();
                if (checkedId == R.id.radio_birth_to_age) {
                    layoutCalculateAge.setVisibility(View.VISIBLE);
                    layoutCalculateBirthYear.setVisibility(View.GONE);
                } else {
                    layoutCalculateAge.setVisibility(View.GONE);
                    layoutCalculateBirthYear.setVisibility(View.VISIBLE);
                }
            }
        });

        btnCalculateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearInput = editBirthYear.getText().toString();

                if (yearInput.isEmpty()) {
                    Toast.makeText(getBaseContext(), "출생 연도를 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int birthYear = Integer.parseInt(yearInput);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                    if (birthYear >= 1900 && birthYear <= currentYear) {
                        int age = currentYear - birthYear + 1;
                        String resultMessage = "당신의 나이는 " + age + "세 입니다.";
                        Toast.makeText(getBaseContext(), resultMessage, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(), "출생 연도를 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getBaseContext(), "출생 연도를 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCalculateBirthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageInput = editAge.getText().toString();

                if (ageInput.isEmpty()) {
                    Toast.makeText(getBaseContext(), "나이를 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int age = Integer.parseInt(ageInput);

                    if (age >= 0 && age <= 130) {
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                        int birthYear = currentYear - age + 1;
                        String resultMessage = "당신의 태어난 해는 " + birthYear + "년 입니다.";
                        Toast.makeText(getBaseContext(), resultMessage, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(), "나이를 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getBaseContext(), "나이를 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}