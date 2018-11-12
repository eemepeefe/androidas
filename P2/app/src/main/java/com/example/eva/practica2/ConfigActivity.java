package com.example.eva.practica2;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

    private Typeface veganfont;

    private TextView elegirnombre;
    private TextView elegirpreguntas;
    private TextView elegirtematica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        db  = AppDatabase.getAppDatabase(this);
        dao = db.userDao();
        veganfont = Typeface.createFromAsset(getAssets(),  "fonts/timeburnerbold.ttf");
        //SI EN ESTA PANTALLA NO SE HA METIDO NADA, HAY QUE AVISAR AL USUARIO CUANDO LE DA A JUGAR

        elegirnombre = (TextView)findViewById(R.id.introducenombre);
        elegirpreguntas = (TextView)findViewById(R.id.numeropreguntas);
        elegirtematica = (TextView)findViewById(R.id.eligetematica);
        elegirnombre.setTypeface(veganfont);
        elegirpreguntas.setTypeface(veganfont);
        elegirtematica.setTypeface(veganfont);

        boton1 = (RadioButton)findViewById(R.id.fivequestions);
        boton1.setTypeface(veganfont);
        boton2 = (RadioButton)findViewById(R.id.tenquestions);
        boton2.setTypeface(veganfont);
        boton3 = (RadioButton)findViewById(R.id.tenquestions);
        boton3.setTypeface(veganfont);

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
        texto.setTypeface(veganfont);

        buttonIndie = (Button) findViewById(R.id.indie);
        buttonIndie.setTypeface(veganfont);
        buttonRock = (Button) findViewById(R.id.rock);
        buttonRock.setTypeface(veganfont);

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
        btnConfirmar.setTypeface(veganfont);
        btnAtras = (Button)findViewById(R.id.backtomenu);
        btnAtras.setTypeface(veganfont);

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
