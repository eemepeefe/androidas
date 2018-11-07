package com.example.eva.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //aquí tengo que tener yo una tablita con jugadores y scores
        //que almaceno de forma persistente, o sea se que
        //creo que los voy a tener en la bd y haré una query ordenando
        //las 10 mejores o algo así
    }
}
