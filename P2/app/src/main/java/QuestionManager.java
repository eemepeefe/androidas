import java.util.ArrayList;
import java.util.List;

public class QuestionManager {

    //AQUI SE HARIAN VARIAS LISTAS DE VARIAS TEMATICAS
    //HABRIA QUE METER TB OPCION DE TEMATICA EN LA BBDD PARA LA CONFIGURACION

    private List<Question> questionList;

    //METER TODAS LAS RUTAS DE AUDIO EN LA CARPETA RES

    QuestionManager(){
        //metemos aquí a pelo todas las preguntas con las rutas de audio etc
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

    //METODO ELEGIR TEMATICA
}
