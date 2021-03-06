package xyz.lebalex.atx.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class OperationalModel implements Serializable {
    private int	mm;
    private String	interval;
    private int year_e;
    private float	d_out;
    private float	d_good;
    private float	d_operational;
    private float	d_remont;
    private float	h_order;
    private float	norma_run;
    private float	fact_run;
    private float	to1;
    private float	to2;
    private float	kp;
    private float	gsm_begin_m;
    private float	gsm_given;
    private float	gsm_end_m;
    private float	gsm_norma;
    private float	gsm_out;
    private String	comm;
    private Date dat;
    private int	kv;
    private float	speed_begin;
    private float	speed_end;
    private float	oil_l;
    private int	oil_type;
    private boolean newOper;

    public OperationalModel()
    {
        this.mm = 1;
        this.interval = "null";
        this.year_e = (Calendar.getInstance()).get(Calendar.YEAR);
        this.newOper = true;
        /*this.d_out = 0;
        this.d_good = 0;
        this.d_operational = 0;
        this.d_remont = 0;
        this.h_order = 0;
        this.norma_run = 0;
        this.fact_run = 0;
        this.to1 = 0;
        this.to2 = 0;
        this.kp = 0;
        this.gsm_begin_m = 0;
        this.gsm_given = 0;
        this.gsm_end_m = 0;
        this.gsm_norma = 0;
        this.gsm_out = 0;
        this.comm = null;
        this.dat = null;
        this.kv = 0;
        this.speed_begin = 0;
        this.speed_end = 0;
        this.oil_l = 0;
        this.oil_type = 0;*/
    }

    public OperationalModel(int mm, String interval, int year_e, float d_out, float d_good, float d_operational, float d_remont, float h_order, float norma_run, float fact_run, float to1, float to2, float kp, float gsm_begin_m, float gsm_given, float gsm_end_m, float gsm_norma, float gsm_out, String comm, Date dat, int kv, float speed_begin, float speed_end, float oil_l, int oil_type) {
        this.mm = mm;
        this.interval = interval;
        this.year_e = year_e;
        this.d_out = d_out;
        this.d_good = d_good;
        this.d_operational = d_operational;
        this.d_remont = d_remont;
        this.h_order = h_order;
        this.norma_run = norma_run;
        this.fact_run = fact_run;
        this.to1 = to1;
        this.to2 = to2;
        this.kp = kp;
        this.gsm_begin_m = gsm_begin_m;
        this.gsm_given = gsm_given;
        this.gsm_end_m = gsm_end_m;
        this.gsm_norma = gsm_norma;
        this.gsm_out = gsm_out;
        this.comm = comm;
        this.dat = dat;
        this.kv = kv;
        this.speed_begin = speed_begin;
        this.speed_end = speed_end;
        this.oil_l = oil_l;
        this.oil_type = oil_type;
        this.newOper = false;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public int getYear_e() {
        return year_e;
    }

    public void setYear_e(int year_e) {
        this.year_e = year_e;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }


    public float getD_out() {
        return d_out;
    }

    public void setD_out(float d_out) {
        this.d_out = d_out;
    }

    public float getD_good() {
        return d_good;
    }

    public void setD_good(float d_good) {
        this.d_good = d_good;
    }

    public float getD_operational() {
        return d_operational;
    }

    public void setD_operational(float d_operational) {
        this.d_operational = d_operational;
    }

    public float getD_remont() {
        return d_remont;
    }

    public void setD_remont(float d_remont) {
        this.d_remont = d_remont;
    }

    public float getH_order() {
        return h_order;
    }

    public void setH_order(float h_order) {
        this.h_order = h_order;
    }

    public float getNorma_run() {
        return norma_run;
    }

    public void setNorma_run(float norma_run) {
        this.norma_run = norma_run;
    }

    public float getFact_run() {
        return fact_run;
    }

    public void setFact_run(float fact_run) {
        this.fact_run = fact_run;
    }

    public float getTo1() {
        return to1;
    }

    public void setTo1(float to1) {
        this.to1 = to1;
    }

    public float getTo2() {
        return to2;
    }

    public void setTo2(float to2) {
        this.to2 = to2;
    }

    public float getKp() {
        return kp;
    }

    public void setKp(float kp) {
        this.kp = kp;
    }

    public float getGsm_begin_m() {
        return gsm_begin_m;
    }

    public void setGsm_begin_m(float gsm_begin_m) {
        this.gsm_begin_m = gsm_begin_m;
    }

    public float getGsm_given() {
        return gsm_given;
    }

    public void setGsm_given(float gsm_given) {
        this.gsm_given = gsm_given;
    }

    public float getGsm_end_m() {
        return gsm_end_m;
    }

    public void setGsm_end_m(float gsm_end_m) {
        this.gsm_end_m = gsm_end_m;
    }

    public float getGsm_norma() {
        return gsm_norma;
    }

    public void setGsm_norma(float gsm_norma) {
        this.gsm_norma = gsm_norma;
    }

    public float getGsm_out() {
        return gsm_out;
    }

    public void setGsm_out(float gsm_out) {
        this.gsm_out = gsm_out;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public int getKv() {
        return kv;
    }

    public void setKv(int kv) {
        this.kv = kv;
    }

    public float getSpeed_begin() {
        return speed_begin;
    }

    public void setSpeed_begin(float speed_begin) {
        this.speed_begin = speed_begin;
    }

    public float getSpeed_end() {
        return speed_end;
    }

    public void setSpeed_end(float speed_end) {
        this.speed_end = speed_end;
    }

    public float getOil_l() {
        return oil_l;
    }

    public void setOil_l(float oil_l) {
        this.oil_l = oil_l;
    }

    public int getOil_type() {
        return oil_type;
    }

    public void setOil_type(int oil_type) {
        this.oil_type = oil_type;
    }

    public boolean isNewOper() {
        return newOper;
    }

    public void setNewOper(boolean newOper) {
        this.newOper = newOper;
    }
}
