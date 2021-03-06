package xyz.lebalex.atx.models;

import java.io.Serializable;

public class TypeTCModel implements Serializable, ModelInterface {
    private int id;
    private String name;//категория А.В

    public TypeTCModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
