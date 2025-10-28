package com.example.listviewrecycler;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.MemberViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position, Member member);
    }

    private final List<Member> memberList;
    private final OnItemClickListener listener;

    public SimpleRecyclerAdapter(List<Member> memberList, OnItemClickListener listener) {
        this.memberList = memberList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_simple_recycler, parent, false);
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
        TextView tvName;
        TextView tvInfo;
        Context context;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvName = itemView.findViewById(R.id.tv_member_name);
            tvInfo = itemView.findViewById(R.id.tv_member_info);
        }

        public void bind(Member member, int position, OnItemClickListener listener) {
            tvName.setText(member.getName());
            tvName.setTextColor(ContextCompat.getColor(context, R.color.text_green));

            String ageStr = String.valueOf(member.getAge());
            String infoFormat = "(%s세) %s";
            String infoText = String.format(Locale.KOREAN, infoFormat, ageStr, member.getJob());
            SpannableStringBuilder ssb = new SpannableStringBuilder(infoText);
            int ageStart = infoText.indexOf(ageStr);
            int ageEnd = ageStart + ageStr.length();
            int jobStart = infoText.indexOf(member.getJob());
            int jobEnd = jobStart + member.getJob().length();

            if (ageStart != -1) {
                ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.text_red)),
                        ageStart, ageEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (jobStart != -1) {
                ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.text_blue)),
                        jobStart, jobEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            tvInfo.setText(ssb);
            itemView.setOnClickListener(v -> listener.onItemClick(position, member));
        }
    }
}