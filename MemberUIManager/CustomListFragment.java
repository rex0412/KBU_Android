package com.example.exam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout rootLayout = new LinearLayout(getContext());
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        TextView tvHeader = new TextView(getContext());
        tvHeader.setText("회원 명단");
        tvHeader.setTextSize(20);
        tvHeader.setTextColor(Color.DKGRAY);
        tvHeader.setGravity(Gravity.CENTER);
        tvHeader.setBackgroundColor(Color.WHITE);
        tvHeader.setPadding(0, 40, 0, 40);

        rootLayout.addView(tvHeader, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setBackgroundColor(Color.TRANSPARENT);
        rv.setAdapter(new MyCustomAdapter());

        LinearLayout.LayoutParams rvParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0);
        rvParams.weight = 1;
        rv.setLayoutParams(rvParams);
        rootLayout.addView(rv);

        return rootLayout;
    }

    class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Member m = MainActivity.memberList.get(position);
            holder.iv.setImageResource(m.imageResId);
            holder.tvName.setText(m.name);
            holder.tvAge.setText("(" + m.getAge() + " 세)");
            holder.tvJob.setText(m.job);

            holder.itemView.setOnClickListener(v -> {
                MemberDetailDialog.newInstance(m).show(getParentFragmentManager(), "detail");
            });
        }

        @Override
        public int getItemCount() {
            return MainActivity.memberList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;
            TextView tvName, tvAge, tvJob;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.iv_profile);
                tvName = itemView.findViewById(R.id.tv_name);
                tvAge = itemView.findViewById(R.id.tv_age);
                tvJob = itemView.findViewById(R.id.tv_job);
            }
        }
    }
}