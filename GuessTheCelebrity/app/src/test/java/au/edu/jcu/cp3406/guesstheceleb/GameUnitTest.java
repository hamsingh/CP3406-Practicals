package au.edu.jcu.cp3406.guesstheceleb;

import android.graphics.Bitmap;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import au.edu.jcu.cp3406.guesstheceleb.game.CelebrityManager;
import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.Question;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTest
{
    @Test
    public void test_set()
    {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(1);

        assertTrue(set.size() == 2);
    }

    @Test
    public void addition_isCorrect()
    {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void questionCheck()
    {
        String celebName = "first-last";

        Question question = new Question(celebName, null, null);

        assertTrue(question.check(celebName));
    }

    @Test
    public void testGame()
    {
        Question[] questions = new Question[3];
        String[] answers = new String[]{"bob", "jane", "harry"};
        for (int i = 0; i < 3; ++i)
        {
            questions[i] = new Question(answers[i], null, answers);
        }

        Game game = new Game(questions);

        while(!game.isGameOver())
        {
            Question question = game.next();
            game.updateScore(question.check("bob"));
        }
        assertEquals("Score: 1/3", game.getScore());
    }
}