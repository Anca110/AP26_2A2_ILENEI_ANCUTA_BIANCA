package compulsory;

import javax.swing.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;

    JButton createButton;
    JButton resetButton;
    JButton exitButton;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        createButton = new JButton("Create");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        add(createButton);
        add(resetButton);
        add(exitButton);

        createButton.addActionListener(e -> frame.drawingPanel.createRandomMaze());

        resetButton.addActionListener(e -> frame.drawingPanel.resetMaze());

        exitButton.addActionListener(e -> System.exit(0));
        // cand se apasa butonul, executa System.exit(0)
    }
}