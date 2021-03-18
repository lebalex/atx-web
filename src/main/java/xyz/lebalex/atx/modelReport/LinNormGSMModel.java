package xyz.lebalex.atx.modelReport;

import java.io.Serializable;

public class LinNormGSMModel implements Serializable {

    private String	depart;
    private String	fin;
    private String	model;
    private String	numb_1;
    private String	m_year;
    private String	mark_gsm;
    private String	group_used;
    private float	norm;
    private float	nasel;
    private float	nignegor;
    private float	old;
    private float	procent_gsm;
    private float	leto;
    private float	m0;
    private float	m1;
    private float	m2;
    private float	m3;
    private float	m4;
    private float	m5;
    private float	norma_run;
    private float	leto_2;
    private float	m0_2;
    private float	m1_2;
    private float	m2_2;
    private float	m3_2;
    private float	m4_2;
    private float	m5_2;
    private float	kv1;
    private float	kv2;
    private float	kv3;
    private float	kv4;
    private float	god;
    private float	val;
    private String	modelDVC;
    private String	volumeDVC;
    private String	power_avt;
    private String groupField;

    public LinNormGSMModel(String depart, String fin, String model, String numb_1, String m_year, String mark_gsm, String group_used, float norm, float nasel, float nignegor, float old, float procent_gsm, float leto, float m0, float m1, float m2, float m3, float m4, float m5, float norma_run, float leto_2, float m0_2, float m1_2, float m2_2, float m3_2, float m4_2, float m5_2, float kv1, float kv2, float kv3, float kv4, float god, float val, String modelDVC, String volumeDVC, String power_avt, int what) {
        this.depart = depart;
        this.fin = fin;
        this.model = model;
        this.numb_1 = numb_1;
        this.m_year = m_year;
        this.mark_gsm = mark_gsm;
        this.group_used = group_used;
        this.norm = norm;
        this.nasel = nasel;
        this.nignegor = nignegor;
        this.old = old;
        this.procent_gsm = procent_gsm;
        this.leto = leto;
        this.m0 = m0;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.m4 = m4;
        this.m5 = m5;
        this.norma_run = norma_run;
        this.leto_2 = leto_2;
        this.m0_2 = m0_2;
        this.m1_2 = m1_2;
        this.m2_2 = m2_2;
        this.m3_2 = m3_2;
        this.m4_2 = m4_2;
        this.m5_2 = m5_2;
        this.kv1 = kv1;
        this.kv2 = kv2;
        this.kv3 = kv3;
        this.kv4 = kv4;
        this.god = god;
        this.val = val;
        this.modelDVC = modelDVC;
        this.volumeDVC = volumeDVC;
        this.power_avt = power_avt;
        switch(what){
            case 3:this.groupField = depart+((fin!=null)?", "+fin:""); break;
            case 4:this.groupField = depart+((fin!=null)?", "+fin:"")+((this.mark_gsm!=null)?", "+this.mark_gsm:""); break;
            case 5:this.groupField = depart+((fin!=null)?", "+fin:"")+((this.mark_gsm!=null)?", "+this.mark_gsm:""); break;
        }
    }

    public String getDepart() {
        return depart;
    }

    public String getFin() {
        return fin;
    }

    public String getModel() {
        return model;
    }

    public String getNumb_1() {
        return numb_1;
    }

    public String getM_year() {
        return m_year;
    }

    public String getMark_gsm() {
        return mark_gsm;
    }

    public String getGroup_used() {
        return group_used;
    }

    public float getNorm() {
        return norm;
    }

    public float getNasel() {
        return nasel;
    }

    public float getNignegor() {
        return nignegor;
    }

    public float getOld() {
        return old;
    }

    public float getProcent_gsm() {
        return procent_gsm;
    }

    public float getLeto() {
        return leto;
    }

    public float getM0() {
        return m0;
    }

    public float getM1() {
        return m1;
    }

    public float getM2() {
        return m2;
    }

    public float getM3() {
        return m3;
    }

    public float getM4() {
        return m4;
    }

    public float getM5() {
        return m5;
    }

    public float getNorma_run() {
        return norma_run;
    }

    public float getLeto_2() {
        return leto_2;
    }

    public float getM0_2() {
        return m0_2;
    }

    public float getM1_2() {
        return m1_2;
    }

    public float getM2_2() {
        return m2_2;
    }

    public float getM3_2() {
        return m3_2;
    }

    public float getM4_2() {
        return m4_2;
    }

    public float getM5_2() {
        return m5_2;
    }

    public float getKv1() {
        return kv1;
    }

    public float getKv2() {
        return kv2;
    }

    public float getKv3() {
        return kv3;
    }

    public float getKv4() {
        return kv4;
    }

    public float getGod() {
        return god;
    }

    public float getVal() {
        return val;
    }

    public String getModelDVC() {
        return modelDVC;
    }

    public String getVolumeDVC() {
        return volumeDVC;
    }

    public String getPower_avt() {
        return power_avt;
    }

    public String getGroupField() {
        return groupField;
    }
}
