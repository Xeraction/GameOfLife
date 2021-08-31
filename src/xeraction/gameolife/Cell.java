package xeraction.gameolife;

public class Cell {
    private int row;
    private int column;
    private boolean alive;
    private boolean dirty;
    public Cell(int row, int column, boolean alive) {
        this.row = row;
        this.column = column;
        this.alive = alive;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void evaluate(Cell[][] board) {
        byte neighbours = getNeighbours(board);
        if (this.alive) {
            if (neighbours == 2 || neighbours == 3) this.dirty = false;
            else this.dirty = true;
        } else if (neighbours == 3) this.dirty = true;
    }

    public void change() {
        if (this.dirty) this.alive = !this.alive;
        this.dirty = false;
    }

    private byte getNeighbours(Cell[][] board) {
        byte neighbours = 0;
        try {if (board[this.row - 1][this.column].isAlive()) neighbours++;} catch (Exception ignored) {}
        try {if (board[this.row - 1][this.column + 1].isAlive()) neighbours++;} catch (Exception ignored) {}
        try {if (board[this.row][this.column + 1].isAlive()) neighbours++;} catch (Exception ignored) {}
        try {if (board[this.row + 1][this.column + 1].isAlive()) neighbours++;} catch (Exception ignored) {}
        try {if (board[this.row + 1][this.column].isAlive()) neighbours++;} catch (Exception ignored) {}
        try {if (board[this.row + 1][this.column - 1].isAlive()) neighbours++;} catch (Exception ignored) {}
        try {if (board[this.row][this.column - 1].isAlive()) neighbours++;} catch (Exception ignored) {}
        try {if (board[this.row - 1][this.column - 1].isAlive()) neighbours++;} catch (Exception ignored) {}
        return neighbours;
    }
}
