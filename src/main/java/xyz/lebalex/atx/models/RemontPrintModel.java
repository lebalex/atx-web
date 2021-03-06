package xyz.lebalex.atx.models;

import java.io.Serializable;
import java.util.Date;

public class RemontPrintModel implements Serializable {
    private Date date_begin;
    private Date date_end;
    private String vid;
    private String remont_name;
    private float remont_coast;
    private String zap_name;
    private float zap_coast;
    private String tmc_name;
    private float tmc_coast;
    private String tmc2_name;
    private float tmc2_coast;
    private String arg_name;
    private String arg_old_num;
    private String arg_new_num;
    private int oil;
    private int days;
    private String place;
    private int speed_b;
    private int speed_n;

    public RemontPrintModel(Date date_begin, Date date_end, String vid, String remont_name, float remont_coast, String zap_name, float zap_coast, String tmc_name, float tmc_coast, String tmc2_name, float tmc2_coast, String arg_name, String arg_old_num, String arg_new_num, int oil, int days, String place, int speed_b, int speed_n) {
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.vid = vid;
        this.remont_name = remont_name;
        this.remont_coast = remont_coast;
        this.zap_name = zap_name;
        this.zap_coast = zap_coast;
        this.tmc_name = tmc_name;
        this.tmc_coast = tmc_coast;
        this.tmc2_name = tmc2_name;
        this.tmc2_coast = tmc2_coast;
        this.arg_name = arg_name;
        this.arg_old_num = arg_old_num;
        this.arg_new_num = arg_new_num;
        this.oil = oil;
        this.days = days;
        this.place = place;
        this.speed_b = speed_b;
        this.speed_n = speed_n;
    }
    public RemontPrintModel(Date date_begin, Date date_end, String vid, int oil, int days, String place, int speed_b, int speed_n) {
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.vid = vid;
        this.oil = oil;
        this.days = days;
        this.place = place;
        this.speed_b = speed_b;
        this.speed_n = speed_n;
    }

    public RemontPrintModel() {
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

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getRemont_name() {
        return remont_name;
    }

    public void setRemont_name(String remont_name) {
        this.remont_name = remont_name;
    }

    public float getRemont_coast() {
        return remont_coast;
    }

    public void setRemont_coast(float remont_coast) {
        this.remont_coast = remont_coast;
    }

    public String getZap_name() {
        return zap_name;
    }

    public void setZap_name(String zap_name) {
        this.zap_name = zap_name;
    }

    public float getZap_coast() {
        return zap_coast;
    }

    public void setZap_coast(float zap_coast) {
        this.zap_coast = zap_coast;
    }

    public String getTmc_name() {
        return tmc_name;
    }

    public void setTmc_name(String tmc_name) {
        this.tmc_name = tmc_name;
    }

    public float getTmc_coast() {
        return tmc_coast;
    }

    public void setTmc_coast(float tmc_coast) {
        this.tmc_coast = tmc_coast;
    }

    public String getTmc2_name() {
        return tmc2_name;
    }

    public void setTmc2_name(String tmc2_name) {
        this.tmc2_name = tmc2_name;
    }

    public float getTmc2_coast() {
        return tmc2_coast;
    }

    public void setTmc2_coast(float tmc2_coast) {
        this.tmc2_coast = tmc2_coast;
    }

    public String getArg_name() {
        return arg_name;
    }

    public void setArg_name(String arg_name) {
        this.arg_name = arg_name;
    }

    public String getArg_old_num() {
        return arg_old_num;
    }

    public void setArg_old_num(String arg_old_num) {
        this.arg_old_num = arg_old_num;
    }

    public String getArg_new_num() {
        return arg_new_num;
    }

    public void setArg_new_num(String arg_new_num) {
        this.arg_new_num = arg_new_num;
    }

    public int getOil() {
        return oil;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getSpeed_b() {
        return speed_b;
    }

    public void setSpeed_b(int speed_b) {
        this.speed_b = speed_b;
    }

    public int getSpeed_n() {
        return speed_n;
    }

    public void setSpeed_n(int speed_n) {
        this.speed_n = speed_n;
    }
}
