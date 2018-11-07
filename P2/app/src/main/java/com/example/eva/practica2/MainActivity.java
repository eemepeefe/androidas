package com.example.eva.practica2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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

    private TextView aciertosfallos;
    private TextView totalpreguntas;

    private MediaPlayer mediaPlayer;
    private boolean prepared = true;

    private User player_settings;
    private QuestionManager questionManager;
    private List<Question> questions;

    private int numQuestions;

    //ESTO LO USARE PA LO QUE SE VE EN DIRECTO EN UN TEXTVIEW
    private int questionIndex;
    private String correctAnswer;
    private int aciertos;
    private int fallos;


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

        //Inicializacion botones y otros
        buttonPlay = (Button) findViewById(R.id.playbutton);
        buttonPause = (Button) findViewById(R.id.pausebutton);
        buttonStop = (Button) findViewById(R.id.stopbutton);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        totalpreguntas = (TextView) findViewById(R.id.totalpreguntas);
        aciertosfallos = (TextView) findViewById(R.id.aciertosfallos);


        //Opciones de la partida desde la bd y datos necesarios para la misma
        System.out.println("Tu usuario es " + player_settings.getUsername());
        questionManager = new QuestionManager(player_settings.getCategory());
        //questionManager.SaberSiHagoBienLasCosas();
        questions = questionManager.getQuestions(player_settings.getDifficulty());
        numQuestions = questions.size();
        questionIndex = 0;
        aciertos = 0;

        siguientePregunta();

        //Botones para el audio
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
                mediaPlayer.stop();
                prepared = false;
            }
        });


        //Botones respuesta
        answer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (answer1.getText() == correctAnswer){
                    aciertos++;
                    mediaPlayer.stop();
                    siguientePregunta();
                } else {
                    fallos++;
                    mediaPlayer.stop();
                    siguientePregunta();
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (answer2.getText() == correctAnswer){
                    aciertos++;
                    mediaPlayer.stop();
                    siguientePregunta();
                } else {
                    fallos++;
                    mediaPlayer.stop();
                    siguientePregunta();
                }
            }
        });

        answer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (answer3.getText() == correctAnswer){
                    aciertos++;
                    mediaPlayer.stop();
                    siguientePregunta();
                } else {
                    fallos++;
                    mediaPlayer.stop();
                    siguientePregunta();
                }
            }
        });

        answer4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (answer4.getText() == correctAnswer){
                    aciertos++;
                    mediaPlayer.stop();
                    siguientePregunta();
                } else {
                    fallos++;
                    mediaPlayer.stop();
                    siguientePregunta();
                }
            }
        });
    }


    private void siguientePregunta(){
        if (questionIndex < numQuestions){

            updateViews();
            mediaPlayer = MediaPlayer.create(MainActivity.this, questions.get(questionIndex).getQuestionRoute());
            answer1.setText(questions.get(questionIndex).getPossibleAnswers()[0]);
            answer2.setText(questions.get(questionIndex).getPossibleAnswers()[1]);
            answer3.setText(questions.get(questionIndex).getPossibleAnswers()[2]);
            answer4.setText(questions.get(questionIndex).getPossibleAnswers()[3]);
            correctAnswer = questions.get(questionIndex).getCorrectAnswer();
            questionIndex++;

        } else {
            Intent intent = new Intent(MainActivity.this, EndGameActivity.class);
            dao.setScore(player_settings.getUsername(), aciertos);
            //Mandar aciertos y fallos en el intent a la siguiente actividad
            startActivity(intent);
        }
    }


    private void updateViews(){
        totalpreguntas.setText("Pregunta actual: " + questionIndex + " / " + " de " + numQuestions);
        aciertosfallos.setText("Aciertos: " + aciertos + "  Fallos: " + fallos);
    }
}
