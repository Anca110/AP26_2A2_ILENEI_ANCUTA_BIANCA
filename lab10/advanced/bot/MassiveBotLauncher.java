package advanced.bot;

import advanced.model.Question;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MassiveBotLauncher {
    private static final int NUMBER_OF_BOTS = 100;//seteaza cati boti pornesti

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();//creaza cate un thread virtual pt fiecare bot lansat de tester
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_BOTS);//folosit pt ca sa astepte pana termina toti botii
        //fara el main() s ar putea termina inainte ca botii sa termine

        for (int i = 1; i <= NUMBER_OF_BOTS; i++) {
            int botIndex = i;

            executor.execute(() -> {
                runBot(botIndex);
                latch.countDown();
            });
        }

        latch.await();

        long endTime = System.currentTimeMillis();

        executor.shutdown();

        System.out.println("Au terminat " + NUMBER_OF_BOTS + " boti.");
        System.out.println("Timp total: " + (endTime - startTime) + " ms");
    }

    private static void runBot(int botIndex) {
        BotPlayer bot = new SmartBotPlayer();

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
                if (message.equals("Introdu numele tau:")) {
                    out.println(bot.getName() + "-" + botIndex);
                }

                if (message.startsWith("Intrebare: ")) {
                    String questionText = message.substring("Intrebare: ".length());

                    Question question = new Question(questionText, "");
                    String answer = bot.answerQuestion(question);

                    System.out.println(bot.getName() + "-" + botIndex + " raspunde: " + answer);

                    out.println(answer);
                }
            }

            socket.close();

        } catch (IOException e) {
            System.out.println("Botul " + botIndex + " nu s-a putut conecta: " + e.getMessage());
        }
    }
}