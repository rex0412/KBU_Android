package com.example.fruitlistview;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnShowResults;
    Button btnAddData;
    ArrayList<String> fruitList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnShowResults = findViewById(R.id.resultButton);
        btnAddData = findViewById(R.id.dataButton);

        String[] fruits = getResources().getStringArray(R.array.fruits);
        fruitList = new ArrayList<>(Arrays.asList(fruits));

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                fruitList
        );

        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        btnShowResults.setOnClickListener(v -> showResultsDialog());

        btnAddData.setOnClickListener(v -> showAddFruitDialog());
    }

    private void showAddFruitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_fruit, null);
        final EditText etFruitName = dialogView.findViewById(R.id.etFruitName);

        builder.setView(dialogView)
                .setTitle(R.string.add_label);

        builder.setPositiveButton(R.string.check, (dialog, which) -> {
            String newFruit = etFruitName.getText().toString().trim();

            if (!TextUtils.isEmpty(newFruit)) {
                fruitList.add(newFruit);
                adapter.notifyDataSetChanged();

                Toast.makeText(getBaseContext(), "입력하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void showResultsDialog() {
        final SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        final ArrayList<String> selectedFruits = new ArrayList<>();
        final ArrayList<Integer> selectedPositions = new ArrayList<>();

        for (int i = 0; i < adapter.getCount(); i++) {
            if (checkedItems.get(i)) {
                selectedFruits.add(adapter.getItem(i));
                selectedPositions.add(i);
            }
        }

        StringBuilder dialogMessage = new StringBuilder();
        for (String fruit : selectedFruits) {
            dialogMessage.append(fruit).append("\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(dialogMessage.toString().trim());

        builder.setPositiveButton(R.string.check, (dialog, which) -> dialog.dismiss());

        builder.setNegativeButton(R.string.delete, (dialog, which) -> {
            deleteSelectedItems(selectedPositions);
            dialog.dismiss();
        });

        builder.create().show();
    }

    private void deleteSelectedItems(ArrayList<Integer> selectedPositions) {
        selectedPositions.sort(Collections.reverseOrder());

        int deleteCount = selectedPositions.size();

        for (int position : selectedPositions) {
            String itemToRemove = adapter.getItem(position);
            fruitList.remove(itemToRemove);
        }

        listView.clearChoices();
        adapter.notifyDataSetChanged();

        String toastMessage = String.format("%d개 삭제하였습니다", deleteCount);
        Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }
}