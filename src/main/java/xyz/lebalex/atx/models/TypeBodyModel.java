package xyz.lebalex.atx.models;

import java.io.Serializable;

public class TypeBodyModel implements Serializable, ModelInterface {
    private int id;
    private String name;

    public TypeBodyModel(int id, String name) {
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
