public class Question {

    private int questionIndex;
    private String questionRoute;
    private String[] possibleAnswers;
    private String correctAnswer;

    Question(int questionIndex, String questionRoute, String[] possibleAnswers, String correctAnswer){
        this.questionIndex = questionIndex;
        this.questionRoute = questionRoute;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionIndex() {return questionIndex;}
    public String getQuestionRoute(){return questionRoute;}
    public String[] getPossibleAnswers(){return possibleAnswers;}
    public String getCorrectAnswer(){return correctAnswer;}
}
