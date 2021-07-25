package com.iryna.webserver;

import com.iryna.webserver.handler.RequestHandler;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private String webAppPath;
    private int port;

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.setPort(3000);
        server.setWebAppPath("src/main/resources");
        server.startHttpServer();
    }

    public void startHttpServer() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
            ) {
                RequestHandler requestHandler = new RequestHandler(bufferedWriter, bufferedReader, webAppPath);
                requestHandler.handle();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
