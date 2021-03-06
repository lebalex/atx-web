package xyz.lebalex.atx.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AvtoModel implements Serializable {
    private int id;
    private int id_region;
    private int id_division;
    private int id_depart;
    private int id_preDepart;
    private int id_serv;
    private int id_PreServ;
    private int id_dislocation;
    private int id_mark;
    private String model;

    private String numb_1;
    private String numb_2;
    private String m_year;
    private String color;
    private String vin;
    private String n_engine;
    private String n_body;
    private String n_chassis;
    private String n_pts;
    private int id_source_fin;
    private int id_source_bay;
    private int id_type_body;
    private int id_tech;
    private int id_group;
    private String radio;
    private String insur;
    private String comm;
    private Date in_account;
    private String in_pr;
    private boolean out_state_ch;
    private Date out_state;
    private String out_state_pr;
    private boolean out_account_ch;
    private Date out_account;
    private String out_account_pr;
    private boolean out_life_ch;
    private Date out_life;
    private String out_life_pr;
    private Date date_insur;
    private Date date_texpasport;
    private String inv_b;
    private String inv_t;
    private String plase_sto_address;
    private String plase_sto_prikaz;
    private Date plase_sto_date;
    private String sgu;
    private boolean out_state_perez;
    private boolean out_state_out;
    private int id_mark_gsm;
    private float procent_gsm;
    private String old_number;
    private String power_energy;
    private int id_insure_company;
    private int id_base_norm;
    private float norm_probeg;
    private int id_typetc;
    private Date arenda_d1;
    private Date arenda_d2;
    private int expl_year;

    private boolean notAtx;
    private String out_state_osnovanie;
    private boolean out_state_expl;
    private int out_state_state;//Отстой
    private String modelDVC;
    private String volumeDVC;
    private int id_typeDVC;
    private int id_manufactureTC;
    private int export;
    private float power_energy_l;
    private float power_energy_k;


    private String new_numb;
    private Date new_numb_date;
    private List<HistoryChangeNumb> listHistoryChangeNumb = new ArrayList();

    private String from_peredacha;
    private Date date_peredacha;
    private String n_peredacha;
    private String to_peredacha;
    private List<PeredachaModel> listPeredachaModel = new ArrayList();

    private Date expl_date1;
    private Date expl_date2;
    private OperationalModel selectedOperational;

    private List<OperationalModel> listOperationalModel = new ArrayList();
    private List<RemontModel> listRemontModel = new ArrayList();
    private RemontModel selectedRemont;

    private List<RemontZayModel> listRemontZayModel = new ArrayList();
    private RemontZayModel selectedRemontZay;

    private List<ZakrepModel> listZakrepModel = new ArrayList();
    private ZakrepModel selectedZakrep;




    public AvtoModel(int id, int id_region, int id_depart, int id_mark, String model, String numb_1, String m_year) {
        this.id = id;
        this.id_region = id_region;
        this.id_depart = id_depart;
        this.id_mark = id_mark;
        this.model = model;
        this.numb_1 = numb_1;
        this.m_year = m_year;
    }

    public AvtoModel(int id, int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, String model, String numb_1, String numb_2, String m_yaer, String color, String vin, String n_engine, String n_body, String n_chassis, String n_pts, int id_source_fin, int id_source_bay, int id_type_body, int id_tech, int id_group, String radio, String insur, String comm, Date in_account, String in_pr, boolean out_state_ch, Date out_state, String out_state_pr, boolean out_account_ch, Date out_account, String out_account_pr, boolean out_life_ch, Date out_life, String out_life_pr, Date date_insur, Date date_texpasport, String inv_b, String inv_t, String plase_sto_address, String plase_sto_prikaz, Date plase_sto_date, String sgu, boolean out_state_perez, boolean out_state_out, int id_mark_gsm, float procent_gsm, String old_number, String power_energy, int id_insure_company, int id_base_norm, float norm_probeg, int id_typetc, Date arenda_d1, Date arenda_d2, int expl_year, boolean notAtx, String out_state_osnovanie, boolean out_state_expl, int out_state_state, String modelDVC, String volumeDVC, int id_typeDVC, int id_manufactureTC, int export, float power_energy_l, float power_energy_k) {
        this.id = id;
        this.id_region = id_region;
        this.id_division = id_division;
        this.id_depart = id_depart;
        this.id_preDepart = id_preDepart;
        this.id_serv = id_serv;
        this.id_PreServ = id_PreServ;
        this.id_dislocation = id_dislocation;
        this.id_mark = id_mark;
        this.model = model;
        this.numb_1 = numb_1;
        this.numb_2 = numb_2;
        this.m_year = m_yaer;
        this.color = color;
        this.vin = vin;
        this.n_engine = n_engine;
        this.n_body = n_body;
        this.n_chassis = n_chassis;
        this.n_pts = n_pts;
        this.id_source_fin = id_source_fin;
        this.id_source_bay = id_source_bay;
        this.id_type_body = id_type_body;
        this.id_tech = id_tech;
        this.id_group = id_group;
        this.radio = radio;
        this.insur = insur;
        this.comm = comm;
        this.in_account = in_account;
        this.in_pr = in_pr;
        this.out_state_ch = out_state_ch;
        this.out_state = out_state;
        this.out_state_pr = out_state_pr;
        this.out_account_ch = out_account_ch;
        this.out_account = out_account;
        this.out_account_pr = out_account_pr;
        this.out_life_ch = out_life_ch;
        this.out_life = out_life;
        this.out_life_pr = out_life_pr;
        this.date_insur = date_insur;
        this.date_texpasport = date_texpasport;
        this.inv_b = inv_b;
        this.inv_t = inv_t;
        this.plase_sto_address = plase_sto_address;
        this.plase_sto_prikaz = plase_sto_prikaz;
        this.plase_sto_date = plase_sto_date;
        this.sgu = sgu;
        this.out_state_perez = out_state_perez;
        this.out_state_out = out_state_out;
        this.id_mark_gsm = id_mark_gsm;
        this.procent_gsm = procent_gsm;
        this.old_number = old_number;
        this.power_energy = power_energy;
        this.id_insure_company = id_insure_company;
        this.id_base_norm = id_base_norm;
        this.norm_probeg = norm_probeg;
        this.id_typetc = id_typetc;
        this.arenda_d1 = arenda_d1;
        this.arenda_d2 = arenda_d2;
        this.expl_year = expl_year;
        this.notAtx = notAtx;
        this.out_state_osnovanie = out_state_osnovanie;
        this.out_state_expl = out_state_expl;
        this.out_state_state = out_state_state;
        this.modelDVC = modelDVC;
        this.volumeDVC = volumeDVC;
        this.id_typeDVC = id_typeDVC;
        this.id_manufactureTC = id_manufactureTC;
        this.export = export;
        this.power_energy_l = power_energy_l;
        this.power_energy_k = power_energy_k;
        Calendar calen = Calendar.getInstance();
        calen.set(Calendar.MONTH, 11);
        calen.set(Calendar.DAY_OF_MONTH, 31);
        this.expl_date2 = calen.getTime();
        Calendar calen2 = (Calendar)calen.clone();
        calen2.add(Calendar.YEAR,-1);
        calen2.set(Calendar.DAY_OF_YEAR, 1);
        this.expl_date1 = calen2.getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public int getId_type_body() {
        return id_type_body;
    }

    public void setId_type_body(int id_type_body) {
        this.id_type_body = id_type_body;
    }

    public int getId_tech() {
        return id_tech;
    }

    public void setId_tech(int id_tech) {
        this.id_tech = id_tech;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getInsur() {
        return insur;
    }

    public void setInsur(String insur) {
        this.insur = insur;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public Date getIn_account() {
        return in_account;
    }

    public void setIn_account(Date in_account) {
        this.in_account = in_account;
    }

    public String getIn_pr() {
        return in_pr;
    }

    public void setIn_pr(String in_pr) {
        this.in_pr = in_pr;
    }

    public boolean isOut_state_ch() {
        return out_state_ch;
    }

    public void setOut_state_ch(boolean out_state_ch) {
        this.out_state_ch = out_state_ch;
    }

    public Date getOut_state() {
        return out_state;
    }

    public void setOut_state(Date out_state) {
        this.out_state = out_state;
    }

    public String getOut_state_pr() {
        return out_state_pr;
    }

    public void setOut_state_pr(String out_state_pr) {
        this.out_state_pr = out_state_pr;
    }

    public boolean isOut_account_ch() {
        return out_account_ch;
    }

    public void setOut_account_ch(boolean out_account_ch) {
        this.out_account_ch = out_account_ch;
    }

    public Date getOut_account() {
        return out_account;
    }

    public void setOut_account(Date out_account) {
        this.out_account = out_account;
    }

    public String getOut_account_pr() {
        return out_account_pr;
    }

    public void setOut_account_pr(String out_account_pr) {
        this.out_account_pr = out_account_pr;
    }

    public boolean isOut_life_ch() {
        return out_life_ch;
    }

    public void setOut_life_ch(boolean out_life_ch) {
        this.out_life_ch = out_life_ch;
    }

    public Date getOut_life() {
        return out_life;
    }

    public void setOut_life(Date out_life) {
        this.out_life = out_life;
    }

    public String getOut_life_pr() {
        return out_life_pr;
    }

    public void setOut_life_pr(String out_life_pr) {
        this.out_life_pr = out_life_pr;
    }

    public Date getDate_insur() {
        return date_insur;
    }

    public void setDate_insur(Date date_insur) {
        this.date_insur = date_insur;
    }

    public Date getDate_texpasport() {
        return date_texpasport;
    }

    public void setDate_texpasport(Date date_texpasport) {
        this.date_texpasport = date_texpasport;
    }

    public String getInv_b() {
        return inv_b;
    }

    public void setInv_b(String inv_b) {
        this.inv_b = inv_b;
    }

    public String getInv_t() {
        return inv_t;
    }

    public void setInv_t(String inv_t) {
        this.inv_t = inv_t;
    }

    public String getPlase_sto_address() {
        return plase_sto_address;
    }

    public void setPlase_sto_address(String plase_sto_address) {
        this.plase_sto_address = plase_sto_address;
    }

    public String getPlase_sto_prikaz() {
        return plase_sto_prikaz;
    }

    public void setPlase_sto_prikaz(String plase_sto_prikaz) {
        this.plase_sto_prikaz = plase_sto_prikaz;
    }

    public Date getPlase_sto_date() {
        return plase_sto_date;
    }

    public void setPlase_sto_date(Date plase_sto_date) {
        this.plase_sto_date = plase_sto_date;
    }

    public String getSgu() {
        return sgu;
    }

    public void setSgu(String sgu) {
        this.sgu = sgu;
    }

    public boolean isOut_state_perez() {
        return out_state_perez;
    }

    public void setOut_state_perez(boolean out_state_perez) {
        this.out_state_perez = out_state_perez;
    }

    public boolean isOut_state_out() {
        return out_state_out;
    }

    public void setOut_state_out(boolean out_state_out) {
        this.out_state_out = out_state_out;
    }

    public int getId_mark_gsm() {
        return id_mark_gsm;
    }

    public void setId_mark_gsm(int id_mark_gsm) {
        this.id_mark_gsm = id_mark_gsm;
    }

    public float getProcent_gsm() {
        return procent_gsm;
    }

    public void setProcent_gsm(float procent_gsm) {
        this.procent_gsm = procent_gsm;
    }

    public String getOld_number() {
        return old_number;
    }

    public void setOld_number(String old_number) {
        this.old_number = old_number;
    }

    public String getPower_energy() {
        return power_energy;
    }

    public void setPower_energy(String power_energy) {
        this.power_energy = power_energy;
    }

    public int getId_insure_company() {
        return id_insure_company;
    }

    public void setId_insure_company(int id_insure_company) {
        this.id_insure_company = id_insure_company;
    }

    public int getId_base_norm() {
        return id_base_norm;
    }

    public void setId_base_norm(int id_base_norm) {
        this.id_base_norm = id_base_norm;
    }

    public float getNorm_probeg() {
        return norm_probeg;
    }

    public void setNorm_probeg(float norm_probeg) {
        this.norm_probeg = norm_probeg;
    }

    public int getId_typetc() {
        return id_typetc;
    }

    public void setId_typetc(int id_typetc) {
        this.id_typetc = id_typetc;
    }

    public Date getArenda_d1() {
        return arenda_d1;
    }

    public void setArenda_d1(Date arenda_d1) {
        this.arenda_d1 = arenda_d1;
    }

    public Date getArenda_d2() {
        return arenda_d2;
    }

    public void setArenda_d2(Date arenda_d2) {
        this.arenda_d2 = arenda_d2;
    }

    public int getExpl_year() {
        return expl_year;
    }

    public void setExpl_year(int expl_year) {
        this.expl_year = expl_year;
    }

    public boolean isNotAtx() {
        return notAtx;
    }

    public void setNotAtx(boolean notAtx) {
        this.notAtx = notAtx;
    }

    public String getOut_state_osnovanie() {
        return out_state_osnovanie;
    }

    public void setOut_state_osnovanie(String out_state_osnovanie) {
        this.out_state_osnovanie = out_state_osnovanie;
    }

    public boolean isOut_state_expl() {
        return out_state_expl;
    }

    public void setOut_state_expl(boolean out_state_expl) {
        this.out_state_expl = out_state_expl;
    }

    public int getOut_state_state() {
        return out_state_state;
    }

    public void setOut_state_state(int out_state_state) {
        this.out_state_state = out_state_state;
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

    public int getId_typeDVC() {
        return id_typeDVC;
    }

    public void setId_typeDVC(int id_typeDVC) {
        this.id_typeDVC = id_typeDVC;
    }

    public int getId_manufactureTC() {
        return id_manufactureTC;
    }

    public void setId_manufactureTC(int id_manufactureTC) {
        this.id_manufactureTC = id_manufactureTC;
    }

    public int getExport() {
        return export;
    }

    public void setExport(int export) {
        this.export = export;
    }

    public float getPower_energy_l() {
        return power_energy_l;
    }

    public void setPower_energy_l(float power_energy_l) {
        this.power_energy_l = power_energy_l;
    }

    public float getPower_energy_k() {
        return power_energy_k;
    }

    public void setPower_energy_k(float power_energy_k) {
        this.power_energy_k = power_energy_k;
    }

    public String getNew_numb() {
        return new_numb;
    }

    public void setNew_numb(String new_numb) {
        this.new_numb = new_numb;
    }

    public Date getNew_numb_date() {
        return new_numb_date;
    }

    public void setNew_numb_date(Date new_numb_date) {
        this.new_numb_date = new_numb_date;
    }

    public List<HistoryChangeNumb> getListHistoryChangeNumb() {
        return listHistoryChangeNumb;
    }

    public void setListHistoryChangeNumb(List<HistoryChangeNumb> listHistoryChangeNumb) {
        this.listHistoryChangeNumb = listHistoryChangeNumb;
    }

    public String getFrom_peredacha() {
        return from_peredacha;
    }

    public void setFrom_peredacha(String from_peredacha) {
        this.from_peredacha = from_peredacha;
    }

    public Date getDate_peredacha() {
        return date_peredacha;
    }

    public void setDate_peredacha(Date date_peredacha) {
        this.date_peredacha = date_peredacha;
    }

    public String getN_peredacha() {
        return n_peredacha;
    }

    public void setN_peredacha(String n_peredacha) {
        this.n_peredacha = n_peredacha;
    }

    public String getTo_peredacha() {
        return to_peredacha;
    }

    public void setTo_peredacha(String to_peredacha) {
        this.to_peredacha = to_peredacha;
    }

    public List<PeredachaModel> getListPeredachaModel() {
        return listPeredachaModel;
    }

    public void setListPeredachaModel(List<PeredachaModel> listPeredachaModel) {
        this.listPeredachaModel = listPeredachaModel;
    }

    public Date getExpl_date1() {
        return expl_date1;
    }

    public void setExpl_date1(Date expl_date1) {
        this.expl_date1 = expl_date1;
    }

    public Date getExpl_date2() {
        return expl_date2;
    }

    public void setExpl_date2(Date expl_date2) {
        this.expl_date2 = expl_date2;
    }

    public List<OperationalModel> getListOperationalModel() {
        return listOperationalModel;
    }

    public void setListOperationalModel(List<OperationalModel> listOperationalModel) {
        this.listOperationalModel = listOperationalModel;
    }

    public OperationalModel getSelectedOperational() {
        return selectedOperational;
    }

    public void setSelectedOperational(OperationalModel selectedOperational) {
        this.selectedOperational = selectedOperational;
    }

    public List<RemontModel> getListRemontModel() {
        return listRemontModel;
    }

    public void setListRemontModel(List<RemontModel> listRemontModel) {
        this.listRemontModel = listRemontModel;
    }

    public RemontModel getSelectedRemont() {
        return selectedRemont;
    }

    public void setSelectedRemont(RemontModel selectedRemont) {
        this.selectedRemont = selectedRemont;
    }

    public List<RemontZayModel> getListRemontZayModel() {
        return listRemontZayModel;
    }

    public void setListRemontZayModel(List<RemontZayModel> listRemontZayModel) {
        this.listRemontZayModel = listRemontZayModel;
    }

    public RemontZayModel getSelectedRemontZay() {
        return selectedRemontZay;
    }

    public void setSelectedRemontZay(RemontZayModel selectedRemontZay) {
        this.selectedRemontZay = selectedRemontZay;
    }

    public List<ZakrepModel> getListZakrepModel() {
        return listZakrepModel;
    }

    public void setListZakrepModel(List<ZakrepModel> listZakrepModel) {
        this.listZakrepModel = listZakrepModel;
    }

    public ZakrepModel getSelectedZakrep() {
        return selectedZakrep;
    }

    public void setSelectedZakrep(ZakrepModel selectedZakrep) {
        this.selectedZakrep = selectedZakrep;
    }
}
