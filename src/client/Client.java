package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.io.IOException;

public class Client extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
         clientSocket = new Socket(ip,port);
         out = new PrintWriter(clientSocket.getOutputStream(),true);
         // autoFlush(true) = sterge mesajul trimis catre server
         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String response = " ";
            while ((response != null)) {
                response = in.readLine();
                // Get every user's action
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
