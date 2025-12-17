package com.example.exam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ColorPickerDialog extends DialogFragment {

    public interface ColorPickerListener {
        void onColorSelected(String type, int r, int g, int b);
    }

    public static ColorPickerDialog newInstance(String type) {
        ColorPickerDialog f = new ColorPickerDialog();
        Bundle args = new Bundle();
        args.putString("type", type);
        f.setArguments(args);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String type = getArguments().getString("type");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_color, null);

        SeekBar sbR = view.findViewById(R.id.sb_red);
        SeekBar sbG = view.findViewById(R.id.sb_green);
        SeekBar sbB = view.findViewById(R.id.sb_blue);
        TextView tvR = view.findViewById(R.id.tv_r);
        TextView tvG = view.findViewById(R.id.tv_g);
        TextView tvB = view.findViewById(R.id.tv_b);
        Button btnPreview = view.findViewById(R.id.btn_preview);
        TextView tvTitle = view.findViewById(R.id.tv_title);

        tvTitle.setText(type.equals("text") ? "탭 글자 색을 선택해 주세요!" : "탭 배경 색을 선택해 주세요!");

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int r = sbR.getProgress();
                int g = sbG.getProgress();
                int b = sbB.getProgress();
                tvR.setText("Red : " + r);
                tvG.setText("Green : " + g);
                tvB.setText("Blue : " + b);

                int color = Color.rgb(r, g, b);
                if (type.equals("text")) btnPreview.setTextColor(color);
                else btnPreview.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        sbR.setOnSeekBarChangeListener(listener);
        sbG.setOnSeekBarChangeListener(listener);
        sbB.setOnSeekBarChangeListener(listener);

        builder.setView(view)
                .setPositiveButton("확인", (dialog, which) -> {
                    ((ColorPickerListener) getActivity()).onColorSelected(type, sbR.getProgress(), sbG.getProgress(), sbB.getProgress());
                })
                .setNegativeButton("취소", null);

        return builder.create();
    }
}