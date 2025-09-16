package com.example.login;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etId = findViewById(R.id.editText_id);
        EditText etPw = findViewById(R.id.editText_password);
        Button btnCheck = findViewById(R.id.check);
        Button btnCancel = findViewById(R.id.cancel);

        btnCheck.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();
            String password = etPw.getText().toString().trim();

            if (id.equals("kyungbok") && password.equals("test1234!")) {
                etId.setText("");
                etPw.setText("");
                etId.requestFocus();
            } else if (id.equals("kyungbok")) {
                Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다", LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "id를 확인할 수 없습니다", LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}