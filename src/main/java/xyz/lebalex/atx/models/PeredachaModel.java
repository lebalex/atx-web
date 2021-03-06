package xyz.lebalex.atx.models;

import java.io.Serializable;
import java.util.Date;

public class PeredachaModel implements Serializable {
    private Date date;
    private String from_edit;
    private String to_edit;
    private String nprok_edit;

    public PeredachaModel(Date date, String from_edit, String to_edit, String nprok_edit) {
        this.date = date;
        this.from_edit = from_edit;
        this.to_edit = to_edit;
        this.nprok_edit = nprok_edit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom_edit() {
        return from_edit;
    }

    public void setFrom_edit(String from_edit) {
        this.from_edit = from_edit;
    }

    public String getTo_edit() {
        return to_edit;
    }

    public void setTo_edit(String to_edit) {
        this.to_edit = to_edit;
    }

    public String getNprok_edit() {
        return nprok_edit;
    }

    public void setNprok_edit(String nprok_edit) {
        this.nprok_edit = nprok_edit;
    }
}
