package guessinggame.cp3406.jcu.edu.au.guessinggame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.*;
import java.util.Random;


import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    Game game = new Game();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void checkGuess(View view){

        EditText guessnumber = (EditText)findViewById(R.id.editNum);
        int number = Integer.parseInt(guessnumber.getText().toString());

        if(game.check(number)){
            System.out.println("yes");
            Toast.makeText(MainActivity.this, "Yes", LENGTH_SHORT).show();
        }else{
            System.out.println("no");
            Toast.makeText(MainActivity.this, "No", LENGTH_SHORT).show();
        }
    }
}


