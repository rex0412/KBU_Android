package com.example.notice;

import static android.graphics.Color.RED;
import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setSelected(true);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setTextColor(RED);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Toast.makeText(getBaseContext(), R.string.button_clicked_toast, LENGTH_SHORT).show();
        });
    }

    public void textViewClicked(View view) {
        Toast.makeText(getBaseContext(), R.string.textview_clicked_toast, LENGTH_SHORT).show();
    }
}