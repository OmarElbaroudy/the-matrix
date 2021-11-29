package services;

import modules.*;

import java.util.*;

public class GraphHandler {
    private final int N, M, neoIdx;
    private final TreeMap<Pair, Integer> mp;
    private final ArrayList<ArrayList<Integer>> adj;
    private final List<Cell> cells;
    private int TBIdx, numOfAliveHostages, distToTB, pills;

    public GraphHandler(State state) {
        this.N = state.getGrid().getN();
        this.M = state.getGrid().getM();
        mp = new TreeMap<>();
        adj = new ArrayList<>();
        cells = new ArrayList<>();
        build(state.getGrid());
        neoIdx = mp.get(new Pair(state.getX(), state.getY()));
    }

    private void build(Grid grid) {
        for (int i = 0; i < N * M; i++) {
            adj.add(new ArrayList<>());
        }
        constructNodes(grid);
        constructEdges(grid);
    }

    private void constructNodes(Grid grid) {
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Host type = grid.getHostAtPos(i, j);
                if (type == Host.TELEPHONE) {
                    this.TBIdx = idx;
                } else if (type == Host.HOSTAGE) {
                    this.numOfAliveHostages++;
                }else if(type == Host.PILL){
                    this.pills++;
                }

                mp.put(new Pair(i, j), idx++);
                cells.add(grid.getCellAtPos(i, j));
            }
        }
    }

    private void constructEdges(Grid grid) {
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    if (isValid(i + dy[k], j + dx[k])) {
                        constructEdge(i, j, i + dy[k], j + dx[k]);
                    }
                }

                if (grid.getHostAtPos(i, j) == Host.PAD) {
                    int toX = grid.getPadXAtPos(i, j);
                    int toY = grid.getPadYAtPos(i, j);
                    constructEdge(i, j, toX, toY);
                }
            }
        }
    }

    private void constructEdge(int fromI, int fromJ, int toI, int toJ) {
        int fromIdx = mp.get(new Pair(fromI, fromJ));
        int toIdx = mp.get(new Pair(toI, toJ));

        ArrayList<Integer> arr = adj.get(fromIdx);
        arr.add(toIdx);
    }

    private int[] getShortestDist(int idx) {
        int[] dist = new int[N * M];
        Arrays.fill(dist, -1);
        dist[idx] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(idx);

        while (!q.isEmpty()) {
            int u = q.remove();
            for (int v : adj.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }
        return dist;
    }

    private boolean isValid(int x, int y) {
        return x < N && x >= 0 && y >= 0 && y < M;
    }

    public int getHostageKills() {
        int cnt = 0;
        int[] dist = getShortestDist(neoIdx);
        for (int i = 0; i < N * M; i++) {
            Host type = cells.get(i).getHost();
            if (type == Host.HOSTAGE) {
                int d = dist[i];
                int damage = cells.get(i).getDamage();
                cnt += damage + d * 2 - pills * 20 >= 100 ? 1 : 0;
            }

            cnt += type == Host.MUTATED_AGENT ? 1 : 0;
        }

        this.distToTB = dist[TBIdx];
        return cnt;
    }

    public boolean canReachTB() {
        boolean[] vis = new boolean[N * M];

        Queue<Integer> q = new LinkedList<>();
        q.add(neoIdx);

        while (!q.isEmpty()) {
            int u = q.remove();
            vis[u] = true;
            Host type = cells.get(u).getHost();

            if (type == Host.MUTATED_AGENT || type == Host.AGENT) {
                continue;
            }

            if (type == Host.TELEPHONE) {
                return true;
            }

            for (int v : adj.get(u)) {
                if (!vis[v]) {
                    q.add(v);
                }
            }
        }
        return false;
    }

    public int getNumOfPotentialDrops() {
        int cnt = 0;
        int[] fst = getShortestDist(neoIdx);
        int[] snd = getShortestDist(TBIdx);

        for (int i = 0; i < N * M; i++) {
            if (cells.get(i).getHost() == Host.HOSTAGE) {
                int damage = cells.get(i).getDamage();
                damage += 2 * (fst[i] + snd[i]) + 1;
                cnt += damage <= 100 ? 1 : 0;
            }
        }
        return cnt;
    }

    public int getDepth() {
        return this.distToTB == 0 && numOfAliveHostages == 0 ? 0 : 1;
    }

    public void print() {
        System.out.println(getHostageKills());
        System.out.println(canReachTB());
    }

}
