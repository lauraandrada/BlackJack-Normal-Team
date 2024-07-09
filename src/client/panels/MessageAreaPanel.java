package client.panels;

import javax.swing.*;
import java.awt.*;

public class MessageAreaPanel extends JPanel {
    private JTextArea textArea;
    private JTextField input;
    private JButton sendBtn;

    public MessageAreaPanel() {
        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        // Setup the text area and scroll pane
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(this.getWidth(), 200));

        northPanel.add(scrollPane, BorderLayout.CENTER);

        // Setup the input field and send button
        input = new JTextField();
        sendBtn = new JButton("Send");

        southPanel.add(input, BorderLayout.CENTER);
        southPanel.add(sendBtn, BorderLayout.EAST);

        this.add(northPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        // Resize listener to adjust input field width
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                adjustInputFieldWidth();
            }
        });
    }

    private void adjustInputFieldWidth() {
        int buttonWidth = sendBtn.getWidth();
        input.setPreferredSize(new Dimension(this.getWidth() - buttonWidth, input.getHeight()));
        input.revalidate();
    }

    public void updateTextArea(String message) {
        if (!message.isEmpty() && !message.isBlank())
            this.textArea.append("\n" + message);
    }
}
