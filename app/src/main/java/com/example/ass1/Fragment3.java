package com.example.ass1;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.ass1.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    private Fragment3Binding binding;
    private String userName;

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString("USER_NAME_KEY", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString("USER_NAME_KEY");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvWelcome.setText("Welcome, " + userName + "!");

        binding.cbConfirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.btnFinish.setEnabled(true);
                binding.btnFinish.setText("Finish");
            } else {
                binding.btnFinish.setEnabled(false);
                binding.btnFinish.setText("Continue");
            }
        });

        binding.btnFinish.setOnClickListener(v -> {
            Toast toast = new Toast(getContext());

            android.widget.TextView textView = new android.widget.TextView(getContext());
            textView.setText("🎉 Registration Complete!\nWelcome.");
            textView.setTextColor(android.graphics.Color.WHITE);
            textView.setTextSize(18);
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setPadding(30, 30, 30, 30);
            textView.setBackgroundColor(getResources().getColor(com.example.ass1.R.color.purple_500));

            toast.setView(textView);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(android.view.Gravity.CENTER, 0, 0);
            toast.show();

            binding.btnFinish.setEnabled(false);
            binding.btnFinish.setText("Completed");
        });
    }
}