package com.example.piano;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    ImageView tile10, tile11, tile12, tile13, tile20, tile21, tile22, tile23, tile30, tile31, tile32, tile33, tile40, tile41, tile42, tile43;
    TextView time, score;
    int currentScore = 0;
    CountDownTimer timer;
    Random r;
    int TileLocationRow1, TileLocationRow2, TileLocationRow3, TileLocationRow4;
    int blackKey, emptyKey, blackTile;
    private SoundPool soundPool;
    private int sound_a, sound_b, sound_c, sound_d;

    private void loadImages(){
        emptyKey = R.drawable.empty_key;
        blackKey = R.drawable.key_black;
        blackTile = R.drawable.tile_black;
    }

    private int millisToTime(long millis){
        return (int) millis/1000;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tile10 = findViewById(R.id.tile10);
        tile11 = findViewById(R.id.tile11);
        tile12 = findViewById(R.id.tile12);
        tile13 = findViewById(R.id.tile13);
        tile20 = findViewById(R.id.tile20);
        tile21 = findViewById(R.id.tile21);
        tile22 = findViewById(R.id.tile22);
        tile23 = findViewById(R.id.tile23);
        tile30 = findViewById(R.id.tile30);
        tile31 = findViewById(R.id.tile31);
        tile32 = findViewById(R.id.tile32);
        tile33 = findViewById(R.id.tile33);
        tile40 = findViewById(R.id.tile40);
        tile41 = findViewById(R.id.tile41);
        tile42 = findViewById(R.id.tile42);
        tile43 = findViewById(R.id.tile43);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            soundPool = new SoundPool.Builder().setMaxStreams(5).build();
        }
        else{
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }

        sound_a = soundPool.load(this, R.raw.a1,1);
        sound_b = soundPool.load(this, R.raw.b1,1);
        sound_c = soundPool.load(this, R.raw.c1,1);
        sound_d = soundPool.load(this, R.raw.d1, 1);

        score = findViewById(R.id.score);
        score.setText("Score: " + currentScore);

        time = findViewById(R.id.time);
        time.setText("Time: " + millisToTime(20000));

        r = new Random();
        loadImages();

        timer = new CountDownTimer(21000, 500){
            @Override
            public void onTick(long millisUntilFinished){
                time.setText("Time: " + millisToTime(millisUntilFinished));
            }
            @Override
            public void onFinish(){
                time.setText("Time: " + millisToTime(0));

                tile40.setEnabled(false);
                tile41.setEnabled(false);
                tile42.setEnabled(false);
                tile43.setEnabled(false);

                tile10.setImageResource(emptyKey);
                tile11.setImageResource(emptyKey);
                tile12.setImageResource(emptyKey);
                tile13.setImageResource(emptyKey);
                tile20.setImageResource(emptyKey);
                tile21.setImageResource(emptyKey);
                tile22.setImageResource(emptyKey);
                tile23.setImageResource(emptyKey);
                tile30.setImageResource(emptyKey);
                tile31.setImageResource(emptyKey);
                tile32.setImageResource(emptyKey);
                tile33.setImageResource(emptyKey);
                tile40.setImageResource(emptyKey);
                tile41.setImageResource(emptyKey);
                tile42.setImageResource(emptyKey);
                tile43.setImageResource(emptyKey);

                Toast.makeText(Main2Activity.this, "Time is up!", Toast.LENGTH_SHORT).show();

                openActivity3();
            }
        };

        tile40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TileLocationRow4 == 1){
                    tile40.setSoundEffectsEnabled(false);
                    soundPool.play(sound_a, 1, 1, 0, 0, 1);
                    continueGame();
                }
                else{
                    endGame();
                }
            }
        });

        tile41.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TileLocationRow4 == 2){
                    tile41.setSoundEffectsEnabled(false);
                    soundPool.play(sound_b, 1, 1, 0, 0, 1);
                    continueGame();
                }
                else{
                    endGame();
                }
            }
        });

        tile42.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TileLocationRow4 == 3){
                    tile42.setSoundEffectsEnabled(false);
                    soundPool.play(sound_c, 1, 1, 0, 0, 1);
                    continueGame();
                }
                else{
                    endGame();
                }
            }
        });

        tile43.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TileLocationRow4 == 4){
                    tile43.setSoundEffectsEnabled(false);
                    soundPool.play(sound_d, 1, 1, 0, 0, 1);
                    continueGame();
                }
                else{
                    endGame();
                }
            }
        });

        startGame();
    }

    private void continueGame(){

        TileLocationRow4 = TileLocationRow3;
        setTileLocation(TileLocationRow4, 4);

        TileLocationRow3 = TileLocationRow2;
        setTileLocation(TileLocationRow3, 3);

        TileLocationRow2 = TileLocationRow1;
        setTileLocation(TileLocationRow2, 2);

        TileLocationRow1 = r.nextInt(3) + 1;
        setTileLocation(TileLocationRow1, 1);

        currentScore++;
        score.setText("Score: " + currentScore);
    }

    private void startGame(){
        tile11.setEnabled(true);
        tile40.setEnabled(true);
        tile41.setEnabled(true);
        tile42.setEnabled(true);
        tile43.setEnabled(true);

        currentScore = 0;
        score.setText("Score: " + currentScore);

        timer.start();

        TileLocationRow4 = 3;
        tile42.setImageResource(blackKey);
        TileLocationRow3 = r.nextInt(3) + 1;
        setTileLocation(TileLocationRow3, 3);
        TileLocationRow2 = r.nextInt(3) + 1;
        setTileLocation(TileLocationRow2, 2);
        TileLocationRow1 = r.nextInt(3) + 1;
        setTileLocation(TileLocationRow1, 1);
    }

    private void endGame(){
        timer.cancel();

        tile11.setEnabled(false);
        tile40.setEnabled(false);
        tile41.setEnabled(false);
        tile42.setEnabled(false);
        tile43.setEnabled(false);

        tile10.setImageResource(emptyKey);
        tile11.setImageResource(emptyKey);
        tile12.setImageResource(emptyKey);
        tile13.setImageResource(emptyKey);
        tile20.setImageResource(emptyKey);
        tile21.setImageResource(emptyKey);
        tile22.setImageResource(emptyKey);
        tile23.setImageResource(emptyKey);
        tile30.setImageResource(emptyKey);
        tile31.setImageResource(emptyKey);
        tile32.setImageResource(emptyKey);
        tile33.setImageResource(emptyKey);
        tile40.setImageResource(emptyKey);
        tile41.setImageResource(emptyKey);
        tile42.setImageResource(emptyKey);
        tile43.setImageResource(emptyKey);

        Toast.makeText(Main2Activity.this, "Game over!", Toast.LENGTH_SHORT).show();

        openActivity3();


    }

    private void setTileLocation(int place, int row){
        if(row == 1){
            tile10.setImageResource(emptyKey);
            tile11.setImageResource(emptyKey);
            tile12.setImageResource(emptyKey);
            tile13.setImageResource(emptyKey);

            switch (place){
                case 1:
                    tile10.setImageResource(blackTile);
                    break;
                case 2:
                    tile11.setImageResource(blackTile);
                    break;
                case 3:
                    tile12.setImageResource(blackTile);
                    break;
                case 4:
                    tile13.setImageResource(blackTile);
                    break;
            }
        }

        if(row == 2){
            tile20.setImageResource(emptyKey);
            tile21.setImageResource(emptyKey);
            tile22.setImageResource(emptyKey);
            tile23.setImageResource(emptyKey);

            switch (place){
                case 1:
                    tile20.setImageResource(blackTile);
                    break;
                case 2:
                    tile21.setImageResource(blackTile);
                    break;
                case 3:
                    tile22.setImageResource(blackTile);
                    break;
                case 4:
                    tile23.setImageResource(blackTile);
                    break;
            }
        }

        if(row == 3){
            tile30.setImageResource(emptyKey);
            tile31.setImageResource(emptyKey);
            tile32.setImageResource(emptyKey);
            tile33.setImageResource(emptyKey);

            switch (place){
                case 1:
                    tile30.setImageResource(blackTile);
                    break;
                case 2:
                    tile31.setImageResource(blackTile);
                    break;
                case 3:
                    tile32.setImageResource(blackTile);
                    break;
                case 4:
                    tile33.setImageResource(blackTile);
                    break;
            }
        }

        if(row == 4){
            tile40.setImageResource(emptyKey);
            tile41.setImageResource(emptyKey);
            tile42.setImageResource(emptyKey);
            tile43.setImageResource(emptyKey);

            switch (place){
                case 1:
                    tile40.setImageResource(blackKey);
                    break;
                case 2:
                    tile41.setImageResource(blackKey);
                    break;
                case 3:
                    tile42.setImageResource(blackKey);
                    break;
                case 4:
                    tile43.setImageResource(blackKey);
                    break;
            }
        }
    }

    public void openActivity3(){

        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        intent.putExtra("score", currentScore);
        startActivity(intent);
    }
}
