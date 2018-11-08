package com.example.eva.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private AppDatabase db;
    private UserDao dao;

    private List<User> topten;

    private TableLayout table;
    private TableRow tablerow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        db  = AppDatabase.getAppDatabase(this);
        dao = db.userDao();

        table = (TableLayout) findViewById(R.id.main_table);
        tablerow = (TableRow) findViewById(R.id.tablerow);


        topten = new ArrayList<User>();
        topten = dao.getTopTen();

        /*TextView[] textArray = new TextView[productsList.length()];
        TableRow[] tr_head = new TableRow[productsList.length()];

        for(int i=0; i<productsList.length();i++){
            JSONObject product = productsList.getJSONObject(i);
            JSONObject productData = product.getJSONObject("Product");
            String productDescription = productData.getString("description");

//Create the tablerows
            tr_head[i] = new TableRow(this);
            tr_head[i].setId(i+1);
            tr_head[i].setBackgroundColor(Color.GRAY);
            tr_head[i].setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            // Here create the TextView dynamically

            textArray[i] = new TextView(this);
            textArray[i].setId(i+111);
            textArray[i].setText(productDescription);
            textArray[i].setTextColor(Color.WHITE);
            textArray[i].setPadding(5, 5, 5, 5);
            tr_head[i].addView(textArray[i]);
// Add each table row to table layout

            tl.addView(tr_head[i], new TableLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

        } // end of for loop

        for (int i = 0; i<10; i++){
            topten.get(i).getUsername();
            topten.get(i).getDifficulty();
            topten.get(i).getScore();
        }
*/
        //aquí tengo que tener yo una tablita con jugadores y scores
        //que almaceno de forma persistente, o sea se que
        //creo que los voy a tener en la bd y haré una query ordenando
        //las 10 mejores o algo así
    }
}
