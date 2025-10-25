package com.example.grade;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        editText.setFocusable(false);

        editText.setOnClickListener(view -> {
            final String[] grades = new String[]{"1학년", "2학년", "3학년", "4학년"};
            final int[] selectedIndex = {-1};

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.yourGrade);
            builder.setIcon(R.drawable.star);

            builder.setSingleChoiceItems(grades, -1,
                    (dialog, which) -> selectedIndex[0] = which);

            builder.setPositiveButton("닫기", (dialog, which) -> {
                if (selectedIndex[0] != -1) {
                    editText.setText(grades[selectedIndex[0]]);
                }
                dialog.dismiss();
            });
            builder.show();
        });
    }
}