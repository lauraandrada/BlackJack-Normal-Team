package client.panels.components;

import cards.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayersPanel extends JPanel {
    private JPanel north = new JPanel();
    private JPanel center = new JPanel(new BorderLayout());
    private JPanel south = new JPanel();
    private BufferedImage backgroundImage;

    public PlayersPanel() {
        this.setLayout(new BorderLayout());

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("assets/background/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        north.setOpaque(false);
        center.setOpaque(false);
        south.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }


    //TODO: WHERE THE DEALER
    public void addPlayer(String username, String where) {
        CardsPanel cardsPanel = new CardsPanel(username);
        cardsPanel.setName(username);
        cardsPanel.setOpaque(false);
        for (Component comp : cardsPanel.getComponents()) {
            if (comp instanceof JLabel) {
                ((JLabel) comp).setOpaque(false);
            }
        }
        switch (where) {
            case "NORTH":
                north.add(cardsPanel);
                this.add(north, BorderLayout.NORTH);
                break;
            case "WEST":
            case "EAST":
                switch (where) {
                    case "WEST":
                        center.add(cardsPanel, BorderLayout.WEST);
                        break;
                    case "EAST":
                        center.add(cardsPanel, BorderLayout.EAST);
                        break;
                }
                this.add(center, BorderLayout.CENTER);
                break;
            case "SOUTH":
                south.add(cardsPanel);
                this.add(south, BorderLayout.SOUTH);
                break;
        }
    }

    //TODO WHAT IS THIS CODE DOING
    private CardsPanel getPlayerCardPanel(String username) {
        for (Component c : this.getComponents()) { // Changed from getRootPane().getComponents() to this.getComponents()
            if (c.getName().equalsIgnoreCase(username)) {
                if (c instanceof CardsPanel) {
                    return (CardsPanel) c;
                } else {
                    System.out.println("The desired PlayerCardPanel is not a CardsPanel");
                }
            }
        }
        return null;
    }

    public void addCardToPlayer(String username, Card cardToAdd) {
        int cardValue = cardToAdd.getValue();
        String cardSuit = cardToAdd.getType();
        CardsPanel cardsPanel = getPlayerCardPanel(username);
        if (cardsPanel != null) {
            cardsPanel.addCard(cardValue, cardSuit);
        }
    }
}