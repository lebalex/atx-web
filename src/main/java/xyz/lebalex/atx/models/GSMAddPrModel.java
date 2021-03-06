package xyz.lebalex.atx.models;

import java.io.Serializable;

public class GSMAddPrModel implements Serializable {
    private int	id_dislok;
    private float	pr1;
    private float	pr2;
    private float	klimat0;
    private float	klimat1;
    private float	klimat2;
    private float	klimat3;
    private float	klimat4;
    private float	klimat5;
    private boolean newRec;

    public GSMAddPrModel(int id_dislok)
    {
        this.id_dislok = id_dislok;
        this.newRec = true;
    }

    public GSMAddPrModel(int id_dislok, float pr1, float pr2, float klimat0, float klimat1, float klimat2, float klimat3, float klimat4, float klimat5) {
        this.id_dislok = id_dislok;
        this.pr1 = pr1;
        this.pr2 = pr2;
        this.klimat0 = klimat0;
        this.klimat1 = klimat1;
        this.klimat2 = klimat2;
        this.klimat3 = klimat3;
        this.klimat4 = klimat4;
        this.klimat5 = klimat5;
        this.newRec = false;
    }

    public int getId_dislok() {
        return id_dislok;
    }

    public void setId_dislok(int id_dislok) {
        this.id_dislok = id_dislok;
    }

    public float getPr1() {
        return pr1;
    }

    public void setPr1(float pr1) {
        this.pr1 = pr1;
    }

    public float getPr2() {
        return pr2;
    }

    public void setPr2(float pr2) {
        this.pr2 = pr2;
    }

    public float getKlimat0() {
        return klimat0;
    }

    public void setKlimat0(float klimat0) {
        this.klimat0 = klimat0;
    }

    public float getKlimat1() {
        return klimat1;
    }

    public void setKlimat1(float klimat1) {
        this.klimat1 = klimat1;
    }

    public float getKlimat2() {
        return klimat2;
    }

    public void setKlimat2(float klimat2) {
        this.klimat2 = klimat2;
    }

    public float getKlimat3() {
        return klimat3;
    }

    public void setKlimat3(float klimat3) {
        this.klimat3 = klimat3;
    }

    public float getKlimat4() {
        return klimat4;
    }

    public void setKlimat4(float klimat4) {
        this.klimat4 = klimat4;
    }

    public float getKlimat5() {
        return klimat5;
    }

    public void setKlimat5(float klimat5) {
        this.klimat5 = klimat5;
    }

    public boolean isNewRec() {
        return newRec;
    }

    public void setNewRec(boolean newRec) {
        this.newRec = newRec;
    }
}
