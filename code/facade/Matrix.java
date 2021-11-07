package facade;

import modules.Grid;
import modules.Host;
import modules.State;

import java.util.ArrayList;

public class Matrix extends Problem {

    public Matrix(State initialState) {
        super(initialState);
        initOperators();
    }

    @Override
    public boolean testGoal(State state) {
        Grid grid = state.getGrid();
        int n = grid.getN(), m = grid.getM();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Host host = grid.getHostAtPos(i, j);

                if (host == Host.MutatedAgent
                        || host == Host.Hostage){
                    return false;
                }
            }
        }

        return true;
    }

    private void initOperators() {
        this.operators = new ArrayList<>();
    }

}
