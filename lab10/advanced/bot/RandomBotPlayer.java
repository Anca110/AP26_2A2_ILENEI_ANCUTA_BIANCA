package advanced.bot;

import advanced.model.Question;

import java.util.Random;


//raspunde random uneori corect alteori gresit
//IA aleatorie
//nu folosesc extends Player pt ca botul e doar o strategie de raspuns, nu jucator complet
public class RandomBotPlayer implements BotPlayer {
    private Random random = new Random();

    //raspunde random dintr o lista
    private String[] answers ={"Bucuresti", "Paris", "4", "7", "Java", "Nu stiu"};

    @Override
    public String getName() {
        return "RandomBot";
    }

    @Override
    public String answerQuestion(Question question) {
        if (random.nextBoolean()){
            return question.getCorrectAnswer();
        }
        //random.nextBoolean() returneaza true sau false
        //false=> raspuns gresit
        //true=>question.getCorrectAnswer()

        return "Raspuns gresit";
    }
}