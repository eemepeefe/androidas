package com.example.eva.practica2;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "game_db";
    private AppDatabase gameDB;
    private UserDao dao;

    private Button buttonJugar;
    private Button buttonConfig;
    private Button buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Creación de la DB
        gameDB = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();

        buttonJugar = (Button) findViewById(R.id.btnJugar);
        buttonConfig = (Button) findViewById(R.id.btnConfig);
        buttonScore = (Button) findViewById(R.id.btnScore);

        //Botones
        buttonJugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                //aquí hay que comprobar antes que se haya introducido una configuración, este menú tengo que replantearlo de diseño
            }
        });

        buttonConfig.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MenuActivity.this, ConfigActivity.class);
                startActivity(intent);
            }
        });

        buttonScore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MenuActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });
    }


}
