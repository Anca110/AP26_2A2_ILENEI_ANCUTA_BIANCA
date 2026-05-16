package advanced.server;

import advanced.model.QuizGame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameServer {
    private ServerSocket serverSocket;
    private boolean running = true;
    private QuizGame game;//tine jocul(intrebari jucatori scoruri)

    private ExecutorService executor;
    //nu mai fac direct clientThread.start(); ci folosesc un executor(manager de thread uri)
    //in loc sa creez manual un thread t fiecare client=> ii dau lui task ul si rl ruleaza pe un thread disponibil

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        game = new QuizGame();

        //un thread virtual pt toti clientii(pot avea mai multe conexiuni)
        executor = Executors.newVirtualThreadPerTaskExecutor();
        // creeaza cate un thread virtual pentru fiecare client
        // e potrivit cand avem foarte multi clienti care asteapta raspunsuri prin socket
    }

    public void start() {
        System.out.println("Serverul pentru quiz a pornit...");

        while (running) {
            try {
                Socket socket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(socket, this, game);
                //creez obiectul care se va ocupa de clientul respectiv
                executor.execute(clientHandler);
                //asta porneste handler ul prin executor
                //pt ClientHandler implementez Runnable

            } catch (SocketException e) {
                if (!running) {
                    System.out.println("Serverul s-a inchis");
                } else {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;

        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            executor.shutdown();

            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }

        } catch (IOException | InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}