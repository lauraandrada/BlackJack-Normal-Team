package client.panels;

import client.panels.CardsPanel;
import cards.Card;
import javax.swing.*;
import java.awt.*;

public class PlayersPanel extends JPanel {
    public PlayersPanel() {
        this.setLayout(new BorderLayout());
    }

    public void addPlayer(String username, String where) {
        CardsPanel cardsPanel = new CardsPanel(username);
        cardsPanel.setName(username);
        switch (where) {
            case "NORTH":
                this.add(cardsPanel, BorderLayout.NORTH);
                break;
            case "EAST":
                this.add(cardsPanel, BorderLayout.EAST);
                break;
            case "WEST":
                this.add(cardsPanel, BorderLayout.WEST);
                break;
            case "SOUTH":
                this.add(cardsPanel, BorderLayout.SOUTH);
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
