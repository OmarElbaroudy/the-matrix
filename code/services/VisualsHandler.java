package services;

import modules.Host;
import modules.State;

public class VisualsHandler {
    public static String visualize(State state) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("neo's damage is ").
                append(state.getDamage()).
                append("\n");

        if (state.getCarriedDamages().isEmpty()) {
            sb.append("Neo is not carrying any hostages \n");
        } else {
            sb.append("Neo is carrying ")
                    .append(state.getCarriedDamages().size())
                    .append(" Hostages and their damages are as follows :\n")
                    .append(state.getCarriedDamages());
        }

        int n = state.getGrid().getN();
        int m = state.getGrid().getM();

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                String s = getCellVisual(state, i, j);
                sb.append(equalize(s, false)).append("| ");
            }

            sb.append('\n');
            for (int j = 0; j < m; j++) {
                sb.append(equalize("", true)).append("+ ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private static String getCellVisual(State state, int i, int j) {
        Host type = state.getGrid().getHostAtPos(i, j);
        String neo = i == state.getX() && j == state.getY() ? " Neo" : "";

        if (type == Host.PAD) {
            int damage = state.getGrid().getDamageAtPos(i, j);
            return type + "(" + damage + ")" + neo;
        }

        if (type == Host.HOSTAGE) {
            int toX = state.getGrid().getPadXAtPos(i, j);
            int toY = state.getGrid().getPadYAtPos(i, j);
            return type + "(" + toX + ", " + toY + ")" + neo;
        }

        return type.toString() + neo;
    }

    private static String equalize(String s, boolean dashes) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < 15) {
            sb.append(dashes ? '-' : ' ');
        }
        return sb.toString();
    }

}
