package com.example.listviewrecycler;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.MemberViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position, Member member);
    }

    private final List<Member> memberList;
    private final OnItemClickListener listener;

    public CustomRecyclerAdapter(List<Member> memberList, OnItemClickListener listener) {
        this.memberList = memberList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom_recycler, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.bind(member, position, listener);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName;
        TextView tvAge;
        TextView tvJob;
        Context context;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            ivPhoto = itemView.findViewById(R.id.iv_member_photo);
            tvName = itemView.findViewById(R.id.tv_member_name_custom);
            tvAge = itemView.findViewById(R.id.tv_member_age_custom);
            tvJob = itemView.findViewById(R.id.tv_member_job_custom);
        }

        public void bind(Member member, int position, OnItemClickListener listener) {
            tvName.setText(member.getName());
            tvName.setTextColor(ContextCompat.getColor(context, R.color.text_green));

            String ageStr = String.valueOf(member.getAge());
            String ageText = String.format(Locale.KOREAN, "(%s세)", ageStr);
            SpannableStringBuilder ssbAge = new SpannableStringBuilder(ageText);
            int ageStart = ageText.indexOf(ageStr);
            int ageEnd = ageStart + ageStr.length();

            if (ageStart != -1) {
                ssbAge.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.text_red)),
                        ageStart, ageEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            tvAge.setText(ssbAge);

            tvJob.setText(member.getJob());
            tvJob.setTextColor(ContextCompat.getColor(context, R.color.text_blue));

            ivPhoto.setImageResource(member.getMemberAssetId());
            itemView.setOnClickListener(v -> listener.onItemClick(position, member));
        }
    }
}