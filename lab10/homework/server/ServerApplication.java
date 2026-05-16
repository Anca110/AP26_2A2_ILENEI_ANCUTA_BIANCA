package homework.server;

import java.io.IOException;

public class ServerApplication {
    public static void main(String[] args) {
        try{
            GameServer server = new GameServer(1234);
            server.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}