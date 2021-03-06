package xyz.lebalex.atx.controls;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.AtxLogin;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class FullSearch extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(FullSearch.class);

    private int id_region;private boolean on_region=false;
    private int id_division;
    private int id_depart;
    private int id_preDepart;
    private int id_serv;
    private int id_PreServ;
    private int id_dislocation;
    private int id_mark;
    private int id_model;
    private String numb_1;
    private String numb_2;
    private String m_year1;
    private String m_year2;
    private String vin;
    private boolean on_division;
    private boolean on_depart;
    private boolean on_preDepart;
    private boolean on_serv;
    private boolean on_PreServ;
    private boolean on_dislocation;
    private boolean on_mark;
    private boolean on_model;
    private boolean on_numb_1;
    private boolean on_numb_2;
    private boolean on_m_year;
    private boolean on_vin;



    private String n_engine;private boolean on_engine=false;
    private String n_body;
    private String n_chassis;
    private String n_pts;
    private int id_source_fin;
    private int id_source_bay;
    private int id_group;
    private String insur;
    private boolean on_body;
    private boolean on_chassis;
    private boolean on_pts;
    private boolean on_source_fin;
    private boolean on_source_bay;
    private boolean on_group;
    private boolean on_insur;

    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;
    @Inject
    QuickSearch quickSearch;

    @PostConstruct
    public void initialize() {

    }
    public FullSearch() {
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public boolean isOn_region() {
        return on_region;
    }

    public void setOn_region(boolean on_region) {
        this.on_region = on_region;
    }

    public String getN_engine() {
        return n_engine;
    }

    public void setN_engine(String n_engine) {
        this.n_engine = n_engine;
    }

    public boolean isOn_engine() {
        return on_engine;
    }

    public void setOn_engine(boolean on_engine) {
        this.on_engine = on_engine;
    }

    public int getId_division() {
        return id_division;
    }

    public void setId_division(int id_division) {
        this.id_division = id_division;
    }

    public int getId_depart() {
        return id_depart;
    }

    public void setId_depart(int id_depart) {
        this.id_depart = id_depart;
    }

    public int getId_preDepart() {
        return id_preDepart;
    }

    public void setId_preDepart(int id_preDepart) {
        this.id_preDepart = id_preDepart;
    }

    public int getId_serv() {
        return id_serv;
    }

    public void setId_serv(int id_serv) {
        this.id_serv = id_serv;
    }

    public int getId_PreServ() {
        return id_PreServ;
    }

    public void setId_PreServ(int id_PreServ) {
        this.id_PreServ = id_PreServ;
    }

    public int getId_dislocation() {
        return id_dislocation;
    }

    public void setId_dislocation(int id_dislocation) {
        this.id_dislocation = id_dislocation;
    }

    public int getId_mark() {
        return id_mark;
    }

    public void setId_mark(int id_mark) {
        this.id_mark = id_mark;
    }

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int id_model) {
        this.id_model = id_model;
    }

    public String getNumb_1() {
        return numb_1;
    }

    public void setNumb_1(String numb_1) {
        this.numb_1 = numb_1;
    }

    public String getNumb_2() {
        return numb_2;
    }

    public void setNumb_2(String numb_2) {
        this.numb_2 = numb_2;
    }

    public String getM_year1() {
        return m_year1;
    }

    public void setM_year1(String m_year1) {
        this.m_year1 = m_year1;
    }

    public String getM_year2() {
        return m_year2;
    }

    public void setM_year2(String m_year2) {
        this.m_year2 = m_year2;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public boolean isOn_division() {
        return on_division;
    }

    public void setOn_division(boolean on_division) {
        this.on_division = on_division;
    }

    public boolean isOn_depart() {
        return on_depart;
    }

    public void setOn_depart(boolean on_depart) {
        this.on_depart = on_depart;
    }

    public boolean isOn_preDepart() {
        return on_preDepart;
    }

    public void setOn_preDepart(boolean on_preDepart) {
        this.on_preDepart = on_preDepart;
    }

    public boolean isOn_serv() {
        return on_serv;
    }

    public void setOn_serv(boolean on_serv) {
        this.on_serv = on_serv;
    }

    public boolean isOn_PreServ() {
        return on_PreServ;
    }

    public void setOn_PreServ(boolean on_PreServ) {
        this.on_PreServ = on_PreServ;
    }

    public boolean isOn_dislocation() {
        return on_dislocation;
    }

    public void setOn_dislocation(boolean on_dislocation) {
        this.on_dislocation = on_dislocation;
    }

    public boolean isOn_mark() {
        return on_mark;
    }

    public void setOn_mark(boolean on_mark) {
        this.on_mark = on_mark;
    }

    public boolean isOn_model() {
        return on_model;
    }

    public void setOn_model(boolean on_model) {
        this.on_model = on_model;
    }

    public boolean isOn_numb_1() {
        return on_numb_1;
    }

    public void setOn_numb_1(boolean on_numb_1) {
        this.on_numb_1 = on_numb_1;
    }

    public boolean isOn_numb_2() {
        return on_numb_2;
    }

    public void setOn_numb_2(boolean on_numb_2) {
        this.on_numb_2 = on_numb_2;
    }

    public boolean isOn_m_year() {
        return on_m_year;
    }

    public void setOn_m_year(boolean on_m_year) {
        this.on_m_year = on_m_year;
    }

    public boolean isOn_vin() {
        return on_vin;
    }

    public void setOn_vin(boolean on_vin) {
        this.on_vin = on_vin;
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

    public String getN_pts() {
        return n_pts;
    }

    public void setN_pts(String n_pts) {
        this.n_pts = n_pts;
    }

    public int getId_source_fin() {
        return id_source_fin;
    }

    public void setId_source_fin(int id_source_fin) {
        this.id_source_fin = id_source_fin;
    }

    public int getId_source_bay() {
        return id_source_bay;
    }

    public void setId_source_bay(int id_source_bay) {
        this.id_source_bay = id_source_bay;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getInsur() {
        return insur;
    }

    public void setInsur(String insur) {
        this.insur = insur;
    }

    public boolean isOn_body() {
        return on_body;
    }

    public void setOn_body(boolean on_body) {
        this.on_body = on_body;
    }

    public boolean isOn_chassis() {
        return on_chassis;
    }

    public void setOn_chassis(boolean on_chassis) {
        this.on_chassis = on_chassis;
    }

    public boolean isOn_pts() {
        return on_pts;
    }

    public void setOn_pts(boolean on_pts) {
        this.on_pts = on_pts;
    }

    public boolean isOn_source_fin() {
        return on_source_fin;
    }

    public void setOn_source_fin(boolean on_source_fin) {
        this.on_source_fin = on_source_fin;
    }

    public boolean isOn_source_bay() {
        return on_source_bay;
    }

    public void setOn_source_bay(boolean on_source_bay) {
        this.on_source_bay = on_source_bay;
    }

    public boolean isOn_group() {
        return on_group;
    }

    public void setOn_group(boolean on_group) {
        this.on_group = on_group;
    }

    public boolean isOn_insur() {
        return on_insur;
    }

    public void setOn_insur(boolean on_insur) {
        this.on_insur = on_insur;
    }

    public void find(ActionEvent e) {
        boolean doit=false;
        String modelAvt="-1";
        int id_region,id_division,id_depart,id_preDepart,id_serv,id_PreServ,id_dislocation,id_mark,id_source_bay,
                id_source_fin,id_group;
        String numb_1,numb_2,m_year1,m_year2,vin,n_engine,n_body,n_chassis,n_pts,insur;

        if (on_region && this.id_region>0) {doit=true;id_region=this.id_region;} else id_region=-1;
        if (on_division && this.id_division>0) {doit=true;id_division=this.id_division;} else id_division=-1;
        if (on_depart && this.id_depart>0) {doit=true;id_depart=this.id_depart;} else id_depart=-1;
        if (on_preDepart && this.id_preDepart>0) {doit=true;id_preDepart=this.id_preDepart;} else id_preDepart=-1;
        if (on_serv && this.id_serv>0) {doit=true;id_serv=this.id_serv;} else id_serv=-1;
        if (on_PreServ && this.id_PreServ>0) {doit=true;id_PreServ=this.id_PreServ;} else id_PreServ=-1;

        if (on_dislocation && this.id_dislocation>0) {doit=true;id_dislocation=this.id_dislocation;} else id_dislocation=-1;
        if (on_mark && this.id_mark>0) {doit=true;id_mark=this.id_mark;} else id_mark=-1;
        if (on_source_bay && this.id_source_bay>0) {doit=true;id_source_bay=this.id_source_bay;} else id_source_bay=-1;
        if (on_source_fin && this.id_source_fin>0) {doit=true;id_source_fin=this.id_source_fin;} else id_source_fin=-1;
        if (on_group && this.id_group>0) {doit=true;id_group=this.id_group;} else id_group=-1;

        if (on_model && this.id_model>0) {doit=true; modelAvt=quickSearch.getModelName(id_model);} else modelAvt="-1";
        if (on_numb_1 && this.numb_1.length()>0) {doit=true;numb_1=this.numb_1;} else numb_1="-1";
        if (on_numb_2 && this.numb_2.length()>0) {doit=true;numb_2=this.numb_2;} else numb_2="-1";

        if (on_m_year  && this.m_year1.length()>0) {doit=true;m_year1=this.m_year1;} else m_year1="-1";
        if (on_m_year  && this.m_year2.length()>0) {doit=true;m_year2=this.m_year2;} else m_year2="-1";


        if (on_vin && this.vin.length()>0) {doit=true;vin=this.vin;} else vin="-1";
        if (on_engine && this.n_engine.length()>0) {doit=true;n_engine=this.n_engine;} else n_engine="-1";
        if (on_body && this.n_body.length()>0) {doit=true;n_body=this.n_body;} else n_body="-1";
        if (on_chassis && this.n_chassis.length()>0) {doit=true;n_chassis=this.n_chassis;} else n_chassis="-1";
        if (on_pts && this.n_pts.length()>0) {doit=true;n_pts=this.n_pts;} else n_pts="-1";
        if (on_insur && this.insur.length()>0) {doit=true;insur=this.insur;} else insur="-1";
        
        
        if(doit)
            quickSearch.findFull(id_region,id_division,id_depart,id_preDepart,id_serv,id_PreServ,id_dislocation,id_mark,id_source_bay,
                id_source_fin,id_group,modelAvt,numb_1,numb_2,m_year1,m_year2,vin,n_engine,n_body,n_chassis,n_pts,insur);
    }
}
