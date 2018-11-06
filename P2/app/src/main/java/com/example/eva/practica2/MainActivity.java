package com.example.eva.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SI NO SE HA CREADO UNA CONFIGURACION PARA LA PARTIDA, NO SE PUEDE JUGAR

        //AQUI:
        //Cojo de la BBDD el último usuario introducido, miro que settings ha elegido.
        //problema que se me ocurre ahora: tengo que saber siempre de alguna manera cual es el último en meterse
        //inicializo un questionManager con el numero de preguntas de su configuracion
        //voy poniendo los audios de las preguntas en el mediaplayer y rellenando botones de respuestas
        //al pulsar el boton se debe pasar directamente a la siguiente

        //CUANDO ACABE Eso: arriba tiene que ir viendose el total de preguntas y cuantas hemos hecho

    }
}
