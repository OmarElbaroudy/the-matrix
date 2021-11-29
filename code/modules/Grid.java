package modules;

public class Grid {
    private final Cell[][] grid;

    public Grid(Cell[][] grid) {
        this.grid = grid;
    }


    public Host getHostAtPos(int x, int y) {
        return this.grid[x][y].getHost();
    }

    public byte getDamageAtPos(int x, int y) {
        return this.grid[x][y].getDamage();
    }

    public int getPadXAtPos(int x, int y) {
        return this.grid[x][y].getToX();
    }

    public int getPadYAtPos(int x, int y) {
        return this.grid[x][y].getToY();
    }

    public Cell getCellAtPos(int x, int y) {
        return this.grid[x][y];
    }

    public int getN() {
        return grid.length;
    }

    public int getM() {
        return grid[0].length;
    }


    public void clearPos(int x, int y) {
        grid[x][y] = new Cell(Host.EMPTY);
    }

    public boolean heal(int x, int y, int damage) {
        return grid[x][y].heal(damage);
    }
}
