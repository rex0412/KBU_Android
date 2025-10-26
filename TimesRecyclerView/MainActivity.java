package com.example.timesrecyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private final String[] countries = {"신라", "고구려", "백제", "발해", "옥저", "동예", "부여", "대한민국"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, parent, false);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setBackgroundColor(getResources().getColor(R.color.red));
                textView.setTextColor(getResources().getColor(android.R.color.black));
                textView.setTextSize(18);
                textView.setPadding(30, 30, 30, 30);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                String country = countries[position];
                holder.textView.setText(country);

                holder.itemView.setOnClickListener(v ->
                        Toast.makeText(getBaseContext(),
                                country + "에 대하여 알아보자", Toast.LENGTH_SHORT).show());
            }

            @Override
            public int getItemCount() {
                return countries.length;
            }
        });
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}