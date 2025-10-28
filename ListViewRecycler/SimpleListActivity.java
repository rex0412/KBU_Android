package com.example.listviewrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class SimpleListActivity extends AppCompatActivity {

    private Button btnBack;
    private String[] members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        Toolbar toolbar = findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_simple_list);
        }

        ListView listView = findViewById(R.id.listview);
        btnBack = findViewById(R.id.btn_back_list);

        members = getResources().getStringArray(R.array.member_names);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_simple_list,
                R.id.list_item_text,
                members
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMember = members[position];
            Toast.makeText(getBaseContext(), selectedMember, Toast.LENGTH_SHORT).show();
        });

        btnBack.setOnClickListener(v -> finish());

        applyColors();
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