package homework.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


//clientul rămâne conectat
//serverul poate trimite întrebări
//tu poți scrie răspunsuri
//clientul poate primi mesaje în același timp

public class GameClient {
    private String serverAddress;
    private int port;

    public GameClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void start(){
        try{
            Socket socket = new Socket(serverAddress, port);//conectez client la server

            //citeste msj de la server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //trimite msj catre server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);


            //1)+2) in acelasi timp in paralel

            //1)un fir separat care doar asculta serverul
            //adc mereu face: server a triis cv? da=> afiseaza; a mai trimis ceva? afiseaza..
            Thread readThread = new Thread(() ->{
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                }
                catch (IOException e) {
                    System.out.println("Conexiunea cu serverul s-a inchis.");
                }
            });

            readThread.start();

            while (true){
                //2)in acelasi timp programul principal e aici
                //citeste ce scriu de la tastatura
                String request = scanner.nextLine();

                if (request.equals("exit")){
                    break;
                }
                out.println(request);
            }

            socket.close();
            scanner.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}