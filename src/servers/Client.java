package servers;


import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",5005);
        BufferedReader inSock= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outSock = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        BufferedReader inClv = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String msg = inClv.readLine();
            outSock.println(msg);
            String msgServe = inSock.readLine();
            System.out.println(msgServe);
        }

    }
}
