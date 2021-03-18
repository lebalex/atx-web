package xyz.lebalex.atx.modelReport;

import java.io.Serializable;

public class ReportExplPlanModel implements Serializable {
    private String	region;
    private String	division;
    private String	depart;
    private int	countTC1;
    private int	countTC2;
    private int	countTC3;
    private int	countTC4;
    private int	countTC5;
    private float	norm_probeg_year1;
    private float	norm_probeg_year2;
    private float	norm_probeg_year3;
    private float	norm_probeg_year4;
    private float	norm_probeg_year5;
    private float	norm_probeg_kv1;
    private float	norm_probeg_kv2;
    private float	norm_probeg_kv3;
    private float	norm_probeg_kv4;
    private  float	norm_probeg_kv5;
    private String groupField;

    public ReportExplPlanModel(String region, String division, String depart, int countTC1, int countTC2, int countTC3, int countTC4, int countTC5, float norm_probeg_year1, float norm_probeg_year2, float norm_probeg_year3, float norm_probeg_year4, float norm_probeg_year5, float norm_probeg_kv1, float norm_probeg_kv2, float norm_probeg_kv3, float norm_probeg_kv4, float norm_probeg_kv5) {
        this.region = region;
        this.division = division;
        this.depart = depart;
        this.countTC1 = countTC1;
        this.countTC2 = countTC2;
        this.countTC3 = countTC3;
        this.countTC4 = countTC4;
        this.countTC5 = countTC5;
        this.norm_probeg_year1 = norm_probeg_year1;
        this.norm_probeg_year2 = norm_probeg_year2;
        this.norm_probeg_year3 = norm_probeg_year3;
        this.norm_probeg_year4 = norm_probeg_year4;
        this.norm_probeg_year5 = norm_probeg_year5;
        this.norm_probeg_kv1 = norm_probeg_kv1;
        this.norm_probeg_kv2 = norm_probeg_kv2;
        this.norm_probeg_kv3 = norm_probeg_kv3;
        this.norm_probeg_kv4 = norm_probeg_kv4;
        this.norm_probeg_kv5 = norm_probeg_kv5;
        this.groupField = region+((division!=null)?", "+division:"");
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

    public int getCountTC1() {
        return countTC1;
    }

    public int getCountTC2() {
        return countTC2;
    }

    public int getCountTC3() {
        return countTC3;
    }

    public int getCountTC4() {
        return countTC4;
    }

    public int getCountTC5() {
        return countTC5;
    }

    public float getNorm_probeg_year1() {
        return norm_probeg_year1;
    }

    public float getNorm_probeg_year2() {
        return norm_probeg_year2;
    }

    public float getNorm_probeg_year3() {
        return norm_probeg_year3;
    }

    public float getNorm_probeg_year4() {
        return norm_probeg_year4;
    }

    public float getNorm_probeg_year5() {
        return norm_probeg_year5;
    }

    public float getNorm_probeg_kv1() {
        return norm_probeg_kv1;
    }

    public float getNorm_probeg_kv2() {
        return norm_probeg_kv2;
    }

    public float getNorm_probeg_kv3() {
        return norm_probeg_kv3;
    }

    public float getNorm_probeg_kv4() {
        return norm_probeg_kv4;
    }

    public float getNorm_probeg_kv5() {
        return norm_probeg_kv5;
    }

    public String getGroupField() {
        return groupField;
    }
}
