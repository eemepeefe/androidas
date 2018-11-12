package com.example.eva.practica2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private AppDatabase db;
    private UserDao dao;

    private List<User> topten;

    private TableLayout table;
    private TextView usernameview;
    private TextView scoreview;

    private TextView halloffame;
    private Typeface veganfont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        db  = AppDatabase.getAppDatabase(this);
        dao = db.userDao();
        topten = new ArrayList<User>();
        topten = dao.getTopTen();

        veganfont = Typeface.createFromAsset(getAssets(),  "fonts/timeburnerbold.ttf");

        table = (TableLayout) findViewById(R.id.main_table);
        halloffame = (TextView) findViewById(R.id.halloffame);
        halloffame.setTypeface(veganfont);

        TableRow contentrow= new TableRow(this);

        TableRow.LayoutParams lpcontent = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        contentrow.setLayoutParams(lpcontent);
        usernameview = new TextView(this);
        scoreview = new TextView(this);
        usernameview.setText("  USUARIO");
        scoreview.setText("      ACIERTOS (%)");
        usernameview.setTextColor(Color.WHITE);
        usernameview.setTextSize(30);
        usernameview.setTypeface(veganfont);
        scoreview.setTextColor(Color.WHITE);
        scoreview.setTextSize(30);
        scoreview.setTypeface(veganfont);
        contentrow.addView(usernameview);
        contentrow.addView(scoreview);

        table.addView(contentrow,0);

        for (int i = 0; i<topten.size(); i++){
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            usernameview = new TextView(this);
            scoreview = new TextView(this);

            if (topten.get(i)!=null && topten.get(i).getScore()!=-1) {
                usernameview.setText("  " + topten.get(i).getUsername());
                scoreview.setText("      " + topten.get(i).getScore() + "%");
                usernameview.setTextColor(Color.WHITE);
                usernameview.setTextSize(30);
                usernameview.setTypeface(veganfont);
                scoreview.setTextColor(Color.WHITE);
                scoreview.setTextSize(30);
                scoreview.setTypeface(veganfont);
            }

            row.addView(usernameview);
            row.addView(scoreview);
            table.addView(row,i+1);

        }
    }
}
