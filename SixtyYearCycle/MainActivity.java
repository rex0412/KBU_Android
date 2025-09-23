package com.example.sixtyyearcycle;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText editYear;
    private TextView textView;
    private ImageView imageView;
    private final String[] heavenlyStems = {"경", "신", "임", "계", "갑", "을", "병", "정", "무", "기"};
    private final String[] earthlyBranches = {"신", "유", "술", "해", "자", "축", "인", "묘", "진", "사", "오", "미"};

    private final int[] image = {
            R.drawable.monkey,
            R.drawable.chicken,
            R.drawable.dog,
            R.drawable.pig,
            R.drawable.rat,
            R.drawable.cow,
            R.drawable.tiger,
            R.drawable.rabbit,
            R.drawable.dragon,
            R.drawable.snake,
            R.drawable.horse,
            R.drawable.sheep
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editYear = findViewById(R.id.editYear);
        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(v -> {
            String yearString = editYear.getText().toString().trim();

            if (yearString.isEmpty()) {
                Toast.makeText(this, "년도를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int year = Integer.parseInt(yearString);

                String stem = heavenlyStems[year % 10];
                String branch = earthlyBranches[year % 12];
                String sixty = stem + branch;

                String sixtyWithYear = sixty + "년";

                String format = "%d년은 %s 입니다";
                String resultText = String.format(Locale.KOREA, format, year, sixtyWithYear);

                SpannableStringBuilder ssb = new SpannableStringBuilder(resultText);

                String yearPart = String.valueOf(year);
                int yearStart = resultText.indexOf(yearPart);
                ssb.setSpan(new ForegroundColorSpan(Color.RED), yearStart, yearStart + yearPart.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                int sixtyStart = resultText.indexOf(sixtyWithYear);
                ssb.setSpan(new ForegroundColorSpan(Color.RED), sixtyStart, sixtyStart + sixtyWithYear.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                int blueColor = ContextCompat.getColor(this, R.color.blue);
                String bluePart1 = "년은 ";
                String bluePart2 = " 입니다";

                int blueStart1 = resultText.indexOf(bluePart1);
                ssb.setSpan(new ForegroundColorSpan(blueColor), blueStart1, blueStart1 + bluePart1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                int blueStart2 = resultText.indexOf(bluePart2);
                ssb.setSpan(new ForegroundColorSpan(blueColor), blueStart2, blueStart2 + bluePart2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                textView.setText(ssb);

                int branchIndex = year % 12;
                imageView.setImageResource(image[branchIndex]);
                imageView.setVisibility(View.VISIBLE);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "유효한 숫자 형식이 아닙니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}