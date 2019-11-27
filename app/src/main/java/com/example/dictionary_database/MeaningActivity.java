package com.example.dictionary_database;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MeaningActivity extends AppCompatActivity {

    private TextView tvMeaning;

    private TextToSpeech convertToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);
        tvMeaning = findViewById(R.id.tvMeaning);

        //Intent
        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){

            String meaning = bundle.getString("meaning");
            String word = bundle.getString("word");
            this.setTitle(word);
            tvMeaning.setText(meaning);

        }

        else
            {

            Toast.makeText(this,"No Meaning",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.voice:
                Bundle bundle = getIntent().getExtras();

                if (bundle != null) {
                    final String word = bundle.getString("meaning");
                    convertToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

                        @Override
                        public void onInit(int status) {

                            if(status != TextToSpeech.ERROR){
                                convertToSpeech.setLanguage(Locale.US);
                                convertToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null);
                            }
                        }
                    });
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}