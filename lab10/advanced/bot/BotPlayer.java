package advanced.bot;

import advanced.model.Question;

//interfata pt ca voi folosi mai multetipuri de boti
//RandomBotPlayer
//SmartBotPlayer
//LLMBotPlayer
//toti diferiti dar toti trb sa aiba metoda answerQuestion
public interface BotPlayer {
    String getName();//nume bot
    String answerQuestion(Question question);//primeste o intrebare si returneaza un raspuns
}