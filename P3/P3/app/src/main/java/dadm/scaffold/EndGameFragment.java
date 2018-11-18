package dadm.scaffold;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dadm.scaffold.engine.GameEngine;

public class EndGameFragment extends BaseFragment implements View.OnClickListener {

    private GameEngine theGameEngine;
    private TextView scoreView;

    public EndGameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_end_game, container, false);
        TextView scoreView = (TextView)rootView.findViewById(R.id.finalscore);
        scoreView.setText("GAME OVER! FINAL SCORE: " + getArguments().getInt("score"));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.back_to_menu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ((ScaffoldActivity)getActivity()).backToMenu();
    }

    }

