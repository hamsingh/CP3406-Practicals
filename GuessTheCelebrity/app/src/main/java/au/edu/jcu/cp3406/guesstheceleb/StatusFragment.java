package au.edu.jcu.cp3406.guesstheceleb;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment
{
    private StateListener listener;
    private TextView message;
    private TextView score;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_status, container, false);

        message = view.findViewById(R.id.message);
        score = view.findViewById(R.id.score);

        return view;
    }

    public void setMessage(String text) { message.setText(text); }

    public void setScore(String text) { score.setText(text); }

}
