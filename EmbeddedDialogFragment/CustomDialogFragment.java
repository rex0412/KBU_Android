package com.example.report2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CustomDialogFragment extends Fragment {

    private EditText etName;
    private RadioGroup radioGroupGender;
    private Button btnConfirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_custom, container, false);

        etName = view.findViewById(R.id.et_name);
        radioGroupGender = view.findViewById(R.id.radio_group_gender);
        btnConfirm = view.findViewById(R.id.btn_confirm);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String gender = "";
                int selectedId = radioGroupGender.getCheckedRadioButtonId();

                RadioButton selectedRadioButton = view.findViewById(selectedId);

                if (selectedRadioButton != null) {
                    gender = selectedRadioButton.getText().toString();
                }

                String message = name + " " + gender;
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}