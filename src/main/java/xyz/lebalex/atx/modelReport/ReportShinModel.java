package xyz.lebalex.atx.modelReport;

import java.io.Serializable;
import java.util.Date;

public class ReportShinModel implements Serializable {
    private String	name;
    private String	expr1;
    private String	expr2;
    private String	expr3;
    private String	expr6;
    private String	expr6p;
    private String	mark;
    private String	numb_1;
    private int	id_avt;
    private String	name_r;
    private String	razmer;
    private String	name_k;
    private String	name_t;
    private String	number;
    private float	probeg;
    private int	lim_value;
    private Date date2;
    private int	id;
    private String groupField;


    public ReportShinModel(String name, String expr1, String expr2, String expr3, String expr6, String expr6p, String mark, String numb_1, int id_avt, String name_r, String razmer, String name_k, String name_t, String number, float probeg, int lim_value, Date date2, int id) {
        this.name = name;
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.expr6 = expr6;
        this.expr6p = expr6p;
        this.mark = mark;
        this.numb_1 = numb_1;
        this.id_avt = id_avt;
        this.name_r = name_r;
        this.razmer = razmer;
        this.name_k = name_k;
        this.name_t = name_t;
        this.number = number;
        this.probeg = probeg;
        this.lim_value = lim_value;
        this.date2 = date2;
        this.id = id;
        this.groupField = name+((expr1!=null)?", "+expr1:"")+((expr2!=null)?", "+expr2:"")+((expr3!=null)?", "+expr3:"");

    }

    public String getName() {
        return name;
    }

    public String getExpr1() {
        return expr1;
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

    public String getExpr6p() {
        return expr6p;
    }

    public String getMark() {
        return mark;
    }

    public String getNumb_1() {
        return numb_1;
    }

    public int getId_avt() {
        return id_avt;
    }

    public String getName_r() {
        return name_r;
    }

    public String getRazmer() {
        return razmer;
    }

    public String getName_k() {
        return name_k;
    }

    public String getName_t() {
        return name_t;
    }

    public String getNumber() {
        return number;
    }

    public float getProbeg() {
        return probeg;
    }

    public int getLim_value() {
        return lim_value;
    }

    public Date getDate2() {
        return date2;
    }

    public int getId() {
        return id;
    }

    public String getGroupField() {
        return groupField;
    }
}
