package xyz.lebalex.atx.models;

import java.io.Serializable;
import java.util.Date;

public class LibrGSMPriceModel implements Serializable {
    private int id_mark_gsm;
    private Date dat;
    private float val;
    private int num;

    public LibrGSMPriceModel(int id_mark_gsm, Date dat, float val, int num) {
        this.id_mark_gsm = id_mark_gsm;
        this.dat = dat;
        this.val = val;
        this.num = num;
    }

    public int getId_mark_gsm() {
        return id_mark_gsm;
    }

    public void setId_mark_gsm(int id_mark_gsm) {
        this.id_mark_gsm = id_mark_gsm;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
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
