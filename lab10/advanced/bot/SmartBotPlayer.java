package advanced.bot;

import advanced.model.Question;

//nu raspunde random ci incerca sa recunoasca intrebari cunoscute si raspunde corect
//are  mica baza de cunostinte
public class SmartBotPlayer implements BotPlayer {

    @Override
    public String getName() {
        return "SmartBot";
    }

    @Override
    public String answerQuestion(Question question) {
        String text = question.getText().toLowerCase();//citeste textul intrebarii si trans in text mic(ca sa pot compara mai usor)

        //daca intrebarea contine anumite cuvinte
        if (text.contains("capitala romaniei")) {
            return "Bucuresti";
        }

        if (text.contains("2+2")) {
            return "4";
        }

        if (text.contains("saptamana")) {
            return "7";
        }

        return "Nu stiu";
    }
}