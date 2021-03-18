package xyz.lebalex.atx.modelReport;

import java.io.Serializable;

public class ReportExpl_1Model implements Serializable {
    private String	name;
    private String	expr2;
    private String	expr3;
    private String	expr4;
    private String	expr5;
    private String	expr6;
    private String	expr1;
    private String	numb_1;
    private String	dat;
    private float	norma_run;
    private float	fact_run;
    private float	d_out;
    private float	d_good;
    private float	d_operational;
    private float	gsm_begin_m;
    private float	gsm_given;
    private float	gsm_end_m;
    private float	gsm_out;
    private float	gsm_norma;
    private float	pere;
    private float	to1;
    private float	to2;
    private float	kp;
    private int	kv;
    private float	speed_end;

    public ReportExpl_1Model(String name, String expr2, String expr3, String expr4, String expr5, String expr6, String expr1, String numb_1, String dat, float norma_run, float fact_run, float d_out, float d_good, float d_operational, float gsm_begin_m, float gsm_given, float gsm_end_m, float gsm_out, float gsm_norma, float pere, float to1, float to2, float kp, int kv, float speed_end) {
        this.name = name;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.expr4 = expr4;
        this.expr5 = expr5;
        this.expr6 = expr6;
        this.expr1 = expr1;
        this.numb_1 = numb_1;
        this.dat = dat;
        this.norma_run = norma_run;
        this.fact_run = fact_run;
        this.d_out = d_out;
        this.d_good = d_good;
        this.d_operational = d_operational;
        this.gsm_begin_m = gsm_begin_m;
        this.gsm_given = gsm_given;
        this.gsm_end_m = gsm_end_m;
        this.gsm_out = gsm_out;
        this.gsm_norma = gsm_norma;
        this.pere = pere;
        this.to1 = to1;
        this.to2 = to2;
        this.kp = kp;
        this.kv = kv;
        this.speed_end = speed_end;
    }

    public String getName() {
        return name;
    }

    public String getExpr2() {
        return expr2;
    }

    public String getExpr3() {
        return expr3;
    }

    public String getExpr4() {
        return expr4;
    }

    public String getExpr5() {
        return expr5;
    }

    public String getExpr6() {
        return expr6;
    }

    public String getExpr1() {
        return expr1;
    }

    public String getNumb_1() {
        return numb_1;
    }

    public String getDat() {
        return dat;
    }

    public float getNorma_run() {
        return norma_run;
    }

    public float getFact_run() {
        return fact_run;
    }

    public float getD_out() {
        return d_out;
    }

    public float getD_good() {
        return d_good;
    }

    public float getD_operational() {
        return d_operational;
    }

    public float getGsm_begin_m() {
        return gsm_begin_m;
    }

    public float getGsm_given() {
        return gsm_given;
    }

    public float getGsm_end_m() {
        return gsm_end_m;
    }

    public float getGsm_out() {
        return gsm_out;
    }

    public float getGsm_norma() {
        return gsm_norma;
    }

    public float getPere() {
        return pere;
    }

    public float getTo1() {
        return to1;
    }

    public float getTo2() {
        return to2;
    }

    public float getKp() {
        return kp;
    }

    public int getKv() {
        return kv;
    }
    public String getKvString() {
        return Integer.toString(kv);
    }

    public float getSpeed_end() {
        return speed_end;
    }
}
