package client;

import client.panels.ActionPanel;
import client.panels.MessageAreaPanel;
import client.panels.TopPanel;

import utils.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class Gui extends JFrame {
    // IP
    private String IP;
    private int PORT;
    private JTextField usernameField;
    private final JTextField ipField;
    private final JButton connectBtn;
    private final JTextPane creditsArea;
    private final JTextField betField;
    private final JButton hitBtn;
    private final JButton standBtn;
    private final JButton betBtn;

    private final Client client;
    private  Player player;

    private final Player dealer;

    private final ArrayList<String> where;

    public Gui() {
        super("BlackJack");
        where = new ArrayList<>();
        where.add("NORTH");
        where.add("SOUTH");
        where.add("EAST");
        where.add("WEST");

        this.dealer = new Player("Dealer");
        setPreferredSize(new Dimension(800, 1200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image image = null;
        try {
            image = Toolkit.getDefaultToolkit().getImage("assets/logo/icon.png");
        } catch (Exception e) {
            System.err.println("Icon image not found");
        }
        if (image != null) {
            this.setIconImage(image);
        }

        client = new Client();

        usernameField = new JTextField(10);
        ipField = new JTextField(15);

        connectBtn = new JButton("Connect");

        creditsArea = new JTextPane();
        betField = new JTextField(5);

        betBtn = new JButton("Bet");
        hitBtn = new JButton("Hit");

        standBtn = new JButton("Stand");

        JPanel topPanel = new TopPanel(usernameField, ipField, connectBtn);
        this.add(topPanel, BorderLayout.NORTH);


        ActionPanel actionPanel = new ActionPanel(creditsArea, betField, betBtn, hitBtn, standBtn);
        actionPanel.addPlayer(dealer, where.removeFirst());
        actionPanel.setVisible(false);
        this.add(actionPanel, BorderLayout.CENTER);


        MessageAreaPanel messageAreaPanel = new MessageAreaPanel();
        this.add(messageAreaPanel,BorderLayout.SOUTH);


        // Action Listeners
        connectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().trim() != null) {
                    String name = usernameField.getText().trim();
                    player = new Player(name);
                } else {
                    JOptionPane.showMessageDialog(Gui.this, "Please provide a username!");
                }
                if (ipField.getText().trim().contains(":")) {
                    String[] ipAndPort = ipField.getText().trim().split(":");
                    IP = ipAndPort[0];
                    PORT = Integer.parseInt(ipAndPort[1]);
                } else {
                    JOptionPane.showMessageDialog(Gui.this, "Invalid IP and PORT");
                }

                if (player != null && IP != null && PORT > 1000) {
                    try {
                        actionPanel.setVisible(true);
                        creditsArea.setText(String.valueOf(player.getCredit()));
                        actionPanel.addPlayer(player, where.removeFirst());
                        client.startConnection(IP, PORT);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(Gui.this, "An error occured during connection, please try again!");
                }
            }
        });

        pack();
        setVisible(true);
    }
}
