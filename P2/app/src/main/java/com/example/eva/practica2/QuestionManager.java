package com.example.eva.practica2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager {

    //AQUI SE HARIAN VARIAS LISTAS DE VARIAS TEMATICAS
    //HABRIA QUE METER TB OPCION DE TEMATICA EN LA BBDD PARA LA CONFIGURACION

    private List<Question> questionList;

    //METER TODAS LAS RUTAS DE AUDIO EN LA CARPETA RES

    QuestionManager(String category) {
        if (category=="indie"){
            introIndieQuestions();
        } else if (category=="rock"){
            introRockQuestions();
        }
    }

    public List<Question> getQuestions(int numberQuestions) {
        List<Question> auxQuestionList = new ArrayList<Question>();
        for (int i = 0; i<numberQuestions; i++){
           //numero random, get de la lista de questions, que coja el índice
            //que compruebe tb si ese índice no está en la lista aux
            //eso lo hago tantas veces como numberQuestions quiera
        }
        return auxQuestionList;
    }

    public void introIndieQuestions(){




    }

    public void introRockQuestions(){

        questionList.add(new Question(0, R.raw.boikot_1, new String[]{"La Pegatina", "Boikot", "La Raiz", "Reincidentes"}, "Boikot" ));
        questionList.add(new Question(1, R.raw.boikot_2, new String[]{"La Raiz", "Boikot", "Porretas", "Extremoduro"}, "Boikot" ));
        questionList.add(new Question(2, R.raw.extremoduro_1, new String[]{"Extremoduro", "Boikot", "La Raiz", "Fito y Fitipaldis"}, "Extremoduro" ));
        questionList.add(new Question(3, R.raw.extremoduro_2, new String[]{"La Pegatina", "Platero y tu", "Extremoduro", "Reincidentes"}, "Extremoduro" ));
        questionList.add(new Question(4, R.raw.lapegatina_1, new String[]{"La Pegatina", "Txarango", "La Raiz", "Bongo Botrako"}, "La Pegatina" ));
        questionList.add(new Question(5, R.raw.lapegatina_2, new String[]{"Boikot", "Txarango", "Bongo Botrako", "La Pegatina"}, "La Pegatina" ));
        questionList.add(new Question(6, R.raw.lapollarecords_1, new String[]{"La Pegatina", "La Polla Records", "Gatillazo", "Eskorbuto"}, "La Polla Records" ));
        questionList.add(new Question(7, R.raw.laraiz_1, new String[]{"La Pegatina", "Zoo", "La Raiz", "Reincidentes"}, "La Raiz" ));
        questionList.add(new Question(8, R.raw.laraiz_2, new String[]{"Zoo", "Boikot", "La Raiz", "Reincidentes"}, "La Raiz" ));
        questionList.add(new Question(9, R.raw.laraiz_3, new String[]{"La Pegatina", "Zoo", "La Raiz", "Extremoduro"}, "La Raiz" ));
        questionList.add(new Question(10, R.raw.lossuaves_1, new String[]{"Extremoduro", "M-Clan", "Los Suaves", "Marea"}, "Los Suaves" ));
        questionList.add(new Question(11, R.raw.magodeoz_1, new String[]{"Extremoduro", "Mago de Oz", "La Raiz", "Fito y Fitipaldis"}, "Mago de Oz" ));
        questionList.add(new Question(12, R.raw.mojinos_1, new String[]{"La Pegatina", "Platero y tu", "Extremoduro", "Mojinos Escocios"}, "Mojinos Escocios" ));
        questionList.add(new Question(13, R.raw.plateroytu_1, new String[]{"Fito y Fitipaldis", "Extremoduro", "Platero y tu", "Extrechinato"}, "Platero y tu" ));
        questionList.add(new Question(14, R.raw.porretas_1, new String[]{"Boikot", "Txarango", "Porretas", "Eskorbuto"}, "Porretas" ));
        questionList.add(new Question(15, R.raw.reincidentes_1, new String[]{"Marea", "Boikot", "La Raiz", "Reincidentes"}, "Reincidentes" ));
        questionList.add(new Question(16, R.raw.reincidentes_2, new String[]{"La Pegatina", "Boikot", "Marea", "Reincidentes"}, "Reincidentes" ));
        questionList.add(new Question(17, R.raw.rosendo_1, new String[]{"Rosendo", "Extremoduro", "Marea", "Reincidentes"}, "Rosendo" ));
        questionList.add(new Question(18, R.raw.skap_1, new String[]{"Skalarriak", "Boikot", "Skap", "Reincidentes"}, "Skap" ));
        questionList.add(new Question(19, R.raw.skap_2, new String[]{"La Polla Records", "Gatillazo", "Skap", "Reincidentes"}, "Skap" ));
    }
}
