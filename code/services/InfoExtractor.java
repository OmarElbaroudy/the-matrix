package services;

import java.util.Arrays;

public class InfoExtractor {

    public static int numberOfHostages;
    private static String[] arrOfStr;

    public InfoExtractor(String Grid) {
        arrOfStr = Grid.split(";");
    }


    public int getWidth() {
        String[] gridSize = arrOfStr[0].split(",");
        return Integer.parseInt(gridSize[0]);
    }

    public int getHeight() {
        String[] gridSize = arrOfStr[0].split(",");
        return Integer.parseInt(gridSize[1]);
    }

    public int getCarrySize() {
        return Integer.parseInt(arrOfStr[1]);
    }

    public int getNeoX() {
        String[] gridSize = arrOfStr[2].split(",");
        return Integer.parseInt(gridSize[0]);
    }

    public int getNeoY() {
        String[] gridSize = arrOfStr[2].split(",");
        return Integer.parseInt(gridSize[1]);
    }

    public int getTelephoneX() {
        String[] gridSize = arrOfStr[3].split(",");
        return Integer.parseInt(gridSize[0]);
    }

    public int getTelephoneY() {
        String[] gridSize = arrOfStr[3].split(",");
        return Integer.parseInt(gridSize[1]);
    }

    private int[] getAgents() {
        return Arrays.stream(arrOfStr[4].split(",")).
                mapToInt(Integer::parseInt).toArray();
    }

    public int[] getAgentsX() {
        int[] agents = getAgents();
        int[] out = new int[agents.length / 2];
        for (int i = 0; i < agents.length; i += 2)
            out[i / 2] = agents[i];

        return out;
    }

    public int[] getAgentsY() {
        int[] agents = getAgents();
        int[] out = new int[agents.length / 2];
        for (int i = 1; i < agents.length; i += 2)
            out[i / 2] = agents[i];

        return out;
    }


    private int[] getPills() {

        return Arrays.stream(arrOfStr[5].split(",")).
                mapToInt(Integer::parseInt).toArray();

    }

    public int[] getPillsX() {
        int[] pills = getPills();
        int[] out = new int[pills.length / 2];
        for (int i = 0; i < pills.length; i += 2)
            out[i / 2] = pills[i];

        return out;
    }

    public int[] getPillsY() {
        int[] pills = getPills();
        int[] out = new int[pills.length / 2];
        for (int i = 1; i < pills.length; i += 2)
            out[i / 2] = pills[i];

        return out;
    }

    private int[] getPads() {

        return Arrays.stream(arrOfStr[6].split(",")).
                mapToInt(Integer::parseInt).toArray();

    }

    public int[] getStartPadsX() {
        int[] pads = getPads();
        int[] out = new int[pads.length / 4];
        for (int i = 0; i < pads.length; i += 4)
            out[i / 4] = pads[i];

        return out;
    }

    public int[] getStartPadsY() {
        int[] pads = getPads();
        int[] out = new int[pads.length / 4];
        for (int i = 1; i < pads.length; i += 4)
            out[i / 4] = pads[i];

        return out;
    }

    public int[] getFinishPadsX() {
        int[] pads = getPads();
        int[] out = new int[pads.length / 4];
        for (int i = 2; i < pads.length; i += 4)
            out[i / 4] = pads[i];

        return out;
    }

    public int[] getFinishPadsY() {
        int[] pads = getPads();
        int[] out = new int[pads.length / 4];
        for (int i = 3; i < pads.length; i += 4)
            out[i / 4] = pads[i];

        return out;
    }

    private int[] getHostages() {
        int[] arr = Arrays.stream(arrOfStr[7].split(",")).
                mapToInt(Integer::parseInt).toArray();
        numberOfHostages = (arr.length + 2) / 3;
        return arr;
    }

    public int[] getHostagesX() {
        int[] hostages = getHostages();
        int[] out = new int[hostages.length / 3];
        for (int i = 0; i < hostages.length; i += 3)
            out[i / 3] = hostages[i];

        return out;
    }

    public int[] getHostagesY() {
        int[] hostages = getHostages();
        int[] out = new int[hostages.length / 3];
        for (int i = 1; i < hostages.length; i += 3)
            out[i / 3] = hostages[i];

        return out;
    }

    public int[] getHostagesDamage() {
        int[] hostages = getHostages();
        int[] out = new int[hostages.length / 3];
        for (int i = 2; i < hostages.length; i += 3)
            out[i / 3] = hostages[i];

        return out;
    }
}

