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
public class Report01 extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(Report01.class);

    private int id_region;
    private boolean on_region=false;
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

    private String volDvc;
    private boolean on_volDvc;
    private int id_typetc;
    private boolean on_typetc;
    private int id_typedvc;
    private boolean on_typedvc;
    private int id_manufactureTC;
    private boolean on_manufactureTC;

    private Date inAc1;
    private Date inAc2;
    private boolean on_account;

    private String stateOut="0";
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

    private int year_selected;
    private List<Integer> listYear = new ArrayList<>();

    private boolean tf01,tf02,tf03,tf04,tf05,tf06,tf07,tf08,tf09,tf10,tf11,tf12,tf13,tf14,tf15,tf16,tf17,tf18,tf19,tf20,tf21,tf22,tf23,tf24,tf25,tf26;

    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;
    @Inject
    QuickSearch quickSearch;
    @Inject
    ReportInventar reportInventar;
    @Inject
    ReportExpl01 reportExpl01;
    @Inject
    ReportRemont reportRemont;
    @Inject
    ReportTO reportTO;

    @Inject
    ReportLinNormGSM reportLinNormGSM;


    @PostConstruct
    public void initialize() {
        //logger.log(Level.INFO,"initialize");
        Calendar c1 = Calendar.getInstance();
        for(int i=2010;i<=(c1.get(Calendar.YEAR)+3);i++)
            listYear.add(i);
        this.year_selected = c1.get(Calendar.YEAR);

        handleYearChange(this.year_selected);
        if(managerPages.getWhatLibr()==1 || managerPages.getWhatLibr()==7 || managerPages.getWhatLibr()==9 || managerPages.getWhatLibr()==10) {
            tf01 = true;
            tf02 = true;
            tf03 = true;
        }
        if(managerPages.getWhatLibr()==8) {
            tf01 = true;
            tf02 = true;
            tf03 = true;
            tf20 = true;
            Calendar c0 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c0.add(Calendar.MONTH,-3);
            this.dr1 = new Date();
            this.dr2 = new Date();
            this.dr1.setTime(c0.getTimeInMillis());
            this.dr2.setTime(c2.getTimeInMillis());

        }
        if(managerPages.getWhatLibr()==6) {
            tf01 = true;
            tf02 = true;
            tf12 = true;tf13 = true;tf14 = true;tf15 = true;tf16 = true;tf17 = true;tf18 = true;tf19 = true;tf20 = true;
            tf21 = true;tf22 = true;tf23 = true;tf24 = true;tf25 = true;tf26 = true;
        }
    }
    public Report01() {
        //logger.log(Level.INFO,"constructor");
    }

    public void handleYearChange(int year) {
        Calendar c1 = Calendar.getInstance();
        c1.set(year,0,1);
        Calendar c2 = Calendar.getInstance();
        c2.set(year,11,31);
        this.dr1 = new Date();
        this.dr2 = new Date();
        this.dr1.setTime(c1.getTimeInMillis());
        this.dr2.setTime(c2.getTimeInMillis());
    }

    public int getYear_selected() {
        return year_selected;
    }

    public void setYear_selected(int year_selected) {
        this.year_selected = year_selected;
    }

    public List<Integer> getListYear() {
        return listYear;
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

    public String getVolDvc() {
        return volDvc;
    }

    public void setVolDvc(String volDvc) {
        this.volDvc = volDvc;
    }

    public boolean isOn_volDvc() {
        return on_volDvc;
    }

    public void setOn_volDvc(boolean on_volDvc) {
        this.on_volDvc = on_volDvc;
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

    public int getId_typedvc() {
        return id_typedvc;
    }

    public void setId_typedvc(int id_typedvc) {
        this.id_typedvc = id_typedvc;
    }

    public boolean isOn_typedvc() {
        return on_typedvc;
    }

    public void setOn_typedvc(boolean on_typedvc) {
        this.on_typedvc = on_typedvc;
    }

    public int getId_manufactureTC() {
        return id_manufactureTC;
    }

    public void setId_manufactureTC(int id_manufactureTC) {
        this.id_manufactureTC = id_manufactureTC;
    }

    public boolean isOn_manufactureTC() {
        return on_manufactureTC;
    }

    public void setOn_manufactureTC(boolean on_manufactureTC) {
        this.on_manufactureTC = on_manufactureTC;
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

    public String getStateOut() {
        return stateOut;
    }

    public void setStateOut(String stateOut) {
        this.stateOut = stateOut;
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

    public boolean isTf01() {
        return tf01;
    }

    public void setTf01(boolean tf01) {
        this.tf01 = tf01;
    }

    public boolean isTf02() {
        return tf02;
    }

    public void setTf02(boolean tf02) {
        this.tf02 = tf02;
    }

    public boolean isTf03() {
        return tf03;
    }

    public void setTf03(boolean tf03) {
        this.tf03 = tf03;
    }

    public boolean isTf04() {
        return tf04;
    }

    public void setTf04(boolean tf04) {
        this.tf04 = tf04;
    }

    public boolean isTf05() {
        return tf05;
    }

    public void setTf05(boolean tf05) {
        this.tf05 = tf05;
    }

    public boolean isTf06() {
        return tf06;
    }

    public void setTf06(boolean tf06) {
        this.tf06 = tf06;
    }

    public boolean isTf07() {
        return tf07;
    }

    public void setTf07(boolean tf07) {
        this.tf07 = tf07;
    }

    public boolean isTf08() {
        return tf08;
    }

    public void setTf08(boolean tf08) {
        this.tf08 = tf08;
    }

    public boolean isTf09() {
        return tf09;
    }

    public void setTf09(boolean tf09) {
        this.tf09 = tf09;
    }

    public boolean isTf10() {
        return tf10;
    }

    public void setTf10(boolean tf10) {
        this.tf10 = tf10;
    }

    public boolean isTf11() {
        return tf11;
    }

    public void setTf11(boolean tf11) {
        this.tf11 = tf11;
    }

    public boolean isTf12() {
        return tf12;
    }

    public void setTf12(boolean tf12) {
        this.tf12 = tf12;
    }

    public boolean isTf13() {
        return tf13;
    }

    public void setTf13(boolean tf13) {
        this.tf13 = tf13;
    }

    public boolean isTf14() {
        return tf14;
    }

    public void setTf14(boolean tf14) {
        this.tf14 = tf14;
    }

    public boolean isTf15() {
        return tf15;
    }

    public void setTf15(boolean tf15) {
        this.tf15 = tf15;
    }

    public boolean isTf16() {
        return tf16;
    }

    public void setTf16(boolean tf16) {
        this.tf16 = tf16;
    }

    public boolean isTf17() {
        return tf17;
    }

    public void setTf17(boolean tf17) {
        this.tf17 = tf17;
    }

    public boolean isTf18() {
        return tf18;
    }

    public void setTf18(boolean tf18) {
        this.tf18 = tf18;
    }

    public boolean isTf19() {
        return tf19;
    }

    public void setTf19(boolean tf19) {
        this.tf19 = tf19;
    }

    public boolean isTf20() {
        return tf20;
    }

    public void setTf20(boolean tf20) {
        this.tf20 = tf20;
    }

    public boolean isTf21() {
        return tf21;
    }

    public void setTf21(boolean tf21) {
        this.tf21 = tf21;
    }

    public boolean isTf22() {
        return tf22;
    }

    public void setTf22(boolean tf22) {
        this.tf22 = tf22;
    }

    public boolean isTf23() {
        return tf23;
    }

    public void setTf23(boolean tf23) {
        this.tf23 = tf23;
    }

    public boolean isTf24() {
        return tf24;
    }

    public void setTf24(boolean tf24) {
        this.tf24 = tf24;
    }

    public boolean isTf25() {
        return tf25;
    }

    public void setTf25(boolean tf25) {
        this.tf25 = tf25;
    }

    public boolean isTf26() {
        return tf26;
    }

    public void setTf26(boolean tf26) {
        this.tf26 = tf26;
    }

    public String getTitle20()
    {
        switch(managerPages.getWhatLibr()){
            case 1:
            case 9:
            case 10:
                return "Страховая компания";
            case 8:
                return "Последнее ТО";
            default: return "Страховая компания";
        }
    }
    public boolean getVisibleOut()
    {
        switch(managerPages.getWhatLibr()){
            case 2:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return true;
            default: return false;
        }
    }
    public boolean getVisibleFieldsInventer()
    {
        switch(managerPages.getWhatLibr()) {
            case 1:
            case 7:
            case 8:
            case 9:
            case 10:
                return true;
            default:
                return false;
        }
    }
    public boolean getVisibleFieldsRemont()
    {
        if(managerPages.getWhatLibr()==6) return true;
        else return false;
    }
    public boolean getVisibleDatePeriod()
    {
        //logger.log(Level.INFO,"getVisibleDatePeriod");
        switch(managerPages.getWhatLibr()){
            case 2:
            case 6:
            case 8:
            case 10:
                return true;
            default: return false;
        }
    }
    public boolean getVisibleYearPeriod()
    {
        switch(managerPages.getWhatLibr()){
            case 3:
            case 4:
                return true;
            default: return false;
        }
    }

    public void find(ActionEvent e) {
        boolean doit=false;
        String modelAvt="-1";
        int id_region,id_division,id_depart,id_preDepart,id_serv,id_PreServ,id_dislocation,id_mark,id_source_bay,
                id_source_fin,id_group,id_typetc,id_typedvc,id_manufactureTC,id_markGsm,state_p,uotAc_p,life_p,spasan,spasan_next,arend,notAtx;
        String numb_1,numb_2,m_year1,m_year2,vin,n_engine,n_body,n_chassis,n_pts,insur,inAc1,inAc2,outD1,outD2,volDvc,drD1,drD2;


        if (this.dr1!=null) {doit=true; drD1=DateHelper.DateToString(this.dr1,"dd.MM.yyyy");} else drD1="-1";
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
        if (on_insur && this.insur.length()>0) {doit=true;insur=this.insur;} else insur="-1";


        if (on_typetc && this.id_typetc>0) {doit=true;id_typetc=this.id_typetc;} else id_typetc=-1;
        if (on_typedvc && this.id_typedvc>0) {doit=true;id_typedvc=this.id_typedvc;} else id_typedvc=-1;
        if (on_manufactureTC && this.id_manufactureTC>0) {doit=true;id_manufactureTC=this.id_manufactureTC;} else id_manufactureTC=-1;
        if (on_markGsm && this.id_markGsm>0) {doit=true;id_markGsm=this.id_markGsm;} else id_markGsm=-1;

        if (on_account && this.inAc1!=null) {doit=true; inAc1=DateHelper.DateToString(this.inAc1,"dd.MM.yyyy");} else inAc1="-1";
        if (on_account && this.inAc2!=null) {doit=true; inAc2=DateHelper.DateToString(this.inAc2,"dd.MM.yyyy");} else inAc2="-1";


        if (stateOut.equalsIgnoreCase("1")) {doit=true; state_p=1;} else state_p=0;
        if (stateOut.equalsIgnoreCase("2")) {doit=true; uotAc_p=1;} else uotAc_p=0;
        if (stateOut.equalsIgnoreCase("3")) {doit=true; life_p=1;} else life_p=0;

        if (this.out1!=null) {doit=true; outD1=DateHelper.DateToString(this.out1,"dd.MM.yyyy");} else outD1="-1";
        if (this.out2!=null) {doit=true; outD2=DateHelper.DateToString(this.out2,"dd.MM.yyyy");} else outD2="-1";

        if (on_volDvc && this.volDvc.length()>0) {doit=true;volDvc=this.volDvc;} else volDvc="-1";


        if (on_spasan) {doit=true; spasan=1;} else spasan=-1;
        if (on_spasan_next) {doit=true; spasan_next=1;} else spasan_next=-1;
        if (on_arend) {doit=true; arend=1;} else arend=-1;
        if (on_notAtx) {doit=true; notAtx=1;} else notAtx=-1;

        List<Boolean> filds = new ArrayList();
        if(this.tf01)filds.add(true);else filds.add(false);
        if(this.tf02)filds.add(true);else filds.add(false);
        if(this.tf03)filds.add(true);else filds.add(false);
        if(this.tf04)filds.add(true);else filds.add(false);
        if(this.tf05)filds.add(true);else filds.add(false);
        if(this.tf06)filds.add(true);else filds.add(false);
        if(this.tf07)filds.add(true);else filds.add(false);
        if(this.tf08)filds.add(true);else filds.add(false);
        if(this.tf09)filds.add(true);else filds.add(false);
        if(this.tf10)filds.add(true);else filds.add(false);
        if(this.tf11)filds.add(true);else filds.add(false);
        if(this.tf12)filds.add(true);else filds.add(false);
        if(this.tf13)filds.add(true);else filds.add(false);
        if(this.tf14)filds.add(true);else filds.add(false);
        if(this.tf15)filds.add(true);else filds.add(false);
        if(this.tf16)filds.add(true);else filds.add(false);
        if(this.tf17)filds.add(true);else filds.add(false);
        if(this.tf18)filds.add(true);else filds.add(false);
        if(this.tf19)filds.add(true);else filds.add(false);
        if(this.tf20)filds.add(true);else filds.add(false);
        if(this.tf21)filds.add(true);else filds.add(false);
        if(this.tf22)filds.add(true);else filds.add(false);
        if(this.tf23)filds.add(true);else filds.add(false);
        if(this.tf24)filds.add(true);else filds.add(false);
        if(this.tf25)filds.add(true);else filds.add(false);
        if(this.tf26)filds.add(true);else filds.add(false);

        if(doit) {
            int what = managerPages.getWhatLibr();
            switch(what) {
                case 1: reportInventar.inventar(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, state_p, uotAc_p, life_p, outD1, outD2, id_typetc, arend, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, filds, what);break;
                case 2: reportExpl01.expl01(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, drD1, drD2, id_typetc, arend, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next);break;
                case 3: reportLinNormGSM.rep01(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, drD1, drD2, id_typetc, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, this.year_selected, what);break;
                case 4: reportLinNormGSM.rep01(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, drD1, drD2, id_typetc, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, this.year_selected, what);break;
                case 5: reportLinNormGSM.rep01(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, drD1, drD2, id_typetc, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, this.year_selected, what);break;
                case 6: reportRemont.remont(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, outD1, outD2, id_typetc, arend, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, filds);break;
                case 7: reportTO.to(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, state_p, uotAc_p, life_p, outD1, outD2, id_typetc, arend, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, filds, what);break;
                case 8: reportTO.notTo(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, outD1, outD2, id_typetc, arend, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, filds, what);break;
                case 9: reportInventar.inventar(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, state_p, uotAc_p, life_p, outD1, outD2, id_typetc, arend, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, filds, what);break;
                case 10: reportInventar.govNumber(id_region, id_division, id_depart, id_preDepart, id_serv, id_PreServ, id_dislocation, id_mark, id_source_bay,
                        id_source_fin, id_group, modelAvt, numb_1, numb_2, m_year1, m_year2, vin, n_engine, n_body, n_chassis, n_pts, insur,
                        inAc1, inAc2, drD1, drD2, state_p, uotAc_p, life_p, outD1, outD2, id_typetc, arend, spasan, id_markGsm,
                        notAtx, volDvc, id_typedvc, id_manufactureTC, spasan_next, filds, what);break;
            }

        }



    }
}
