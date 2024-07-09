package client.panels;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {

    public TopPanel(JTextField usernameField, JTextField ipField, JButton connectBtn) {
        this.setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Username:");
        JLabel ipLabel = new JLabel("IP:PORT:");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(usernameLabel, gbc);

        // Username Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(usernameField, gbc);

        // IP Label
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(ipLabel, gbc);

        // IP Field
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(ipField, gbc);

        // Connect Button
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        this.add(connectBtn, gbc);

    }
}
