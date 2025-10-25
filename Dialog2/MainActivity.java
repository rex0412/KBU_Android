package com.example.dialog2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(v -> {
            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIcon(R.drawable.key);
            progressDialog.setTitle("프로그래스 다이얼로그 (Spinner)");
            progressDialog.setMessage("현재 진행중입니다.");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "취소", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
            progressDialog.show();
        });

        button2.setOnClickListener(v -> {
            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIcon(R.drawable.key);
            progressDialog.setTitle("프로그래스 다이얼로그 (Horizontal)");
            progressDialog.setMessage("잠시만 기다려 주세요.");

            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);

            progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "취소", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });

            progressDialog.show();

            Handler handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    int progress = msg.arg1;
                    progressDialog.setProgress(progress);

                    if (progress >= 100) {
                        progressDialog.setMessage("로딩이 완료되었습니다");
                        Toast.makeText(getApplicationContext(), "로딩이 완료되었습니다", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

            new Thread(() -> {
                int progress = 0;
                while (progress <= 100 && progressDialog.isShowing()) {
                    try {
                        Thread.sleep(100);

                        Message message = handler.obtainMessage();
                        message.arg1 = progress;
                        handler.sendMessage(message);

                        progress++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        button3.setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_custom, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("커스텀 다이얼로그");
            builder.setIcon(R.drawable.mic);
            builder.setView(dialogView);

            final EditText editTextId = dialogView.findViewById(R.id.editTextId);
            final EditText editTextPwd = dialogView.findViewById(R.id.editTextPwd);
            final Button loginButton = dialogView.findViewById(R.id.loginButton);

            builder.setNegativeButton("취소", (dialog, which) -> dialog.dismiss());

            final AlertDialog dialog = builder.create();

            loginButton.setOnClickListener(loginBtnView -> {
                String id = editTextId.getText().toString().trim();
                String pwd = editTextPwd.getText().toString().trim();

                if (id.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "정확하게 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    String message = id + "/" + pwd;
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            dialog.show();
        });
    }
}