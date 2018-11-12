package com.example.eva.practica2;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager {

    private List<Question> questionList;

    private List<Question> questionsForMainClass;

    private List<Integer> resourcesList;
    private List<String> answersList;
    private List <String> correctAnswersList;

    private QuestionDatabase db;
    private QuestionDao daoquestion;

    QuestionManager(Context context) {
        db = QuestionDatabase.getQuestionDatabase(context);
        daoquestion = db.questionDao();
        if(daoquestion.countQuestions()==0){
            questionList = new ArrayList<>();
            //questionlist.rellenar
            for (int i = 0; i<questionList.size(); i++){
                daoquestion.insert(questionList.get(i));
            }

        }
    }

    public List<Question> getQuestionListFromDB(String category){
        //query para coger la lista de DB y pegarla en la lista de preguntas
    }
    public List<Question> getNumberOfQuestions(int numberQuestions) {
        List<Question> auxQuestionList = new ArrayList<Question>();
        for (int i = 0; i<numberQuestions; i++){
            Random r = new Random();
            int aux = r.nextInt(19-i);
            auxQuestionList.add(questionList.get(aux));
            questionList.remove(aux);
            System.out.println("Tamaño lista preguntas : " + questionList.size());
        }
        return auxQuestionList;
    }


    public void introIndieQuestions(){
        //en vez de add a la lista, insertarlas a la base de datos
        questionList.add(new Question("indie", R.raw.dorian_1, "Lori Meyers;Dorian;Los Planetas;La habitación roja", "Dorian" ));
        questionList.add(new Question("indie", R.raw.izal_1, "La casa azul;Love of lesbian;IZAL;Niños mutantes", "IZAL" ));
        questionList.add(new Question("indie", R.raw.izal_2, "IZAL;Viva Suecia;Supersubmarina;Los punsetes", "IZAL" ));
        questionList.add(new Question("indie", R.raw.izal_3, "Dorian;IZAL;Lori Meyers;Love of lesbian", "IZAL" ));
        questionList.add(new Question("indie", R.raw.izal_4, "Vetusta Morla;Miss Caffeina;Triángulo de amor bizarro;IZAL", "IZAL" ));
        questionList.add(new Question("indie", R.raw.izal_5, "Interpol;Supersubmarina;IZAL;Sr. Chinarro", "IZAL" ));
        questionList.add(new Question("indie", R.raw.lol_1, "Love of lesbian;Los punsetes;Miss Caffeina;Lori Meyers", "Love of lesbian" ));
        questionList.add(new Question("indie", R.raw.lol_2, "Viva Suecia;La habitación roja;Love of lesbian;Vetusta Morla", "Love of lesbian" ));
        questionList.add(new Question("indie", R.raw.mc_1, "IZAL;Miss Caffeina;La casa azul;Dorian", "Miss Caffeina" ));
        questionList.add(new Question("indie", R.raw.mc_2, "Interpol;Miss Caffeina;Lori Meyers;Niños mutantes", "Miss Caffeina" ));
        questionList.add(new Question("indie", R.raw.mc_3, "Lori Meyers;Dorian;Niños mutantes;Miss Caffeina", "Miss Caffeina" ));
        questionList.add(new Question("indie", R.raw.mc_4, "Sr. Chinarro;La casa azul;Miss Caffeina;Love of lesbian", "Miss Caffeina" ));
        questionList.add(new Question("indie", R.raw.mc_5, "Miss Caffeina;Viva Suecia;Lori Meyers;IZAL", "Miss Caffeina" ));
        questionList.add(new Question("indie", R.raw.ss_1, "Love of lesbian;Supersubmarina;Triángulo de amor bizarro;Niños mutantes", "Supersubmarina" ));
        questionList.add(new Question("indie", R.raw.ss_2,"Supersubmarina;Interpol;IZAL;Love of lesbian", "Supersubmarina" ));
        questionList.add(new Question("indie", R.raw.ss_3, "Miss Caffeina;Vetusta Morla;Supersubmarina;Lori Meyers", "Supersubmarina" ));
        questionList.add(new Question("indie", R.raw.ss_4,"Triángulo de amor bizarro;Los Planetas;Supersubmarina;Dorian", "Supersubmarina" ));
        questionList.add(new Question("indie", R.raw.vm_1,"Dorian;IZAL;Sr. Chinarro;Vetusta Morla", "Vetusta Morla" ));
        questionList.add(new Question("indie", R.raw.vm_2, "Lori Meyers;Vetusta Morla;La casa azul;Los punsetes", "Vetusta Morla" ));
        questionList.add(new Question("indie", R.raw.vm_3,"Vetusta Morla;Los Planetas;Viva Suecia;La habitación roja", "Vetusta Morla" ));
    }

    public void introRockQuestions(){
        questionList.add(new Question("rock", R.raw.boikot_1, "La Pegatina;Boikot;La Raiz;Reincidentes", "Boikot" ));
        questionList.add(new Question("rock", R.raw.boikot_2, new String[]{"La Raiz", "Boikot", "Porretas", "Extremoduro"}, "Boikot" ));
        questionList.add(new Question("rock", R.raw.extremoduro_1, new String[]{"Extremoduro", "Boikot", "La Raiz", "Fito y Fitipaldis"}, "Extremoduro" ));
        questionList.add(new Question("rock", R.raw.extremoduro_2, new String[]{"La Pegatina", "Platero y tu", "Extremoduro", "Reincidentes"}, "Extremoduro" ));
        questionList.add(new Question("rock", R.raw.lapegatina_1, new String[]{"La Pegatina", "Txarango", "La Raiz", "Bongo Botrako"}, "La Pegatina" ));
        questionList.add(new Question("rock", R.raw.lapegatina_2, new String[]{"Boikot", "Txarango", "Bongo Botrako", "La Pegatina"}, "La Pegatina" ));
        questionList.add(new Question("rock", R.raw.lapollarecords_1, new String[]{"La Pegatina", "La Polla Records", "Gatillazo", "Eskorbuto"}, "La Polla Records" ));
        questionList.add(new Question("rock", R.raw.laraiz_1, new String[]{"La Pegatina", "Zoo", "La Raiz", "Reincidentes"}, "La Raiz" ));
        questionList.add(new Question("rock", R.raw.laraiz_2, new String[]{"Zoo", "Boikot", "La Raiz", "Reincidentes"}, "La Raiz" ));
        questionList.add(new Question("rock", R.raw.laraiz_3, new String[]{"La Pegatina", "Zoo", "La Raiz", "Extremoduro"}, "La Raiz" ));
        questionList.add(new Question("rock", R.raw.lossuaves_1, new String[]{"Extremoduro", "M-Clan", "Los Suaves", "Marea"}, "Los Suaves" ));
        questionList.add(new Question("rock", R.raw.magodeoz_1, new String[]{"Extremoduro", "Mago de Oz", "La Raiz", "Fito y Fitipaldis"}, "Mago de Oz" ));
        questionList.add(new Question("rock", R.raw.mojinos_1, new String[]{"La Pegatina", "Platero y tu", "Extremoduro", "Mojinos Escocios"}, "Mojinos Escocios" ));
        questionList.add(new Question("rock", R.raw.plateroytu_1, new String[]{"Fito y Fitipaldis", "Extremoduro", "Platero y tu", "Extrechinato"}, "Platero y tu" ));
        questionList.add(new Question("rock", R.raw.porretas_1, new String[]{"Boikot", "Txarango", "Porretas", "Eskorbuto"}, "Porretas" ));
        questionList.add(new Question("rock", R.raw.reincidentes_1, new String[]{"Marea", "Boikot", "La Raiz", "Reincidentes"}, "Reincidentes" ));
        questionList.add(new Question("rock", R.raw.reincidentes_2, new String[]{"La Pegatina", "Boikot", "Marea", "Reincidentes"}, "Reincidentes" ));
        questionList.add(new Question("rock", R.raw.rosendo_1, new String[]{"Rosendo", "Extremoduro", "Marea", "Reincidentes"}, "Rosendo" ));
        questionList.add(new Question("rock", R.raw.skap_1, new String[]{"Skalarriak", "Boikot", "Skap", "Reincidentes"}, "Skap" ));
        questionList.add(new Question("rock", R.raw.skap_2, new String[]{"La Polla Records", "Gatillazo", "Skap", "Reincidentes"}, "Skap" ));
    }

}
