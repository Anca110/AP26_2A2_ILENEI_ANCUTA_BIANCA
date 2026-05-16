package advanced.bot;

import advanced.model.Question;

import java.io.*;
import java.net.Socket;

public class BotClientLauncher {

    public static void main(String[] args) {
        BotPlayer bot = new LLMBotPlayer(BotDifficulty.HARD);
        try {
            Socket socket = new Socket("localhost", 1234);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true
            );

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println(message);

                if (message.equals("Introdu numele tau:")){
                    out.println(bot.getName());
                }//cand serverul cere numele=>botul isi trimite automat numele

                if (message.startsWith("Intrebare: ")){//verifica daca mesajul primit de la server e o intrebare
                    String questionText = message.substring("Intrebare: ".length());//scot textul intrebarii

                    Question question = new Question(questionText, "");//fac un obiect question ca sa l putem da botului
                    String answer = bot.answerQuestion(question);//botul raspunde

                    System.out.println("Botul raspunde: " + answer);
                    out.println(answer);
                }
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}