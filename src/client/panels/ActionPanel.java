package client.panels;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {
    private JPanel playersPanel;
    private Image bg;

    public ActionPanel(JTextPane creditsArea, JTextField betField, JButton betBtn, JButton hitBtn, JButton standBtn) {
        this.setLayout(new BorderLayout());

        try {
            bg = Toolkit.getDefaultToolkit().getImage("assets/background/background.jpg");
        } catch (Exception e) {
            System.err.println("Background image not found!");
        }

        // Players Panel
        playersPanel = new PlayersPanel();
        playersPanel.setLayout(new BorderLayout());

        // Bet Panel
        JPanel betPanel = new JPanel();
        betPanel.setLayout(new BorderLayout());
        betPanel.add(betField, BorderLayout.WEST);
        betPanel.add(betBtn, BorderLayout.EAST);

        // Choice Panel
        JPanel choicePanel = new JPanel();
        choicePanel.setLayout(new BorderLayout());
        choicePanel.add(hitBtn, BorderLayout.WEST);
        choicePanel.add(standBtn, BorderLayout.EAST);

        // North Panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        JPanel creditsPanel = new JPanel();
        JLabel creditsLabel = new JLabel("Credits:");
        creditsPanel.add(creditsLabel, BorderLayout.WEST);
        creditsPanel.add(creditsArea, BorderLayout.EAST);
        northPanel.add(creditsPanel, BorderLayout.WEST);

        // South Panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(betPanel, BorderLayout.WEST);
        southPanel.add(choicePanel, BorderLayout.EAST);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(playersPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
