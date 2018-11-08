package com.example.eva.practica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

public class EndGameActivity extends AppCompatActivity {

    private TextView verpuntuacionfinal;
    private Button gobacktomenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        verpuntuacionfinal = (TextView) findViewById(R.id.aciertosfallosendgame);
        gobacktomenu = (Button) findViewById(R.id.backtomenufromendgame);

        Bundle bundle = this.getIntent().getExtras();
        verpuntuacionfinal.setText("Aciertos: " + bundle.getInt("ACIERTOS") + " Fallos: " + bundle.getInt("FALLOS"));

        gobacktomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
