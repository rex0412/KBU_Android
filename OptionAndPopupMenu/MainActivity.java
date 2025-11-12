package com.example.report2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button popupButton;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        popupButton = findViewById(R.id.popup_button);

        popupButton.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(getApplicationContext(), v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.popup_menu, popup.getMenu());

            Menu popupMenu = popup.getMenu();

            MenuItem bgSub = popupMenu.findItem(R.id.popup_submenu_bg);
            if (bgSub != null && bgSub.hasSubMenu()) {
                SpannableString s = new SpannableString(bgSub.getTitle());
                s.setSpan(new ForegroundColorSpan(Color.GREEN), 0, s.length(), 0);
                Objects.requireNonNull(bgSub.getSubMenu()).setHeaderTitle(s);
            }

            MenuItem textSub = popupMenu.findItem(R.id.popup_submenu_text);
            if (textSub != null && textSub.hasSubMenu()) {
                SpannableString s = new SpannableString(textSub.getTitle());
                s.setSpan(new ForegroundColorSpan(Color.GREEN), 0, s.length(), 0);
                Objects.requireNonNull(textSub.getSubMenu()).setHeaderTitle(s);
            }

            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.popup_bg_white ||
                        itemId == R.id.popup_bg_red ||
                        itemId == R.id.popup_bg_blue ||
                        itemId == R.id.popup_bg_green) {

                    item.setChecked(true);
                    Toast.makeText(getBaseContext(), "팝업메뉴 - 배경색 변경", Toast.LENGTH_SHORT).show();

                    if (itemId == R.id.popup_bg_white) {
                        popupButton.setBackgroundColor(Color.WHITE);
                    } else if (itemId == R.id.popup_bg_red) {
                        popupButton.setBackgroundColor(Color.RED);
                    } else if (itemId == R.id.popup_bg_blue) {
                        popupButton.setBackgroundColor(Color.BLUE);
                    } else if (itemId == R.id.popup_bg_green) {
                        popupButton.setBackgroundColor(Color.GREEN);
                    }
                    return true;

                } else if (itemId == R.id.popup_text_white ||
                        itemId == R.id.popup_text_black ||
                        itemId == R.id.popup_text_yellow) {

                    item.setChecked(true);
                    Toast.makeText(getBaseContext(), "팝업메뉴 - 글자색 변경", Toast.LENGTH_SHORT).show();

                    if (itemId == R.id.popup_text_white) {
                        popupButton.setTextColor(Color.WHITE);
                    } else if (itemId == R.id.popup_text_black) {
                        popupButton.setTextColor(Color.BLACK);
                    } else if (itemId == R.id.popup_text_yellow) {
                        popupButton.setTextColor(Color.YELLOW);
                    }
                    return true;

                } else if (itemId == R.id.popup_item_music) {
                    item.setChecked(!item.isChecked());
                    Toast.makeText(getBaseContext(), "배경음악 " + (item.isChecked() ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            });

            popup.show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        MenuItem bgSubMenu = menu.findItem(R.id.option_submenu_bg);
        if (bgSubMenu != null && bgSubMenu.hasSubMenu()) {
            SpannableString bgTitle = new SpannableString(bgSubMenu.getTitle());
            bgTitle.setSpan(new ForegroundColorSpan(Color.GRAY), 0, bgTitle.length(), 0);
            Objects.requireNonNull(bgSubMenu.getSubMenu()).setHeaderTitle(bgTitle);
        }

        MenuItem textSubMenu = menu.findItem(R.id.option_submenu_text);
        if (textSubMenu != null && textSubMenu.hasSubMenu()) {
            SpannableString textTitle = new SpannableString(textSubMenu.getTitle());
            textTitle.setSpan(new ForegroundColorSpan(Color.GRAY), 0, textTitle.length(), 0);
            Objects.requireNonNull(textSubMenu.getSubMenu()).setHeaderTitle(textTitle);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.option_bg_white ||
                itemId == R.id.option_bg_red ||
                itemId == R.id.option_bg_blue ||
                itemId == R.id.option_bg_green) {

            item.setChecked(true);
            Toast.makeText(this, "옵션 메뉴 - 배경색 변경", Toast.LENGTH_SHORT).show();

            if (itemId == R.id.option_bg_white) {
                toolbar.setBackgroundColor(Color.WHITE);
            } else if (itemId == R.id.option_bg_red) {
                toolbar.setBackgroundColor(Color.RED);
            } else if (itemId == R.id.option_bg_blue) {
                toolbar.setBackgroundColor(Color.BLUE);
            } else if (itemId == R.id.option_bg_green) {
                toolbar.setBackgroundColor(Color.GREEN);
            }
            return true;

        } else if (itemId == R.id.option_text_white ||
                itemId == R.id.option_text_black ||
                itemId == R.id.option_text_yellow) {

            item.setChecked(true);
            Toast.makeText(this, "옵션 메뉴 - 글자색 변경", Toast.LENGTH_SHORT).show();

            if (itemId == R.id.option_text_white) {
                toolbar.setTitleTextColor(Color.WHITE);
            } else if (itemId == R.id.option_text_black) {
                toolbar.setTitleTextColor(Color.BLACK);
            } else if (itemId == R.id.option_text_yellow) {
                toolbar.setTitleTextColor(Color.YELLOW);
            }
            return true;

        } else if (itemId == R.id.option_music) {
            item.setChecked(!item.isChecked());
            Toast.makeText(this, "옵션 메뉴 - 배경음악 " + (item.isChecked() ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}