package homework.server;

import homework.model.QuizGame;

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
        executor = Executors.newFixedThreadPool(10);//creaza un grup d emaxim 10 thread uri( deci serverul poate trata pana la 10 clienti in paralel)
    }

    public void start() {
        System.out.println("Serverul pt quiz a pornit");

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