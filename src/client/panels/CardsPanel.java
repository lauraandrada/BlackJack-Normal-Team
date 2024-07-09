package client.panels;

import javax.swing.*;
import java.awt.*;

public class CardsPanel extends JPanel {
    private JPanel northPanel;
    private JPanel cardsPanel;
    private JPanel southPanel;
    private JTextPane scoreLabel = new JTextPane();
    private int score = 0;

    public CardsPanel(String username){
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new BorderLayout());

        northPanel = new JPanel();

        southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        JTextPane usernameLabel = new JTextPane();
        usernameLabel.setText(username + ":");
        scoreLabel.setText("0");
        southPanel.add(usernameLabel, BorderLayout.WEST);
        southPanel.add(scoreLabel, BorderLayout.EAST);

        cardsPanel.add(northPanel, BorderLayout.NORTH);
        cardsPanel.add(southPanel, BorderLayout.SOUTH);
    }

    public void addCard(int cardNumber, String cardSuit){
        String cardPath = "assets/cards/" + cardNumber + "_of_" + cardSuit;
        ImageIcon cardImage = null;
        try {
            cardImage = new ImageIcon(cardPath);
        }catch (Exception e){
            System.err.println("The given card doesn't exist");
        }

        JLabel cardImg = new JLabel();
        if (cardImage != null){
            cardImg.setIcon(cardImage);
        }
        cardImg.setPreferredSize(new Dimension(100, 50));
        northPanel.add(cardImg);
    }

    public void addScore(int score) {
        this.score += score;
        scoreLabel.setText(String.valueOf(score));
    }

}
