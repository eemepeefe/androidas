package com.example.eva.practica2;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;


public class ConfigActivity extends AppCompatActivity {

    private AppDatabase db;
    private UserDao dao;

    private EditText texto;
    private Button btnConfirmar;
    private Button btnAtras;
    private Button buttonIndie;
    private Button buttonRock;
    private RadioGroup botonera;
    private RadioButton boton1;
    private RadioButton boton2;
    private RadioButton boton3;
    private int difficulty;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        db  = AppDatabase.getAppDatabase(this);
        dao = db.userDao();

        //SI EN ESTA PANTALLA NO SE HA METIDO NADA, HAY QUE AVISAR AL USUARIO CUANDO LE DA A JUGAR

        boton1 = (RadioButton)findViewById(R.id.fivequestions);
        boton2 = (RadioButton)findViewById(R.id.tenquestions);
        boton3 = (RadioButton)findViewById(R.id.tenquestions);

        View.OnClickListener list = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opcion = "";
                switch(view.getId()) {
                    case R.id.fivequestions:
                        difficulty = 5;
                        break;
                    case R.id.tenquestions:
                        difficulty = 10;
                        break;
                    case R.id.fifteenquestions:
                        difficulty = 15;
                        break;
                }

            }
        };

        boton1.setOnClickListener(list);
        boton2.setOnClickListener(list);
        boton3.setOnClickListener(list);

        texto = (EditText) findViewById(R.id.plain_text_input);

        buttonIndie = (Button) findViewById(R.id.indie);
        buttonRock = (Button) findViewById(R.id.rock);

        buttonIndie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "indie";
            }
        });

        buttonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "rock";
            }
        });

        btnConfirmar = (Button) findViewById(R.id.confirm);
        btnAtras = (Button)findViewById(R.id.backtomenu);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = texto.getText().toString();
                User user = new User();
                user.setUsername(username);
                user.setScore(-1);
                user.setDifficulty(difficulty);
                user.setCategory(category);
                dao.insertAll(user);

                List<User> totalUsers = dao.getAll();
                for (int i =0; i<totalUsers.size();i++){
                    System.out.println(totalUsers.get(i).getId());
                    System.out.println(totalUsers.get(i).getUsername());
                    System.out.println(totalUsers.get(i).getDifficulty());
                    System.out.println(totalUsers.get(i).getCategory());
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }




}
