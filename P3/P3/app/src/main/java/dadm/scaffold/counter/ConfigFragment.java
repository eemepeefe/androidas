package dadm.scaffold.counter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;

public class ConfigFragment extends BaseFragment implements View.OnClickListener {
    public ConfigFragment() {
    }

    private int ship;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_config, container, false);
        ship = R.drawable.ufo1;
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.respuesta1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ship = R.drawable.ufo2;
                ((ScaffoldActivity)getActivity()).setShip(ship);
            }
        });

        view.findViewById(R.id.respuesta2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ship = R.drawable.ufo3;
                ((ScaffoldActivity)getActivity()).setShip(ship);
            }
        });

        view.findViewById(R.id.backtomenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ScaffoldActivity)getActivity()).setShip(ship);
                ((ScaffoldActivity)getActivity()).backToMenu();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
