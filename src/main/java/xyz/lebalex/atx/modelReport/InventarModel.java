package xyz.lebalex.atx.modelReport;

import java.io.Serializable;

public class InventarModel implements Serializable {
    private String name;
    private String	Expr1;
    private String	Expr2;
    private String	Expr3;
    private String	mark;
    private String	numb_1;
    private String	m_year;
    private String	color;
    private String	vin;
    private String	n_engine;
    private String	n_body;
    private String	n_chassis;
    private String	Expr5;
    private String	Expr6;
    private String	Expr6p;
    private String	Expr7;
    private String	Expr8;
    private String	Expr8d;
    private String	Expr9;
    private String	Expr10;
    private String	Expr11;
    private String	Expr4;
    private float	speed_end;
    private String	typetc;
    private String	aD1;
    private String	aD2;
    private int	id;
    private String	ExplGroup;
    private String	modelDVC;
    private String	volumeDVC;
    private String	typeDVC;
    private String	ManufactureTC;
    private String groupField;


    public InventarModel(String name, String expr1, String expr2, String expr3, String mark, String numb_1, String m_year, String color, String vin, String n_engine, String n_body, String n_chassis, String expr5, String expr6, String expr6p, String expr7, String expr8, String expr8d, String expr9, String expr10, String expr11, String expr4, float speed_end, String typetc, String AD1, String AD2, int id, String explGroup, String modelDVC, String volumeDVC, String typeDVC, String	ManufactureTC, int what) {
        this.name = name;
        Expr1 = expr1;
        Expr2 = expr2;
        Expr3 = expr3;
        this.mark = mark;
        this.numb_1 = numb_1;
        this.m_year = m_year;
        this.color = color;
        this.vin = vin;
        this.n_engine = n_engine;
        this.n_body = n_body;
        this.n_chassis = n_chassis;
        Expr5 = expr5;
        Expr6 = expr6;
        Expr6p = expr6p;
        Expr7 = expr7;
        Expr8 = expr8;
        Expr8d = expr8d;
        Expr9 = expr9;
        Expr10 = expr10;
        Expr11 = expr11;
        Expr4 = expr4;
        this.speed_end = speed_end;
        this.typetc = typetc;
        this.aD1 = AD1;
        this.aD2 = AD2;
        this.id = id;
        ExplGroup = explGroup;
        this.modelDVC = modelDVC;
        this.volumeDVC = volumeDVC;
        this.typeDVC=typeDVC;
        this.ManufactureTC=ManufactureTC;
        if(what==1)
            this.groupField = name+((expr1!=null)?", "+expr1:"")+((expr2!=null)?", "+expr2:"")+((expr3!=null)?", "+expr3:"");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpr1() {
        return Expr1;
    }

    public void setExpr1(String expr1) {
        Expr1 = expr1;
    }

    public String getExpr2() {
        return Expr2;
    }

    public void setExpr2(String expr2) {
        Expr2 = expr2;
    }

    public String getExpr3() {
        return Expr3;
    }

    public void setExpr3(String expr3) {
        Expr3 = expr3;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNumb_1() {
        return numb_1;
    }

    public void setNumb_1(String numb_1) {
        this.numb_1 = numb_1;
    }

    public String getM_year() {
        return m_year;
    }

    public void setM_year(String m_year) {
        this.m_year = m_year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getN_engine() {
        return n_engine;
    }

    public void setN_engine(String n_engine) {
        this.n_engine = n_engine;
    }

    public String getN_body() {
        return n_body;
    }

    public void setN_body(String n_body) {
        this.n_body = n_body;
    }

    public String getN_chassis() {
        return n_chassis;
    }

    public void setN_chassis(String n_chassis) {
        this.n_chassis = n_chassis;
    }

    public String getExpr5() {
        return Expr5;
    }

    public void setExpr5(String expr5) {
        Expr5 = expr5;
    }

    public String getExpr6() {
        return Expr6;
    }

    public void setExpr6(String expr6) {
        Expr6 = expr6;
    }

    public String getExpr6p() {
        return Expr6p;
    }

    public void setExpr6p(String expr6p) {
        Expr6p = expr6p;
    }

    public String getExpr7() {
        return Expr7;
    }

    public void setExpr7(String expr7) {
        Expr7 = expr7;
    }

    public String getExpr8() {
        return Expr8;
    }

    public void setExpr8(String expr8) {
        Expr8 = expr8;
    }

    public String getExpr8d() {
        return Expr8d;
    }

    public void setExpr8d(String expr8d) {
        Expr8d = expr8d;
    }

    public String getExpr9() {
        return Expr9;
    }

    public void setExpr9(String expr9) {
        Expr9 = expr9;
    }

    public String getExpr10() {
        return Expr10;
    }

    public void setExpr10(String expr10) {
        Expr10 = expr10;
    }

    public String getExpr11() {
        return Expr11;
    }

    public void setExpr11(String expr11) {
        Expr11 = expr11;
    }

    public String getExpr4() {
        return Expr4;
    }

    public void setExpr4(String expr4) {
        Expr4 = expr4;
    }

    public float getSpeed_end() {
        return speed_end;
    }

    public void setSpeed_end(float speed_end) {
        this.speed_end = speed_end;
    }

    public String getTypetc() {
        return typetc;
    }

    public void setTypetc(String typetc) {
        this.typetc = typetc;
    }

    public String getaD1() {
        return aD1;
    }

    public void setaD1(String aD1) {
        this.aD1 = aD1;
    }

    public String getaD2() {
        return aD2;
    }

    public void setaD2(String aD2) {
        this.aD2 = aD2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExplGroup() {
        return ExplGroup;
    }

    public void setExplGroup(String explGroup) {
        ExplGroup = explGroup;
    }

    public String getModelDVC() {
        return modelDVC;
    }

    public void setModelDVC(String modelDVC) {
        this.modelDVC = modelDVC;
    }

    public String getVolumeDVC() {
        return volumeDVC;
    }

    public void setVolumeDVC(String volumeDVC) {
        this.volumeDVC = volumeDVC;
    }

    public String getTypeDVC() {
        return typeDVC;
    }

    public void setTypeDVC(String typeDVC) {
        this.typeDVC = typeDVC;
    }

    public String getManufactureTC() {
        return ManufactureTC;
    }

    public void setManufactureTC(String manufactureTC) {
        ManufactureTC = manufactureTC;
    }

    public String getGroupField() {
        if(groupField!=null)
            return groupField;
        return "0";
    }
}
