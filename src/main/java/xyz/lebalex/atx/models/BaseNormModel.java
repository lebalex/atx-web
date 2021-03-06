package xyz.lebalex.atx.models;

import java.io.Serializable;

public class BaseNormModel implements Serializable {
    private int id;
    private int id_mark;
    private int id_model;
    private int id_gsm;
    private float norm;
    private int num;

    public BaseNormModel(int id, int id_mark, int id_model, int id_gsm, float norm) {
        this.id = id;
        this.id_mark = id_mark;
        this.id_model = id_model;
        this.id_gsm = id_gsm;
        this.norm = norm;
    }
    public BaseNormModel(int id, int id_mark, int id_model, int id_gsm, float norm, int num) {
        this.id = id;
        this.id_mark = id_mark;
        this.id_model = id_model;
        this.id_gsm = id_gsm;
        this.norm = norm;
        this.num = num;
    }
    public BaseNormModel(int id_mark, int id_model, int id_gsm, int num) {
        this.id = 0;
        this.id_mark = id_mark;
        this.id_model = id_model;
        this.id_gsm = id_gsm;
        this.norm = 0;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_mark() {
        return id_mark;
    }

    public void setId_mark(int id_mark) {
        this.id_mark = id_mark;
    }

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int id_model) {
        this.id_model = id_model;
    }

    public int getId_gsm() {
        return id_gsm;
    }

    public void setId_gsm(int id_gsm) {
        this.id_gsm = id_gsm;
    }

    public float getNorm() {
        return norm;
    }

    public void setNorm(float norm) {
        this.norm = norm;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

