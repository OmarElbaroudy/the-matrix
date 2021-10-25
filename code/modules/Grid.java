package modules;

public class Grid {
    private final Cell[][] grid;

    public Grid(Cell[][] grid){
        this.grid = grid;
    }

    public Host getHostAtPos(int x, int y){
        return this.grid[x][y].getHost();
    }

}
