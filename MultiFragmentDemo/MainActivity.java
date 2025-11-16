package com.example.report;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    Button btnList, btnDialog, btnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnList = findViewById(R.id.btn_list);
        btnDialog = findViewById(R.id.btn_dialog);
        btnFragment = findViewById(R.id.btn_fragment);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new OneFragment());
            transaction.commit();
        }

        btnList.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new OneFragment());
            transaction.commit();
        });

        btnDialog.setOnClickListener(v -> {
            TwoFragment dialogFragment = new TwoFragment();
            dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
        });

        btnFragment.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new ThreeFragment());
            transaction.commit();
        });
    }
}