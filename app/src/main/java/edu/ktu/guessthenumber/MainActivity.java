package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGameBtn = findViewById((R.id.start_btn));
        startGameBtn.setOnClickListener(new View.OnClickListener() {//grazina mytuka, kuris buvo paspaustas
            @Override
            public void onClick(View v) {
                startClick(v);
            }
        });
    }
    public void startClick(View view)
    {
        if(view == findViewById(R.id.start_btn)){
            Intent intent = new Intent(this, PlayerActivity.class);
            startActivity(intent);
        }else if(view == findViewById(R.id.settings_btn))
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        else if(view == findViewById(R.id.result_btn))
        {
            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        }
        else if(view == findViewById(R.id.about_btn)){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
    }
}
