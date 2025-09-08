package com.example.welcome;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        EditText editText = findViewById(R.id.editText);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> {
            String name = editText.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(getApplicationContext(), R.string.name_required_message, Toast.LENGTH_SHORT).show();
                Snackbar.make(v, R.string.name_required_message, Snackbar.LENGTH_SHORT).show();
            } else {
                String welcomeMessage = getString(R.string.welcome_message, name);
                textView.setText(welcomeMessage);
                editText.setText("");
            }
        });

        button2.setOnClickListener(v -> {
            editText.setText("");
            textView.setText(getString(R.string.default_program_text));
        });

        button2.setOnLongClickListener(v -> {
            Toast.makeText(getApplicationContext(), R.string.long_press_message, Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}