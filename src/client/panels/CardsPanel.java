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
        this.setLayout(new BorderLayout());
        northPanel = new JPanel();

        southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setPreferredSize(new Dimension(75, 25)); // Set the preferred size
        JTextPane usernameLabel = new JTextPane();
        usernameLabel.setOpaque(false); // Make background transparent
        usernameLabel.setText(username + ":");
        scoreLabel.setText("0");
        scoreLabel.setOpaque(false);

        southPanel.add(usernameLabel, BorderLayout.WEST);
        southPanel.add(scoreLabel, BorderLayout.EAST);

        JPanel southContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Use FlowLayout to center
        southContainer.add(southPanel);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southContainer, BorderLayout.SOUTH); // Add the southContainer instead of southPanel
    }

    public void addCard(int cardNumber, String cardSuit){
        String cardPath = "assets/cards/" + cardNumber + "_of_" + cardSuit + ".png"; // Ensure correct path with file extension
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(cardPath);
            Image cardImage = imageIcon.getImage();
            Image reseziedCardImage = cardImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(reseziedCardImage);
        }catch (Exception e){
            System.err.println("The given card doesn't exist");
        }

        JLabel cardImg = new JLabel();
        if (imageIcon != null){
            cardImg.setIcon(imageIcon);
        }
        northPanel.add(cardImg);
    }

    public void addScore(int score) {
        this.score += score;
        scoreLabel.setText(String.valueOf(this.score)); // Update with cumulative score
    }

}
