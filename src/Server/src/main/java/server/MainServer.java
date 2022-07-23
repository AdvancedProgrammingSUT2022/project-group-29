package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    private static int Server_port = 8000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Server_port);
        while (true) {
            Socket socket = serverSocket.accept();
            new MyThread(socket).start();
        }
    }

}
