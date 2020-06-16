package au.edu.jcu.cp3406.guesstheceleb.game;

import java.util.Locale;

public class Game
{
    private int questionNumber;
    private int score;
    private Question[] questions;

    public Game(Question[] questions)
    {
        questionNumber = 0;
        score = 0;

        this.questions = questions;
    }

    public String getScore()
    {
        return String.format(Locale.ENGLISH, "Score: %d/%d", score, questions.length);
    }

    public boolean isGameOver()
    {
        return questionNumber == questions.length;
    }

    public Question next()
    {
        return questions[questionNumber++];
    }

    public void updateScore(boolean correct)
    {
        if(correct) score++;
    }

    public int count()
    {
        return questions.length;
    }
}
