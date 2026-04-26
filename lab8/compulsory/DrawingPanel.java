package compulsory;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DrawingPanel extends JPanel {
    // desenez grila
    final MainFrame frame;

    int rows = 10;
    int cols = 10;
    int cellSize = 30;

    Cell[][] cells;
    Random random = new Random();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(cols * cellSize + 1, rows * cellSize + 1));
        setBackground(Color.WHITE);

        createCells();
    }

    public void setGridSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        setPreferredSize(new Dimension(cols * cellSize + 1, rows * cellSize + 1));

        createCells();

        revalidate();
        repaint();
    }

    private void createCells() {
        cells = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    public void createRandomMaze() {
        createCells();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                removeRandomWall(row, col);
            }
        }

        repaint();
    }

    private void removeRandomWall(int row, int col) {
        int wall = random.nextInt(4);

        if (wall == 0 && row > 0) {
            cells[row][col].topWall = false;
            cells[row - 1][col].bottomWall = false;
        }

        if (wall == 1 && col < cols - 1) {
            cells[row][col].rightWall = false;
            cells[row][col + 1].leftWall = false;
        }

        if (wall == 2 && row < rows - 1) {
            cells[row][col].bottomWall = false;
            cells[row + 1][col].topWall = false;
        }

        if (wall == 3 && col > 0) {
            cells[row][col].leftWall = false;
            cells[row][col - 1].rightWall = false;
        }
    }

    public void resetMaze() {
        createCells();
        repaint();
    }

    // metoda apelata de Java cand trebuie redesenat panoul
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(Color.BLACK);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                Cell cell = cells[row][col];

                // nu mai desenam doar un patrat complet
                // desenam separat fiecare perete, daca exista
                if (cell.topWall) {
                    graphics.drawLine(x, y, x + cellSize, y);
                }

                if (cell.rightWall) {
                    graphics.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                }

                if (cell.bottomWall) {
                    graphics.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                }

                if (cell.leftWall) {
                    graphics.drawLine(x, y, x, y + cellSize);
                }
            }
        }
    }
}