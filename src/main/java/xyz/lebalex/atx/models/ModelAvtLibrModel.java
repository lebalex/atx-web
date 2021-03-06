package xyz.lebalex.atx.models;

import java.io.Serializable;

public class ModelAvtLibrModel implements Serializable {
    private int id;
    private int id_mark;
    private String name;
    private int expl_year;
    private int old_expl_year;
    private int num;


    public ModelAvtLibrModel(int id, int id_mark, String name, int expl_year, int old_expl_year, int num) {
        this.id = id;
        this.id_mark = id_mark;
        this.name = name;
        this.expl_year = expl_year;
        this.old_expl_year = old_expl_year;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpl_year() {
        return expl_year;
    }

    public void setExpl_year(int expl_year) {
        this.expl_year = expl_year;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOld_expl_year() {
        return old_expl_year;
    }

    public void setOld_expl_year(int old_expl_year) {
        this.old_expl_year = old_expl_year;
    }
}
