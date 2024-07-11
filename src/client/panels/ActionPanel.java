package client.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import cards.Deck;
import cards.Card;
import client.panels.components.PlayersPanel;
import utils.Player;

public class ActionPanel extends JPanel {
    private Deck deck;
    private Card card;
    private ArrayList<Card> cardList;
    private PlayersPanel playersPanel;

    private Player player;

    public ActionPanel(JTextPane creditsArea,
                       JTextField betField,
                       JButton betBtn,
                       JButton hitBtn,
                       JButton standBtn) {
        this.setLayout(new BorderLayout());
        this.deck = new Deck();
        deck.initializeDeck();

        // Players Panel
        playersPanel = new PlayersPanel();

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
        creditsPanel.setLayout(new BorderLayout());
        JLabel creditsLabel = new JLabel("Credits:");
        creditsPanel.add(creditsLabel, BorderLayout.WEST);
        creditsArea.setOpaque(false);
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

        betBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int bet = Integer.parseInt(betField.getText().trim());
                if (player.getCredit() <= 0) {
                    betBtn.setEnabled(false);
                }
                if (bet > 10 && bet % 10 == 0 && bet <= player.getCredit()) {
                    if (!player.getName().equals("Dealer")) {
                        player.removeCredit(bet);
                        creditsArea.setText(String.valueOf(player.getCredit()));
                    }
                }
            }
        });

        //TODO - GET COLLECT PLAYER PANEL COMPONENT
        hitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hit
                Card cardToGive = deck.removeCardFromDeck();
                playersPanel.addCardToPlayer("", cardToGive);
            }
        });

        standBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Stand
            }
        });
    }

    public void addPlayer( Player player, String where) {
        this.player = player;
        playersPanel.addPlayer(player.getName(), where);
    }


}
