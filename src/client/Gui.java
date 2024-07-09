package client;

import utils.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Gui extends JFrame {
    private final JTextField usernameField; //user's name
    private final JTextField ipField;
    private final JButton connectBtn;
//    private final JTextField betField;
//    private final JPanel actionPanel;
//    private final JPanel cardsPanel; // cartile, totalul si bet-ul
//    private final JTextPane creditsArea;
//    private final JButton hitBtn;
//    private final JButton standBtn;
//    private final JButton betBtn;
//    private final JPanel textPanel;
//    private final JTextArea messageArea;
//    private final JTextField inputField;
//    private final JButton sendBtn;

    private final Client client;
//    private final Player player;

    public Gui() {
        super("BlackJack");

        setPreferredSize(new Dimension(600, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Image image = Toolkit.getDefaultToolkit().getImage("assets/logo/icon.png");
        this.setIconImage(image);

        client = new Client();

        usernameField = new JTextField(10);
        JLabel usernameLabel = new JLabel("Username:");

        ipField = new JTextField(15);
        JLabel ipLabel = new JLabel("IP:PORT:");

        connectBtn = new JButton("Connect");
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(usernameLabel, gbc);

        // Username Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.HORIZONTAL;
        topPanel.add(usernameField, gbc);

        // Ip Label
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NONE;
        topPanel.add(ipLabel, gbc);

        // Ip field
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.HORIZONTAL;
        topPanel.add(ipField, gbc);

        // Connect
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        topPanel.add(connectBtn, gbc);

        this.add(topPanel, BorderLayout.NORTH);



    }
}
