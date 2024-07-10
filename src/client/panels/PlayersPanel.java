package client.panels;

import client.panels.CardsPanel;
import cards.Card;
import javax.swing.*;
import java.awt.*;

public class PlayersPanel extends JPanel {
    private JPanel north = new JPanel();
    private JPanel center = new JPanel();
    private JPanel south = new JPanel();
    public PlayersPanel() {
        this.setLayout(new BorderLayout());
    }


    //TODO: WHERE DEALER?
    public void addPlayer(String username, String where) {
        CardsPanel cardsPanel = new CardsPanel(username);
        cardsPanel.setName(username);
        switch (where) {
            case "NORTH":
                north.add(cardsPanel);
                this.add(north);
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
                this.add(center);
                break;
            case "SOUTH":
                south.add(cardsPanel);
                this.add(south);
                break;
        }
    }

    private CardsPanel getPlayerCardPanel(String username) {
        for (Component c : this.getRootPane().getComponents()) {
            if (c.getName().toLowerCase().equals(username.toLowerCase())) {
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
        cardsPanel.addCard(cardValue, cardSuit);
    }
}
