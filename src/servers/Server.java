package servers;

import models.Compte;
import threads.Processing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Compte> compteList=new ArrayList<>();
    public static void main(String [] args) throws IOException {
        ServerSocket servSock = new ServerSocket(5005);
        while (true){
            Socket socket = servSock.accept();
            Processing processing = new Processing(socket);
            processing.start();
        }
    }
}
