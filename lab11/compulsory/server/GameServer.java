package compulsory.server;

import compulsory.util.JpaUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    private ServerSocket serverSocket;
    private boolean running = true;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("Serverul a pornit si asteapta clienti...");

        while (running) {
            try {
                Socket socket = serverSocket.accept();

                ClientThread clientThread = new ClientThread(socket, this);
                clientThread.start();

            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        }

        JpaUtil.close();
        System.out.println("Server inchis.");
    }

    public void stop() throws IOException {
        running = false;
        serverSocket.close();
    }
}