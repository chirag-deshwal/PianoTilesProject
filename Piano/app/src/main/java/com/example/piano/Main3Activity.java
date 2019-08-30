package com.example.piano;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private Button button;
    int bestScore = 0;

    private static final String KEY_BESTSCORE= "bestscore_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        bestScore = preferences.getInt("bestscore", 0);

        Intent intent = getIntent();
        int currentScore = intent.getIntExtra("score",0);
        TextView score = findViewById(R.id.score);
        TextView best = findViewById(R.id.best);
        score.setText("Score: " + currentScore);

        if(currentScore > bestScore){
            bestScore = currentScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("bestscore", bestScore);
            editor.apply();
        }

        best.setText("Best: " + bestScore);

        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}

