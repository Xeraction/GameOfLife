package xeraction.gameolife;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Board {
    public static Cell[][] board = new Cell[100][100];

    public static void loadBoard() {
        try {
            String jarPath = URLDecoder.decode(Board.class.getProtectionDomain().getCodeSource().getLocation().getPath(), StandardCharsets.UTF_8);
            String path = jarPath.replace("GameOfLife.jar", "board.txt");
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        writer.write("0");
                    }
                    writer.write("\n");
                }
                writer.flush();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] lines = new String[100];
            for (int i = 0; i < 100; i++) {
                lines[i] = reader.readLine();
            }
            int row = 0;
            int column = 0;
            for (String s : lines) {
                char[] chars = s.toCharArray();
                for (char c : chars) {
                    if (c == '0') board[row][column] = new Cell(row, column, false);
                    if (c == '1') board[row][column] = new Cell(row, column, true);
                    column++;
                }
                row++;
                column = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
