package xyz.lebalex.atx.models;

import java.io.Serializable;

public class RemontZayDifrModel implements Serializable {
    private int id_z;
    private int numb;
    private String zay;

    public RemontZayDifrModel(int id_z, int numb, String zay) {
        this.id_z = id_z;
        this.numb = numb;
        this.zay = zay;
    }

    public int getId_z() {
        return id_z;
    }

    public void setId_z(int id_z) {
        this.id_z = id_z;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public String getZay() {
        return zay;
    }

    public void setZay(String zay) {
        this.zay = zay;
    }
}
