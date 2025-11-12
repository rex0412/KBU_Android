package com.example.report03;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

public class MainActivity extends AppCompatActivity {

    EditText etContent;
    TextView tvMode;
    SwitchCompat switchMode;
    Button btnContext;
    private int currentBgColorId = R.id.menu_bg_gray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContent = findViewById(R.id.et_content);
        tvMode = findViewById(R.id.tv_mode);
        switchMode = findViewById(R.id.switch_mode);
        btnContext = findViewById(R.id.btn_context_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnContext.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.context_menu, popup.getMenu());

            styleSubMenuHeader(popup.getMenu());
            MenuItem currentItem = popup.getMenu().findItem(currentBgColorId);
            if (currentItem != null) {
                currentItem.setChecked(true);
            }

            popup.setOnMenuItemClickListener(this::handleStyleMenuItem);
            popup.show();
        });

        etContent.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));

        int colorRed = ContextCompat.getColor(this, R.color.red);
        int colorGray = ContextCompat.getColor(this, R.color.gray);
        int colorBlack = ContextCompat.getColor(this, R.color.black);

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked },
                new int[] { -android.R.attr.state_checked }
        };

        int[] thumbColors = new int[] { colorRed, colorGray };
        ColorStateList thumbList = new ColorStateList(states, thumbColors);
        switchMode.setThumbTintList(thumbList);

        int[] trackColors = new int[] {
                ColorUtils.setAlphaComponent(colorRed, 128),
                ColorUtils.setAlphaComponent(colorGray, 128)
        };
        ColorStateList trackList = new ColorStateList(states, trackColors);
        switchMode.setTrackTintList(trackList);

        switchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvMode.setText(R.string.expert_mode);
                tvMode.setTextColor(colorRed);
            } else {
                tvMode.setText(R.string.beginner_mode);
                tvMode.setTextColor(colorBlack);
            }
            invalidateOptionsMenu();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();

        if (switchMode.isChecked()) {
            getMenuInflater().inflate(R.menu.menu_expert, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_beginner, menu);
        }

        getMenuInflater().inflate(R.menu.context_menu, menu);

        styleSubMenuHeader(menu);
        MenuItem currentItem = menu.findItem(currentBgColorId);
        if (currentItem != null) {
            currentItem.setChecked(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (handleStyleMenuItem(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void styleSubMenuHeader(Menu menu) {
        MenuItem bgMenuItem = menu.findItem(R.id.menu_bg);

        if (bgMenuItem != null && bgMenuItem.hasSubMenu()) {
            SubMenu subMenu = bgMenuItem.getSubMenu();
            SpannableString redTitle = new SpannableString(bgMenuItem.getTitle());
            int redColor = ContextCompat.getColor(this, R.color.red);
            redTitle.setSpan(new ForegroundColorSpan(redColor), 0, redTitle.length(), 0);
            assert subMenu != null;
            subMenu.setHeaderTitle(redTitle);
        }
    }

    private boolean handleStyleMenuItem(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_bg_gray) {
            etContent.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
            item.setChecked(true);
            currentBgColorId = id;
            return true;
        } else if (id == R.id.menu_bg_cyan) {
            etContent.setBackgroundColor(ContextCompat.getColor(this, R.color.cyan));
            item.setChecked(true);
            currentBgColorId = id;
            return true;
        } else if (id == R.id.menu_bg_red) {
            etContent.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            item.setChecked(true);
            currentBgColorId = id;
            return true;
        } else if (id == R.id.menu_bg_white) {
            etContent.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            item.setChecked(true);
            currentBgColorId = id;
            return true;
        } else if (id == R.id.menu_text_color_blue) {
            boolean checked = !item.isChecked();
            item.setChecked(checked);
            etContent.setTextColor(ContextCompat.getColor(this,
                    checked ? R.color.blue : R.color.black
            ));
            return true;
        } else if (id == R.id.menu_text_size_large) {
            boolean enlarged = !item.isChecked();
            item.setChecked(enlarged);
            etContent.setTextSize(enlarged ? 22 : 16);
            return true;
        } else if (id == R.id.menu_text_size_default) {
            etContent.setTextSize(16);
            return true;
        }

        return false;
    }
}