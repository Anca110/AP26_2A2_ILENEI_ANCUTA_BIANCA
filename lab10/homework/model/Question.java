package homework.model;


//e o intrebare din quiz
public class Question {
    //intrebare si raspuns corect
    private String text;
    private String correctAnswer;

    public Question(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    //verific daca raspunsul primit de la client e corect
    public boolean isCorrect(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
        //equalsIgnoreCase => nu conteaza litere mari/mici
        //trim() elibereaza spatii
    }
}