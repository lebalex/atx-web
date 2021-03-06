package xyz.lebalex.atx.models;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RemontZayModel implements Serializable {
    static Logger logger = Logger.getLogger(RemontZayModel.class);
    private int id;
    private int id_avt;
    private int num;
    private Date date;
    private float speed;
    private String fio;
    private int id_vid_to;
    private String vid_to;
    private int id_org;
    private String org;
    private int graf;
    private List<RemontZayDifrModel> listRemontZayDifrModel;

    public RemontZayModel(int id_avt)
    {
        this.id = -1;
        this.id_avt = id_avt;
        listRemontZayDifrModel = new ArrayList<>();
    }

    public RemontZayModel(int id, int id_avt, int num, Date date, float speed, String fio, int id_vid_to, String vid_to, int id_org, String org, int graf) {
        this.id = id;
        this.id_avt = id_avt;
        this.num = num;
        this.date = date;
        this.speed = speed;
        this.fio = fio;
        this.id_vid_to = id_vid_to;
        this.vid_to = vid_to;
        this.id_org = id_org;
        this.org = org;
        this.graf = graf;
        listRemontZayDifrModel = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getId_vid_to() {
        return id_vid_to;
    }

    public void setId_vid_to(int id_vid_to) {
        this.id_vid_to = id_vid_to;
    }

    public String getVid_to() {
        return vid_to;
    }

    public void setVid_to(String vid_to) {
        this.vid_to = vid_to;
    }

    public int getId_org() {
        return id_org;
    }

    public void setId_org(int id_org) {
        this.id_org = id_org;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public int getGraf() {
        return graf;
    }

    public void setGraf(int graf) {
        this.graf = graf;
    }

    public int getId_avt() {
        return id_avt;
    }

    public void setId_avt(int id_avt) {
        this.id_avt = id_avt;
    }

    public List<RemontZayDifrModel> getListRemontZayDifrModel() {
        return listRemontZayDifrModel;
    }

    public void setListRemontZayDifrModel(List<RemontZayDifrModel> listRemontZayDifrModel) {
        this.listRemontZayDifrModel = listRemontZayDifrModel;
    }

    public void onAddNewDifr() {
        RemontZayDifrModel newAgr = new RemontZayDifrModel(listRemontZayDifrModel.size(), this.id,"название");
        listRemontZayDifrModel.add(newAgr);

    }
    public void onDeleteDifr(int id) {
        Iterator<RemontZayDifrModel> i = listRemontZayDifrModel.iterator();
        while (i.hasNext()) {
            RemontZayDifrModel agr = i.next();
            if(agr.getNumb()==id)
                i.remove();
        }
    }
}
