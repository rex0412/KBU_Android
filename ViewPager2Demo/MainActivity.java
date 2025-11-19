package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private LinearLayout firstLayout;
    private LinearLayout secondLayout;
    private ViewPager2 viewPagerSlide;
    private CheckBox chkToggle, chkToggle2;

    private final String[] TAB_TITLES = {"HOME", "CHATTING", "NEWS", "SETTING"};
    private final int[] TAB_ICONS = {
            android.R.drawable.ic_menu_compass,
            android.R.drawable.ic_menu_send,
            android.R.drawable.ic_menu_agenda,
            android.R.drawable.ic_menu_preferences
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstLayout = findViewById(R.id.first);
        secondLayout = findViewById(R.id.second);
        viewPagerSlide = findViewById(R.id.viewPagerSlide);
        ViewPager2 viewPagerTab = findViewById(R.id.viewPagerTab);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        Button btnSlideMode = findViewById(R.id.btnSlideMode);
        chkToggle = findViewById(R.id.chkToggle);
        chkToggle2 = findViewById(R.id.chkToggle2);
        LinearLayout layoutToggleContainer1 = findViewById(R.id.layoutToggleContainer1);
        LinearLayout layoutToggleContainer2 = findViewById(R.id.layoutToggleContainer2);

        FragmentPagerAdapter slideAdapter = new FragmentPagerAdapter(this, MyFragment.MODE_SLIDE);
        viewPagerSlide.setAdapter(slideAdapter);
        viewPagerSlide.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        btnSlideMode.setOnClickListener(v -> {
            if (viewPagerSlide.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                viewPagerSlide.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                btnSlideMode.setText(R.string.verticalSlide);
            } else {
                viewPagerSlide.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                btnSlideMode.setText(R.string.widthSlide);
            }
        });

        FragmentPagerAdapter tabAdapter = new FragmentPagerAdapter(this, MyFragment.MODE_TAB);
        viewPagerTab.setAdapter(tabAdapter);
        viewPagerTab.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        new TabLayoutMediator(tabLayout, viewPagerTab, (tab, position) -> {
            tab.setText(TAB_TITLES[position]);
            tab.setIcon(TAB_ICONS[position]);
        }).attach();

        View.OnClickListener toggleListener = v -> {
            boolean isChecked;
            if (v.getId() == R.id.layoutToggleContainer1) {
                isChecked = !chkToggle.isChecked();
            } else {
                isChecked = !chkToggle2.isChecked();
            }

            if (isChecked) {
                firstLayout.setVisibility(View.INVISIBLE);
                secondLayout.setVisibility(View.VISIBLE);
                chkToggle.setChecked(true);
                chkToggle2.setChecked(true);
            } else {
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.INVISIBLE);
                chkToggle.setChecked(false);
                chkToggle2.setChecked(false);
            }
        };

        layoutToggleContainer1.setOnClickListener(toggleListener);
        layoutToggleContainer2.setOnClickListener(toggleListener);

        firstLayout.setVisibility(View.VISIBLE);
        secondLayout.setVisibility(View.INVISIBLE);
        chkToggle.setChecked(false);
        chkToggle2.setChecked(false);
    }
}