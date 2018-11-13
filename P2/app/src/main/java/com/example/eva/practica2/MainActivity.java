package com.example.eva.practica2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Chronometer;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private LinearLayout mainBackground;

    private Button buttonPlay;
    private Button buttonPause;
    private Button buttonStop;

    private TextView aciertosfallos;
    private TextView totalpreguntas;
    private TextView adivinapreguntas;

    private Chronometer chronometer;

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
    private boolean anon;

    private Typeface veganfont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        veganfont = Typeface.createFromAsset(getAssets(),  "fonts/timeburnerbold.ttf");
        mainBackground =(LinearLayout)findViewById(R.id.mainlayout);
        db  = AppDatabase.getAppDatabase(this);
        dao = db.userDao();

        anon = false;
        //Si la base de datos está vacía o no se ha insertado una configuración
        //el programa nos permite volver a la configuración o continuar como anónimo
        //mediante la aparición de un pop-up

        if(dao.getLastUser()==null || (dao.getLastUser().getScore()!=-1) ){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    MainActivity.this);

            alertDialogBuilder.setTitle("No se ha introducido configuración.");

            alertDialogBuilder
                    .setMessage("¿Deseas continuar como anónimo con configuración estándar (10 preguntas, categoría aleatoria) o volver al menú?")
                    .setCancelable(false)
                    .setPositiveButton("Volver a configuración",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            //Se vuelve a la configuración
                            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Continuar como anónimo",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                            //Se inserta un nuevo usuario con el username anon+número random
                            Random rnd = new Random();
                            int useraux = (int) rnd.nextInt(1000);
                            String username = "anon"+useraux;
                            user = new User();
                            user.setUsername(username);
                            user.setScore(-1);
                            user.setDifficulty(10);
                            int categoryaux = rnd.nextInt(2);
                            if(categoryaux>=1)
                                user.setCategory("indie");
                            else
                                user.setCategory("rock");
                            dao.insertAll(user);
                            anon = true;
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }


        //Cada uno de los textos, botones etc con su correspondiente fuente
        buttonPlay = (Button) findViewById(R.id.playbutton);
        buttonPlay.setTypeface(veganfont);
        buttonPause = (Button) findViewById(R.id.pausebutton);
        buttonPause.setTypeface(veganfont);
        buttonStop = (Button) findViewById(R.id.stopbutton);
        buttonStop.setTypeface(veganfont);
        answer1 = (Button) findViewById(R.id.answer1);
        answer1.setTypeface(veganfont);
        answer2 = (Button) findViewById(R.id.answer2);
        answer2.setTypeface(veganfont);
        answer3 = (Button) findViewById(R.id.answer3);
        answer3.setTypeface(veganfont);
        answer4 = (Button) findViewById(R.id.answer4);
        answer4.setTypeface(veganfont);
        totalpreguntas = (TextView) findViewById(R.id.totalpreguntas);
        totalpreguntas.setTypeface(veganfont);
        aciertosfallos = (TextView) findViewById(R.id.aciertosfallos);
        aciertosfallos.setTypeface(veganfont);
        adivinapreguntas = (TextView) findViewById(R.id.titulo);
        adivinapreguntas.setTypeface(veganfont);

        //Cronómetro
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setTypeface(veganfont);


        //Si se ha conseguido obtener finalmente una configuración válida, se comienza la partida
        if(dao.getLastUser()!=null) {
            player_settings = dao.getLastUser();
            //Opciones de la partida desde la bd y datos necesarios para la misma:
            //elección del conjunto de preguntas y cambio del background en función del tipo de las mismas
            questionManager = new QuestionManager(this);
            questionManager.getQuestionsByTheme(player_settings.getCategory()); //selección del tema
            if(player_settings.getCategory().equals("indie")){
                mainBackground.setBackgroundResource(R.drawable.indie_music);
            } else if (player_settings.getCategory().equals("rock")){
                mainBackground.setBackgroundResource(R.drawable.rock_music);
            }

            questions = questionManager.getNumberOfQuestions(player_settings.getDifficulty()); //selección del número de preguntas deseado del tema deseado
            numQuestions = questions.size();
            questionIndex = 0;
            aciertos = 0;

            //Comienzo del juego
            siguientePregunta();
            chronometer.start();

            //////////////////////////////////////
            /////////////    Botones para el audio
            ///////////////////////////////////////

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


            /////////////////////////////////
            ////////////// Botones para las respuestas
            /////////////////////////////////

            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(answer1.getText());
                    if (answer1.getText().equals(correctAnswer)) {
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
                    System.out.println(answer2.getText());
                    if (answer2.getText().equals(correctAnswer)) {
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
                    System.out.println(answer3.getText() + ".");
                    if (answer3.getText().equals(correctAnswer)) {
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
                    System.out.println(answer4.getText());
                    if (answer4.getText().equals(correctAnswer)) {
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


    //Método que permite cambiar el audio y el texto de los botones para cada pregunta

    long time;

    private void siguientePregunta(){
        if (questionIndex < numQuestions){
            updateViews();
            mediaPlayer = MediaPlayer.create(MainActivity.this, questions.get(questionIndex).getQuestionRoute());
            String[] splittedAnswers = questions.get(questionIndex).splitAnswers();
            answer1.setText(splittedAnswers[0]);
            answer2.setText(splittedAnswers[1]);
            answer3.setText(splittedAnswers[2]);
            answer4.setText(splittedAnswers[3]);
            correctAnswer = questions.get(questionIndex).getCorrectAnswer();
            System.out.println(correctAnswer + ".");
            questionIndex++;

        } else {
            //Si se han terminado las preguntas, se detiene el cronómetro, se guarda la puntuación
            //en la base de datos y se envía dicha puntuación a la actividad end game
            chronometer.stop();
            time = (SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000; //tiempo en segundos
            Intent intent = new Intent(MainActivity.this, EndGameActivity.class);
            float score = calculateScore();
            dao.setScore(player_settings.getId(), (int)score);
            //Mandar aciertos y fallos en el intent a la siguiente actividad
            // (FALTA FÓRMULA DISTINTA PARA LA PUNTUACIÓN)
            Bundle bundle = new Bundle();
            bundle.putInt("ACIERTOS", aciertos);
            bundle.putInt("FALLOS", fallos);
            bundle.putFloat("TIEMPO", time);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    //Método que actualiza las vistas del número de pregunta actual respecto al total
    //y del número de aciertos y fallos
    private void updateViews(){
        totalpreguntas.setText("Pregunta actual: " + questionIndex + " / " + " de " + numQuestions+ ".   Usuario: " + player_settings.getUsername());
        aciertosfallos.setText("Aciertos: " + aciertos + "  Fallos: " + fallos);
    }

    //Método para calcular score en función de tiempo, aciertos y total de preguntas
    private float calculateScore() {
        float tiempoporpregunta = (float) time/numQuestions;
        float normalizedtime = normalize (tiempoporpregunta, 2, 10);
        float scoreaux = (float)aciertos/numQuestions;
        scoreaux=(scoreaux*normalizedtime) * 100;
        return scoreaux;

    }

    //Método auxiliar para normalizar tiempo/numero de preguntas y obtener la proporción
    //que hemos tardado en finalizar
    private float normalize(float value, float min, float max) {
        return 1 - ((value - min) / (max - min));
    }
}
