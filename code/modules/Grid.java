package modules;

import java.util.Arrays;

public class Grid {
    private final Cell[][] grid;

    public Grid(Cell[][] grid) {
        this.grid = grid;
    }

    public Host getHostAtPos(int x, int y) {
        return this.grid[x][y].getHost();
    }

    public boolean isAlive(int x, int y) {
        return this.grid[x][y].getDamage() < 100;
    }

    public int getN() {
        return grid.length;
    }

    public int getM() {
        return grid[0].length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid1 = (Grid) o;
        return Arrays.deepEquals(grid, grid1.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }
}
