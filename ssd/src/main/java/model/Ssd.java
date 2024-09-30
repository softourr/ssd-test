package model;

public class Ssd {
    private int[] data;

    public int getData(int index) {
        return data[index];
    }

    public void setData(int index, int value) {
        this.data[index] = value;
    }
}
