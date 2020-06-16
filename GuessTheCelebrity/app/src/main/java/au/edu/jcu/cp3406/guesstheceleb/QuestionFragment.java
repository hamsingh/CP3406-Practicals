package au.edu.jcu.cp3406.guesstheceleb;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.Question;


public class QuestionFragment extends Fragment
{
    private StateListener listener;
    private Game currentGame;

    private Question currentQuestion;
    private int questionNumber;


    private GridView possibleAnswers;
    private ArrayAdapter<String> adapter;

    private ImageView image;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        listener = (StateListener) context;

        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        possibleAnswers = view.findViewById(R.id.answers);
        image = view.findViewById(R.id.image);


        possibleAnswers.setAdapter(adapter);

        possibleAnswers.setOnItemClickListener((parent, view1, position, id) -> {
            TextView answer = (TextView) view1;
            currentGame.updateScore(currentQuestion.check(answer.getText().toString()));
            showNextQuestion();
        });

        return view;
    }

    public void setGame(Game game)
    {
        currentGame = game;
        questionNumber = 0;
        showNextQuestion();
    }

    public String getScore()
    {
        return currentGame.getScore();
    }

    public void showNextQuestion()
    {
        questionNumber++;

        if(questionNumber > currentGame.count())
        {
            listener.onUpdate(State.GAME_OVER);
        }
        else
        {
            listener.onUpdate(State.CONTINUE_GAME);

            currentQuestion = currentGame.next();

            adapter.clear();

            String[] answers = currentQuestion.getPossibleNames();

            List<String> shuffleList = Arrays.asList(answers);
            Collections.shuffle(shuffleList);

            String[] shuffledAnswers = shuffleList.toArray(new String[0]);

            adapter.addAll(shuffledAnswers);
            image.setImageBitmap(currentQuestion.getCelebrityImage());
        }
    }

    public void setVisibility(boolean isVisible)
    {
        if(isVisible)
        {
            possibleAnswers.setVisibility(View.VISIBLE);
        }
        else
        {
            possibleAnswers.setVisibility(View.INVISIBLE);
        }
    }
}
