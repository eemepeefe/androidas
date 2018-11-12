package com.example.eva.practica2;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;
import java.util.List;
import java.util.Random;

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

    private User user;
    private AppDatabase db;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db  = AppDatabase.getAppDatabase(this);
        dao = db.userDao();

        player_settings = dao.getLastUser();

        if(player_settings==null || (player_settings.getScore()!=-1) ){
            System.out.println("ENTRA");
            //if no se ha creado config
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    MainActivity.this);

            // set title
            alertDialogBuilder.setTitle("No se ha introducido configuración.");

            // set dialog message
            alertDialogBuilder
                    .setMessage("¿Deseas continuar como anónimo con configuración estándar (10 preguntas, categoría aleatoria) o volver al menú?")
                    .setCancelable(false)
                    .setPositiveButton("Continuar como anónimo",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            Random rnd = new Random();
                            int useraux = (int) rnd.nextInt(1000);
                            String username = "anon"+useraux;
                            user = new User();
                            user.setUsername(username);
                            user.setScore(-1);
                            user.setDifficulty(10);
                            int categoryaux = rnd.nextInt(1);

                            if(categoryaux==1)
                                user.setCategory("rock");
                            else
                                user.setCategory("indie");
                            dao.insertAll(user);
                            player_settings = dao.getLastUser();
                        }
                    })
                    .setNegativeButton("Volver a configuración",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                            startActivity(intent);
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }



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

        if(player_settings!=null) {

            //Opciones de la partida desde la bd y datos necesarios para la misma
            System.out.println("Tu usuario es " + player_settings.getUsername());
            questionManager = new QuestionManager(this);
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
            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answer1.getText() == correctAnswer) {
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

            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answer2.getText() == correctAnswer) {
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

            answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answer3.getText() == correctAnswer) {
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

            answer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answer4.getText() == correctAnswer) {
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
            float scoreaux = (float)aciertos/numQuestions;
            scoreaux=scoreaux*100;
            System.out.println(scoreaux);
            dao.setScore(player_settings.getId(), (int)scoreaux);
            //Mandar aciertos y fallos en el intent a la siguiente actividad
            Bundle bundle = new Bundle();
            bundle.putInt("ACIERTOS", aciertos);
            bundle.putInt("FALLOS", fallos);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }


    private void updateViews(){
        totalpreguntas.setText("Pregunta actual: " + questionIndex + " / " + " de " + numQuestions);
        aciertosfallos.setText("Aciertos: " + aciertos + "  Fallos: " + fallos);
    }
}
