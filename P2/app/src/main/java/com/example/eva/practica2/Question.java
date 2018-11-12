package com.example.eva.practica2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "questions")
public class Question {

    @ColumnInfo(name = "index")
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int questionIndex;

    @ColumnInfo(name = "type")
    private String questionType;

    @ColumnInfo(name = "route")
    private int questionRoute;

    @ColumnInfo(name = "answers")
    private String possibleAnswers;

    @ColumnInfo(name = "correctanswer")
    private String correctAnswer;


    Question(String questionType, int questionRoute, String possibleAnswers, String correctAnswer){
        this.questionType=questionType;
        this.questionRoute=questionRoute;
        this.possibleAnswers=possibleAnswers;
        this.correctAnswer=correctAnswer;
    }

    public List<String> splitAnswers(){
        List<String> aux = new ArrayList<String>();
        String[] split = this.possibleAnswers.split(";");
        for (int i = 0; i<4; i++){
            aux.add(split[i]);
        }
        return aux;
    }
    public int getQuestionIndex() {return questionIndex;}
    public void setQuestionIndex(int index){this.questionIndex=index;}

    public int getQuestionRoute(){return questionRoute;}
    public void setQuestionRoute(int route){this.questionRoute=route;}

    public void setQuestionType(String type){this.questionType=type;}
    public String getQuestionType(){return questionType;}

    public String getPossibleAnswers(){return possibleAnswers;}
    public void setPossibleAnswers(String possibleAnswers){this.possibleAnswers=possibleAnswers;}

    public void setCorrectAnswer(String correctAnswer){this.correctAnswer=correctAnswer;}
    public String getCorrectAnswer(){return correctAnswer;}


}
