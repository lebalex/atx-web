package xyz.lebalex.atx.modelReport;

import java.io.Serializable;

public class ReportExpl_2Model implements Serializable {
    private String	name;
    private String	expr2;
    private String	expr3;
    private String	expr4;
    private String	expr5;
    private String	expr6;
    private String	mark;
    private String	numb_1;
    private float	d_out;
    private float	d_g;
    private float	d_oper;
    private float	d_rem;
    private float	h_or;
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
    private float	speed_end;
    private String groupField;

    public ReportExpl_2Model(String name, String expr2, String expr3, String expr4, String expr5, String expr6, String mark, String numb_1, float d_out, float d_g, float d_oper, float d_rem, float h_or, float gsm_nor, float gsm_out, float gsm_b, float gsm_g, float gsm_end, float n_run, float f_run, float to1, float to2, float kp, float speed_end) {
        this.name = name;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.expr4 = expr4;
        this.expr5 = expr5;
        this.expr6 = expr6;
        this.mark = mark;
        this.numb_1 = numb_1;
        this.d_out = d_out;
        this.d_g = d_g;
        this.d_oper = d_oper;
        this.d_rem = d_rem;
        this.h_or = h_or;
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
        this.speed_end = speed_end;
        this.groupField = name+((expr2!=null)?", "+expr2:"")+((expr3!=null)?", "+expr3:"")+((expr4!=null)?", "+expr4:"");
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

    public String getMark() {
        return mark;
    }

    public String getNumb_1() {
        return numb_1;
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

    public float getSpeed_end() {
        return speed_end;
    }

    public String getGroupField() {
        return groupField;
    }
}
