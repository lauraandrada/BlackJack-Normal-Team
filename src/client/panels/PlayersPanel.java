package client.panels;

import javax.swing.*;
import java.awt.*;

public class PlayersPanel extends JPanel {
    public PlayersPanel() {
        this.setLayout(new BorderLayout());
        CardsPanel dealerPanel = new CardsPanel("Dealer");
        dealerPanel.addCard(1, "spades");
        dealerPanel.addScore(11);
        dealerPanel.addCard(10, "diamond");
        dealerPanel.addScore(10);

//        CardsPanel playerPanel = new CardsPanel("player");
//        playerPanel.addCard(1, "spades");
//        playerPanel.addScore(11);
//        playerPanel.addCard(10, "diamond");
//        playerPanel.addScore(10);

        this.add(dealerPanel, BorderLayout.NORTH);
//        this.add(playerPanel, BorderLayout.SOUTH);

    }
}
