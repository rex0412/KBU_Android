package com.example.year;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    SwitchCompat toggle;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        toggle = findViewById(R.id.toggle);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearStr = editText.getText().toString();
                if (yearStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "연도를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int year = Integer.parseInt(yearStr);
                String resultText;

                if (year < 1700) {
                    Toast.makeText(MainActivity.this, "연도는 1700년 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (toggle.isChecked()) {
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        resultText = year + "년은 윤년입니다.";
                    } else {
                        resultText = year + "년은 윤년이 아닙니다.";
                    }
                } else {
                    String[] animals = {"원숭이", "닭", "개", "돼지", "쥐", "소", "호랑이", "토끼", "용", "뱀", "말", "양"};
                    String animal = animals[year % 12];
                    resultText = year + "년의 띠 동물은 " + animal + "입니다.";
                }

                SpannableString spannableString = new SpannableString(resultText);
                String yearForColor = String.valueOf(year);
                int start = resultText.indexOf(yearForColor);
                int end = start + yearForColor.length();

                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFC107")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvResult.setText(spannableString);
            }
        });
    }
}