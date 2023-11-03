package net.nextfight.socketserver;

import net.nextfight.socketserver.playerdata.Cape;
import net.nextfight.socketserver.playerdata.ClientPlayer;
import net.nextfight.socketserver.playerdata.Rank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final short PORT = 1111;

    public static void main(String[] args) {
        startSocketServer();
    }

    private static Object onMessage(String[] args) {
        switch (args[0]) {
            case "playerprofile" -> {
                ClientPlayer player = new ClientPlayer(args[1]);

                //TODO get data from database
                player.setName("MaximDe");
                player.setRank(Rank.PLAYER);
                player.setSelectedCape(Cape.MAXIMCLIENT);
                return player;
            }
        }

        return null;
    }

    private static void startSocketServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Started socket server on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread clientThread = new Thread(() -> {
                    try {
                        final var ois = new ObjectInputStream(clientSocket.getInputStream());
                        String message = (String) ois.readObject();
                        System.out.println("Message Received: " + message);

                        final var object = onMessage(message.split(":"));

                        final var out = new ObjectOutputStream(clientSocket.getOutputStream());
                        out.writeObject(object);
                        out.close();
                        clientSocket.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}