package xyz.lebalex.atx.modelReport;

import java.io.Serializable;
import java.util.Date;

public class RemontModel implements Serializable {
    private String	name;
    private String	Expr1;
    private String	Expr2;
    private String	Expr3;
    private String	mark;
    private String	numb_1;
    private String	m_year;
    private String	vin;
    private String	n_engine;
    private String	n_body;
    private String	n_chassis;
    private String	Expr5;
    private String	Expr6;
    private float	coast1;
    private float	coast2;
    private float	coast3;
    private float	coast4;
    private int	Expr4;
    private Date date_begin;
    private Date	date_end;
    private int	ddd;
    private String	n1;
    private String	n2;
    private String	n3;
    private String	n4;
    private float	oil_l;
    private String	modelDVC;
    private String	volumeDVC;
    private String	typeDVC;
    private String	ManufactureTC;
    private String groupField;

    public RemontModel(String name, String expr1, String expr2, String expr3, String mark, String numb_1, String m_year, String vin, String n_engine, String n_body, String n_chassis, String expr5, String expr6, float coast1, float coast2, float coast3, float coast4, int expr4, Date date_begin, Date date_end, int ddd, String n1, String n2, String n3, String n4, float oil_l, String modelDVC, String volumeDVC, String typeDVC, String manufactureTC) {
        this.name = name;
        Expr1 = expr1;
        Expr2 = expr2;
        Expr3 = expr3;
        this.mark = mark;
        this.numb_1 = numb_1;
        this.m_year = m_year;
        this.vin = vin;
        this.n_engine = n_engine;
        this.n_body = n_body;
        this.n_chassis = n_chassis;
        Expr5 = expr5;
        Expr6 = expr6;
        this.coast1 = coast1;
        this.coast2 = coast2;
        this.coast3 = coast3;
        this.coast4 = coast4;
        Expr4 = expr4;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.ddd = ddd;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.oil_l = oil_l;
        this.modelDVC = modelDVC;
        this.volumeDVC = volumeDVC;
        this.typeDVC = typeDVC;
        ManufactureTC = manufactureTC;
        this.groupField = name+((expr1!=null)?", "+expr1:"")+((expr2!=null)?", "+expr2:"")+((expr3!=null)?", "+expr3:"")+((expr5!=null)?", "+expr5:"");
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

    public String getExpr3() {
        return Expr3;
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
        return Expr5;
    }

    public String getExpr6() {
        return Expr6;
    }

    public float getCoast1() {
        return coast1;
    }

    public float getCoast2() {
        return coast2;
    }

    public float getCoast3() {
        return coast3;
    }

    public float getCoast4() {
        return coast4;
    }

    public int getExpr4() {
        return Expr4;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public int getDdd() {
        return ddd;
    }

    public String getN1() {
        return n1;
    }

    public String getN2() {
        return n2;
    }

    public String getN3() {
        return n3;
    }

    public String getN4() {
        return n4;
    }

    public float getOil_l() {
        return oil_l;
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
        return ManufactureTC;
    }

    public String getGroupField() {
        return groupField;
    }
}
