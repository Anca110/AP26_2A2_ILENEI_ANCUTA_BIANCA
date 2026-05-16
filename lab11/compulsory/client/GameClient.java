package compulsory.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {

    private String serverAddress;
    private int port;

    public GameClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void sendRequest(String request) {
        try {
            Socket socket = new Socket(serverAddress, port);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(request);

            String response = in.readLine();
            System.out.println(response);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}