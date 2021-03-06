package xyz.lebalex.atx.models;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import xyz.lebalex.atx.controls.QuickSearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RemontModel implements Serializable {
    static Logger logger = Logger.getLogger(RemontModel.class);
    private int id;
    private int	id_avt;
    private Date	date_begin;
    private Date date_end;
    private int day_remont;
    private int	id_vid;
    private String	name_vid;
    private int	id_place;
    private String	name_place;
    private float	speed_begin;
    private float	speed_to;
    private float	oil_l;
    private int	num;
    private int	oil_type;
    private float	freez;
    private int	freez_type;
    private float	liq_break;
    private float	oil_atf;
    private int	oil_atf_type;
    private float sum_remont1;
    private float sum_remont2;
    private float sum_remont3;
    private List<RemontAgrModel> listRemontAgrModel;
    private List<RemontDiffModel> listRemontDiffModel;


    public RemontModel(int id_avt)
    {
        this.id = -1;
        this.id_avt = id_avt;
        listRemontAgrModel = new ArrayList<>();
        listRemontDiffModel = new ArrayList<>();
    }

    public RemontModel(int id, int id_avt, Date date_begin, Date date_end, int day_remont, int id_vid, String name_vid, int id_place, String name_place, float speed_begin, float speed_to, float oil_l, int num, int oil_type, float freez, int freez_type, float liq_break, float oil_atf, int oil_atf_type, float sum_remont1, float sum_remont2, float sum_remont3) {
        this.id = id;
        this.id_avt = id_avt;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.day_remont = day_remont;
        this.id_vid = id_vid;
        this.name_vid = name_vid;
        this.id_place = id_place;
        this.name_place = name_place;
        this.speed_begin = speed_begin;
        this.speed_to = speed_to;
        this.oil_l = oil_l;
        this.num = num;
        this.oil_type = oil_type;
        this.freez = freez;
        this.freez_type = freez_type;
        this.liq_break = liq_break;
        this.oil_atf = oil_atf;
        this.oil_atf_type = oil_atf_type;
        this.sum_remont1 = sum_remont1;
        this.sum_remont2 = sum_remont2;
        this.sum_remont3 = sum_remont3;
        listRemontAgrModel = new ArrayList<>();
        listRemontDiffModel = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_avt() {
        return id_avt;
    }

    public void setId_avt(int id_avt) {
        this.id_avt = id_avt;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getId_vid() {
        return id_vid;
    }

    public void setId_vid(int id_vid) {
        this.id_vid = id_vid;
    }

    public int getId_place() {
        return id_place;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public float getSpeed_begin() {
        return speed_begin;
    }

    public void setSpeed_begin(float speed_begin) {
        this.speed_begin = speed_begin;
    }

    public float getSpeed_to() {
        return speed_to;
    }

    public void setSpeed_to(float speed_to) {
        this.speed_to = speed_to;
    }

    public float getOil_l() {
        return oil_l;
    }

    public void setOil_l(float oil_l) {
        this.oil_l = oil_l;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOil_type() {
        return oil_type;
    }

    public void setOil_type(int oil_type) {
        this.oil_type = oil_type;
    }

    public float getFreez() {
        return freez;
    }

    public void setFreez(float freez) {
        this.freez = freez;
    }

    public int getFreez_type() {
        return freez_type;
    }

    public void setFreez_type(int freez_type) {
        this.freez_type = freez_type;
    }

    public float getLiq_break() {
        return liq_break;
    }

    public void setLiq_break(float liq_break) {
        this.liq_break = liq_break;
    }

    public float getOil_atf() {
        return oil_atf;
    }

    public void setOil_atf(float oil_atf) {
        this.oil_atf = oil_atf;
    }

    public int getOil_atf_type() {
        return oil_atf_type;
    }

    public void setOil_atf_type(int oil_atf_type) {
        this.oil_atf_type = oil_atf_type;
    }

    public int getDay_remont() {
        return day_remont;
    }

    public void setDay_remont(int day_remont) {
        this.day_remont = day_remont;
    }

    public String getName_vid() {
        return name_vid;
    }

    public void setName_vid(String name_vid) {
        this.name_vid = name_vid;
    }

    public String getName_place() {
        return name_place;
    }

    public void setName_place(String name_place) {
        this.name_place = name_place;
    }

    public float getSum_remont1() {
        return sum_remont1;
    }

    public void setSum_remont1(float sum_remont1) {
        this.sum_remont1 = sum_remont1;
    }

    public float getSum_remont2() {
        return sum_remont2;
    }

    public void setSum_remont2(float sum_remont2) {
        this.sum_remont2 = sum_remont2;
    }

    public float getSum_remont3() {
        return sum_remont3;
    }

    public void setSum_remont3(float sum_remont3) {
        this.sum_remont3 = sum_remont3;
    }

    public List<RemontAgrModel> getListRemontAgrModel() {
        return listRemontAgrModel;
    }

    public void setListRemontAgrModel(List<RemontAgrModel> listRemontAgrModel) {
        this.listRemontAgrModel = listRemontAgrModel;
    }

    public List<RemontDiffModel> getListRemontDiffModel() {
        return listRemontDiffModel;
    }

    public void setListRemontDiffModel(List<RemontDiffModel> listRemontDiffModel) {
        this.listRemontDiffModel = listRemontDiffModel;
    }

    public void onAddNewAgr() {
        RemontAgrModel newAgr = new RemontAgrModel(listRemontAgrModel.size(), this.id,"название", "старый номер","новый номер");
        listRemontAgrModel.add(newAgr);

    }
    public void onDeleteAgr(int id) {
        Iterator<RemontAgrModel> i = listRemontAgrModel.iterator();
        while (i.hasNext()) {
            RemontAgrModel agr = i.next();
            if(agr.getId()==id)
                i.remove();
        }
    }
    public void onAddNewDiff(int type) {
        RemontDiffModel newAgr = new RemontDiffModel(listRemontDiffModel.size(), this.id,"название", 0, type);
        listRemontDiffModel.add(newAgr);

    }
    public void onDeleteDiff(int id) {
        Iterator<RemontDiffModel> i = listRemontDiffModel.iterator();
        while (i.hasNext()) {
            RemontDiffModel agr = i.next();
            if(agr.getId()==id)
                i.remove();
        }
    }
}
