package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {
    private static final String ARG_PAGE = "page_number";
    private static final String ARG_MODE = "display_mode";

    public static final int MODE_SLIDE = 1;
    public static final int MODE_TAB = 2;

    public static MyFragment newInstance(int page, int mode) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_MODE, mode);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        TextView textView = view.findViewById(R.id.pageTextView);

        assert getArguments() != null;
        int page = getArguments().getInt(ARG_PAGE);
        int mode = getArguments().getInt(ARG_MODE);

        view.setBackgroundColor(Color.LTGRAY);
        textView.setText(String.format("%d Page/Tab", page));

        if (mode == MODE_SLIDE) {
            view.setBackgroundColor(Color.parseColor("#9932CC"));
            textView.setText(String.format("%d Page", page));

        } else if (mode == MODE_TAB) {
            switch (page) {
                case 1:
                    view.setBackgroundColor(Color.parseColor("#8BC34A"));
                    textView.setText("첫번째 TAB");
                    break;
                case 2:
                    view.setBackgroundColor(Color.parseColor("#8BC34A"));
                    textView.setText("두번째 TAB");
                    break;
                case 3:
                    view.setBackgroundColor(Color.parseColor("#8BC34A"));
                    textView.setText("세번째 TAB");
                    break;
                case 4:
                    view.setBackgroundColor(Color.parseColor("#8BC34A"));
                    textView.setText("네번째 TAB");
                    break;
            }
        }
        return view;
    }
}