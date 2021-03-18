package xyz.lebalex.atx.reports;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.AtxLogin;
import xyz.lebalex.atx.controls.ManagerPages;
import xyz.lebalex.atx.controls.QuickSearch;
import xyz.lebalex.atx.utils.DateHelper;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class Report02 extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(Report02.class);
    private int id_region;
    private boolean on_region = false;
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
    private String n_engine;
    private boolean on_engine = false;
    private String n_body;
    private String n_chassis;
    private String n_pts;
    private int id_source_fin;
    private int id_source_bay;
    private int id_group;
    private boolean on_body;
    private boolean on_chassis;
    private boolean on_pts;
    private boolean on_source_fin;
    private boolean on_source_bay;
    private boolean on_group;
    private int id_typetc;
    private boolean on_typetc;
    private Date inAc1;
    private Date inAc2;
    private boolean on_account;
    private boolean on_stateOut = false;
    private boolean on_stateOutAc = false;
    private boolean on_stateOutLife = false;
    private Date out1;
    private Date out2;
    private Date dr1;
    private Date dr2;
    private boolean on_spasan;
    private boolean on_spasan_next;
    private int id_markGsm;
    private boolean on_markGsm;
    private boolean on_arend;
    private boolean on_notAtx;
    private int id_remont;
    private boolean on_remont;
    private int id_remontCTO;
    private boolean on_remontCTO;
    private String search_fio;
    private boolean on_search_fio;
    private boolean on_sort_fio;
    private boolean on_group_depart;
    private int countDay = 90;
    private String search_place;
    private boolean on_search_place;
    private boolean on_includeOutSpisan;
    private String vinoven = "0";
    private String postradali = "0";
    private int what;

    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;
    @Inject
    QuickSearch quickSearch;

    @Inject
    ReportExpl_1 reportExpl_1;
    @Inject
    ReportExpl_2 reportExpl_2;
    @Inject
    ReportExpl_3 reportExpl_3;
    @Inject
    ReportExplPlan reportExplPlan;
    @Inject
    ReportShin reportShin;
    @Inject
    ReportOil reportOil;


    @PostConstruct
    public void initialize() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(c1.get(Calendar.YEAR),0,1);
        this.dr1 = new Date();
        this.dr2 = new Date();
        this.dr1.setTime(c1.getTimeInMillis());
        this.dr2.setTime(c2.getTimeInMillis());
    }
    public int getWhat() {
        this.what = managerPages.getWhatLibr();

        switch(this.what){
            case 11: this.on_numb_1=true;break;
            case 12: this.on_source_fin=true; this.id_source_fin = quickSearch.getListSourceModel().get(0).getId();break;

        }


        return what;
    }


    public void setWhat(int what) {
        this.what = what;
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

    public int getId_typetc() {
        return id_typetc;
    }

    public void setId_typetc(int id_typetc) {
        this.id_typetc = id_typetc;
    }

    public boolean isOn_typetc() {
        return on_typetc;
    }

    public void setOn_typetc(boolean on_typetc) {
        this.on_typetc = on_typetc;
    }

    public Date getInAc1() {
        return inAc1;
    }

    public void setInAc1(Date inAc1) {
        this.inAc1 = inAc1;
    }

    public Date getInAc2() {
        return inAc2;
    }

    public void setInAc2(Date inAc2) {
        this.inAc2 = inAc2;
    }

    public boolean isOn_account() {
        return on_account;
    }

    public void setOn_account(boolean on_account) {
        this.on_account = on_account;
    }

    public boolean isOn_stateOut() {
        return on_stateOut;
    }

    public void setOn_stateOut(boolean on_stateOut) {
        this.on_stateOut = on_stateOut;
    }

    public boolean isOn_stateOutAc() {
        return on_stateOutAc;
    }

    public void setOn_stateOutAc(boolean on_stateOutAc) {
        this.on_stateOutAc = on_stateOutAc;
    }

    public boolean isOn_stateOutLife() {
        return on_stateOutLife;
    }

    public void setOn_stateOutLife(boolean on_stateOutLife) {
        this.on_stateOutLife = on_stateOutLife;
    }

    public Date getOut1() {
        return out1;
    }

    public void setOut1(Date out1) {
        this.out1 = out1;
    }

    public Date getOut2() {
        return out2;
    }

    public void setOut2(Date out2) {
        this.out2 = out2;
    }

    public Date getDr1() {
        return dr1;
    }

    public void setDr1(Date dr1) {
        this.dr1 = dr1;
    }

    public Date getDr2() {
        return dr2;
    }

    public void setDr2(Date dr2) {
        this.dr2 = dr2;
    }

    public boolean isOn_spasan() {
        return on_spasan;
    }

    public void setOn_spasan(boolean on_spasan) {
        this.on_spasan = on_spasan;
    }

    public boolean isOn_spasan_next() {
        return on_spasan_next;
    }

    public void setOn_spasan_next(boolean on_spasan_next) {
        this.on_spasan_next = on_spasan_next;
    }

    public int getId_markGsm() {
        return id_markGsm;
    }

    public void setId_markGsm(int id_markGsm) {
        this.id_markGsm = id_markGsm;
    }

    public boolean isOn_markGsm() {
        return on_markGsm;
    }

    public void setOn_markGsm(boolean on_markGsm) {
        this.on_markGsm = on_markGsm;
    }

    public boolean isOn_arend() {
        return on_arend;
    }

    public void setOn_arend(boolean on_arend) {
        this.on_arend = on_arend;
    }

    public boolean isOn_notAtx() {
        return on_notAtx;
    }

    public void setOn_notAtx(boolean on_notAtx) {
        this.on_notAtx = on_notAtx;
    }

    public int getId_remont() {
        return id_remont;
    }

    public void setId_remont(int id_remont) {
        this.id_remont = id_remont;
    }

    public boolean isOn_remont() {
        return on_remont;
    }

    public void setOn_remont(boolean on_remont) {
        this.on_remont = on_remont;
    }

    public int getId_remontCTO() {
        return id_remontCTO;
    }

    public void setId_remontCTO(int id_remontCTO) {
        this.id_remontCTO = id_remontCTO;
    }

    public boolean isOn_remontCTO() {
        return on_remontCTO;
    }

    public void setOn_remontCTO(boolean on_remontCTO) {
        this.on_remontCTO = on_remontCTO;
    }

    public String getSearch_fio() {
        return search_fio;
    }

    public void setSearch_fio(String search_fio) {
        this.search_fio = search_fio;
    }

    public boolean isOn_search_fio() {
        return on_search_fio;
    }

    public void setOn_search_fio(boolean on_search_fio) {
        this.on_search_fio = on_search_fio;
    }

    public boolean isOn_sort_fio() {
        return on_sort_fio;
    }

    public void setOn_sort_fio(boolean on_sort_fio) {
        this.on_sort_fio = on_sort_fio;
    }

    public boolean isOn_group_depart() {
        return on_group_depart;
    }

    public void setOn_group_depart(boolean on_group_depart) {
        this.on_group_depart = on_group_depart;
    }

    public int getCountDay() {
        return countDay;
    }

    public void setCountDay(int countDay) {
        this.countDay = countDay;
    }

    public String getSearch_place() {
        return search_place;
    }

    public void setSearch_place(String search_place) {
        this.search_place = search_place;
    }

    public boolean isOn_search_place() {
        return on_search_place;
    }

    public void setOn_search_place(boolean on_search_place) {
        this.on_search_place = on_search_place;
    }

    public boolean isOn_includeOutSpisan() {
        return on_includeOutSpisan;
    }

    public void setOn_includeOutSpisan(boolean on_includeOutSpisan) {
        this.on_includeOutSpisan = on_includeOutSpisan;
    }

    public String getVinoven() {
        return vinoven;
    }

    public void setVinoven(String vinoven) {
        this.vinoven = vinoven;
    }

    public String getPostradali() {
        return postradali;
    }

    public void setPostradali(String postradali) {
        this.postradali = postradali;
    }
    public boolean getVisibleDatePeriod()
    {
        //logger.log(Level.INFO,"getVisibleDatePeriod");
        switch(managerPages.getWhatLibr()){
            case 11:
            case 12:
            case 13:
            case 16:
                return true;
            default: return false;
        }
    }
    public String getVisibleStateOutTitle()
    {
        switch(managerPages.getWhatLibr()){
            case 15:
                return "С превышением лимита";
            default: return "В отстое";
        }
    }
    public boolean getVisibleStateOut()
    {
        switch(managerPages.getWhatLibr()){
            case 15:
                return true;
            default: return false;
        }
    }
    public boolean getVisibleStateOutAc()
    {
        switch(managerPages.getWhatLibr()){
            case 0:
                return true;
            default: return false;
        }
    }
    public boolean getVisibleStateOutLife()
    {
        switch(managerPages.getWhatLibr()){
            case 0:
                return true;
            default: return false;
        }
    }
    public boolean getVisibleVinoven()
    {
        switch(managerPages.getWhatLibr()){
            case 0:
                return true;
            default: return false;
        }
    }
    public boolean getVisibleSortFio()
    {
        switch(managerPages.getWhatLibr()){
            case 0:
                return true;
            default: return false;
        }
    }
    public boolean getVisibleOutSpisan()
    {
        switch(what){
            case 13:
                return true;
            default: return false;
        }
    }
    public void find(ActionEvent e) {
        boolean doit=false;
        String modelAvt="-1";
        int id_region,id_division,id_depart,id_preDepart,id_serv,id_PreServ,id_dislocation,id_mark,id_source_bay,
                id_source_fin,id_group,id_typetc,id_markGsm,state_p,uotAc_p,life_p,notAtx,id_remont;
        String numb_1,numb_2,m_year1,m_year2,vin,n_engine,n_body,n_chassis,n_pts,inAc1,inAc2,drD1,drD2;


        if (this.dr1!=null) {doit=true; drD1= DateHelper.DateToString(this.dr1,"dd.MM.yyyy");} else drD1="-1";
        if (this.dr2!=null) {doit=true; drD2=DateHelper.DateToString(this.dr2,"dd.MM.yyyy");} else drD2="-1";

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


        if (on_typetc && this.id_typetc>0) {doit=true;id_typetc=this.id_typetc;} else id_typetc=-1;
        if (on_remont && this.id_remont>0) {doit=true;id_remont=this.id_remont;} else id_remont=-1;

        if (on_markGsm && this.id_markGsm>0) {doit=true;id_markGsm=this.id_markGsm;} else id_markGsm=-1;

        if (on_account && this.inAc1!=null) {doit=true; inAc1=DateHelper.DateToString(this.inAc1,"dd.MM.yyyy");} else inAc1="-1";
        if (on_account && this.inAc2!=null) {doit=true; inAc2=DateHelper.DateToString(this.inAc2,"dd.MM.yyyy");} else inAc2="-1";


        if (on_stateOut) {doit=true; state_p=1;} else state_p=0;
        if (on_stateOutAc) {doit=true; uotAc_p=1;} else uotAc_p=0;
        if (on_stateOutLife) {doit=true; life_p=1;} else life_p=0;

        /*if (this.out1!=null) {doit=true; outD1=DateHelper.DateToString(this.out1,"dd.MM.yyyy");} else outD1="-1";
        if (this.out2!=null) {doit=true; outD2=DateHelper.DateToString(this.out2,"dd.MM.yyyy");} else outD2="-1";*/


        /*if (on_spasan) {doit=true; spasan=1;} else spasan=-1;
        if (on_spasan_next) {doit=true; spasan_next=1;} else spasan_next=-1;
        if (on_arend) {doit=true; arend=1;} else arend=-1;*/
        if (on_notAtx) {doit=true; notAtx=1;} else notAtx=-1;






        int what = managerPages.getWhatLibr();
        if(what==11 && numb_1.equals("-1")) doit=false;

        if(doit) {

            switch(what) {
                case 11: reportExpl_1.report(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, notAtx, what);break;
                case 12: reportExpl_2.report(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, notAtx, what);break;
                case 13: reportExpl_3.report(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, notAtx, id_markGsm, on_includeOutSpisan, what);break;
                case 14: reportExplPlan.report(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, notAtx, what);break;
                case 15: reportShin.report(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, id_typetc, notAtx, what);break;
                case 16: reportOil.report(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, id_typetc, id_remont, notAtx, what);break;


            }

        }



    }

}
