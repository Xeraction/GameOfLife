package xeraction.gameolife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOfLife implements Runnable {
    private static boolean running = false;
    private static Screen screen = new Screen();
    private static JFrame frame;

    public static void main(String[] args) {
        Board.loadBoard();
        frame = new JFrame("Game of Life");
        frame.add(screen);
        frame.setSize(516, 539); //416 439
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((e) -> {
            if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_ENTER) {
                running = !running;
                return true;
            }
            if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_R) {
                running = false;
                Board.loadBoard();
                screen.drawBoard();
                frame.repaint();
                return true;
            }
            return false;
        });
        frame.setVisible(true);
        Thread thread = new Thread(new GameOfLife());
        thread.start();
    }

    public void update() {
        for (Cell[] cs : Board.board) {
            for (Cell c : cs) {
                c.evaluate(Board.board);
            }
        }
        for (Cell[] cs : Board.board) {
            for (Cell c : cs) {
                c.change();
            }
        }
        screen.drawBoard();
        frame.repaint();
    }

    public void run() {
        long initialTime = System.nanoTime();
        final double timeF = 1000000000 / 10F;
        double deltaF = 0;
        while (true) {
            long currentTime = System.nanoTime();
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;
            if (deltaF >= 1) {
                if (running) update();
                deltaF--;
            }
        }
    }
}
