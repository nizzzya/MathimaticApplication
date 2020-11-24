package com.nizzzya.mathimaticapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.nizzzya.mathimaticapplication.databinding.ActivityMainBinding;
import com.nizzzya.mathimaticapplication.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
    private int uRes, maxRes;

    SharedPreferences preferences;
    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        uRes = preferences.getInt("correctAnswer", 0);
        maxRes = preferences.getInt("maxResult", 0);

        String result = String.format("Ваш результат %d\nМаксимальный результат %d", uRes,maxRes);

        binding.textViewTableResult.setText(result);
    }
}