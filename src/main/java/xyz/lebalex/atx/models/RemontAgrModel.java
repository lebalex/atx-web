package xyz.lebalex.atx.models;

import java.io.Serializable;

public class RemontAgrModel implements Serializable {
    private int id;
    private int	id_remont;
    private String	name;
    private String	num_old;
    private String	num_new;

    public RemontAgrModel(int id, int id_remont, String name, String num_old, String num_new) {
        this.id = id;
        this.id_remont = id_remont;
        this.name = name;
        this.num_old = num_old;
        this.num_new = num_new;
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

    public String getNum_old() {
        return num_old;
    }

    public void setNum_old(String num_old) {
        this.num_old = num_old;
    }

    public String getNum_new() {
        return num_new;
    }

    public void setNum_new(String num_new) {
        this.num_new = num_new;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
