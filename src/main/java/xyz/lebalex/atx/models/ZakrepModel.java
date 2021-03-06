package xyz.lebalex.atx.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ZakrepModel implements Serializable {
    private int id;
    private int id_avt;
    private Date date_begin;
    private Date date_end;
    private String prikaz;
    private Date date_prikaz;
    private String out_prikaz;
    private Date date_out_prikaz;
    private boolean spravka;
    private String fio;
    private String phone;
    private String dolg;
    private String zvan;
    private String address;
    private String vod_ud;
    private Date med;
    private String comm;
    private boolean temp;
    private Date spravka_date;
    private boolean card;
    private Date card_date;

    public ZakrepModel(int id_avt) {
        this.id = -1;
        this.id_avt = id_avt;
    }

    public ZakrepModel(int id, int id_avt, Date date_begin, Date date_end, String prikaz, Date date_prikaz, String out_prikaz, Date date_out_prikaz, boolean spravka, String fio, String phone, String dolg, String zvan, String address, String vod_ud, Date med, String comm, boolean temp, Date spravka_date, boolean card, Date card_date) {
        this.id = id;
        this.id_avt = id_avt;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.prikaz = prikaz;
        this.date_prikaz = date_prikaz;
        this.out_prikaz = out_prikaz;
        this.date_out_prikaz = date_out_prikaz;
        this.spravka = spravka;
        this.fio = fio;
        this.phone = phone;
        this.dolg = dolg;
        this.zvan = zvan;
        this.address = address;
        this.vod_ud = vod_ud;
        this.med = med;
        this.comm = comm;
        this.temp = temp;
        this.spravka_date = spravka_date;
        this.card = card;
        this.card_date = card_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_avt() {
        return id_avt;
    }

    public void setId_avt(int id_avt) {
        this.id_avt = id_avt;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getPrikaz() {
        return prikaz;
    }

    public void setPrikaz(String prikaz) {
        this.prikaz = prikaz;
    }

    public Date getDate_prikaz() {
        return date_prikaz;
    }

    public void setDate_prikaz(Date date_prikaz) {
        this.date_prikaz = date_prikaz;
    }

    public String getOut_prikaz() {
        return out_prikaz;
    }

    public void setOut_prikaz(String out_prikaz) {
        this.out_prikaz = out_prikaz;
    }

    public Date getDate_out_prikaz() {
        return date_out_prikaz;
    }

    public void setDate_out_prikaz(Date date_out_prikaz) {
        this.date_out_prikaz = date_out_prikaz;
    }

    public boolean isSpravka() {
        return spravka;
    }

    public void setSpravka(boolean spravka) {
        this.spravka = spravka;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDolg() {
        return dolg;
    }

    public void setDolg(String dolg) {
        this.dolg = dolg;
    }

    public String getZvan() {
        return zvan;
    }

    public void setZvan(String zvan) {
        this.zvan = zvan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVod_ud() {
        return vod_ud;
    }

    public void setVod_ud(String vod_ud) {
        this.vod_ud = vod_ud;
    }

    public Date getMed() {
        return med;
    }

    public void setMed(Date med) {
        this.med = med;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public Date getSpravka_date() {
        return spravka_date;
    }

    public void setSpravka_date(Date spravka_date) {
        this.spravka_date = spravka_date;
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public Date getCard_date() {
        return card_date;
    }

    public void setCard_date(Date card_date) {
        this.card_date = card_date;
    }
}
