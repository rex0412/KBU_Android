package com.example.listviewrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class SimpleDetailActivity extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_detail);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_simple_detail);
        }
        TextView tvDetailInfo = findViewById(R.id.tv_detail_info);
        btnBack = findViewById(R.id.btn_back_detail);

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", 0);
        String name = intent.getStringExtra("NAME");
        int age = intent.getIntExtra("AGE", 0);
        String job = intent.getStringExtra("JOB");

        String ageStr = String.valueOf(age);
        String detailText = getString(R.string.detail_format, position, name, age, job);
        SpannableStringBuilder ssb = new SpannableStringBuilder(detailText);
        String ageTargetText = "(" + ageStr + " 세)";
        int ageTextStart = detailText.indexOf(ageTargetText);

        if (ageTextStart != -1) {
            int ageNumberStart = ageTextStart + 1;
            int ageNumberEnd = ageNumberStart + ageStr.length();
            ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.text_red)),
                    ageNumberStart, ageNumberEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tvDetailInfo.setText(ssb);
        btnBack.setOnClickListener(v -> finish());
        applyColors(intent);
    }

    private void applyColors(Intent intent) {
        int defaultTextColor = ContextCompat.getColor(this, R.color.default_button_text);
        int defaultBgColor = ContextCompat.getColor(this, R.color.default_button_bg);
        int textColor = intent.getIntExtra("TEXT_COLOR", defaultTextColor);
        int bgColor = intent.getIntExtra("BG_COLOR", defaultBgColor);
        btnBack.setTextColor(textColor);
        btnBack.setBackgroundColor(bgColor);
    }
}