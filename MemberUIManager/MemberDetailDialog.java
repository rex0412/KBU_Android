package com.example.exam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MemberDetailDialog extends DialogFragment {

    private static Member member;

    public static MemberDetailDialog newInstance(Member m) {
        MemberDetailDialog f = new MemberDetailDialog();
        member = m;
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_member_detail, null);

        ImageView iv = view.findViewById(R.id.iv_detail_profile);
        TextView tvName = view.findViewById(R.id.tv_detail_name);
        TextView tvAge = view.findViewById(R.id.tv_detail_age);
        TextView tvJob = view.findViewById(R.id.tv_detail_job);

        if (member != null) {
            iv.setImageResource(member.imageResId);
            tvName.setText(member.name);
            tvAge.setText("(" + member.getAge() + " 세)");
            tvJob.setText(member.job);
        }

        builder.setView(view)
                .setTitle("선택한 회원")
                .setPositiveButton("확인", null);

        return builder.create();
    }
}