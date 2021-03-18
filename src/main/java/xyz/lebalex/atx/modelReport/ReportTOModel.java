package xyz.lebalex.atx.modelReport;

import java.io.Serializable;
import java.util.Date;

public class ReportTOModel implements Serializable {
    private String name;
    private String expr1;
    private String expr2;
    private String expr3;
    private String mark;
    private String numb_1;
    private String m_year;
    private String color;
    private String vin;
    private String n_engine;
    private String n_body;
    private String n_chassis;
    private String expr5;
    private String expr6;
    private String expr6p;
    private String expr7;
    private String expr8;
    private String expr8d;
    private String expr9;
    private String expr10;
    private String expr11;
    private String expr4;
    private float speed_end;
    private String typetc;
    private String aD1;
    private String aD2;
    private int id;
    private String explGroup;
    private String	modelDVC;
    private String	volumeDVC;
    private String	typeDVC;
    private String	manufactureTC;
    private Date maxOperDate;
    private String groupField;


    public ReportTOModel(String name, String expr1, String expr2, String expr3, String mark, String numb_1, String m_year, String color, String vin,
                         String n_engine, String n_body, String n_chassis, String expr5, String expr6, String expr6p, String expr7, String expr8,
                         String expr8d, String expr9, String expr10, String expr11, String expr4, float speed_end, String typetc, String aD1, String aD2,
                         int id, String explGroup, String modelDVC, String volumeDVC, String typeDVC, String manufactureTC, Date maxOperDate) {
        this.name = name;
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.mark = mark;
        this.numb_1 = numb_1;
        this.m_year = m_year;
        this.color = color;
        this.vin = vin;
        this.n_engine = n_engine;
        this.n_body = n_body;
        this.n_chassis = n_chassis;
        this.expr5 = expr5;
        this.expr6 = expr6;
        this.expr6p = expr6p;
        this.expr7 = expr7;
        this.expr8 = expr8;
        this.expr8d = expr8d;
        this.expr9 = expr9;
        this.expr10 = expr10;
        this.expr11 = expr11;
        this.expr4 = expr4;
        this.speed_end = speed_end;
        this.typetc = typetc;
        this.aD1 = aD1;
        this.aD2 = aD2;
        this.id = id;
        this.explGroup = explGroup;
        this.modelDVC = modelDVC;
        this.volumeDVC = volumeDVC;
        this.typeDVC = typeDVC;
        this.manufactureTC = manufactureTC;
        this.maxOperDate = maxOperDate;
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

    public String getMark() {
        return mark;
    }

    public String getNumb_1() {
        return numb_1;
    }

    public String getM_year() {
        return m_year;
    }

    public String getColor() {
        return color;
    }

    public String getVin() {
        return vin;
    }

    public String getN_engine() {
        return n_engine;
    }

    public String getN_body() {
        return n_body;
    }

    public String getN_chassis() {
        return n_chassis;
    }

    public String getExpr5() {
        return expr5;
    }

    public String getExpr6() {
        return expr6;
    }

    public String getExpr6p() {
        return expr6p;
    }

    public String getExpr7() {
        return expr7;
    }

    public String getExpr8() {
        return expr8;
    }

    public String getExpr8d() {
        return expr8d;
    }

    public String getExpr9() {
        return expr9;
    }

    public String getExpr10() {
        return expr10;
    }

    public String getExpr11() {
        return expr11;
    }

    public String getExpr4() {
        return expr4;
    }

    public float getSpeed_end() {
        return speed_end;
    }

    public String getTypetc() {
        return typetc;
    }

    public String getaD1() {
        return aD1;
    }

    public String getaD2() {
        return aD2;
    }

    public int getId() {
        return id;
    }

    public String getExplGroup() {
        return explGroup;
    }

    public String getModelDVC() {
        return modelDVC;
    }

    public String getVolumeDVC() {
        return volumeDVC;
    }

    public String getTypeDVC() {
        return typeDVC;
    }

    public String getManufactureTC() {
        return manufactureTC;
    }

    public String getGroupField() {
        return groupField;
    }

    public Date getMaxOperDate() {
        return maxOperDate;
    }
}
