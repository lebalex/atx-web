package xyz.lebalex.atx.models;

import java.io.Serializable;

public class RemontDiffModel implements Serializable {
    private int	id;
    private int	id_remont;
    private String	name;
    private float	coast;
    private int	type;

    public RemontDiffModel(int id, int id_remont, String name, float coast, int type) {
        this.id = id;
        this.id_remont = id_remont;
        this.name = name;
        this.coast = coast;
        this.type = type;
    }

    public int getId_remont() {
        return id_remont;
    }

    public void setId_remont(int id_remont) {
        this.id_remont = id_remont;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCoast() {
        return coast;
    }

    public void setCoast(float coast) {
        this.coast = coast;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
