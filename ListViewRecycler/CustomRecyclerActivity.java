package com.example.listviewrecycler;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CustomRecyclerActivity extends AppCompatActivity {

    private Button btnBack;
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recycler);

        Toolbar toolbar = findViewById(R.id.toolbar_custom_recycler);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_custom_recycler);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview_custom);
        btnBack = findViewById(R.id.btn_back_custom_recycler);

        loadMembers();

        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter(memberList, (position, member) -> showCustomDetailDialog(member));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        btnBack.setOnClickListener(v -> finish());
        applyColors();
    }

    private void loadMembers() {
        memberList = new ArrayList<>();
        String[] names = getResources().getStringArray(R.array.member_names);
        String[] years = getResources().getStringArray(R.array.member_birth_years);
        String[] jobs = getResources().getStringArray(R.array.job);
        TypedArray photos = getResources().obtainTypedArray(R.array.member_photos);

        for (int i = 0; i < names.length; i++) {
            int photoResId = photos.getResourceId(i, android.R.drawable.ic_menu_gallery);
            memberList.add(new Member(names[i], years[i], jobs[i], photoResId));
        }
        photos.recycle();
    }

    private void showCustomDetailDialog(Member member) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_custom_detail, null);
        builder.setView(dialogView);

        ImageView ivPhoto = dialogView.findViewById(R.id.iv_dialog_photo);
        TextView tvInfo = dialogView.findViewById(R.id.tv_dialog_info);
        TextView tvJob = dialogView.findViewById(R.id.tv_dialog_job);
        Button btnConfirm = dialogView.findViewById(R.id.btn_dialog_confirm);

        ivPhoto.setImageResource(member.getMemberAssetId());

        String ageStr = String.valueOf(member.getAge());
        String infoText = String.format(Locale.KOREAN, "%s (%s세)", member.getName(), ageStr);
        SpannableStringBuilder ssbInfo = new SpannableStringBuilder(infoText);
        int ageStart = infoText.indexOf("(" + ageStr);
        int ageEnd = ageStart + ageStr.length() + 1;

        if (ageStart != -1) {
            ssbInfo.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.text_red)),
                    ageStart + 1, ageEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tvInfo.setText(ssbInfo);

        tvJob.setText(member.getJob());
        tvJob.setTextColor(ContextCompat.getColor(this, R.color.text_blue));

        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(CustomRecyclerActivity.this, "확인하였습니다", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

    private void applyColors() {
        Intent intent = getIntent();
        int defaultTextColor = ContextCompat.getColor(this, R.color.default_button_text);
        int defaultBgColor = ContextCompat.getColor(this, R.color.default_button_bg);
        int textColor = intent.getIntExtra("TEXT_COLOR", defaultTextColor);
        int bgColor = intent.getIntExtra("BG_COLOR", defaultBgColor);
        btnBack.setTextColor(textColor);
        btnBack.setBackgroundColor(bgColor);
    }
}