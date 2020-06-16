package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

import au.edu.jcu.cp3406.guesstheceleb.game.CelebrityManager;
import au.edu.jcu.cp3406.guesstheceleb.game.Difficulty;
import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.GameBuilder;

public class MainActivity extends AppCompatActivity implements StateListener
{

    private GameFragment gameFragment;
    private StatusFragment statusFragment;
    private QuestionFragment questionFragment;
    private boolean isLargeScreen;

    private CountDownTimer timer;

    private GameBuilder gameBuilder;
    private CelebrityManager celebrityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrityManager =  new CelebrityManager(getAssets(), "celebs/");
        gameBuilder = new GameBuilder(celebrityManager);


        FragmentManager fragmentManager = getSupportFragmentManager();
        gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.game);
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);
        isLargeScreen = statusFragment != null;
    }

    @Override
    public void onUpdate(State state)
    {
        Difficulty level = gameFragment.getLevel();

        String text = String.format(Locale.getDefault(), "state: %s level: %s", state, level);
        Log.i("MainActivity", text);

        if(isLargeScreen)
        {
            switch(state)
            {
                case START_GAME:
                    Game game = gameBuilder.create(level);
                    questionFragment.setGame(game);
                    questionFragment.setVisibility(true);
                    enableTimer(level);
                    break;
                case CONTINUE_GAME:
                    statusFragment.setScore(questionFragment.getScore());
                    break;
                case GAME_OVER:
                    timer.cancel();
                    statusFragment.setScore(questionFragment.getScore());
                    statusFragment.setMessage("Game Over!");
                    questionFragment.setVisibility(false);
                    break;
            }
        }
        else
        {
            switch (state)
            {
                case START_GAME:
                    Intent intent = new Intent(this, QuestionActivity.class);
                    intent.putExtra("level", level);
                    startActivity(intent);
                    break;
            }
        }
    }

    public void enableTimer(Difficulty difficulty)
    {
        int timerSeconds;

        switch (difficulty)
        {
            case EASY:
                timerSeconds = 30;
                break;
            case MEDIUM:
                timerSeconds = 60;
                break;
            case HARD:
                timerSeconds = 90;
                break;
            case EXPERT:
                timerSeconds = 180;
                break;
            default:
                timerSeconds = 30;
        }

         timer = new CountDownTimer((timerSeconds+1) * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                statusFragment.setMessage(String.format(Locale.getDefault(),
                        "Time Remaining: %d", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish()
            {
                onUpdate(State.GAME_OVER);
            }
        };

        timer.start();


    }


}
