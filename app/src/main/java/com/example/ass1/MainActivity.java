package com.example.ass1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import com.example.ass1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Fragment2.OnFragment2Listener {

    private ActivityMainBinding binding;
    private int currentFragment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new Fragment1())
                    .commit();
        }

        binding.buttonContinue.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (currentFragment == 1) {
                transaction.replace(R.id.fragment_container, new Fragment2());
                transaction.addToBackStack(null);
                transaction.commit();
                currentFragment = 2;
            } else if (currentFragment == 2) {
                Fragment2 fragment2 = (Fragment2) fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment2 != null) {
                    fragment2.sendDataToActivity();
                }
            }
        });
    }

    @Override
    public void onDataEntered(String name) {
        Fragment3 fragment3 = Fragment3.newInstance(name);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();
        currentFragment = 3;

        binding.buttonContinue.setVisibility(View.GONE);
    }
}