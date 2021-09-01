package xeraction.gameolife;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        drawBoard();
    }

    private Graphics g;

    public void drawBoard() {
        Graphics2D g2 = (Graphics2D)this.g;
        Cell[][] board = Board.board;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                boolean value = board[i][j].isAlive();
                if (value) {
                    g2.setColor(Color.BLACK);
                }
                else {
                    g2.setColor(Color.WHITE);
                }
                g2.fillRect(j * 4 + j, i * 4 + i, 4, 4);
            }
        }
    }
}
