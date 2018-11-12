package com.example.eva.practica2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        db  = AppDatabase.getAppDatabase(this);
        dao = db.userDao();
        topten = new ArrayList<User>();
        topten = dao.getTopTen();

        table = (TableLayout) findViewById(R.id.main_table);

        TableRow contentrow= new TableRow(this);
        TableRow.LayoutParams lpcontent = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        contentrow.setLayoutParams(lpcontent);
        usernameview = new TextView(this);
        scoreview = new TextView(this);
        usernameview.setText("USUARIO");
        scoreview.setText("ACIERTOS (%)");
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
                usernameview.setText(topten.get(i).getUsername());
                scoreview.setText(topten.get(i).getScore() + "%");
            }

            row.addView(usernameview);
            row.addView(scoreview);
            table.addView(row,i+1);

        }
    }
}
