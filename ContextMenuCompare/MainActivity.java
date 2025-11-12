package com.example.report1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView sampleTitleTextView;
    boolean isXmlMenu = true;

    private int mCurrentColor = Color.BLACK;

    final int COLOR_BLACK = 1;
    final int COLOR_RED = 2;
    final int COLOR_GREEN = 3;
    final int COLOR_BLUE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.textView);
        sampleTitleTextView = findViewById(R.id.sampleTitleTextView);

        registerForContextMenu(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.option_xml) {
            item.setChecked(true);
            isXmlMenu = true;
            sampleTitleTextView.setText(R.string.textBarXML);
            return true;
        } else if (itemId == R.id.option_java) {
            item.setChecked(true);
            isXmlMenu = false;
            sampleTitleTextView.setText(R.string.textBarJAVA);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (isXmlMenu) {
            String title = "글자색 변경(XML)";
            SpannableString s = new SpannableString(title);
            s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
            menu.setHeaderTitle(s);

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);

            if (mCurrentColor == Color.BLACK) menu.findItem(R.id.color_black).setChecked(true);
            else if (mCurrentColor == Color.RED) menu.findItem(R.id.color_red).setChecked(true);
            else if (mCurrentColor == Color.GREEN) menu.findItem(R.id.color_green).setChecked(true);
            else if (mCurrentColor == Color.BLUE) menu.findItem(R.id.color_blue).setChecked(true);

        } else {
            String title = "글자색 변경(JAVA)";
            SpannableString s = new SpannableString(title);
            s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
            menu.setHeaderTitle(s);

            menu.add(Menu.NONE, COLOR_BLACK, 1, getString(R.string.black));
            menu.add(Menu.NONE, COLOR_RED, 2, getString(R.string.red));
            menu.add(Menu.NONE, COLOR_GREEN, 3, getString(R.string.green));
            menu.add(Menu.NONE, COLOR_BLUE, 4, getString(R.string.blue));

            menu.setGroupCheckable(Menu.NONE, true, true);

            if (mCurrentColor == Color.BLACK) menu.findItem(COLOR_BLACK).setChecked(true);
            else if (mCurrentColor == Color.RED) menu.findItem(COLOR_RED).setChecked(true);
            else if (mCurrentColor == Color.GREEN) menu.findItem(COLOR_GREEN).setChecked(true);
            else if (mCurrentColor == Color.BLUE) menu.findItem(COLOR_BLUE).setChecked(true);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.color_black || itemId == COLOR_BLACK) {
            textView.setTextColor(Color.BLACK);
            mCurrentColor = Color.BLACK;
            item.setChecked(true);
            return true;
        } else if (itemId == R.id.color_red || itemId == COLOR_RED) {
            textView.setTextColor(Color.RED);
            mCurrentColor = Color.RED;
            item.setChecked(true);
            return true;
        } else if (itemId == R.id.color_green || itemId == COLOR_GREEN) {
            textView.setTextColor(Color.GREEN);
            mCurrentColor = Color.GREEN;
            item.setChecked(true);
            return true;
        } else if (itemId == R.id.color_blue || itemId == COLOR_BLUE) {
            textView.setTextColor(Color.BLUE);
            mCurrentColor = Color.BLUE;
            item.setChecked(true);
            return true;
        }

        return super.onContextItemSelected(item);
    }
}