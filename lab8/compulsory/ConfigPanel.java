package compulsory;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    // JPanel e un panou; bucata din fereastra
    final MainFrame frame;

    JLabel rowsLabel;
    JLabel colsLabel;
    JTextField rowsField;
    JTextField colsField;
    JButton drawButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        rowsLabel = new JLabel("Rows:");
        colsLabel = new JLabel("Cols:");

        rowsField = new JTextField("10", 5);
        colsField = new JTextField("10", 5);

        drawButton = new JButton("Draw");

        add(rowsLabel);
        add(rowsField);
        add(colsLabel);
        add(colsField);
        add(drawButton);

        // cand apas Draw, citesc numerele scrise de utilizator
        // apoi schimb dimensiunea grilei din DrawingPanel
        drawButton.addActionListener(e -> {
            try {
                int rows = Integer.parseInt(rowsField.getText().trim());
                int cols = Integer.parseInt(colsField.getText().trim());

                frame.drawingPanel.setGridSize(rows, cols);
                frame.pack();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(frame, "randurile si coloanele trebuie sa fie numere");
            }
        });
    }
}