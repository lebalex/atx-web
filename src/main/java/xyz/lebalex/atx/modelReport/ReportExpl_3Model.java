package xyz.lebalex.atx.modelReport;

import java.io.Serializable;

public class ReportExpl_3Model implements Serializable {
    private String	name;
    private String	expr2;
    private String	expr3;
    private String	expr6;
    private String	inv_t;
    private String	in_pr;
    private String	expr5;
    private String	expr5p;
    private String	mark;
    private String	numb_1;
    private float	speed_end;
    private float	d_out;
    private float	d_g;
    private float	d_oper;
    private float	d_rem;
    private float	h_or;
    private float	norm;
    private float	gsm_nor;
    private float	gsm_out;
    private float	gsm_b;
    private float	gsm_g;
    private float	gsm_end;
    private float	n_run;
    private float	f_run;
    private float	to1;
    private float	to2;
    private float	kp;
    private float	oil_s;
    private float	oil_ps;
    private float	oil_m;
    private String groupField;

    public ReportExpl_3Model(String name, String expr2, String expr3, String expr6, String inv_t, String in_pr, String expr5, String expr5p, String mark, String numb_1, float speed_end, float d_out, float d_g, float d_oper, float d_rem, float h_or, float norm, float gsm_nor, float gsm_out, float gsm_b, float gsm_g, float gsm_end, float n_run, float f_run, float to1, float to2, float kp, float oil_s, float oil_ps, float oil_m) {
        this.name = name;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.expr6 = expr6;
        this.inv_t = inv_t;
        this.in_pr = in_pr;
        this.expr5 = expr5;
        this.expr5p = expr5p;
        this.mark = mark;
        this.numb_1 = numb_1;
        this.speed_end = speed_end;
        this.d_out = d_out;
        this.d_g = d_g;
        this.d_oper = d_oper;
        this.d_rem = d_rem;
        this.h_or = h_or;
        this.norm = norm;
        this.gsm_nor = gsm_nor;
        this.gsm_out = gsm_out;
        this.gsm_b = gsm_b;
        this.gsm_g = gsm_g;
        this.gsm_end = gsm_end;
        this.n_run = n_run;
        this.f_run = f_run;
        this.to1 = to1;
        this.to2 = to2;
        this.kp = kp;
        this.oil_s = oil_s;
        this.oil_ps = oil_ps;
        this.oil_m = oil_m;
        this.groupField = name+((expr2!=null)?", "+expr2:"")+((expr3!=null)?", "+expr3:"")+((expr6!=null)?", "+expr6:"");
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

    public String getExpr6() {
        return expr6;
    }

    public String getInv_t() {
        return inv_t;
    }

    public String getIn_pr() {
        return in_pr;
    }

    public String getExpr5() {
        return expr5;
    }

    public String getExpr5p() {
        return expr5p;
    }

    public String getMark() {
        return mark;
    }

    public String getNumb_1() {
        return numb_1;
    }

    public float getSpeed_end() {
        return speed_end;
    }

    public float getD_out() {
        return d_out;
    }

    public float getD_g() {
        return d_g;
    }

    public float getD_oper() {
        return d_oper;
    }

    public float getD_rem() {
        return d_rem;
    }

    public float getH_or() {
        return h_or;
    }

    public float getNorm() {
        return norm;
    }

    public float getGsm_nor() {
        return gsm_nor;
    }

    public float getGsm_out() {
        return gsm_out;
    }

    public float getGsm_b() {
        return gsm_b;
    }

    public float getGsm_g() {
        return gsm_g;
    }

    public float getGsm_end() {
        return gsm_end;
    }

    public float getN_run() {
        return n_run;
    }

    public float getF_run() {
        return f_run;
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

    public float getOil_s() {
        return oil_s;
    }

    public float getOil_ps() {
        return oil_ps;
    }

    public float getOil_m() {
        return oil_m;
    }

    public String getGroupField() {
        return groupField;
    }
}
