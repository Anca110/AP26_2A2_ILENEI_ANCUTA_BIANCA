package compulsory.server;

public class ServerApplication {

    public static void main(String[] args) {
        try {
            GameServer server = new GameServer(1234);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}