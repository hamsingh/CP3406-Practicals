package au.edu.jcu.cp3406.guesstheceleb.game;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameBuilder
{
    private static final int N_EASY = 3;
    private static final int N_MEDIUM = 6;
    private static final int N_HARD = 12;
    private static final int N_EXPERT = 24;


    private CelebrityManager celebrityManager;

    public GameBuilder(CelebrityManager celebrityManager)
    {
        this.celebrityManager = celebrityManager;
    }

    public Game create(Difficulty difficulty)
    {
        int N;

        switch(difficulty)
        {
            case EASY:
                N = N_EASY;
                break;

            case MEDIUM:
                N = N_MEDIUM;
                break;

            case HARD:
                N = N_HARD;
                break;

            case EXPERT:
                N = N_EXPERT;
                break;

            default:
                N = N_EASY;
        }

        Question[] questions = new Question[N];
        Set<Integer> indexes = new HashSet<Integer>();

        Random random = new Random();

        // Not ideal, but works
        while(indexes.size() != N) indexes.add(1+random.nextInt(celebrityManager.count()-2));

        String[] celebrityNames = new String[N];

        int j = 0;

        for (int i:indexes)
        {
            celebrityNames[j] = celebrityManager.getName(i);
            j++;
        }

        j = 0;

        for (int i:indexes)
        {
            String celebrityName = celebrityManager.getName(i);
            Bitmap celebrityImage = celebrityManager.get(i);

            questions[j++] = new Question(celebrityName, celebrityImage, celebrityNames);
        }

        return new Game(questions);
    }

}

