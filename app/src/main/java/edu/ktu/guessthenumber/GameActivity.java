package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;;
import edu.ktu.guessthenumber.ViewModels.PlayerActivityViewModel;

public class GameActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    private int minNumber = 0;
    private int maxNumber = 10;

    private int level = 1;
    private int nextLevelBoundary = 3;


    private int randomNumber;

    private int winAmount = 0;
    private int lostAmount = 0;

    private int result = 0;

    private int maxTurns = 7;
    private int currentTurn = 0;

    private TextView numberRangeText;
    private TextView resultText;
    private TextView turnsText;
    private TextView nickNameText;
    private PlayerActivityViewModel pViewmodel;
    private User player;

    private EditText numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SharedPreferences prefsDifficulty = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        pViewmodel = new ViewModelProvider(this).get(PlayerActivityViewModel.class);
        int difficulty = prefsDifficulty.getInt("difficulty", 0);
        if (difficulty == 1){
            maxNumber = 50;
        } else if (difficulty == 2){
            maxNumber = 100;
        }
        player = (User)getIntent().getSerializableExtra("user");

        level = player.getLevel();
        nickNameText = findViewById(R.id.playerNickName);
        nickNameText.setText(String.format(getResources().getString(R.string.nickName), player.getName()));
        lostAmount = player.getNumberOfLose();
        winAmount = player.getNumberOfWins();
        numberRangeText = findViewById(R.id.range);
        resultText = findViewById(R.id.result_t);
        turnsText = findViewById(R.id.chance);
        numberField = findViewById(R.id.guess_i);
        maxNumber *= level;
        updateText(0);
        Random random = new Random();
        randomNumber = random.nextInt(maxNumber - minNumber) + minNumber;
    }

    private void updateText(int guessedNumber){
        numberRangeText.setText(String.format(getResources().getString(R.string.number_range_format), minNumber, maxNumber));
        if(result > 0){
            resultText.setText(String.format(getResources().getString(R.string.result_format),guessedNumber,getResources().getString(R.string.result_high_format)));
        }else if(result < 0){
            resultText.setText(String.format(getResources().getString(R.string.result_format),guessedNumber,getResources().getString(R.string.result_low_format)));
        }
        turnsText.setText(String.format(getResources().getString(R.string.turns_format), currentTurn, maxTurns));
    }

    public void guessClick(View view){
        currentTurn++;
        int guessedNumber = 0;
        if (!numberField.getText().toString().trim().equals("")){
            guessedNumber = Integer.parseInt(numberField.getText().toString());
        }
        result = 0;
        if (randomNumber > guessedNumber){
            result = -1;
        }
        else if(randomNumber<guessedNumber){
            result = 1;
        }
        if (currentTurn >= maxTurns && result!= 0){
            //lost
            lostAmount++;
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("guessedNumber", guessedNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("win", false);
            player.setNumberOfLose(lostAmount);
            pViewmodel.updateUser(player);
            intent.putExtra("user", player);
            startActivity(intent);
            finish();
        }else if(result == 0){
            //win
            int winInRow = player.getWinInRow() + 1;
            winAmount++;
            player.setWinInRow(winInRow);
            if(player.getWinInRow() == nextLevelBoundary){
                player.setWinInRow(0);
                level++;
                player.setLevel(level);
                Toast.makeText(this, String.format(getResources().getString(R.string.level_format), level), Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("guessedNumber", guessedNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("win", true);
            player.setNumberOfWins(winAmount);
            pViewmodel.updateUser(player);
            intent.putExtra("user", player);
            startActivity(intent);
            finish();
        }
        updateText(guessedNumber);
    }
}
