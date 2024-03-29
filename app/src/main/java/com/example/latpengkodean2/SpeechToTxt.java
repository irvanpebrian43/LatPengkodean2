package com.example.latpengkodean2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pengkodean2.R;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToTxt extends AppCompatActivity {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speechtxt_layout);
        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);

        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hai, Ada yang bisa saya bantu " +
                "?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result =
                            data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                }
                break;
            }
        }
    }
}