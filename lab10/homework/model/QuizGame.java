package homework.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//la pornire joc citeste toate intrebarile din questions.txt
//si le transforma in obiect Questions

public class QuizGame {
    private List<Question> questions = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Random random = new Random();

    public QuizGame() {
        loadQuestions();
    }//constructor- cand fac new QuizGame() automat se incarca intrebarile


    //citeste fisierul cu intrebari
    private void loadQuestions() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/questions.txt"));
            //FileReader citeste fisierul cu intrebari
            //BufferedReader citesc linie cu linie

            String line;

            while ((line = reader.readLine()) != null){
                String[] parts = line.split(";");
                //ex pt Cat face 2+2?;4
                //parts[0] = "Cat face 2+2?"
                //parts[1] = "4"

                if (parts.length == 2){//am impartit corect intre ;
                    questions.add(new Question(parts[0], parts[1]));//adaug cate o intrebare in lista
                }
            }

            reader.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //returneaza o intrebare random, alege un index random
    public Question getRandomQuestion(){
        return questions.get(random.nextInt(questions.size()));
    }

    //adauga un jucator nou in joc
    public void addPlayer(Player player){
        players.add(player);
    }

    //lista jucatori pt clasament
    public List<Player> getPlayers() {
        return players;
    }


    //alege jucatorul cu scorul cel mai mare; dac scorul e egal castiga cel cu timp mai mic
    public Player getWinner() {
        if (players.isEmpty()) {
            return null;
        }

        Player winner = players.get(0);

        for (Player player : players) {
            if (player.getScore() > winner.getScore()) {
                winner = player;
            } else if (player.getScore() == winner.getScore()
                    && player.getTotalTime() < winner.getTotalTime()) {
                winner = player;
            }
        }
        return winner;
    }
}