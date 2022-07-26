package app;

import controllers.LoginController;
import controllers.SocketController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    private static final int Server_port = 8000;
    private static ServerSocket serverSocket;
    private static ArrayList<SocketController> sockets = new ArrayList<>();
    public static void main(String[] args) {
        createServer();
        LoginController.getInstance().readUserInfo();
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                SocketController socketController = new SocketController(socket);
                sockets.add(socketController);
                socketController.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void createServer() {
        try {
            serverSocket = new ServerSocket(Server_port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<SocketController> getSockets() {
        return sockets;
    }
}
