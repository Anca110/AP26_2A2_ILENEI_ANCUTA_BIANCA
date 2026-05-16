package advanced.server;

import advanced.model.Player;
import advanced.model.Question;
import advanced.model.QuizGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;


//clientul se conecteaza
//serverul cere numele
//serverul trimite 3 intrebari
//clientul raspunde
//serverul verifica raspunsurile
//serverul calculeaza scorul si timpul


//aici clientHandler nu mai face extends Thread
//face implements Runnable
//pt GameServer nu il pornesc cu clientHandler.start(); ci executor.execute(clientHandler);
public class ClientHandler implements Runnable {
    private Socket socket;
    private GameServer server;
    private QuizGame game;

    public ClientHandler(Socket socket, GameServer server, QuizGame game) {
        this.socket = socket;
        this.server = server;
        this.game = game;
    }

    //metoda care se executa pt clientul respectiv
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int timeLimit = 10;

            //trimite msj clientului si asteapta ca acesta sa trimita numele
            out.println("Introdu numele tau:");
            String name = in.readLine();

            //creez jucator si il bag in joc
            Player player = new Player(name);
            game.addPlayer(player);

            out.println("Bine ai venit, " + player.getName() + "!");

            //jucatorul primeste 3 intrebari
            for (int i = 0; i < 3; i++){
                Question question = game.getRandomQuestion();//aleg o intrebare random

                out.println("Intrebare: " + question.getText());//si o trimit clientului
                out.println("Ai " + timeLimit + " secunde pentru raspuns.");

                socket.setSoTimeout(timeLimit * 1000);//spune serveruui ca asteapta raspunsul maxim in 10 sec

                //salvez timpul iainte sa inceapa rasp, apoi dupa ce a raspuns
                long startTime = System.currentTimeMillis();
                try {
                    String answer = in.readLine();

                    long endTime = System.currentTimeMillis();
                    long time = endTime - startTime;//cat timp i a luat

                    if (answer == null) {
                        out.println("Clientul s-a deconectat.");
                        return;
                    }

                    if (answer.equalsIgnoreCase("stop")) {
                        out.println("Serverul s-a oprit");
                        server.stop();
                        socket.close();
                        return;
                    }
                    player.addTime(time);

                    if (question.isCorrect(answer)){//veriic raspuns
                        player.addPoint();
                        out.println("Corect!");
                    }
                    else
                    {
                        out.println("Gresit! Raspuns corect: " + question.getCorrectAnswer());
                    }
                }
                //daca jucatrul n rasp in 10 sec se arunca automat
                catch (SocketTimeoutException e){
                    out.println("timpul a expirat! Raspuns corect: " + question.getCorrectAnswer());
                    player.addTime(timeLimit * 1000);
                }
            }

            out.println("Joc terminat!");
            out.println("Scorul tau: " + player.getScore());
            out.println("Timp total: " + player.getTotalTime() + " ms");

            Player winner = game.getWinner();

            if (winner != null){
                out.println("Castigatorul curent este: "+ winner.getName()+ " cu scorul "+ winner.getScore()+ " si timpul "+ winner.getTotalTime() + " ms");
            }

            socket.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}