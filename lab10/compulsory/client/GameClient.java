package compulsory.client;

import java.io.*;
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
            //acest client incearca sa se conecteze la server
            //localhost-calculatorul meu, 1234

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //clientul trimite
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(request);


            //citeste raspunsul de la server
            String response = in.readLine();
            System.out.println(response);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}