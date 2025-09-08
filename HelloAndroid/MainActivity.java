package com.example.helloandroid;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private boolean flag1 = true;
    private boolean flag2 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);

        textView1.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "토스트 입니다", Toast.LENGTH_SHORT).show();
            if (flag1) {
                textView1.setText(getString(R.string.convert1)); // getString() 사용
                flag1 = false;
            } else {
                textView1.setText(getString(R.string.message));
                flag1 = true;
            }
        });

        textView2.setOnClickListener(v -> {
            Snackbar.make(v, "스낵바 입니다", Snackbar.LENGTH_SHORT).show();
            if (flag2) {
                textView2.setText(getString(R.string.message));
                textView2.setTextColor(ContextCompat.getColor(this, R.color.green));
                flag2 = false;
            } else {
                textView2.setText(getString(R.string.message1));
                textView2.setTextColor(ContextCompat.getColor(this, R.color.blue));
                flag2 = true;
            }
        });
    }
}