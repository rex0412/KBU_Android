package com.example.year;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText yearEdit, monthEdit, dayEdit;
    private TextView textView;

    private final String[] heavenlyStems = {"갑", "을", "병", "정", "무", "기", "경", "신", "임", "계"};
    private final String[] earthlyBranches = {"자", "축", "인", "묘", "진", "사", "오", "미", "신", "유", "술", "해"};
    private final String[] animal = {"쥐", "소", "범", "토끼", "용", "뱀", "말", "양", "원숭이", "닭", "개", "돼지"};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yearEdit = findViewById(R.id.yearEdit);
        monthEdit = findViewById(R.id.monthEdit);
        dayEdit = findViewById(R.id.dayEdit);
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String yearStr = yearEdit.getText().toString().trim();
            String monthStr = monthEdit.getText().toString().trim();
            String dayStr = dayEdit.getText().toString().trim();

            if (yearStr.isEmpty() || monthStr.isEmpty() || dayStr.isEmpty()) {
                Toast.makeText(this, "년, 월, 일을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int year = Integer.parseInt(yearStr);
                int month = Integer.parseInt(monthStr);
                int day = Integer.parseInt(dayStr);

                LocalDate date = LocalDate.of(year, month, day);

                int heavenlyIndex = (year - 4) % 10;
                int earthlyIndex = (year - 4) % 12;

                if (heavenlyIndex < 0) heavenlyIndex += 10;
                if (earthlyIndex < 0) earthlyIndex += 12;

                String sixty = heavenlyStems[heavenlyIndex] + earthlyBranches[earthlyIndex];
                String animals = animal[earthlyIndex];

                DayOfWeek dayOfWeek = date.getDayOfWeek();
                String koreanDay = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
                String englishDay = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                String englishMonth = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                int dayOfYear = date.getDayOfYear();

                textView.setText(year + "년은 " + sixty + "년(" + animals + "띠)입니다.\n" +
                        year + "년 " + month + "월(" + englishMonth + ") " + day + "일은 " + koreanDay + "요일(" + englishDay + ")입니다.\n" +
                        year + "년 " + month + "월(" + englishMonth + ") " + day + "일은 그 해의 " + dayOfYear + "번째 날입니다.");

            } catch (NumberFormatException e) {
                Toast.makeText(this, "숫자 형식으로 올바르게 입력해주세요.", Toast.LENGTH_SHORT).show();
            } catch (DateTimeException e) {
                Toast.makeText(this, "유효하지 않은 날짜입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}