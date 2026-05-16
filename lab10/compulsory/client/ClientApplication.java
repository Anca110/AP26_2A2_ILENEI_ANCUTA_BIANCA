package compulsory.client;

import java.util.Scanner;


public class ClientApplication{
    public static void main(String[] args){

        //craere client care se conecteaza la server pe calculatorul meu pe port
        GameClient client = new GameClient("localhost", 1234);
        Scanner scanner = new Scanner(System.in);//permite sa scriu comenzi in consola

        //clientul sta si asteapta comenzi
        while (true) {
            System.out.print("Introdu comanda: ");
            String request = scanner.nextLine();//citeste clientul ce scriu eu de la tastatura pt el

            if (request.equals("exit")){//exit-inchid clientul; stop-opreste serverul
                break;
            }

            client.sendRequest(request);
        }

        scanner.close();
    }
}