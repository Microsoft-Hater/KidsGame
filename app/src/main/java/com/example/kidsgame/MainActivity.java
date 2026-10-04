package com.example.kidsgame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Random r = new Random();
        final int[] randomNum = {r.nextInt(30) + 1};

        final int[] noOfGuesses = {0};

        Button button = (Button) findViewById(R.id.guess);
        Button replayButton = (Button) findViewById(R.id.playAgain);

        EditText numberField = (EditText) findViewById(R.id.numberInput);
        TextView feedbackText = (TextView) findViewById(R.id.feedback);
        TextView guessNoText = (TextView) findViewById(R.id.noGuesses);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                noOfGuesses[0]++;

                feedbackText.setText("");

                int playerNumber;

                try{
                   playerNumber = Integer.parseInt(numberField.getText().toString());
                }
                catch (NumberFormatException e){
                    playerNumber = -1;
                }
                Log.d("NUMBER", "Player Entered: " + playerNumber);

                if(playerNumber > 30 | playerNumber < 1){
                    feedbackText.setText("Please Enter A Value Between 1 And 30");
                }
                else if(playerNumber < randomNum[0]){
                    feedbackText.setText("Higher :O");
                }
                else if(playerNumber > randomNum[0]){
                    feedbackText.setText("Lower :O");
                }
                else if(playerNumber == randomNum[0]){
                    feedbackText.setText("You Got It!!! :D");
                    guessNoText.setText("You Took " + noOfGuesses[0] + " Guesses");
                    replayButton.setVisibility(View.VISIBLE);
                }
            }
        });

        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                randomNum[0] = r.nextInt(30) + 1;

                noOfGuesses[0] = 0;

                numberField.setText("");
                feedbackText.setText("");
                guessNoText.setText("");
                replayButton.setVisibility(View.INVISIBLE);
            }
        });
    }
}