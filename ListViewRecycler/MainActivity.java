package com.example.listviewrecycler;

import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button btnSimpleList, btnSimpleRecycler, btnCustomRecycler;
    private SwitchCompat switchTextColor;
    private CheckBox checkBgColor;
    private ToggleButton toggleBgImage;
    private ImageView ivBackground;
    private int currentTextColor;
    private int currentBgColor;
    private int defaultTextColor;
    private int defaultBgColor;
    private int tempRed, tempGreen, tempBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        defaultTextColor = ContextCompat.getColor(this, R.color.default_button_text);
        defaultBgColor = ContextCompat.getColor(this, R.color.default_button_bg);
        currentTextColor = defaultTextColor;
        currentBgColor = defaultBgColor;

        ivBackground = findViewById(R.id.iv_background);
        btnSimpleList = findViewById(R.id.btn_simple_list);
        btnSimpleRecycler = findViewById(R.id.btn_simple_recycler);
        btnCustomRecycler = findViewById(R.id.btn_custom_recycler);
        switchTextColor = findViewById(R.id.switch_text_color);
        checkBgColor = findViewById(R.id.check_bg_color);
        toggleBgImage = findViewById(R.id.toggle_bg_image);

        applyButtonColors();
        setupListeners();
    }

    private void setupListeners() {
        toggleBgImage.setOnCheckedChangeListener((buttonView, isChecked) -> ivBackground.setVisibility(isChecked ? View.VISIBLE : View.GONE));

        switchTextColor.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showColorDialog(true);
            } else {
                currentTextColor = defaultTextColor;
                applyButtonColors();
            }
        });

        checkBgColor.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showColorDialog(false);
            } else {
                currentBgColor = defaultBgColor;
                applyButtonColors();
            }
        });

        btnSimpleList.setOnClickListener(v -> startNewActivity(SimpleListActivity.class));
        btnSimpleRecycler.setOnClickListener(v -> startNewActivity(SimpleRecyclerActivity.class));
        btnCustomRecycler.setOnClickListener(v -> startNewActivity(CustomRecyclerActivity.class));
    }

    private void startNewActivity(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        intent.putExtra("TEXT_COLOR", currentTextColor);
        intent.putExtra("BG_COLOR", currentBgColor);
        startActivity(intent);
    }

    private void applyButtonColors() {
        btnSimpleList.setBackgroundColor(currentBgColor);
        btnSimpleList.setTextColor(currentTextColor);
        btnSimpleRecycler.setBackgroundColor(currentBgColor);
        btnSimpleRecycler.setTextColor(currentTextColor);
        btnCustomRecycler.setBackgroundColor(currentBgColor);
        btnCustomRecycler.setTextColor(currentTextColor);
    }

    private void showColorDialog(boolean isForText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_color_picker, null);
        builder.setView(dialogView);

        TextView tvTitle = dialogView.findViewById(R.id.tv_dialog_title);
        SeekBar seekBarRed = dialogView.findViewById(R.id.seekbar_red);
        SeekBar seekBarGreen = dialogView.findViewById(R.id.seekbar_green);
        SeekBar seekBarBlue = dialogView.findViewById(R.id.seekbar_blue);
        TextView tvRed = dialogView.findViewById(R.id.tv_red);
        TextView tvGreen = dialogView.findViewById(R.id.tv_green);
        TextView tvBlue = dialogView.findViewById(R.id.tv_blue);
        Button btnPreview = dialogView.findViewById(R.id.btn_preview);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);

        int initialColor = isForText ? currentTextColor : currentBgColor;
        tempRed = Color.red(initialColor);
        tempGreen = Color.green(initialColor);
        tempBlue = Color.blue(initialColor);

        if (isForText) {
            tvTitle.setText(R.string.dialog_title_text_color);
            btnPreview.setTextColor(initialColor);
            btnPreview.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_bg));
        } else {
            tvTitle.setText(R.string.dialog_title_bg_color);
            btnPreview.setBackgroundColor(initialColor);
            btnPreview.setTextColor(ContextCompat.getColor(this, R.color.default_button_text));
        }

        seekBarRed.setProgress(tempRed);
        seekBarGreen.setProgress(tempGreen);
        seekBarBlue.setProgress(tempBlue);
        updateColorTextView(tvRed, "Red", tempRed);
        updateColorTextView(tvGreen, "Green", tempGreen);
        updateColorTextView(tvBlue, "Blue", tempBlue);

        setSeekBarTint(seekBarRed, ContextCompat.getColor(this, R.color.seekbar_red));
        setSeekBarTint(seekBarGreen, ContextCompat.getColor(this, R.color.seekbar_green));
        setSeekBarTint(seekBarBlue, ContextCompat.getColor(this, R.color.seekbar_blue));

        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setDimAmount(0.8f);

        SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getId() == R.id.seekbar_red) {
                    tempRed = progress;
                    updateColorTextView(tvRed, "Red", tempRed);
                } else if (seekBar.getId() == R.id.seekbar_green) {
                    tempGreen = progress;
                    updateColorTextView(tvGreen, "Green", tempGreen);
                } else if (seekBar.getId() == R.id.seekbar_blue) {
                    tempBlue = progress;
                    updateColorTextView(tvBlue, "Blue", tempBlue);
                }
                int previewColor = Color.rgb(tempRed, tempGreen, tempBlue);
                if (isForText) {
                    btnPreview.setTextColor(previewColor);
                } else {
                    btnPreview.setBackgroundColor(previewColor);
                }
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        seekBarRed.setOnSeekBarChangeListener(seekBarListener);
        seekBarGreen.setOnSeekBarChangeListener(seekBarListener);
        seekBarBlue.setOnSeekBarChangeListener(seekBarListener);

        btnCancel.setOnClickListener(v -> {
            if (isForText) {
                switchTextColor.setChecked(false);
            } else {
                checkBgColor.setChecked(false);
            }
            dialog.dismiss();
        });

        btnConfirm.setOnClickListener(v -> {
            int newColor = Color.rgb(tempRed, tempGreen, tempBlue);
            if (isForText) {
                currentTextColor = newColor;
            } else {
                currentBgColor = newColor;
            }
            applyButtonColors();
            dialog.dismiss();
        });

        dialog.setOnCancelListener(dialogInterface -> {
            if (isForText) {
                switchTextColor.setChecked(false);
            } else {
                checkBgColor.setChecked(false);
            }
        });
        dialog.show();
    }

    private void updateColorTextView(TextView tv, String colorName, int value) {
        String hex = String.format(Locale.KOREAN, "%02X", value);
        int stringId;

        if ("Red".equals(colorName)) {
            stringId = R.string.dialog_red;
        } else if ("Green".equals(colorName)) {
            stringId = R.string.dialog_green;
        } else if ("Blue".equals(colorName)) {
            stringId = R.string.dialog_blue;
        } else {
            stringId = 0;
        }

        if (stringId != 0) {
            tv.setText(getString(stringId, value, hex));
        } else {
            tv.setText(String.format(Locale.KOREAN, "%s : %d (%s)", colorName, value, hex));
        }
    }

    private void setSeekBarTint(SeekBar seekBar, int color) {
        seekBar.getProgressDrawable().setColorFilter(new BlendModeColorFilter(color, BlendMode.SRC_IN));
        seekBar.getThumb().setColorFilter(new BlendModeColorFilter(color, BlendMode.SRC_IN));
    }
}