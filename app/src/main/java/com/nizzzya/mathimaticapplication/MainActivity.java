package com.nizzzya.mathimaticapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nizzzya.mathimaticapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int maxNum = 40;
    private int maxGeneratorQuestion = 4;
    private int correctAnswer, countAnswer, maxResult;
    Rundomize rundomize;


    private ActivityMainBinding binding;
    private SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preference = PreferenceManager.getDefaultSharedPreferences(this);

        binding.textViewAnswear1.setOnClickListener(this);
        binding.textViewAnswear2.setOnClickListener(this);
        binding.textViewAnswear3.setOnClickListener(this);
        binding.textViewAnswear4.setOnClickListener(this);


        rundomize = new Rundomize(maxNum, maxGeneratorQuestion);

        binding.textViewTask.setText(rundomize.getA() + "+" + rundomize.getB());
        binding.textViewAnswear1.setText(rundomize.getResultList().get(0).toString());
        binding.textViewAnswear2.setText(rundomize.getResultList().get(1).toString());
        binding.textViewAnswear3.setText(rundomize.getResultList().get(2).toString());
        binding.textViewAnswear4.setText(rundomize.getResultList().get(3).toString());

        CountDownTimer timer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                binding.textViewTimer.setText("00:"+seconds);
            }

            @Override
            public void onFinish() {
                preference.edit().putInt("correctAnswer", correctAnswer).apply();
                maxResult = preference.getInt("maxResult", 0);
                if ( maxResult < correctAnswer) {
                    preference.edit().putInt("maxResult", correctAnswer).apply();
                }

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        };
        timer.start();

    }

    @Override
    public void onClick(View v) {
        TextView but = (TextView) v;
        int butText = Integer.parseInt(but.getText().toString());

        if ( butText == rundomize.getCorectAnswer()) {
            correctAnswer += 1;
            countAnswer += 1;
        } else {
            countAnswer += 1;
        }
        String uResult = String.format("%d:%d", correctAnswer, countAnswer);
        binding.textViewResult.setText(uResult);


        rundomize = new Rundomize(maxNum, maxGeneratorQuestion);

        binding.textViewTask.setText(rundomize.getA() + "+" + rundomize.getB());
        binding.textViewAnswear1.setText(rundomize.getResultList().get(0).toString());
        binding.textViewAnswear2.setText(rundomize.getResultList().get(1).toString());
        binding.textViewAnswear3.setText(rundomize.getResultList().get(2).toString());
        binding.textViewAnswear4.setText(rundomize.getResultList().get(3).toString());

    }
}