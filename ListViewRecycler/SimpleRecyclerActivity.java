package com.example.listviewrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecyclerActivity extends AppCompatActivity {

    private Button btnBack;
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycler);

        Toolbar toolbar = findViewById(R.id.toolbar_simple_recycler);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_simple_recycler);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview_simple);
        btnBack = findViewById(R.id.btn_back_simple_recycler);

        loadMembers();

        SimpleRecyclerAdapter adapter = new SimpleRecyclerAdapter(memberList, (position, member) -> {
            Intent intent = new Intent(SimpleRecyclerActivity.this, SimpleDetailActivity.class);
            intent.putExtra("POSITION", position + 1);
            intent.putExtra("NAME", member.getName());
            intent.putExtra("AGE", member.getAge());
            intent.putExtra("JOB", member.getJob());
            intent.putExtra("TEXT_COLOR", getIntent().getIntExtra("TEXT_COLOR", 0));
            intent.putExtra("BG_COLOR", getIntent().getIntExtra("BG_COLOR", 0));
            startActivity(intent);
        });

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
        for (int i = 0; i < names.length; i++) {
            memberList.add(new Member(names[i], years[i], jobs[i], 0));
        }
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