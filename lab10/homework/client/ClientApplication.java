package homework.client;

public class ClientApplication {
    public static void main(String[] args) {
        GameClient client = new GameClient("localhost", 1234);
        client.start();
    }
}

//creaza client; conecteaza la server; porneste comunicarea