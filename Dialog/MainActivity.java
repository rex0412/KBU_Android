package com.example.dialog;

import static android.widget.Toast.LENGTH_SHORT;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("알림");
            builder.setMessage("이번주 레포트는 Dialog입니다")
                    .setIcon(R.drawable.alarm)
                    .setCancelable(false)
                    .setNegativeButton("확인", (dialog, which) ->
                            Toast.makeText(getBaseContext(), "확인했습니다", LENGTH_SHORT).show());
            Dialog dialog = builder.create();
            dialog.show();
        });

        button2.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("이름 입력");

            final EditText editText = new EditText(MainActivity.this);
            builder.setView(editText);

            builder.setPositiveButton("OK", (dialog, which) -> {
                String name = editText.getText().toString();

                if (name.trim().isEmpty()) {
                    Toast.makeText(getBaseContext(), "입력해주세요", LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), name, LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("CANCEL", (dialog, which) -> {
                dialog.cancel();
            });

            Dialog dialog = builder.create();
            dialog.show();
        });

        button3.setOnClickListener(v -> {
            final String[] hobbies = {"독서", "등산", "수영", "수집", "영화"};
            final boolean[] selectedItems = new boolean[hobbies.length];

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("당신의 취미는?");
            builder.setIcon(R.drawable.star);

            builder.setMultiChoiceItems(hobbies, selectedItems, (dialog, which, isChecked) -> {
                selectedItems[which] = isChecked;
            });

            builder.setPositiveButton("닫기", (dialog, which) -> {
                ArrayList<String> selectedHobbies = new ArrayList<>();
                for (int i = 0; i < selectedItems.length; i++) {
                    if (selectedItems[i]) {
                        selectedHobbies.add(hobbies[i]);
                    }
                }

                if (selectedHobbies.isEmpty()) {
                    Toast.makeText(getBaseContext(), "취미를 선택해주세요", LENGTH_SHORT).show();
                } else {
                    StringBuilder sb = new StringBuilder("취미 : ");
                    for (int i = 0; i < selectedHobbies.size(); i++) {
                        sb.append(selectedHobbies.get(i));
                        if (i < selectedHobbies.size() - 1) {
                            sb.append(", ");
                        }
                    }
                    Toast.makeText(getBaseContext(), sb.toString(), LENGTH_SHORT).show();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}