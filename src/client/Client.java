package client;

import cards.Card;
import utils.Player;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Client<T> extends Thread {
    private Socket clientSocket;
    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    private String response;
    private Card card;
    private Player player;
    private ConcurrentHashMap<Player, ArrayList<Card>> objectsReceived;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip,port);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        messageOut = new PrintWriter(outputStream,true);
        messageIn = new BufferedReader(new InputStreamReader(inputStream));

        objectOut = new ObjectOutputStream(outputStream);
        objectIn = new ObjectInputStream(inputStream);

        objectsReceived = new ConcurrentHashMap<>();
    }

    private void getMessage() {
        try {
            while ((response = messageIn.readLine()) != null) {
                // Do something with the response
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getData() {
        try {
            ArrayList<T> objectInput;
            while ((objectInput = (ArrayList<T>) objectIn.readObject()) != null) {
                if (objectInput.size() == 1) {
                    if (objectInput.getFirst() instanceof Player) {
                        player = (Player) objectInput.getFirst();
                        objectsReceived.putIfAbsent(player, new ArrayList<Card>());
                    }
                } else if (objectInput.size() == 2) {
                    if (objectInput.get(0) instanceof Player && objectInput.get(1) instanceof Card) {
                        card = (Card) objectInput.get(1);
                        objectsReceived.get(player).add(card);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Thread messageThread = new Thread(this::getMessage);

        Thread playerThread = new Thread(this::getData);

        messageThread.start();
        playerThread.start();

        try {
            messageThread.join();
            playerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
