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
        int randomNum = r.nextInt(30) + 1;

        Button button = (Button) findViewById(R.id.guess);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText numberField = (EditText) findViewById(R.id.numberInput);
                TextView feedbackText = (TextView) findViewById(R.id.feedback);

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
            }
        });
    }
}