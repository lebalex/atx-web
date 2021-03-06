package xyz.lebalex.atx.models;

import java.io.Serializable;

public class LibrGSMYearExplModel implements Serializable {
    private int year;
    private float val;
            private int num;

    public LibrGSMYearExplModel(int year, float val, int num) {
        this.year = year;
        this.val = val;
        this.num = num;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
