package edu.ktu.guessthenumber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    private TextView resultText;
    private TextView gameOverResult;
    private ImageView gameOverImage;
    private ActionBar actionBar;
    private User player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        resultText = findViewById(R.id.gameover);
        gameOverResult = findViewById(R.id.game_over_result);
        SharedPreferences prefsDifficulty = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        boolean playSound = prefsDifficulty.getBoolean("sound", false);
        Intent intent = getIntent();
        boolean win = intent.getBooleanExtra("win", false);
        player = (User)getIntent().getSerializableExtra("user");
        int guessedNumber = intent.getIntExtra("guessedNumber",0);
        int randomNumber = intent.getIntExtra("randomNumber",0);
        gameOverImage = findViewById(R.id.OverImg);
        if(win){
            gameOverResult.setText(R.string.gameOver_win);
            gameOverResult.setBackgroundColor(Color.parseColor("#CEF8F8F7"));
            resultText.setText(getResources().getString(R.string.gameOver_greeting));
            gameOverImage.setImageResource(R.drawable.star);
            resultText.setTextColor(Color.parseColor("#FF5733"));
            if(playSound) {
                MediaPlayer song = MediaPlayer.create(this, R.raw.ywwowoo);
                song.start();
            }
        }else{
            actionBar = getSupportActionBar();
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff5959")));
            gameOverResult.setText(String.format(getResources().getString(R.string.gameOver_result),guessedNumber,randomNumber));
            gameOverResult.setBackgroundColor(Color.parseColor("#CEF8F8F7"));
            resultText.setTextColor(Color.parseColor("#FFFF0000"));
            resultText.setBackgroundColor(Color.parseColor("#CEF8F8F7"));
            gameOverImage.setImageResource(R.drawable.gamefinish);
            if(playSound) {
                MediaPlayer song = MediaPlayer.create(this, R.raw.yellgroan2);
                song.start();
            }
        }
    }
    public void tryAgainClick(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("user", player);
        startActivity(intent);
        finish();
    }
}
