package xyz.lebalex.atx.models;

import java.io.Serializable;
import java.util.Date;

public class HistoryChangeNumb implements Serializable {
    private Date date;
    private String old_n;
    private String new_n;

    public HistoryChangeNumb(Date date, String old_n, String new_n) {
        this.date = date;
        this.old_n = old_n;
        this.new_n = new_n;
    }

    public Date getDate() {
        return date;
    }

    public String getOld_n() {
        return old_n;
    }

    public String getNew_n() {
        return new_n;
    }
}
