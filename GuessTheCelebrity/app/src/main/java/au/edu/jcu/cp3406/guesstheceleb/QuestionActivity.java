package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

import au.edu.jcu.cp3406.guesstheceleb.game.CelebrityManager;
import au.edu.jcu.cp3406.guesstheceleb.game.Difficulty;
import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.GameBuilder;

public class QuestionActivity extends AppCompatActivity implements StateListener
{

    private Difficulty level;
    private GameBuilder gameBuilder;
    private CelebrityManager celebrityManager;

    private CountDownTimer timer;


    private QuestionFragment questionFragment;
    private StatusFragment statusFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        FragmentManager fragmentManager = getSupportFragmentManager();
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);

        celebrityManager =  new CelebrityManager(getAssets(), "celebs/");
        gameBuilder = new GameBuilder(celebrityManager);

        Intent intent = getIntent();
        level = (Difficulty) intent.getSerializableExtra("level");

        Game game = gameBuilder.create(level);
        questionFragment.setGame(game);
        questionFragment.setVisibility(true);
        enableTimer(level);

    }

    @Override
    public void onUpdate(State state)
    {
        switch (state)
        {
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
