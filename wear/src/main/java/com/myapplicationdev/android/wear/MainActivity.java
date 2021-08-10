package com.myapplicationdev.android.wear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myapplicationdev.android.wear.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;
    private Button btnSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = findViewById(R.id.textView);
        btnSpeak = findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent
                        (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                startActivityForResult(intent, 1);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode== RESULT_OK){
            ArrayList<String> results = data.getStringArrayListExtra
                    (RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);

            Toast.makeText(MainActivity.this, spokenText,
                    Toast.LENGTH_SHORT).show();
        }
    }

}