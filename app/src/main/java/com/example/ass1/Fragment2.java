package com.example.ass1;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.ass1.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    public interface OnFragment2Listener {
        void onDataEntered(String name);
    }

    private OnFragment2Listener listener;
    private Fragment2Binding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2Listener) {
            listener = (OnFragment2Listener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void sendDataToActivity() {
        String name = binding.editTextName.getText().toString().trim();
        int selectedGenderId = binding.radioGroupGender.getCheckedRadioButtonId();

        if (name.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedGenderId == -1) {
            Toast.makeText(getContext(), "Please select your gender", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = binding.radioGroupGender.findViewById(selectedGenderId);
        String gender = selectedRadioButton.getText().toString();

        if (listener != null) {
            listener.onDataEntered(name);
        }
    }
}