package com.iryna.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final String PATH_TO_RESOURCES = "src/main/resources";
    private static final int PORT = 3000;
    private static final String[] HTTP_STATUSES = {
            "200 OK",
            "404 Not Found",
            "500 Internal Server Error"
    };

    public static void main(String[] args) {
        startHttpServer();
    }

    private static void startHttpServer() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
            ) {
                String line;
                String data = null;
                int currentStatus = 0;
                String path = null;

                try {
                    while (!(line = bufferedReader.readLine()).isEmpty()) {
                        if (line.contains("GET")) {
                            String[] res = line.split(" ");
                            path = res[1];
                            break;
                        }
                    }
                } catch (NullPointerException e) {}
                try {
                    data = getDataFromFile(path);
                } catch (IOException exception) {
                    if (exception.getClass().getName().equals("java.io.FileNotFoundException")) {
                        currentStatus = 1;
                    } else {
                        currentStatus = 2;
                    }
                }
                bufferedWriter.write("HTTP/1.1 " +
                        HTTP_STATUSES[currentStatus] +
                        "\n" +
                        "\n");
                if (currentStatus == 0) {
                    bufferedWriter.write(data);
                }
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    private static String getDataFromFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_TO_RESOURCES + path)))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            return stringBuilder.toString();
        }
    }
}
