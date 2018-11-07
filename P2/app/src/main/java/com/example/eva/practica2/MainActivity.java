package com.example.eva.practica2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    private Button buttonPlay;
    private Button buttonPause;
    private Button buttonStop;

    private MediaPlayer mediaPlayer;
    private boolean prepared = true;

    private User player_settings;
    private QuestionManager questionManager;
    private List<Question> questions;
    private int numQuestions;

    private AppDatabase db;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db  = AppDatabase.getAppDatabase(this);
        dao = db.userDao();

        player_settings = dao.getLastUser();

        //si no se ha creado configuración (lo compruebo si el score es distinto de 0 porque un user nuevo no tiene score)
        //le doy a elegir a continuar como anon o volver atrás o sea que me falta aquí un pop up o algo de eso
        //si continuo como anon inserto un anon+numero random en la bd
        //Luego:

        //Cojo de la BBDD el último usuario introducido, miro que settings ha elegido.
        //problema que se me ocurre ahora: tengo que saber siempre de alguna manera cual es el último en meterse
        //inicializo un questionManager con el numero de preguntas de su configuracion


        System.out.println("tu usuario es " + player_settings.getUsername());

        questionManager = new QuestionManager(player_settings.getCategory());

        questions = questionManager.getQuestions(player_settings.getDifficulty());

        numQuestions = questions.size();

        //voy poniendo los audios de las preguntas en el mediaplayer y rellenando botones de respuestas
        //al pulsar el boton se debe pasar directamente a la siguiente

        buttonPlay = (Button) findViewById(R.id.playbutton);
        buttonPause = (Button) findViewById(R.id.pausebutton);
        buttonStop = (Button) findViewById(R.id.stopbutton);

        //mediaPlayer = MediaPlayer.create(MainActivity.this, PREGUNTAS.GET INDEX.GET RUTA);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!prepared) {
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    prepared = true;
                }
                mediaPlayer.start();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mediaPlayer.seekTo(0);
                //mediaPlayer.pause();
                mediaPlayer.stop();
                prepared = false;
            }
        });


        answer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        answer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        answer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        answer4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        //al final hago un update en la bd con la puntuación (lo hago justo en la última pregunta)
        //en la última pregunta tb mando al user a la EndGameActivity
        //CUANDO ACABE Eso: arriba tiene que ir viendose el total de preguntas y cuantas hemos hecho

    }

    //metodo update puntuacion
    //metodo siguiente pregunta tb
}
