package au.edu.jcu.cp3406.guesstheceleb;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import au.edu.jcu.cp3406.guesstheceleb.game.Difficulty;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment
{

    private StateListener listener;
    private Difficulty level;
    private TextView currentLevel;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    public Difficulty getLevel() { return  level; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        final Spinner spinner = view.findViewById(R.id.spDifficulty);
        currentLevel = view.findViewById(R.id.tvLevel);

        view.findViewById(R.id.btnPlay).setOnClickListener((v)-> {
            String selection = spinner.getSelectedItem().toString();
            Log.i("GameFragment", "selection: " + selection);
            level = Difficulty.valueOf(selection.toUpperCase());
            currentLevel.setText(String.format("%s%s", getString(R.string.tvLevel), selection));
            listener.onUpdate(State.START_GAME);
        });

        return view;
    }

}
