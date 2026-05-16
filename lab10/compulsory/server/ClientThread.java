package compulsory.server;

import java.io.*;
import java.net.Socket;


public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //pregatesc un cititor care poate citi text trimis de client

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //trimite raspuns clientului

            String request = in.readLine();
            //citesc o linie trimisa de client

            if(request.equals("stop")){
                out.println("Serverul s-a oprit");
                server.stop();//apeleaza metoda din GameServer
            } else
            {
                out.println("Serverul a primit cererea: " + request);
                //asta trimite msj inapoi catre client
            }

            socket.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}