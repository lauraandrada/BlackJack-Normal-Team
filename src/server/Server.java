package server;

import cards.Card;
import utils.Channel;
import utils.Player;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class Server {
    private static int PORT = 2222;
    private static final String CREATE_CHANNEL_COMMAND = "/create";
    private static final String CONNECT_TO_CHANNEL = "/connect";
    private static final String DISCONNECT_FROM_CHANNEL = "/disconnect";
    private ServerSocket serverSocket;
    private static final ConcurrentHashMap<Channel, List<ClientHandler>> channels = new ConcurrentHashMap<>();
    private static final Channel global = new Channel("global", "global");

    private void start() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        System.out.println("Listening on port " + PORT);
        channels.putIfAbsent(global, Collections.synchronizedList(new ArrayList<>()));
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new ClientHandler(clientSocket).start();
        }
    }

    private static class ClientHandler<T> extends Thread {
        private final Socket clientSocket;
        private Player user;
        private Player currentUser;
        private PrintWriter messageOut;
        private BufferedReader messageIn;
        private ObjectOutputStream objectOut;
        private ObjectInputStream objectIn;
        private Channel currentChannel = global;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void createChannel(Player user, String name, String password) {
            for (Channel channel : channels.keySet()) {
                if (channel.getName().equals(name)) {
                    messageOut.println("Channel already exists!");
                    return;
                }
            }
            Channel channel = new Channel(name, password);
            channels.putIfAbsent(channel, Collections.synchronizedList(new ArrayList<>(3)));
            connectToChannel(user, channel, true);
            messageOut.println("Channel created: " + name);
        }

        public void connectToChannel(Player user, Channel channel, boolean status) {
            List<ClientHandler> handlers = channels.get(channel);
            if (handlers != null) {
                if (handlers.size() < 3) {
                    synchronized (handlers) {
                        if (!handlers.contains(this)) {
                            handlers.add(this);
                            channels.put(channel, handlers);
                            if (currentChannel != null && !currentChannel.equals(channel))
                                disconnectFromChannel(user, currentChannel);
                            this.currentChannel = channel;
                            messageOut.println("Connected to " + channel.getName());
                            if (status) {
                                messageOut.println("HTTP_STATUS_200");
                            }
                            broadcastMessage(user.getName() + " has joined the channel " + channel.getName(), channel);
                        }
                    }
                } else {
                    messageOut.println("Channel is full!");
                }
            } else {
                messageOut.println("Channel doesn't exist!");
            }
        }

        public void disconnectFromChannel(Player user, Channel channel) {
            List<ClientHandler> handlers = channels.get(channel);
            if (handlers != null) {
                synchronized (handlers) {
                    handlers.remove(this);
                    if (handlers.isEmpty()) {
                        channels.remove(channel);
                    } else {
                        channels.put(channel, handlers);
                    }
                }
                broadcastMessage(user.getName() + " has left the channel " + channel.getName(), channel);
                messageOut.println("HTTP_STATUS_200");
            } else {
                messageOut.println("You can't disconnect from a channel that doesn't exist!");
            }
        }

        public void broadcastMessage(String message, Channel channel) {
            List<ClientHandler> handlers = channels.get(channel);
            synchronized (handlers) {
                for (ClientHandler handler : handlers) {
                    if (!message.isEmpty() && !message.isBlank())
                        handler.messageOut.println(message);
                }
            }
        }

        public void broadcastObjects(Object<T> obj, Channel channel) throws IOException {
            List<ClientHandler> handlers = channels.get(channel);
            synchronized (handlers) {
                for (ClientHandler handler : handlers) {
                    // Send the given Object
                }
            }
        }

        private Player getPlayer() throws IOException, ClassNotFoundException {
            this.objectIn = new ObjectInputStream(clientSocket.getInputStream());
            if (objectIn.readObject() != null && objectIn.readObject() instanceof Player) {
                Player playerReceived = (Player) objectIn.readObject();
                return playerReceived;
            }
            return null;
        }

        private Card getCard() throws IOException, ClassNotFoundException {
            this.objectIn = new ObjectInputStream(clientSocket.getInputStream());
            if (objectIn.readObject() != null && objectIn.readObject() instanceof Card) {
                Card cardReceived = (Card) objectIn.readObject();
                return cardReceived;
            }
            return null;
        }

        public ArrayList<T> getObjects() throws IOException, ClassNotFoundException {
            ArrayList<T> objectsArr = new ArrayList<>();
            if (getPlayer() != null) objectsArr.add((T) getPlayer());
            if (getCard() != null) objectsArr.add((T) getCard());
            return objectsArr;
        }

        public void handleMessage() throws IOException {
            this.messageOut = new PrintWriter(clientSocket.getOutputStream(), true);
            this.messageIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = messageIn.readLine()) != null) {

                String[] rawMessage = message.split(": ", 2);

                if (rawMessage.length < 2) {
                    messageOut.println("Invalid message format!");
                    continue;
                }

                String username = rawMessage[0];
                message = rawMessage[1];

                if (currentUser == null) {
                    currentUser = new Player(username);
                }

                if (message.startsWith("/")) {
                    String[] command = message.split(" ");
                    if (command.length > 0) {
                        switch (command[0]) {
                            case CREATE_CHANNEL_COMMAND:
                                if (command.length == 3) {
                                    disconnectFromChannel(currentUser, currentChannel);
                                    createChannel(currentUser, command[1], command[2]);
                                } else {
                                    messageOut.println("Invalid create command format!");
                                }
                                break;
                            case CONNECT_TO_CHANNEL:
                                if (command.length == 3) {
                                    boolean found = false;
                                    for (Channel c : channels.keySet()) {
                                        if (c.getName().equals(command[1]) && c.getPassword().equals(command[2])) {
                                            disconnectFromChannel(currentUser, currentChannel);
                                            connectToChannel(currentUser, c, true);
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        messageOut.println("Channel not found!");
                                    }
                                } else {
                                    messageOut.println("Invalid connect command format!");
                                }
                                break;
                            case DISCONNECT_FROM_CHANNEL:
                                boolean disconnected = false;
                                for (Channel c : channels.keySet()) {
                                    List<ClientHandler> handlers = channels.get(c);
                                    if (handlers.contains(this)) {
                                        disconnectFromChannel(currentUser, c);
                                        disconnected = true;
                                        break;
                                    }
                                }
                                if (!disconnected) {
                                    messageOut.println("You are not in any channel!");
                                }
                                break;
                            default:
                                messageOut.println("Command unknown!");
                        }
                    }
                } else {
                    if (!message.isBlank())
                        broadcastMessage(currentUser.getName() + ": " + message, currentChannel);
                }
            }

            messageIn.close();
            messageOut.close();
            clientSocket.close();
        }

        public void handleObjects() throws IOException, ClassNotFoundException {
            objectToSend;
            while ((objectToSend = objectIn.readObject()) != null) {
                if (objectToSend instanceof Player){
                    objectToSend = getPlayer();
                }
                if (objectToSend instanceof Card){
                    objectToSend = getCard();
                }
                broadcastObjects();
            }

        }

        public void run() {
            try {

                this.objectOut = new ObjectOutputStream(clientSocket.getOutputStream());

                String message;
                ArrayList<T> objectsArr = new ArrayList<>();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
