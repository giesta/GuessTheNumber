package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);

        String playerName = prefs.getString("playerName", "name");
        int playerAge = prefs.getInt("playerAge", 1);
        int difficulty = prefs.getInt("difficulty", 0);
        boolean sound = prefs.getBoolean("sound", true);

        EditText nameField = findViewById(R.id.NameInp);
        EditText ageField = findViewById(R.id.ageInp);

        Spinner spinner = findViewById(R.id.SoundsDifficulty);
        Switch soundSwitch = findViewById(R.id.soundSettings);

        nameField.setText(playerName);
        ageField.setText(Integer.toString(playerAge));
        spinner.setSelection(difficulty);
        soundSwitch.setChecked(sound);
    }

    public void saveSetting(View v)
    {
        EditText playerNameField = findViewById(R.id.NameInp);
        EditText playerAgeField = findViewById(R.id.ageInp);

        Spinner spinner = findViewById(R.id.SoundsDifficulty);
        Switch soundSwitch = findViewById(R.id.soundSettings);

        String playerName = playerNameField.getText().toString();
        int playerAge = Integer.parseInt(playerAgeField.getText().toString());
        if(playerNameField.getText().toString().trim().isEmpty() || playerAgeField.getText().toString().trim().isEmpty()){
            Toast.makeText(v.getContext(), getResources().getString(R.string.empty_Field), Toast.LENGTH_SHORT).show();
            return;
        }
        int difficulty = spinner.getSelectedItemPosition();
        boolean sound = soundSwitch.isChecked();

        SharedPreferences.Editor prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).edit();
        prefs.putString("playerName", playerName);
        prefs.putInt("playerAge", playerAge);
        prefs.putInt("difficulty", difficulty);
        prefs.putBoolean("sound", sound);
        prefs.apply();
        finish();
    }
}
