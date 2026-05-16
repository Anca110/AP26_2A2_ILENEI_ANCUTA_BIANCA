package compulsory.server;

import java.io.IOException;

public class ServerApplication{
    public static void main(String[] args){
        try{
            //porneste serverul pe portul asta
            //nu ServerGame ci ServerApplication
            GameServer server = new GameServer(1234);
            server.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}