package com.example.eva.practica2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager {

    private List<Question> questionList;

    QuestionManager(String category) {
        System.out.println("Category is: " + category);
        questionList = new ArrayList<Question>();

        if (category.equals("indie")){
            introIndieQuestions();

        } else if (category.equals("rock")){
            System.out.println("IGUALES MARI IGUALITOS");
            introRockQuestions();
        }
    }

    public List<Question> getQuestions(int numberQuestions) {
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
        questionList.add(new Question(0, R.raw.dorian_1, new String[]{"Lori Meyers", "Dorian", "Los Planetas", "La habitación roja"}, "Dorian" ));
        questionList.add(new Question(1, R.raw.izal_1, new String[]{"La casa azul", "Love of lesbian", "IZAL", "Niños mutantes"}, "IZAL" ));
        questionList.add(new Question(2, R.raw.izal_2, new String[]{"IZAL", "Viva Suecia", "Supersubmarina", "Los punsetes"}, "IZAL" ));
        questionList.add(new Question(3, R.raw.izal_3, new String[]{"Dorian", "IZAL", "Lori Meyers", "Love of lesbian"}, "IZAL" ));
        questionList.add(new Question(4, R.raw.izal_4, new String[]{"Vetusta Morla", "Miss Caffeina", "Triángulo de amor bizarro", "IZAL"}, "IZAL" ));
        questionList.add(new Question(5, R.raw.izal_5, new String[]{"Interpol", "Supersubmarina", "IZAL", "Sr. Chinarro"}, "IZAL" ));
        questionList.add(new Question(6, R.raw.lol_1, new String[]{"Love of lesbian", "Los punsetes", "Miss Caffeina", "Lori Meyers"}, "Love of lesbian" ));
        questionList.add(new Question(7, R.raw.lol_2, new String[]{"Viva Suecia", "La habitación roja", "Love of lesbian", "Vetusta Morla"}, "Love of lesbian" ));
        questionList.add(new Question(8, R.raw.mc_1, new String[]{"IZAL", "Miss Caffeina", "La casa azul", "Dorian"}, "Miss Caffeina" ));
        questionList.add(new Question(9, R.raw.mc_2, new String[]{"Interpol", "Miss Caffeina", "Lori Meyers", "Niños mutantes"}, "Miss Caffeina" ));
        questionList.add(new Question(10, R.raw.mc_3, new String[]{"Lori Meyers", "Dorian", "Niños mutantes", "Miss Caffeina"}, "Miss Caffeina" ));
        questionList.add(new Question(11, R.raw.mc_4, new String[]{"Sr. Chinarro", "La casa azul", "Miss Caffeina", "Love of lesbian"}, "Miss Caffeina" ));
        questionList.add(new Question(12, R.raw.mc_5, new String[]{"Miss Caffeina", "Viva Suecia", "Lori Meyers", "IZAL"}, "Miss Caffeina" ));
        questionList.add(new Question(13, R.raw.ss_1, new String[]{"Love of lesbian", "Supersubmarina", "Triángulo de amor bizarro", "Niños mutantes"}, "Supersubmarina" ));
        questionList.add(new Question(14, R.raw.ss_2, new String[]{"Supersubmarina", "Interpol", "IZAL", "Love of lesbian"}, "Supersubmarina" ));
        questionList.add(new Question(15, R.raw.ss_3, new String[]{"Miss Caffeina", "Vetusta Morla", "Supersubmarina", "Lori Meyers"}, "Supersubmarina" ));
        questionList.add(new Question(16, R.raw.ss_4, new String[]{"Triángulo de amor bizarro", "Los Planetas", "Supersubmarina", "Dorian"}, "Supersubmarina" ));
        questionList.add(new Question(17, R.raw.vm_1, new String[]{"Dorian", "IZAL", "Sr. Chinarro", "Vetusta Morla"}, "Vetusta Morla" ));
        questionList.add(new Question(18, R.raw.vm_2, new String[]{"Lori Meyers", "Vetusta Morla", "La casa azul", "Los punsetes"}, "Vetusta Morla" ));
        questionList.add(new Question(19, R.raw.vm_3, new String[]{"Vetusta Morla", "Los Planetas", "Viva Suecia", "La habitación roja"}, "Vetusta Morla" ));
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
