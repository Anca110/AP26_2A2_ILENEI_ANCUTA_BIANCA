package compulsory.client;

import java.util.Scanner;

public class ClientApplication {

    public static void main(String[] args) {
        GameClient client = new GameClient("localhost", 1234);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Client pornit.");
        System.out.println("Comenzi disponibile:");
        System.out.println("player Ana");
        System.out.println("question Capitala Romaniei?|Bucuresti");
        System.out.println("players");
        System.out.println("questions");
        System.out.println("stop");
        System.out.println();

        while (true) {
            System.out.print("Comanda: ");
            String request = scanner.nextLine();

            client.sendRequest(request);

            if (request.equals("stop")) {
                break;
            }
        }
    }
}