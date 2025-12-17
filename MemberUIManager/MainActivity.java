package com.example.exam;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ColorPickerDialog.ColorPickerListener {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    ImageView ivBackground;
    Toolbar toolbar;

    public static ArrayList<Member> memberList = new ArrayList<>();

    BroadcastReceiver toastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("기말 수행 평가");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        initData();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        ivBackground = findViewById(R.id.iv_background);

        viewPager.setAdapter(new MyPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("LISTVIEW");
                    break;
                case 1:
                    tab.setText("RECYCLERVIEW");
                    break;
                case 2:
                    tab.setText("CUSTOMLIST");
                    break;
            }
        }).attach();

        IntentFilter filter = new IntentFilter("com.example.exam.SHOW_TOAST");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(toastReceiver, filter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(toastReceiver, filter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(toastReceiver);
    }

    private void initData() {
        String[] names = getResources().getStringArray(R.array.member);
        String[] births = getResources().getStringArray(R.array.birth);
        String[] jobs = getResources().getStringArray(R.array.job);

        memberList.clear();
        for (int i = 0; i < names.length; i++) {
            int resId = getResources().getIdentifier("face" + (i + 1), "drawable", getPackageName());
            if (resId == 0) resId = R.mipmap.ic_launcher;
            memberList.add(new Member(names[i], Integer.parseInt(births[i]), jobs[i], resId));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_tab_text) {
            ColorPickerDialog.newInstance("text").show(getSupportFragmentManager(), "color_text");
            return true;
        } else if (id == R.id.menu_tab_bg) {
            ColorPickerDialog.newInstance("bg").show(getSupportFragmentManager(), "color_bg");
            return true;
        } else if (id == R.id.menu_bg_image) {
            item.setChecked(!item.isChecked());
            ivBackground.setVisibility(item.isChecked() ? View.VISIBLE : View.INVISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onColorSelected(String type, int r, int g, int b) {
        int color = Color.rgb(r, g, b);
        if ("text".equals(type)) {
            tabLayout.setTabTextColors(color, color);
        } else {
            tabLayout.setBackgroundColor(color);
        }
    }

    class MyPagerAdapter extends FragmentStateAdapter {
        public MyPagerAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) return new ListViewFragment();
            else if (position == 1) return new RecyclerViewFragment();
            else return new CustomListFragment();
        }
    }
}