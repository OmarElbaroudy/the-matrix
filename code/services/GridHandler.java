package services;

import modules.*;

import java.util.*;

public class GridHandler {
    public static State toState(String s) {
        InfoExtractor ie = new InfoExtractor(s);
        Cell[][] grid = new Cell[ie.getHeight()][ie.getWidth()];

        grid[ie.getTelephoneX()][ie.getTelephoneY()] = new Cell(Host.TELEPHONE);

        int[] agentX = ie.getAgentsX();
        int[] agentY = ie.getAgentsY();

        for (int i = 0; i < agentX.length; i++) {
            grid[agentX[i]][agentY[i]] = new Cell(Host.AGENT);
        }

        int[] pillX = ie.getPillsX();
        int[] pillY = ie.getPillsY();

        for (int i = 0; i < pillX.length; i++) {
            grid[pillX[i]][pillY[i]] = new Cell(Host.PILL);
        }

        int[] hostageX = ie.getHostagesX();
        int[] hostageY = ie.getHostagesY();
        int[] hostageD = ie.getHostagesDamage();

        for (int i = 0; i < hostageD.length; i++) {
            grid[hostageX[i]][hostageY[i]] = new Cell(hostageD[i]);
        }

        int[] stX = ie.getStartPadsX();
        int[] stY = ie.getStartPadsY();
        int[] fiX = ie.getFinishPadsX();
        int[] fiY = ie.getFinishPadsY();

        for (int i = 0; i < stX.length; i++) {
            grid[stX[i]][stY[i]] = new Cell(fiX[i], fiY[i]);
        }

        for (int i = 0; i < ie.getHeight(); i++) {
            for (int j = 0; j < ie.getWidth(); j++) {
                if (grid[i][j] == null) {
                    grid[i][j] = new Cell(Host.EMPTY);
                }
            }
        }

        return new StateBuilder()
                .carriedDamages(new ArrayList<>())
                .remCarry(ie.getCarrySize())
                .Grid(new Grid(grid))
                .xPos(ie.getNeoX())
                .yPos(ie.getNeoY())
                .damage(0)
                .build();
    }

    public static String genGrid() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        int c = rand.nextInt(5);
        int n = rand.nextInt(11) + 5;
        int m = rand.nextInt(11) + 5;
        int hostages = rand.nextInt(8) + 3;
        int pills = rand.nextInt(hostages) + 1;
        int cells = n * m - hostages - pills - 2;

        int pads = rand.nextInt(20) + 1;
        int agents = rand.nextInt(20) + 1;

        while (pads * 2 + agents > cells) {
            pads = rand.nextInt(20) + 1;
            agents = rand.nextInt(20) + 1;
        }

        LinkedList<Pair> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                q.add(new Pair(i, j));
            }
        }

        Collections.shuffle(q);
        sb.append(m).append(',').append(n).append(';');
        sb.append(c).append(';');
        append(sb, q, true);
        append(sb, q, true);

        for (int i = 0; i < agents; i++) {
            append(sb, q, i == agents - 1);
        }

        for (int i = 0; i < pills; i++) {
            append(sb, q, i == pills - 1);
        }

        for (int i = 0; i < pads; i++) {
            appendPads(sb, q, i == pads - 1);
        }

        for (int i = 0; i < hostages; i++) {
            int damage = rand.nextInt(99) + 1;
            appendHostages(sb, q, damage, i == pads - 1);
        }

        return sb.toString();
    }

    private static void append(StringBuilder sb,
                               LinkedList<Pair> q, boolean lst) {

        sb.append(Objects.requireNonNull(q.peek()).x).
                append(',').
                append(q.poll().y).
                append(lst ? ';' : ',');
    }

    private static void appendPads(StringBuilder sb,
                                   LinkedList<Pair> q, boolean lst) {

        int x1 = Objects.requireNonNull(q.peek()).x, y1 = q.poll().y;
        int x2 = Objects.requireNonNull(q.peek()).x, y2 = q.poll().y;

        sb.append(x1).append(',').
                append(y1).append(',').
                append(x2).append(',').
                append(y2).append(',').
                append(x2).append(',').
                append(y2).append(',').
                append(x1).append(',').
                append(y1).append(lst ? ';' : ',');
    }

    private static void appendHostages(StringBuilder sb,
                                       LinkedList<Pair> q,
                                       int damage, boolean lst) {

        sb.append(Objects.requireNonNull(q.peek()).x).append(',')
                .append(q.poll().y).append(',').append(damage)
                .append(lst ? ';' : ',');
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
