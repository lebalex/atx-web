package xyz.lebalex.atx.modelReport;

import java.io.Serializable;

public class ReportOilModel implements Serializable {
    private String	region;
    private String	division;
    private String	depart;
    private String	service;
    private String	mark;
    private String	numb_1;
    private float	oil_s;
    private float	oil_ps;
    private float	oil_m;
    private float	oil_l;
    private float	freez_t;
    private float	freez_a;
    private float	liq_break;
    private float	oil_atf;
    private float	oil_atf_s;
    private float	oil_atf_ps;
    private float	oil_atf_m;
    private String groupField;

    public ReportOilModel(String region, String division, String depart, String service, String mark, String numb_1, float oil_s, float oil_ps, float oil_m, float oil_l, float freez_t, float freez_a, float liq_break, float oil_atf, float oil_atf_s, float oil_atf_ps, float oil_atf_m) {
        this.region = region;
        this.division = division;
        this.depart = depart;
        this.service = service;
        this.mark = mark;
        this.numb_1 = numb_1;
        this.oil_s = oil_s;
        this.oil_ps = oil_ps;
        this.oil_m = oil_m;
        this.oil_l = oil_l;
        this.freez_t = freez_t;
        this.freez_a = freez_a;
        this.liq_break = liq_break;
        this.oil_atf = oil_atf;
        this.oil_atf_s = oil_atf_s;
        this.oil_atf_ps = oil_atf_ps;
        this.oil_atf_m = oil_atf_m;
        this.groupField = region+((division!=null)?", "+division:"")+((depart!=null)?", "+depart:"")+((service!=null)?", "+service:"");
    }

    public ReportOilModel(String region, String division, String depart, String service, String mark, String numb_1, float oil_s, float oil_ps, float oil_m) {
        this.region = region;
        this.division = division;
        this.depart = depart;
        this.service = service;
        this.mark = mark;
        this.numb_1 = numb_1;
        this.oil_s = oil_s;
        this.oil_ps = oil_ps;
        this.oil_m = oil_m;
        this.groupField = region+((division!=null)?", "+division:"")+((depart!=null)?", "+depart:"")+((service!=null)?", "+service:"");
    }

    public String getRegion() {
        return region;
    }

    public String getDivision() {
        return division;
    }

    public String getDepart() {
        return depart;
    }

    public String getService() {
        return service;
    }

    public String getMark() {
        return mark;
    }

    public String getNumb_1() {
        return numb_1;
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

    public float getOil_l() {
        return oil_l;
    }

    public float getFreez_t() {
        return freez_t;
    }

    public float getFreez_a() {
        return freez_a;
    }

    public float getLiq_break() {
        return liq_break;
    }

    public float getOil_atf() {
        return oil_atf;
    }

    public float getOil_atf_s() {
        return oil_atf_s;
    }

    public float getOil_atf_ps() {
        return oil_atf_ps;
    }

    public float getOil_atf_m() {
        return oil_atf_m;
    }

    public String getGroupField() {
        return groupField;
    }
}
