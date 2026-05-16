package compulsory.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//clasa principala  serverului
//sta pe port, asteapta clienti, cand vine un client=>creaza un thread separat pt el
public class GameServer {
    private ServerSocket serverSocket;//serverul. sta pe port si asteapta clienti, onexiuni
    private boolean running = true;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("Serverul a pornit si asteapta clienti...");

        while (running) {
            //socket-conexiunea dintre server-client
            //dupa ce clientul intra pe usa ServerSocket=>primeste propria conexiune(Socket), accepta un client
            Socket socket = serverSocket.accept();

            ClientThread clientThread = new ClientThread(socket, this);
            //pt acest client creaza un thread separat
            //separat pt ca serverul sa poata primi si alti clienti
            clientThread.start();//pornesc threadul si incepe sa ruleze
            //start() creaza fir nou de executie, nu run()
        }
    }

    //opreste serverul
    public void stop() throws IOException {
        running = false;//opreste while(running)=> serverul nu mai accepta clienti
        serverSocket.close();//inchide portul
    }
}