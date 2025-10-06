package com.example.switchonoff;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwitchCompat toggle = findViewById(R.id.toggle);
        LinearLayout androidLayout = findViewById(R.id.android);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        ImageView image = findViewById(R.id.image);

        androidLayout.setVisibility(View.GONE);

        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                androidLayout.setVisibility(View.VISIBLE);
            } else {
                androidLayout.setVisibility(View.GONE);
            }
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio1) {
                image.setImageResource(R.drawable.jellybean);
                Toast.makeText(getBaseContext(), "젤리빈(4.3)이 선택되었습니다.", LENGTH_SHORT).show();
            } else if (checkedId == R.id.radio2) {
                image.setImageResource(R.drawable.kitkat);
                Toast.makeText(getBaseContext(), "킷켓(4.4)이 선택되었습니다.", LENGTH_SHORT).show();
            } else if (checkedId == R.id.radio3) {
                image.setImageResource(R.drawable.lollipop);
                Toast.makeText(getBaseContext(), "롤리팝(5.0)이 선택되었습니다.", LENGTH_SHORT).show();
            }
        });
    }
}