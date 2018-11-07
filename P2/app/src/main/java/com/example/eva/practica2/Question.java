package com.example.eva.practica2;

public class Question {

    private int questionIndex;
    private int questionRoute;
    private String[] possibleAnswers;
    private String correctAnswer;

    Question(int questionIndex, int questionRoute, String[] possibleAnswers, String correctAnswer){
        this.questionIndex = questionIndex;
        this.questionRoute = questionRoute;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionIndex() {return questionIndex;}
    public int getQuestionRoute(){return questionRoute;}
    public String[] getPossibleAnswers(){return possibleAnswers;}
    public String getCorrectAnswer(){return correctAnswer;}
}
