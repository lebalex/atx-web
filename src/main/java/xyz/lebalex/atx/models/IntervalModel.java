package xyz.lebalex.atx.models;

import java.io.Serializable;

public class IntervalModel implements Serializable {
    private int id;
    private String name;

    public IntervalModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
