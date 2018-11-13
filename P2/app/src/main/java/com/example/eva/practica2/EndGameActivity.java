package com.example.eva.practica2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

public class EndGameActivity extends AppCompatActivity {

    private TextView verpuntuacionfinal;
    private Button gobacktomenu;

    private Typeface veganfont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Aquí imprimimos por pantalla el resultado final de la partida y el tiempo que se
        //ha tardado en llevarla a cabo

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        veganfont = Typeface.createFromAsset(getAssets(),  "fonts/timeburnerbold.ttf");
        verpuntuacionfinal = (TextView) findViewById(R.id.aciertosfallosendgame);
        verpuntuacionfinal.setTypeface(veganfont);
        gobacktomenu = (Button) findViewById(R.id.backtomenufromendgame);
        gobacktomenu.setTypeface(veganfont);

        Bundle bundle = this.getIntent().getExtras();
        verpuntuacionfinal.setText("Aciertos: " + bundle.getInt("ACIERTOS") + ", Fallos: " + bundle.getInt("FALLOS") + "\n Tiempo: " + bundle.getFloat("TIEMPO") + "s");

        gobacktomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
