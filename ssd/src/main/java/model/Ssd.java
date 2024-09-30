package model;

public class Ssd {
    private static Ssd instance;
    private int[] data;

    private Ssd() {
        this.data = new int[100];
    }

    public static synchronized Ssd getInstance() {
        if (instance == null) {
            instance = new Ssd();
        }
        return instance;
    }

    public int getData(int index) {
        return data[index];
    }

    public void setData(int index, int value) {
        this.data[index] = value;
    }
}
