package xyz.lebalex.atx.modelReport;

import java.io.Serializable;

public class Expl01Model implements Serializable {
    private String name;
    private String Expr1;
    private String Expr2;
    private String Expr4;
    private float	fact_run;
    private float	pere;
    private float	ktg;
    private float	ktv;
    private float	d_g;
    private float	d_o;
    private float	d_out;
    private String groupField;

    public Expl01Model(String name, String expr1, String expr2, String expr4, float fact_run, float pere, float ktg, float ktv, float d_g, float d_o, float d_out) {
        this.name = name;
        Expr1 = expr1;
        Expr2 = expr2;
        Expr4 = expr4;
        this.fact_run = fact_run;
        this.pere = pere;
        this.ktg = ktg;
        this.ktv = ktv;
        this.d_g = d_g;
        this.d_o = d_o;
        this.d_out = d_out;
        this.groupField = name+((expr1!=null)?", "+expr1:"")+((expr2!=null)?", "+expr2:"");
    }

    public String getGroupField() {
        return groupField;
    }

    public String getName() {
        return name;
    }

    public String getExpr1() {
        return Expr1;
    }

    public String getExpr2() {
        return Expr2;
    }

    public String getExpr4() {
        return Expr4;
    }

    public float getFact_run() {
        return fact_run;
    }

    public float getPere() {
        return pere;
    }

    public float getKtg() {
        return ktg;
    }

    public float getKtv() {
        return ktv;
    }

    public float getD_g() {
        return d_g;
    }

    public float getD_o() {
        return d_o;
    }

    public float getD_out() {
        return d_out;
    }
}
