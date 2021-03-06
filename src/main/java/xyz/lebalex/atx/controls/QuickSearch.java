package xyz.lebalex.atx.controls;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.AtxLogin;
import xyz.lebalex.atx.consts.Roles;
import xyz.lebalex.atx.consts.Txt;
import xyz.lebalex.atx.models.*;
import xyz.lebalex.atx.prints.ZakrepWord;
import xyz.lebalex.atx.prints.ZayvkaWord;
import xyz.lebalex.atx.utils.DateHelper;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.sql.Date;
import java.util.*;


@Named
@ViewScoped
public class QuickSearch extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(QuickSearch.class);
    private String str_find;
    private List<AvtoModel> listAvto = new ArrayList();
    private AvtoModel selectedAvto;
    private List<ModelInterface> listMarkAvtModel = new ArrayList();
    private List<ModelInterface> listRegionModel = new ArrayList();
    private List<ModelInterface> listDepartModel = new ArrayList();

    private List<ModelInterface> listDivisionModel = new ArrayList();
    private List<ModelInterface> listPreDepartModel = new ArrayList();
    private List<ModelInterface> listServModel = new ArrayList();
    private List<ModelInterface> listDislocationModel = new ArrayList();
    private List<ModelInterface> listPreServModel = new ArrayList();
    private List<ModelInterface> listSourceModel = new ArrayList();
    private List<ModelAvtModel> listModelAvtModel = new ArrayList();
    private List<ModelInterface> listTypeDVCModel = new ArrayList();
    private List<ModelInterface> listManufactureTCModel = new ArrayList();

    private List<ModelInterface> listTypeBodyModel = new ArrayList();
    private List<ModelInterface> listTechModel = new ArrayList();
    private List<ModelInterface> listGroupModel = new ArrayList();
    private List<ModelInterface> listTypeTCModel = new ArrayList();

    private List<ModelInterface> listOutStateModel = new ArrayList();
    private List<ModelInterface> listInsureCompanyModel = new ArrayList();

    private List<ModelInterface> listMarkGsmModel = new ArrayList();
    private List<BaseNormModel> listBaseNormModel = new ArrayList();

    private List<IntervalModel> listIntervalModel = new ArrayList();
    private List<ModelInterface> listTypeOilModel = new ArrayList();
    private List<ModelInterface> listTypeFreezModel = new ArrayList();

    private List<String> listYear = new ArrayList();

    private List<ModelInterface> listVidRemontModel = new ArrayList();
    private List<ModelInterface> listPlaceRemontModel = new ArrayList();

    private List<ModelInterface> listDislokModel = new ArrayList();


    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;

    public QuickSearch() {
    }
    @PostConstruct
    public void initialize() {
        if(atxLogin.getUsername()!=null) {
            listModelAvtModel.clear();
            listBaseNormModel.clear();
            getLybr("ATX_UVD.dbo.tRegion", listRegionModel, "RegionModel");
            getLybr("ATX_UVD.dbo.tMark_avt", listMarkAvtModel, "MarkAvtModel");
            getLybr("ATX_UVD.dbo.tDepart", listDepartModel, "DepartModel");

            getLybr("ATX_UVD.dbo.tDivision", listDivisionModel, "DivisionModel");
            getLybr("ATX_UVD.dbo.tPre_Depart", listPreDepartModel, "PreDepartModel");
            getLybr("ATX_UVD.dbo.tServices", listServModel, "ServModel");
            getLybr("ATX_UVD.dbo.tDislokation", listDislocationModel, "DislocationModel");
            getLybr("ATX_UVD.dbo.tPre_Services", listPreServModel, "PreServModel");
            getLybr("ATX_UVD.dbo.tSource_fin", listSourceModel, "SourceModel");
            getLybr("ATX_UVD.dbo.tTypeDVC", listTypeDVCModel, "TypeDVCModel");
            getLybr("ATX_UVD.dbo.tManufactureTC", listManufactureTCModel, "ManufactureTCModel");

            getLybr("ATX_UVD.dbo.tType_body", listTypeBodyModel, "TypeBodyModel");
            getLybr("ATX_UVD.dbo.tTech", listTechModel, "TechModel");
            getLybr("ATX_UVD.dbo.tGroup_used", listGroupModel, "GroupModel");
            getLybr("ATX_UVD.dbo.tTypeTC", listTypeTCModel, "TypeTCModel");

            getLybr("ATX_UVD.dbo.tOut_state", listOutStateModel, "OutStateModel");
            getLybr("ATX_UVD.dbo.tInsureCompany", listInsureCompanyModel, "InsureCompanyModel");
            getLybr("ATX_UVD.dbo.tMarkGsm", listMarkGsmModel, "MarkGsmModel");
            getLybr("ATX_UVD.dbo.tTypeOil", listTypeOilModel, "TypeOilModel");
            getLybr("ATX_UVD.dbo.tTypeFreez", listTypeFreezModel, "TypeFreezModel");

            getLybr("ATX_UVD.dbo.tVidRemont", listVidRemontModel, "VidRemontModel");
            getLybr("ATX_UVD.dbo.tPlaceRemont", listPlaceRemontModel, "PlaceRemontModel");


            listIntervalModel.clear();
            listIntervalModel.add(new IntervalModel(1, "Январь"));
            listIntervalModel.add(new IntervalModel(2, "Февраль"));
            listIntervalModel.add(new IntervalModel(3, "Март"));
            listIntervalModel.add(new IntervalModel(4, "Апрель"));
            listIntervalModel.add(new IntervalModel(5, "Май"));
            listIntervalModel.add(new IntervalModel(6, "Июнь"));
            listIntervalModel.add(new IntervalModel(7, "Июль"));
            listIntervalModel.add(new IntervalModel(8, "Август"));
            listIntervalModel.add(new IntervalModel(9, "Сентябрь"));
            listIntervalModel.add(new IntervalModel(10, "Октябрь"));
            listIntervalModel.add(new IntervalModel(11, "Ноябрь"));
            listIntervalModel.add(new IntervalModel(12, "Декабрь"));
            listIntervalModel.add(new IntervalModel(111, "I   квартал"));
            listIntervalModel.add(new IntervalModel(222, "II  квартал"));
            listIntervalModel.add(new IntervalModel(333, "III квартал"));
            listIntervalModel.add(new IntervalModel(444, "IV  квартал"));

            listYear.clear();
            Calendar c = Calendar.getInstance();
            for (int i = 0; i <= c.get(Calendar.YEAR) - 1999; i++)
                listYear.add(Integer.toString(2000 + i));

        }
    }

    private void getLybr(String table, List<ModelInterface> listModel, String type) {
        try {
            try (Connection con = getConnection()) {
                try (Statement st = con.createStatement()) {
                    try (ResultSet rs = st.executeQuery("select * from " + table)) {
                        listModel.clear();
                        while (rs.next()) {
                            ModelInterface e = null;
                            if (type.equalsIgnoreCase("RegionModel"))
                                e = new RegionModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("MarkAvtModel"))
                                e = new MarkAvtModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("DepartModel"))
                                e = new DepartModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("DivisionModel"))
                                e = new DivisionModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("PreDepartModel"))
                                e = new PreDepartModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("ServModel"))
                                e = new ServModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("DislocationModel"))
                                e = new DislocationModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("PreServModel"))
                                e = new PreServModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("SourceModel"))
                                e = new SourceModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("TypeDVCModel"))
                                e = new TypeDVCModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("ManufactureTCModel"))
                                e = new ManufactureTCModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("TypeBodyModel"))
                                e = new TypeBodyModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("TechModel"))
                                e = new TechModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("GroupModel"))
                                e = new GroupModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("TypeTCModel"))
                                e = new TypeTCModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("OutStateModel"))
                                e = new OutStateModel(rs.getInt("id_out_state"), rs.getString("out_state"));
                            else if (type.equalsIgnoreCase("InsureCompanyModel"))
                                e = new InsureCompanyModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("MarkGsmModel"))
                                e = new MarkGsmModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("TypeOilModel"))
                                e = new TypeOilModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("TypeFreezModel"))
                                e = new TypeFreezModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("VidRemontModel"))
                                e = new VidRemontModel(rs.getInt("id"), rs.getString("name"));
                            else if (type.equalsIgnoreCase("PlaceRemontModel"))
                                e = new PlaceRemontModel(rs.getInt("id"), rs.getString("name"));


                            listModel.add(e);
                        }
                        //logger.log(Level.INFO, listModel);
                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, table);
            logger.log(Level.ERROR, ex);
        }
    }

    private void getLybrModelAvt(int id_mark) {
        listModelAvtModel.clear();
        try {
            try (Connection con = getConnection()) {
                try (Statement st = con.createStatement()) {
                    try (ResultSet rs = st.executeQuery("select * from ATX_UVD.dbo.tModel_avt where id_mark=" + id_mark)) {
                        while (rs.next()) {
                            listModelAvtModel.add(new ModelAvtModel(rs.getInt("id"), rs.getInt("id_mark"), rs.getString("name"), rs.getInt("expl_year")));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    private void getLybrModelBaseNorm(int id_mark, String model, int id_gsm) {
        listBaseNormModel.clear();
        /*logger.log(Level.ERROR, id_mark);
        logger.log(Level.ERROR, model);
        logger.log(Level.ERROR, id_gsm);*/
        try {
            try (Connection con = getConnection()) {
                try (PreparedStatement prst = con.prepareStatement("select a.id, a.id_mark, a.id_model, a.id_gsm, a.norm from ATX_UVD.dbo.tBaseNormGsm a inner join" +
                        " ATX_UVD.dbo.tModel_avt b on a.id_model=b.id where a.id_mark=? and b.name=? and a.id_gsm=?")) {
                    prst.setInt(1, id_mark);
                    prst.setString(2, model);
                    prst.setInt(3, id_gsm);
                    try (ResultSet rs = prst.executeQuery()) {
                        while (rs.next()) {
                            listBaseNormModel.add(new BaseNormModel(rs.getInt("id"), rs.getInt("id_mark"), rs.getInt("id_model"), rs.getInt("id_gsm"), rs.getFloat("norm")));
                        }
                        /*logger.log(Level.ERROR, listBaseNormModel);*/
                    }
                }

            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }


    public String getFromLyb(int id, String type) {
        if (type.equalsIgnoreCase("MarkAvtModel")) return getFromLybName(id, listMarkAvtModel);
        else if (type.equalsIgnoreCase("RegionModel")) return getFromLybName(id, listRegionModel);
        else if (type.equalsIgnoreCase("DepartModel")) return getFromLybName(id, listDepartModel);
        else if (type.equalsIgnoreCase("GroupModel")) return getFromLybName(id, listGroupModel);
        else if (type.equalsIgnoreCase("ServModel")) return getFromLybName(id, listServModel);

        else if (type.equalsIgnoreCase("DivisModel")) return getFromLybName(id, listDivisionModel);
        else if (type.equalsIgnoreCase("PreDepartModel")) return getFromLybName(id, listPreDepartModel);
        else if (type.equalsIgnoreCase("PreServModel")) return getFromLybName(id, listPreServModel);
        else if (type.equalsIgnoreCase("DislocationvModel")) return getFromLybName(id, listDislocationModel);

        else if (type.equalsIgnoreCase("TypeBodyModel")) return getFromLybName(id, listTypeBodyModel);
        else if (type.equalsIgnoreCase("TechModel")) return getFromLybName(id, listTechModel);

        else if (type.equalsIgnoreCase("SourceModel")) return getFromLybName(id, listSourceModel);
        else if (type.equalsIgnoreCase("InsureCompanyModel")) return getFromLybName(id, listInsureCompanyModel);

        else return "";
    }

    public String getModelName(int id) {
        for (ModelAvtModel a : listModelAvtModel) {
            if (a.getId() == id) {
                return a.getName().trim();
            }
        }
        return "";
    }

    private String getFromLybName(int id, List<ModelInterface> list) {
        for (ModelInterface a : list) {
            if (a.getId() == id) {
                return a.getName();
            }
        }
        return "";
    }


    public List<ModelInterface> getListMarkAvtModel() {
        return listMarkAvtModel;
    }

    public List<ModelInterface> getListRegionModel() {
        return listRegionModel;
    }

    public List<ModelInterface> getListDepartModel() {
        return listDepartModel;
    }

    public List<ModelInterface> getListDivisionModel() {
        return listDivisionModel;
    }

    public List<ModelInterface> getListPreDepartModel() {
        return listPreDepartModel;
    }

    public List<ModelInterface> getListServModel() {
        return listServModel;
    }

    public List<ModelInterface> getListDislocationModel() {
        return listDislocationModel;
    }

    public List<ModelInterface> getListPreServModel() {
        return listPreServModel;
    }

    public List<ModelInterface> getListSourceModel() {
        return listSourceModel;
    }

    public void handleChange() {
        listModelAvtModel.clear();
        selectedAvto.setModel("");
        getLybrModelAvt(selectedAvto.getId_mark());

    }

    public void handleMarkSearchChange(int id_mark) {
        listModelAvtModel.clear();
        getLybrModelAvt(id_mark);

    }

    public void handleChangeMarkGsm() {
        /*logger.log(Level.ERROR, selectedAvto.getId_mark());
        logger.log(Level.ERROR, selectedAvto.getModel());
        logger.log(Level.ERROR, selectedAvto.getId_mark_gsm());*/
        getLybrModelBaseNorm(selectedAvto.getId_mark(), selectedAvto.getModel(), selectedAvto.getId_mark_gsm());

    }


    public List<ModelInterface> getListTypeDVCModel() {
        return listTypeDVCModel;
    }

    public List<ModelInterface> getListManufactureTCModel() {
        return listManufactureTCModel;
    }

    public List<ModelAvtModel> getListModelAvtModel() {
        //getLybrModelAvt(selectedAvto.getId_mark());
        return listModelAvtModel;
    }

    public List<ModelInterface> getListTypeBodyModel() {
        return listTypeBodyModel;
    }

    public List<ModelInterface> getListTechModel() {
        return listTechModel;
    }

    public List<ModelInterface> getListGroupModel() {
        return listGroupModel;
    }

    public List<ModelInterface> getListTypeTCModel() {
        return listTypeTCModel;
    }

    public List<ModelInterface> getListOutStateModel() {
        return listOutStateModel;
    }

    public List<ModelInterface> getListInsureCompanyModel() {
        return listInsureCompanyModel;
    }

    public List<ModelInterface> getListMarkGsmModel() {
        return listMarkGsmModel;
    }

    public List<BaseNormModel> getListBaseNormModel() {
        return listBaseNormModel;
    }

    public List<String> getListYear() {
        return listYear;
    }

    public List<ModelInterface> getListTypeOilModel() {
        return listTypeOilModel;
    }

    public List<ModelInterface> getListTypeFreezModel() {
        return listTypeFreezModel;
    }

    public List<IntervalModel> getListIntervalModel() {
        return listIntervalModel;
    }

    public List<ModelInterface> getListVidRemontModel() {
        return listVidRemontModel;
    }

    public List<ModelInterface> getListPlaceRemontModel() {
        return listPlaceRemontModel;
    }

    public String getStr_find() {
        return str_find;
    }

    public void setStr_find(String str_find) {
        this.str_find = str_find;
    }

    public List<AvtoModel> getListAvto() {
        return listAvto;
    }

    public AvtoModel getSelectedAvto() {
        return selectedAvto;
    }

    public void setSelectedAvto(AvtoModel selectedAvto) {
        //logger.log(Level.INFO, selectedAvto.getId());
        getAvto(selectedAvto);
        getLybrModelAvt(this.selectedAvto.getId_mark());
        getLybrModelBaseNorm(this.selectedAvto.getId_mark(), this.selectedAvto.getModel(), this.selectedAvto.getId_mark_gsm());
        /*загрузим историю замены гос номера*/
        getHistoryChangeNumb(this.selectedAvto.getId());
        getPeredacha(this.selectedAvto.getId());
    }

    public void find(ActionEvent e) {
        try {
            //logger.log(Level.INFO, str_find);
            if (!str_find.isEmpty() && str_find.length() > 2) {
                try (Connection con = getConnection()) {
                    try (Statement st = con.createStatement()) {
                        try (ResultSet rs = st.executeQuery("select * from ATX_UVD.dbo.tAvto where numb_1 like '%" + str_find + "%'")) {
                            listAvto.clear();
                            while (rs.next()) {
                                listAvto.add(new AvtoModel(rs.getInt("id"), rs.getInt("id_region"), rs.getInt("id_depart"),
                                        rs.getInt("id_mark"),
                                        rs.getString("model"), rs.getString("numb_1"), rs.getString("m_year")));

                                /*listAvto.add(new AvtoModel(rs.getInt("id"), rs.getInt("id_region"), rs.getInt("id_division"), rs.getInt("id_depart"), rs.getInt("id_predepart"),
                                        rs.getInt("id_serv"), rs.getInt("id_preserv"), rs.getInt("id_dislok"), rs.getInt("id_mark"),
                                        rs.getString("model"), rs.getString("numb_1"), rs.getString("numb_2"), rs.getString("m_year"), rs.getString("color"), rs.getString("vin"),
                                        rs.getString("n_engine"), rs.getString("n_body"), rs.getString("n_chassis"),
                                        rs.getString("n_pts"), rs.getInt("id_source_fin"), rs.getInt("id_source_bay"), rs.getInt("id_type_body"), rs.getInt("id_tech"),
                                        rs.getInt("id_group"), rs.getString("radio"), rs.getString("insur"), rs.getString("comm"),
                                        rs.getDate("in_account"), rs.getString("in_pr"), rs.getBoolean("out_state_ch"), rs.getDate("out_state"), rs.getString("out_state_pr"),
                                        rs.getBoolean("out_account_ch"), rs.getDate("out_account"), rs.getString("out_account_pr"), rs.getBoolean("out_life_ch"), rs.getDate("out_life"), rs.getString("out_life_pr"),
                                rs.getDate("date_insur"), rs.getDate("date_texpasport"), rs.getString("inv_b"), rs.getString("inv_t"), rs.getString("plase_sto_address"), rs.getString("plase_sto_prikaz"),
                                        rs.getDate("plase_sto_date"), rs.getString("sgu"), rs.getBoolean("out_state_perez"), rs.getBoolean("out_state_out"), rs.getInt("id_mark_gsm"),
                                rs.getFloat("procent_gsm"), rs.getString("old_number"), rs.getString("power_energy"), rs.getInt("id_insure_company"), rs.getInt("id_base_norm"),
                                        rs.getFloat("norm_probeg"), rs.getInt("id_typetc"), rs.getDate("arenda_d1"), rs.getDate("arenda_d2"), rs.getInt("expl_year"), rs.getBoolean("notAtx"),
                                rs.getString("out_state_osnovanie"), rs.getBoolean("out_state_expl"), rs.getInt("out_state_state"),
                                rs.getString("modelDVC"), rs.getString("volumeDVC"), rs.getInt("id_typeDVC"), rs.getInt("id_manufactureTC"), rs.getInt("export"),
                                        rs.getFloat("power_energy_l"), rs.getFloat("power_energy_k")));*/


                            }
                            //logger.log(Level.INFO, listAvto);
                            managerPages.setPage("listavto.xhtml");
                            //FacesContext.getCurrentInstance().getExternalContext().redirect("listavto.jsf");

                        }
                    }
                }
            } else {
                viewMessage("Ошибка", "Укажите не менее 3 знаков!");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }


    }

    public void findFull(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                         int id_source_fin, int id_group,
                         String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur) {
        //logger.log(Level.INFO, modelAvt.replaceAll(" ", "%"));
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetListAvto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
                    cStmt.setInt(1, id_region);
                    cStmt.setInt(2, id_division);
                    cStmt.setInt(3, id_depart);
                    cStmt.setInt(4, id_preDepart);
                    cStmt.setInt(5, id_serv);
                    cStmt.setInt(6, id_mark);
                    cStmt.setInt(7, id_source_bay);
                    cStmt.setInt(8, id_source_fin);
                    cStmt.setInt(9, id_group);
                    cStmt.setString(10, modelAvt.replaceAll(" ", "%"));
                    cStmt.setString(11, numb_1);
                    cStmt.setString(12, numb_2);
                    cStmt.setString(13, "-1");//oldnumb_p;
                    cStmt.setString(14, m_year1);
                    cStmt.setString(15, m_year2);
                    cStmt.setString(16, vin);
                    cStmt.setString(17, n_engine);
                    cStmt.setString(18, n_body);
                    cStmt.setString(19, n_chassis);
                    cStmt.setString(20, n_pts);
                    cStmt.setString(21, "-1");//InAc1_p;
                    cStmt.setString(22, "-1");//InAc2_p;
                    cStmt.setInt(23, -1);//State_p;
                    cStmt.setInt(24, -1);//UotAc_p;
                    cStmt.setInt(25, -1);//life_p;
                    cStmt.setString(26, "-1");//OutD1;
                    cStmt.setString(27, "-1");//OutD2;
                    cStmt.setInt(28, 0);//id;
                    cStmt.setString(29, insur);
                    cStmt.setString(30, "-1");//D_insur;
                    cStmt.setInt(31, id_dislocation);
                    cStmt.setInt(32, id_PreServ);
                    cStmt.setInt(33, -1);//notATX_p;

                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listAvto.clear();
                            while (rs.next()) {
                                listAvto.add(new AvtoModel(rs.getInt("id"), rs.getInt("id_region"), rs.getInt("id_depart"),
                                        rs.getInt("id_mark"),
                                        rs.getString("model"), rs.getString("numb_1"), rs.getString("m_year")));
                            }
                            managerPages.setPage("listavto.xhtml");
                            //FacesContext.getCurrentInstance().getExternalContext().redirect("listavto2.jsf");
                        }
                    } else logger.log(Level.ERROR, "false");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public void getAvto(AvtoModel sAvto) {
        try {
            if (sAvto != null) {
                try (Connection con = getConnection()) {
                    try (Statement st = con.createStatement()) {
                        try (ResultSet rs = st.executeQuery("select * from ATX_UVD.dbo.tAvto where id=" + sAvto.getId())) {
                            if (rs.next()) {
                                this.selectedAvto = new AvtoModel(rs.getInt("id"), rs.getInt("id_region"), rs.getInt("id_division"), rs.getInt("id_depart"), rs.getInt("id_predepart"),
                                        rs.getInt("id_serv"), rs.getInt("id_preserv"), rs.getInt("id_dislok"), rs.getInt("id_mark"),
                                        rs.getString("model"), rs.getString("numb_1"), rs.getString("numb_2"), rs.getString("m_year"), rs.getString("color"), rs.getString("vin"),
                                        rs.getString("n_engine"), rs.getString("n_body"), rs.getString("n_chassis"),
                                        rs.getString("n_pts"), rs.getInt("id_source_fin"), rs.getInt("id_source_bay"), rs.getInt("id_type_body"), rs.getInt("id_tech"),
                                        rs.getInt("id_group"), rs.getString("radio"), rs.getString("insur"), rs.getString("comm"),
                                        rs.getDate("in_account"), rs.getString("in_pr"), rs.getBoolean("out_state_ch"), rs.getDate("out_state"), rs.getString("out_state_pr"),
                                        rs.getBoolean("out_account_ch"), rs.getDate("out_account"), rs.getString("out_account_pr"), rs.getBoolean("out_life_ch"), rs.getDate("out_life"), rs.getString("out_life_pr"),
                                        rs.getDate("date_insur"), rs.getDate("date_texpasport"), rs.getString("inv_b"), rs.getString("inv_t"), rs.getString("plase_sto_address"), rs.getString("plase_sto_prikaz"),
                                        rs.getDate("plase_sto_date"), rs.getString("sgu"), rs.getBoolean("out_state_perez"), rs.getBoolean("out_state_out"), rs.getInt("id_mark_gsm"),
                                        rs.getFloat("procent_gsm"), rs.getString("old_number"), rs.getString("power_energy"), rs.getInt("id_insure_company"), rs.getInt("id_base_norm"),
                                        rs.getFloat("norm_probeg"), rs.getInt("id_typetc"), rs.getDate("arenda_d1"), rs.getDate("arenda_d2"), rs.getInt("expl_year"), rs.getBoolean("notAtx"),
                                        rs.getString("out_state_osnovanie"), rs.getBoolean("out_state_expl"), rs.getInt("out_state_state"),
                                        rs.getString("modelDVC"), rs.getString("volumeDVC"), rs.getInt("id_typeDVC"), rs.getInt("id_manufactureTC"), rs.getInt("export"),
                                        rs.getFloat("power_energy_l"), rs.getFloat("power_energy_k"));
                            } else {
                                logger.log(Level.INFO, "rs.next() - null");
                            }
                        }
                    }
                }
            } else {
                logger.log(Level.INFO, "sAvto=null");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }


    private void getHistoryChangeNumb(int id) {
        getSelectedAvto().getListHistoryChangeNumb().clear();

        try {
            try (Connection con = getConnection()) {
                try (PreparedStatement prst = con.prepareStatement("select date, old_n, new_n from ATX_UVD.dbo.tHistoryExchange where id_avt=?")) {
                    prst.setInt(1, id);
                    try (ResultSet rs = prst.executeQuery()) {
                        while (rs.next()) {
                            getSelectedAvto().getListHistoryChangeNumb().add(new HistoryChangeNumb(rs.getDate("date"), rs.getString("old_n"), rs.getString("new_n")));
                        }
                        /*logger.log(Level.ERROR, listHistoryChangeNumb);*/
                    }
                }

            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }

    }

    private void getPeredacha(int id) {
        getSelectedAvto().getListPeredachaModel().clear();
        try {
            try (Connection con = getConnection()) {
                try (PreparedStatement prst = con.prepareStatement("select * from ATX_UVD.dbo.tPeredacha where id_avt=?")) {
                    prst.setInt(1, id);
                    try (ResultSet rs = prst.executeQuery()) {
                        while (rs.next()) {
                            getSelectedAvto().getListPeredachaModel().add(new PeredachaModel(rs.getDate("date"), rs.getString("from_edit"), rs.getString("to_edit"), rs.getString("nprok_edit")));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }

    }

    public void change_numb(ActionEvent e) {
        if (getSelectedAvto() != null) {
            if (atxLogin.isSave(Roles.TECH_EDIT_AVTO, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                if (validMaskNumb(getSelectedAvto().getNew_numb()) && getSelectedAvto().getNew_numb_date() != null) {

                    try {
                        try (Connection con = getConnection()) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.ExchengeNumbr(?, ?,?,?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getId());
                                cStmt.setDate(2, new java.sql.Date(getSelectedAvto().getNew_numb_date().getTime()));
                                cStmt.setString(3, getSelectedAvto().getNumb_1());
                                cStmt.setString(4, getSelectedAvto().getNew_numb());
                                int hadResults = cStmt.executeUpdate();
                                FacesContext context = FacesContext.getCurrentInstance();
                                if (hadResults > 0) {
                                    try (PreparedStatement prst = con.prepareStatement("update ATX_UVD.dbo.tAvto set numb_1=?, old_number=? where id=?")) {
                                        prst.setString(1, getSelectedAvto().getNew_numb());
                                        prst.setString(2, getSelectedAvto().getNumb_1());
                                        prst.setInt(3, getSelectedAvto().getId());
                                        prst.executeUpdate();
                                    }
                                    context.addMessage(null, new FacesMessage("Успешно", "гос № заменен"));
                                    getSelectedAvto().getListHistoryChangeNumb().add(new HistoryChangeNumb(getSelectedAvto().getNew_numb_date(), getSelectedAvto().getNumb_1(), getSelectedAvto().getNew_numb()));
                                } else
                                    context.addMessage(null, new FacesMessage("Ошибка", "Что-то пошло не так"));
                            }

                        }
                    } catch (Exception ex) {
                        logger.log(Level.ERROR, ex);
                    }

                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Ошибка", "Не правильно заполнен гос № или дата"));
                }
                getSelectedAvto().setNew_numb("");
            } else
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
        } else {
            logger.log(Level.INFO, this.selectedAvto);
        }
    }

    public void change_peredacha(ActionEvent e) {
        if (getSelectedAvto() != null) {
            if (atxLogin.isSave(Roles.TECH_EDIT_AVTO, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                if (!getSelectedAvto().getFrom_peredacha().isEmpty() && !getSelectedAvto().getTo_peredacha().isEmpty() && getSelectedAvto().getDate_peredacha() != null) {

                    try {
                        try (Connection con = getConnection()) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.PeredachaAvt(?, ?,?,?,?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getId());
                                cStmt.setDate(2, new java.sql.Date(getSelectedAvto().getDate_peredacha().getTime()));
                                cStmt.setString(3, getSelectedAvto().getFrom_peredacha());
                                cStmt.setString(4, getSelectedAvto().getTo_peredacha());
                                cStmt.setString(5, getSelectedAvto().getN_peredacha());
                                int hadResults = cStmt.executeUpdate();
                                FacesContext context = FacesContext.getCurrentInstance();
                                if (hadResults > 0) {
                                    context.addMessage(null, new FacesMessage("Успешно", "Данные сохранены"));
                                    getSelectedAvto().getListPeredachaModel().add(new PeredachaModel(getSelectedAvto().getDate_peredacha(), getSelectedAvto().getFrom_peredacha(), getSelectedAvto().getTo_peredacha(), getSelectedAvto().getN_peredacha()));
                                } else
                                    context.addMessage(null, new FacesMessage("Ошибка", "Что-то пошло не так"));
                            }

                        }
                    } catch (Exception ex) {
                        logger.log(Level.ERROR, ex);
                    }

                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Ошибка", "Не правильно заполнен поля"));
                }
                getSelectedAvto().setFrom_peredacha("");
                getSelectedAvto().setTo_peredacha("");
                getSelectedAvto().setN_peredacha("");
            } else
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
        } else {
            logger.log(Level.INFO, this.selectedAvto);
        }
    }

    public void getExplCards(ActionEvent e) {
        if (getSelectedAvto() != null) {
            getSelectedAvto().getListOperationalModel().clear();
            try {
                try (Connection con = getConnection()) {
                    try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.getExplCard(?, ?, ?)}")) {
                        cStmt.setInt(1, getSelectedAvto().getId());
                        cStmt.setString(2, DateHelper.DateToString(getSelectedAvto().getExpl_date1(), "dd.MM.yyyy"));
                        cStmt.setString(3, DateHelper.DateToString(getSelectedAvto().getExpl_date2(), "dd.MM.yyyy"));

                        boolean hadResults = cStmt.execute();
                        if (hadResults) {

                            try (ResultSet rs = cStmt.getResultSet()) {
                                while (rs.next()) {
                                    getSelectedAvto().getListOperationalModel().add(new OperationalModel(rs.getInt("mm"),
                                            rs.getString("interval"),
                                            rs.getInt("year_e"),
                                            rs.getFloat("d_out"),
                                            rs.getFloat("d_good"),
                                            rs.getFloat("d_operational"),
                                            rs.getFloat("d_remont"),
                                            rs.getFloat("h_order"),
                                            rs.getFloat("norma_run"),
                                            rs.getFloat("fact_run"),
                                            rs.getFloat("to1"),
                                            rs.getFloat("to2"),
                                            rs.getFloat("kp"),
                                            rs.getFloat("gsm_begin_m"),
                                            rs.getFloat("gsm_given"),
                                            rs.getFloat("gsm_end_m"),
                                            rs.getFloat("gsm_norma"),
                                            rs.getFloat("gsm_out"),
                                            rs.getString("comm"),
                                            rs.getDate("dat"),
                                            rs.getInt("kv"),
                                            rs.getFloat("speed_begin"),
                                            rs.getFloat("speed_end"),
                                            rs.getFloat("oil_l"),
                                            rs.getInt("oil_type")));
                                }
                            }
                        } else logger.log(Level.ERROR, "false");
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
            }
        }
    }

    public void newExplCards(ActionEvent e) {
        if (getSelectedAvto() != null) {
            //logger.log(Level.INFO, getSelectedAvto().getId());
            OperationalModel a = getSelectedAvto().getSelectedOperational();
            a = null;
            a = new OperationalModel();
            getSelectedAvto().setSelectedOperational(a);
            /*logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().getMm());
            logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().getInterval());
            logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().getYear_e());
            logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().isNewOper());
            logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().getD_out());*/
        }

    }
    /*public void saveExplCards(ActionEvent e)
    {
        logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().getMm());
        logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().getInterval());
        logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().getYear_e());
        logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().isNewOper());
        logger.log(Level.INFO, getSelectedAvto().getSelectedOperational().getD_out());
    }*/


    public void saveExplCards(ActionEvent e) {
        logger.log(Level.INFO, getSelectedAvto().getId());
        if (getSelectedAvto() != null) {
            if (atxLogin.isSave(Roles.TECH_EDIT_EXPL, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                Calendar dat = Calendar.getInstance();
                dat.set(Calendar.YEAR, getSelectedAvto().getSelectedOperational().getYear_e());
                int idx_interval = 0;
                switch (getSelectedAvto().getSelectedOperational().getMm()) {
                    case 1:
                        getSelectedAvto().getSelectedOperational().setKv(1);
                        dat.set(Calendar.MONTH, 0);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 0;
                        break;
                    case 2:
                        getSelectedAvto().getSelectedOperational().setKv(1);
                        dat.set(Calendar.MONTH, 1);
                        dat.set(Calendar.DATE, 28);
                        idx_interval = 1;
                        break;
                    case 3:
                        getSelectedAvto().getSelectedOperational().setKv(1);
                        dat.set(Calendar.MONTH, 2);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 2;
                        break;
                    case 4:
                        getSelectedAvto().getSelectedOperational().setKv(2);
                        dat.set(Calendar.MONTH, 3);
                        dat.set(Calendar.DATE, 30);
                        idx_interval = 3;
                        break;
                    case 5:
                        getSelectedAvto().getSelectedOperational().setKv(2);
                        dat.set(Calendar.MONTH, 4);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 4;
                        break;
                    case 6:
                        getSelectedAvto().getSelectedOperational().setKv(2);
                        dat.set(Calendar.MONTH, 5);
                        dat.set(Calendar.DATE, 30);
                        idx_interval = 5;
                        break;
                    case 7:
                        getSelectedAvto().getSelectedOperational().setKv(3);
                        dat.set(Calendar.MONTH, 6);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 6;
                        break;
                    case 8:
                        getSelectedAvto().getSelectedOperational().setKv(3);
                        dat.set(Calendar.MONTH, 7);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 7;
                        break;
                    case 9:
                        getSelectedAvto().getSelectedOperational().setKv(3);
                        dat.set(Calendar.MONTH, 8);
                        dat.set(Calendar.DATE, 30);
                        idx_interval = 8;
                        break;
                    case 10:
                        getSelectedAvto().getSelectedOperational().setKv(4);
                        dat.set(Calendar.MONTH, 9);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 9;
                        break;
                    case 11:
                        getSelectedAvto().getSelectedOperational().setKv(4);
                        dat.set(Calendar.MONTH, 10);
                        dat.set(Calendar.DATE, 30);
                        idx_interval = 10;
                        break;
                    case 12:
                        getSelectedAvto().getSelectedOperational().setKv(4);
                        dat.set(Calendar.MONTH, 11);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 11;
                        break;
                    case 111:
                        getSelectedAvto().getSelectedOperational().setKv(1);
                        dat.set(Calendar.MONTH, 2);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 12;
                        break;
                    case 222:
                        getSelectedAvto().getSelectedOperational().setKv(2);
                        dat.set(Calendar.MONTH, 5);
                        dat.set(Calendar.DATE, 30);
                        idx_interval = 13;
                        break;
                    case 333:
                        getSelectedAvto().getSelectedOperational().setKv(3);
                        dat.set(Calendar.MONTH, 8);
                        dat.set(Calendar.DATE, 30);
                        idx_interval = 14;
                        break;
                    case 444:
                        getSelectedAvto().getSelectedOperational().setKv(4);
                        dat.set(Calendar.MONTH, 11);
                        dat.set(Calendar.DATE, 31);
                        idx_interval = 15;
                        break;
                }
            /*dat.set(Calendar.HOUR,0);
            dat.set(Calendar.MINUTE,0);
            dat.set(Calendar.SECOND,0);
            dat.set(Calendar.MILLISECOND,0);*/

                if (getSelectedAvto().getSelectedOperational().isNewOper())
                    getSelectedAvto().getSelectedOperational().setInterval(((IntervalModel) listIntervalModel.get(idx_interval)).getName());

                /*новая записть*/
                int what = 0;
                FacesContext context = FacesContext.getCurrentInstance();

            /*logger.log(Level.ERROR, getSelectedAvto().getId());
            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getMm());
            logger.log(Level.ERROR, DateHelper.DateToString(new Date(dat.getTimeInMillis()),"yyy-MM-dd"));
            logger.log(Level.ERROR, new Date(dat.getTimeInMillis()));*/
                try {
                    try (Connection con = getConnection()) {
                        if (getSelectedAvto().getSelectedOperational().isNewOper()) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.CheckExpl(?, ?, ?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getId());
                                cStmt.setInt(2, getSelectedAvto().getSelectedOperational().getMm());
                                cStmt.setDate(3, new java.sql.Date(dat.getTimeInMillis()));
                                boolean hadResults = cStmt.execute();
                                if (hadResults) {
                                    //logger.log(Level.INFO, "yes expl");
                                    try (ResultSet rs = cStmt.getResultSet()) {
                                        if (rs.next()) {
                                            getSelectedAvto().getSelectedOperational().setD_out(getSelectedAvto().getSelectedOperational().getD_out() + rs.getFloat("d_out"));
                                            getSelectedAvto().getSelectedOperational().setD_good(getSelectedAvto().getSelectedOperational().getD_good() + rs.getFloat("d_good"));
                                            getSelectedAvto().getSelectedOperational().setD_operational(getSelectedAvto().getSelectedOperational().getD_operational() + rs.getFloat("d_operational"));
                                            getSelectedAvto().getSelectedOperational().setD_remont(getSelectedAvto().getSelectedOperational().getD_remont() + rs.getFloat("d_remont"));
                                            getSelectedAvto().getSelectedOperational().setH_order(getSelectedAvto().getSelectedOperational().getH_order() + rs.getFloat("h_order"));
                                            getSelectedAvto().getSelectedOperational().setNorma_run(getSelectedAvto().getSelectedOperational().getNorma_run() + rs.getFloat("norma_run"));
                                            getSelectedAvto().getSelectedOperational().setFact_run(getSelectedAvto().getSelectedOperational().getFact_run() + rs.getFloat("fact_run"));
                                            getSelectedAvto().getSelectedOperational().setTo1(getSelectedAvto().getSelectedOperational().getTo1() + rs.getFloat("to1"));
                                            getSelectedAvto().getSelectedOperational().setTo2(getSelectedAvto().getSelectedOperational().getTo2() + rs.getFloat("to2"));
                                            getSelectedAvto().getSelectedOperational().setKp(getSelectedAvto().getSelectedOperational().getKp() + rs.getFloat("kp"));
                                            getSelectedAvto().getSelectedOperational().setGsm_begin_m(getSelectedAvto().getSelectedOperational().getGsm_begin_m() + rs.getFloat("gsm_begin_m"));
                                            getSelectedAvto().getSelectedOperational().setGsm_given(getSelectedAvto().getSelectedOperational().getGsm_given() + rs.getFloat("gsm_given"));
                                            getSelectedAvto().getSelectedOperational().setGsm_end_m(getSelectedAvto().getSelectedOperational().getGsm_end_m() + rs.getFloat("gsm_end_m"));
                                            getSelectedAvto().getSelectedOperational().setGsm_norma(getSelectedAvto().getSelectedOperational().getGsm_norma() + rs.getFloat("gsm_norma"));
                                            getSelectedAvto().getSelectedOperational().setGsm_out(getSelectedAvto().getSelectedOperational().getGsm_out() + rs.getFloat("gsm_out"));
                                            what = 1;
                                            logger.log(Level.INFO, "yes expl");

                                        }
                                    }
                                }
                            }
                        } else what = 1;

                        try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.SetExpl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
                            cStmt.setInt(1, getSelectedAvto().getId());
                            cStmt.setInt(2, getSelectedAvto().getSelectedOperational().getMm());
                            cStmt.setFloat(3, getSelectedAvto().getSelectedOperational().getD_out());
                            cStmt.setFloat(4, getSelectedAvto().getSelectedOperational().getD_good());
                            cStmt.setFloat(5, getSelectedAvto().getSelectedOperational().getD_operational());
                            cStmt.setFloat(6, getSelectedAvto().getSelectedOperational().getD_remont());
                            cStmt.setFloat(7, getSelectedAvto().getSelectedOperational().getH_order());
                            cStmt.setFloat(8, getSelectedAvto().getSelectedOperational().getNorma_run());
                            cStmt.setFloat(9, getSelectedAvto().getSelectedOperational().getFact_run());
                            cStmt.setFloat(10, getSelectedAvto().getSelectedOperational().getTo1());
                            cStmt.setFloat(11, getSelectedAvto().getSelectedOperational().getTo2());
                            cStmt.setFloat(12, getSelectedAvto().getSelectedOperational().getKp());
                            cStmt.setFloat(13, getSelectedAvto().getSelectedOperational().getGsm_begin_m());
                            cStmt.setFloat(14, getSelectedAvto().getSelectedOperational().getGsm_given());
                            cStmt.setFloat(15, getSelectedAvto().getSelectedOperational().getGsm_end_m());
                            cStmt.setFloat(16, getSelectedAvto().getSelectedOperational().getGsm_norma());
                            cStmt.setFloat(17, getSelectedAvto().getSelectedOperational().getGsm_out());
                            cStmt.setString(18, getSelectedAvto().getSelectedOperational().getComm());
                            cStmt.setDate(19, new java.sql.Date(dat.getTimeInMillis()));
                            cStmt.setInt(20, getSelectedAvto().getSelectedOperational().getKv());
                            cStmt.setInt(21, what);
                            cStmt.setFloat(22, getSelectedAvto().getSelectedOperational().getSpeed_begin());
                            cStmt.setFloat(23, getSelectedAvto().getSelectedOperational().getSpeed_end());
                            cStmt.setFloat(24, getSelectedAvto().getSelectedOperational().getOil_l());
                            cStmt.setInt(25, getSelectedAvto().getSelectedOperational().getOil_type());


                            /*logger.log(Level.ERROR, getSelectedAvto().getId());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getMm());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getD_out());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getD_good());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getD_operational());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getD_remont());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getH_order());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getNorma_run());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getFact_run());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getTo1());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getTo2());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getKp());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getGsm_begin_m());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getGsm_given());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getGsm_end_m());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getGsm_norma());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getGsm_out());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getComm());
                            logger.log(Level.ERROR,  dat.toString());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getKv());
                            logger.log(Level.ERROR, what);
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getSpeed_begin());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getSpeed_end());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getOil_l());
                            logger.log(Level.ERROR, getSelectedAvto().getSelectedOperational().getOil_type());*/


                            cStmt.executeUpdate();


                            if (what == 0) {
                                getSelectedAvto().getSelectedOperational().setNewOper(false);
                                getSelectedAvto().getListOperationalModel().add(getSelectedAvto().getSelectedOperational());
                            }
                            context.addMessage(null, new FacesMessage("Успешно", Txt.DATA_SAVE));
                            //getSelectedAvto().getListPeredachaModel().add(new PeredachaModel(getSelectedAvto().getDate_peredacha(), getSelectedAvto().getFrom_peredacha(), getSelectedAvto().getTo_peredacha(), getSelectedAvto().getN_peredacha()));

                        }

                    }
                } catch (Exception ex) {
                    logger.log(Level.ERROR, ex);
                    context.addMessage(null, new FacesMessage("Ошибка", ex.getMessage()));
                }
            } else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }
        }

    }

    public void getRemontCards(ActionEvent e) {
        if (getSelectedAvto() != null) {
            getSelectedAvto().getListRemontModel().clear();
            try {
                try (Connection con = getConnection()) {
                    try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.getRemontCard(?, ?, ?)}")) {
                        cStmt.setInt(1, getSelectedAvto().getId());
                        cStmt.setString(2, DateHelper.DateToString(getSelectedAvto().getExpl_date1(), "dd.MM.yyyy"));
                        cStmt.setString(3, DateHelper.DateToString(getSelectedAvto().getExpl_date2(), "dd.MM.yyyy"));

                        boolean hadResults = cStmt.execute();
                        if (hadResults) {

                            try (ResultSet rs = cStmt.getResultSet()) {
                                while (rs.next()) {
                                    getSelectedAvto().getListRemontModel().add(new RemontModel(rs.getInt("id"),
                                            rs.getInt("id_avt"),
                                            rs.getDate("date_begin"),
                                            rs.getDate("date_end"),
                                            rs.getInt("ddd"),
                                            rs.getInt("id_vid"),
                                            rs.getString("name"),
                                            rs.getInt("id_place"),
                                            rs.getString("Expr1"),
                                            rs.getInt("speed_begin"),
                                            rs.getInt("speed_to"),
                                            rs.getFloat("oill"),
                                            rs.getInt("num"),
                                            rs.getInt("oil_type"),
                                            rs.getFloat("freez"),
                                            rs.getInt("freez_type"),
                                            rs.getFloat("liq_break"),
                                            rs.getFloat("oil_atf"),
                                            rs.getInt("oil_atf_type"),
                                            rs.getFloat(7),
                                            rs.getFloat(8),
                                            rs.getFloat(9)));
                                }
                            }
                        } else logger.log(Level.ERROR, "false");
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
            }
        }
    }

    public void onSelectedRemont() {
        logger.log(Level.ERROR, getSelectedAvto().getSelectedRemont().getId());
        if (getSelectedAvto() != null) {
            getSelectedAvto().getSelectedRemont().getListRemontAgrModel().clear();
            getSelectedAvto().getSelectedRemont().getListRemontDiffModel().clear();
            try {
                try (Connection con = getConnection()) {
                    try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.getRemontDifr(?)}")) {
                        cStmt.setInt(1, getSelectedAvto().getSelectedRemont().getId());
                        boolean hadResults = cStmt.execute();
                        if (hadResults) {
                            try (ResultSet rs = cStmt.getResultSet()) {
                                int idx = 0;
                                while (rs.next()) {
                                    if (rs.getInt("type") == 0) {
                                        getSelectedAvto().getSelectedRemont().getListRemontAgrModel().add(new RemontAgrModel(idx,
                                                getSelectedAvto().getSelectedRemont().getId(),
                                                rs.getString("name"),
                                                rs.getString("p1"),
                                                rs.getString("p2")));
                                        idx++;
                                    } else {
                                        getSelectedAvto().getSelectedRemont().getListRemontDiffModel().add(new RemontDiffModel(idx,
                                                getSelectedAvto().getSelectedRemont().getId(),
                                                rs.getString("name"),
                                                Float.parseFloat(rs.getString("p1")),
                                                rs.getInt("type")));
                                        idx++;
                                    }
                                }
                            }
                        } else logger.log(Level.ERROR, "false");
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
            }
        }
    }

    public void newRemontCards(ActionEvent e) {
        if (getSelectedAvto() != null) {
            getSelectedAvto().setSelectedRemont(new RemontModel(getSelectedAvto().getId()));
        }

    }

    public void getRemontZayCards(ActionEvent e) {
        if (getSelectedAvto() != null) {
            getSelectedAvto().getListRemontZayModel().clear();
            try {
                try (Connection con = getConnection()) {
                    try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetZayvkaCard(?, ?, ?)}")) {
                        cStmt.setInt(1, getSelectedAvto().getId());
                        cStmt.setString(2, DateHelper.DateToString(getSelectedAvto().getExpl_date1(), "dd.MM.yyyy"));
                        cStmt.setString(3, DateHelper.DateToString(getSelectedAvto().getExpl_date2(), "dd.MM.yyyy"));

                        boolean hadResults = cStmt.execute();
                        if (hadResults) {

                            try (ResultSet rs = cStmt.getResultSet()) {
                                while (rs.next()) {
                                    getSelectedAvto().getListRemontZayModel().add(new RemontZayModel(rs.getInt("id"),
                                            rs.getInt("id_avt"),
                                            rs.getInt("num"),
                                            rs.getDate("date_begin"),
                                            rs.getFloat("speed_begin"),
                                            rs.getString("fio"),
                                            rs.getInt("id_vidrem"),
                                            rs.getString(7),
                                            rs.getInt("id_remontplace"),
                                            rs.getString(11),
                                            rs.getInt("graf")));
                                }
                            }
                        } else logger.log(Level.ERROR, "false");
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
            }
        }
    }

    public void getZakrepCards(ActionEvent e) {
        if (getSelectedAvto() != null) {
            getSelectedAvto().getListZakrepModel().clear();
            try {
                try (Connection con = getConnection()) {
                    try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetZakreplenAvtoCard(?, ?, ?, ?, ?)}")) {
                        cStmt.setInt(1, getSelectedAvto().getId());
                        cStmt.setString(2, DateHelper.DateToString(getSelectedAvto().getExpl_date1(), "dd.MM.yyyy"));
                        cStmt.setString(3, DateHelper.DateToString(getSelectedAvto().getExpl_date2(), "dd.MM.yyyy"));
                        cStmt.setInt(4, -1);
                        cStmt.setInt(5, 1);

                        boolean hadResults = cStmt.execute();
                        if (hadResults) {

                            try (ResultSet rs = cStmt.getResultSet()) {
                                while (rs.next()) {

                                    getSelectedAvto().getListZakrepModel().add(new ZakrepModel(rs.getInt("id"),
                                            rs.getInt("id_avt"),
                                            rs.getDate("date_begin"),
                                            rs.getDate("date_end"),
                                            rs.getString("prikaz"),
                                            rs.getDate("date_prikaz"),
                                            rs.getString("out_prikaz"),
                                            rs.getDate("date_out_prikaz"),
                                            rs.getBoolean("spravka"),
                                            rs.getString("fio"),
                                            rs.getString("phone"),
                                            rs.getString("dolg"),
                                            rs.getString("zvan"),
                                            rs.getString("address"),
                                            rs.getString("vod_ud"),
                                            rs.getDate("med"),
                                            rs.getString("comm"),
                                            rs.getBoolean("temp"),
                                            rs.getDate("spravka_date"),
                                            rs.getBoolean("card"),
                                            rs.getDate("card_date")));
                                }
                            }
                        } else logger.log(Level.ERROR, "false");
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
            }
        }
    }

    public void onSelectedRemontZay() {
        logger.log(Level.INFO, getSelectedAvto().getSelectedRemontZay().getId());
        if (getSelectedAvto() != null) {
            getSelectedAvto().getSelectedRemontZay().getListRemontZayDifrModel().clear();
            try {
                try (Connection con = getConnection()) {
                    try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetZayvkaDifr(?)}")) {
                        cStmt.setInt(1, getSelectedAvto().getSelectedRemontZay().getId());
                        boolean hadResults = cStmt.execute();
                        if (hadResults) {
                            try (ResultSet rs = cStmt.getResultSet()) {
                                while (rs.next()) {
                                    getSelectedAvto().getSelectedRemontZay().getListRemontZayDifrModel().add(new RemontZayDifrModel(
                                            getSelectedAvto().getSelectedRemontZay().getId(),
                                            rs.getInt("num"),
                                            rs.getString("neisparnost")));
                                }
                            }
                        } else logger.log(Level.ERROR, "false");
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
            }
        }
    }
    public void onSelectedZakrep() {
        logger.log(Level.INFO, getSelectedAvto().getSelectedZakrep().getId());
        if (getSelectedAvto() != null) {


        }
    }

    public void newRemontZayCards(ActionEvent e) {
        if (getSelectedAvto() != null) {
            getSelectedAvto().setSelectedRemontZay(new RemontZayModel(getSelectedAvto().getId()));
        }

    }
    public void newZakrepCards(ActionEvent e) {
        if (getSelectedAvto() != null) {
            getSelectedAvto().setSelectedZakrep(new ZakrepModel(getSelectedAvto().getId()));
        }

    }
    public void deleteZakrepCards(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedZakrep() != null) {
            if (atxLogin.isSave(Roles.ZAKREP_EDIT, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                try {
                    try (Connection con = getConnection()) {
                        if (getSelectedAvto().getSelectedZakrep().getId() > 0) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.DelZakreplen(?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getSelectedZakrep().getId());
                                cStmt.executeUpdate();
                            }
                            viewMessage("Успешно", Txt.DATA_DELETE);
                                Iterator<ZakrepModel> i = getSelectedAvto().getListZakrepModel().iterator();
                                while (i.hasNext()) {
                                    ZakrepModel agr = i.next();
                                    if(agr.getId()==getSelectedAvto().getSelectedZakrep().getId())
                                        i.remove();
                                }

                        }
                    }
                } catch (Exception ex) {
                    logger.log(Level.ERROR, ex);
                    viewMessage("Ошибка", ex.getMessage());
                }
            } else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }

        }
    }

    public void deleteRemZayCards(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedRemontZay() != null) {
            if (atxLogin.isSave(Roles.REM_EDIT, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                try {
                    try (Connection con = getConnection()) {
                        if (getSelectedAvto().getSelectedRemontZay().getId() > 0) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.DelZayvka(?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getSelectedRemontZay().getId());
                                cStmt.executeUpdate();
                            }
                            viewMessage("Успешно", Txt.DATA_DELETE);
                            Iterator<RemontZayModel> i = getSelectedAvto().getListRemontZayModel().iterator();
                            while (i.hasNext()) {
                                RemontZayModel agr = i.next();
                                if(agr.getId()==getSelectedAvto().getSelectedRemontZay().getId())
                                    i.remove();
                            }

                        }
                    }
                } catch (Exception ex) {
                    logger.log(Level.ERROR, ex);
                    viewMessage("Ошибка", ex.getMessage());
                }
            } else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }

        }
    }

    public void deleteRemont(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedRemont() != null) {
            if (atxLogin.isSave(Roles.REM_EDIT, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                try {
                    try (Connection con = getConnection()) {
                        if (getSelectedAvto().getSelectedRemont().getId() > 0) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.DelRemont(?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getSelectedRemont().getId());
                                cStmt.executeUpdate();
                            }
                            viewMessage("Успешно", Txt.DATA_DELETE);
                            Iterator<RemontModel> i = getSelectedAvto().getListRemontModel().iterator();
                            while (i.hasNext()) {
                                RemontModel agr = i.next();
                                if(agr.getId()==getSelectedAvto().getSelectedRemont().getId())
                                    i.remove();
                            }

                        }
                    }
                } catch (Exception ex) {
                    logger.log(Level.ERROR, ex);
                    viewMessage("Ошибка", ex.getMessage());
                }
            } else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }

        }
    }

    public void deleteExplCards(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedOperational() != null) {
            if (atxLogin.isSave(Roles.TECH_EDIT_EXPL, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                try {
                    try (Connection con = getConnection()) {
                        if (!getSelectedAvto().getSelectedOperational().isNewOper()) {
                            try (PreparedStatement cStmt = con.prepareStatement("delete from ATX_UVD.dbo.tOperational where id_avt=? and dat=?")) {
                                cStmt.setInt(1,getSelectedAvto().getId());
                                cStmt.setDate(2, new java.sql.Date(getSelectedAvto().getSelectedOperational().getDat().getTime()));
                                cStmt.executeUpdate();
                            }
                            viewMessage("Успешно", Txt.DATA_DELETE);
                            Iterator<OperationalModel> i = getSelectedAvto().getListOperationalModel().iterator();
                            while (i.hasNext()) {
                                OperationalModel agr = i.next();
                                if(agr.getDat()==getSelectedAvto().getSelectedOperational().getDat())
                                    i.remove();
                            }

                        }
                    }
                } catch (Exception ex) {
                    logger.log(Level.ERROR, ex);
                    viewMessage("Ошибка", ex.getMessage());
                }
            } else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }

        }
    }



    public void saveAvtoData(ActionEvent e) {
        if (atxLogin.isSave(Roles.TECH_EDIT_AVTO, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
            logger.log(Level.INFO, "saveAvtoData");
            logger.log(Level.INFO, getSelectedAvto().getNumb_1());
        } else
            viewMessage("Ошибка", Txt.ACCESS_ERROR);
    }

    public void printRemontCards(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedRemont() != null) {
            //String param1 = FacesUtils.getRequestParameter("mm_id");
            //String param2 = FacesUtils.getRequestParameter("mm_d");
            //String a = "SledMMJsf.jsf?mm_id=" + param1 + "&mm_d=" + param2;
            String link = "prints/remontprint.jsf?id=" + getSelectedAvto().getSelectedRemont().getId() + "&id_avt=" + getSelectedAvto().getId();
            openNewWindows(link, "Ремонтная карточка");
            return;

        }
    }

    public void saveRemontCards(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedRemont() != null) {
            if (atxLogin.isSave(Roles.REM_EDIT, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                try {
                    try (Connection con = getConnection()) {
                        if (getSelectedAvto().getSelectedRemont().getId() > 0) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.DelRemont(?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getSelectedRemont().getId());
                                cStmt.executeUpdate();
                            }

                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.SetRemont(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getId());
                                cStmt.setDate(2, new java.sql.Date((getSelectedAvto().getSelectedRemont().getDate_begin()).getTime()));
                                cStmt.setDate(3, new java.sql.Date((getSelectedAvto().getSelectedRemont().getDate_end()).getTime()));
                                cStmt.setInt(4, getSelectedAvto().getSelectedRemont().getId_vid());
                                cStmt.setInt(5, getSelectedAvto().getSelectedRemont().getId_place());
                                cStmt.setFloat(6, getSelectedAvto().getSelectedRemont().getSpeed_begin());
                                cStmt.setFloat(7, getSelectedAvto().getSelectedRemont().getSpeed_to());
                                cStmt.setFloat(8, getSelectedAvto().getSelectedRemont().getOil_l());
                                cStmt.setInt(9, getSelectedAvto().getSelectedRemont().getNum());
                                cStmt.setInt(10, (getSelectedAvto().getSelectedRemont().getOil_type() > 0) ? getSelectedAvto().getSelectedRemont().getOil_type() : 1);
                                cStmt.setFloat(11, getSelectedAvto().getSelectedRemont().getFreez());
                                cStmt.setInt(12, (getSelectedAvto().getSelectedRemont().getFreez_type() > 0) ? getSelectedAvto().getSelectedRemont().getFreez_type() : 1);
                                cStmt.setFloat(13, getSelectedAvto().getSelectedRemont().getLiq_break());
                                cStmt.setFloat(14, getSelectedAvto().getSelectedRemont().getOil_atf());
                                cStmt.setInt(15, (getSelectedAvto().getSelectedRemont().getOil_atf_type() > 0) ? getSelectedAvto().getSelectedRemont().getOil_atf_type() : 1);
                                cStmt.registerOutParameter(16, java.sql.Types.INTEGER);
                                cStmt.execute();
                                getSelectedAvto().getSelectedRemont().setId(cStmt.getInt("id"));
                            }
                            for (RemontAgrModel agr : getSelectedAvto().getSelectedRemont().getListRemontAgrModel()) {
                                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.SetRemontAgr(?,?,?,?)}")) {
                                    cStmt.setInt(1, getSelectedAvto().getSelectedRemont().getId());
                                    cStmt.setString(2, agr.getName());
                                    cStmt.setString(3, agr.getNum_old());
                                    cStmt.setString(4, agr.getNum_new());
                                    cStmt.execute();
                                }
                            }
                            int j = 1;
                            for (RemontDiffModel diff : getSelectedAvto().getSelectedRemont().getListRemontDiffModel()) {
                                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.SetRemontDifr(?,?,?,?,?)}")) {
                                    cStmt.setInt(1, getSelectedAvto().getSelectedRemont().getId());
                                    cStmt.setString(2, diff.getName());
                                    cStmt.setFloat(3, diff.getCoast());
                                    cStmt.setInt(4, diff.getType());
                                    cStmt.setInt(5, j);
                                    j++;
                                    cStmt.execute();
                                }
                            }
                            viewMessage("Успешно", Txt.DATA_SAVE);
                        }
                    }
                } catch (Exception ex) {
                    logger.log(Level.ERROR, ex);
                    viewMessage("Ошибка", ex.getMessage());
                }
            } else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }
        }

    }

    public void saveRemontZayCards(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedRemontZay() != null && getSelectedAvto().getSelectedRemontZay().getDate() != null) {
            if (atxLogin.isSave(Roles.REM_EDIT, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                try {
                    try (Connection con = getConnection()) {
                        if (getSelectedAvto().getSelectedRemontZay().getId() > 0) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.DelZayvka(?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getSelectedRemontZay().getId());
                                cStmt.executeUpdate();
                            }
                        }
                        try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.SetZayvka(?,?,?,?,?,?,?,?,?)}")) {
                            cStmt.setInt(1, getSelectedAvto().getId());
                            cStmt.setInt(2, getSelectedAvto().getSelectedRemontZay().getNum());
                            cStmt.setDate(3, new java.sql.Date((getSelectedAvto().getSelectedRemontZay().getDate()).getTime()));
                            cStmt.setFloat(4, getSelectedAvto().getSelectedRemontZay().getSpeed());
                            cStmt.setString(5, getSelectedAvto().getSelectedRemontZay().getFio());
                            cStmt.setInt(6, getSelectedAvto().getSelectedRemontZay().getGraf());
                            cStmt.setInt(7, getSelectedAvto().getSelectedRemontZay().getId_vid_to());
                            cStmt.setInt(8, getSelectedAvto().getSelectedRemontZay().getId_org());
                            cStmt.registerOutParameter(9, java.sql.Types.INTEGER);
                            cStmt.execute();
                            getSelectedAvto().getSelectedRemontZay().setId(cStmt.getInt("id"));
                        }
                        for (RemontZayDifrModel difr : getSelectedAvto().getSelectedRemontZay().getListRemontZayDifrModel()) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.SetZayvkaDifr(?,?,?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getSelectedRemontZay().getId());
                                cStmt.setInt(2, difr.getNumb());
                                cStmt.setString(3, difr.getZay());
                                cStmt.execute();
                            }
                        }
                        viewMessage("Успешно", Txt.DATA_SAVE);

                    }
                } catch (Exception ex) {
                    logger.log(Level.ERROR, ex);
                    viewMessage("Ошибка", ex.getMessage());
                }
            } else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }
        }
    }

    public void saveZakrepCards(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedZakrep() != null && getSelectedAvto().getSelectedZakrep().getDate_begin() != null) {
            if (atxLogin.isSave(Roles.ZAKREP_EDIT, getSelectedAvto().getId_region(), getSelectedAvto().getId_depart())) {
                try {
                    try (Connection con = getConnection()) {
                        if (getSelectedAvto().getSelectedZakrep().getId() > 0) {
                            try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.DelZakreplen(?)}")) {
                                cStmt.setInt(1, getSelectedAvto().getSelectedZakrep().getId());
                                cStmt.executeUpdate();
                            }
                        }
                        try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.SetZakreplen(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
                            cStmt.setInt(1, getSelectedAvto().getId());
                            cStmt.setDate(2, new java.sql.Date((getSelectedAvto().getSelectedZakrep().getDate_begin()).getTime()));
                            cStmt.setDate(3, (getSelectedAvto().getSelectedZakrep().getDate_end()!=null)?new java.sql.Date((getSelectedAvto().getSelectedZakrep().getDate_end()).getTime()):null);

                            cStmt.setString(4, getSelectedAvto().getSelectedZakrep().getPrikaz());
                            cStmt.setDate(5, (getSelectedAvto().getSelectedZakrep().getDate_prikaz()!=null)?new java.sql.Date((getSelectedAvto().getSelectedZakrep().getDate_prikaz()).getTime()):null);
                            cStmt.setBoolean(6, getSelectedAvto().getSelectedZakrep().isSpravka());

                            cStmt.setString(7, getSelectedAvto().getSelectedZakrep().getFio());
                            cStmt.setString(8, getSelectedAvto().getSelectedZakrep().getPhone());
                            cStmt.setString(9, getSelectedAvto().getSelectedZakrep().getDolg());
                            cStmt.setString(10, getSelectedAvto().getSelectedZakrep().getZvan());
                            cStmt.setString(11, getSelectedAvto().getSelectedZakrep().getAddress());
                            cStmt.setString(12, getSelectedAvto().getSelectedZakrep().getVod_ud());
                            cStmt.setDate(13, (getSelectedAvto().getSelectedZakrep().getMed()!=null)?new java.sql.Date((getSelectedAvto().getSelectedZakrep().getMed()).getTime()):null);
                            cStmt.setString(14, getSelectedAvto().getSelectedZakrep().getComm());
                            cStmt.setBoolean(15, getSelectedAvto().getSelectedZakrep().isTemp());
                            cStmt.setBoolean(16, getSelectedAvto().getSelectedZakrep().isCard());
                            cStmt.setString(17, getSelectedAvto().getSelectedZakrep().getOut_prikaz());
                            cStmt.setDate(18, (getSelectedAvto().getSelectedZakrep().getDate_out_prikaz()!=null)?new java.sql.Date((getSelectedAvto().getSelectedZakrep().getDate_out_prikaz()).getTime()):null);

                            cStmt.setDate(19, (getSelectedAvto().getSelectedZakrep().isSpravka())?new java.sql.Date(System.currentTimeMillis()):null);
                            cStmt.setDate(20, (getSelectedAvto().getSelectedZakrep().isCard())?new java.sql.Date(System.currentTimeMillis()):null);
                            cStmt.execute();
                        }

                        viewMessage("Успешно", Txt.DATA_SAVE);
                        getZakrepCards(e);

                    }
                } catch (Exception ex) {
                    logger.log(Level.ERROR, ex);
                    viewMessage("Ошибка", ex.getMessage());
                }
            } else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }
        }
    }

    public void doc(ActionEvent e) {
        ZayvkaWord zayvkaWord = new ZayvkaWord();

        /*XWPFDocument  wb = zayvkaWord.zayvkaAct();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        //externalContext.setResponseContentType("application/vnd.msword");
        externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"act.docx\"");
        try {
            wb.write(externalContext.getResponseOutputStream());
            facesContext.responseComplete();
        } catch (Exception er) {
            logger.log(Level.ERROR, er.getMessage());
        }*/
    }

    public void printRemontZayAct(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedRemontZay() != null) {
            ZayvkaWord zayvkaWord = new ZayvkaWord();
            XWPFDocument wb = zayvkaWord.zayvkaAct(getSelectedAvto(), getFromLyb(getSelectedAvto().getId_mark(), "MarkAvtModel"));
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"act.docx\"");
            try {
                wb.write(externalContext.getResponseOutputStream());
                facesContext.responseComplete();
            } catch (Exception er) {
                logger.log(Level.ERROR, er.getMessage());
            }
        }
    }

    public void printRemontZayCards(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getSelectedRemontZay() != null) {
            ZayvkaWord zayvkaWord = new ZayvkaWord();
            XWPFDocument wb = zayvkaWord.zayvkaPrint(getSelectedAvto(), getFromLyb(getSelectedAvto().getId_mark(), "MarkAvtModel")
                    , getFromLyb(getSelectedAvto().getId_mark(), "ServModel")
                    , getFromLyb(getSelectedAvto().getId_mark(), "GroupModel"));
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"act.docx\"");
            try {
                wb.write(externalContext.getResponseOutputStream());
                facesContext.responseComplete();
            } catch (Exception er) {
                logger.log(Level.ERROR, er.getMessage());
            }

        }
    }
    public void printZakrepCard(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getListZakrepModel().size()>0) {
            ZakrepWord zakrepWord = new ZakrepWord();
            XWPFDocument wb = zakrepWord.zakrepCard(getSelectedAvto(), getFromLyb(getSelectedAvto().getId_mark(), "MarkAvtModel")
                    , getFromLyb(getSelectedAvto().getId_depart(), "DepartModel"), getSelectedAvto().getListZakrepModel());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"zakrep_card.docx\"");
            try {
                wb.write(externalContext.getResponseOutputStream());
                facesContext.responseComplete();
            } catch (Exception er) {
                logger.log(Level.ERROR, er.getMessage());
            }
        }
    }

    public void printZakrepSpravka(ActionEvent e) {
        if (getSelectedAvto() != null && getSelectedAvto().getListZakrepModel().size()>0) {
            ZakrepWord zakrepWord = new ZakrepWord();
            XWPFDocument wb = zakrepWord.zakrepSpravka(getSelectedAvto(), getFromLyb(getSelectedAvto().getId_mark(), "MarkAvtModel")
                    , getFromLyb(getSelectedAvto().getId_depart(), "DepartModel"), getSelectedAvto().getListZakrepModel());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"zakrep_spravka.docx\"");
            try {
                wb.write(externalContext.getResponseOutputStream());
                facesContext.responseComplete();
            } catch (Exception er) {
                logger.log(Level.ERROR, er.getMessage());
            }
        }
    }

    public void printAktZakrep(ActionEvent e) {
        if (getSelectedAvto() != null) {
            ZakrepWord zakrepWord = new ZakrepWord();
            XWPFDocument wb = zakrepWord.zakrepAkt(getSelectedAvto(), getFromLyb(getSelectedAvto().getId_mark(), "MarkAvtModel"));
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"zakrep_akt.docx\"");
            try {
                wb.write(externalContext.getResponseOutputStream());
                facesContext.responseComplete();
            } catch (Exception er) {
                logger.log(Level.ERROR, er.getMessage());
            }
        }
    }

    private List<String> getLastZakrep(int id) {
        List<String> z = new ArrayList<>();
            try {
                try (Connection con = getConnection()) {
                    try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetZakreplenLast(?)}")) {
                        cStmt.setInt(1, id);
                        boolean hadResults = cStmt.execute();
                        if (hadResults) {
                            try (ResultSet rs = cStmt.getResultSet()) {
                                while (rs.next()) {
                                    z.add(rs.getString(1));
                                }
                            }
                        } else logger.log(Level.ERROR, "false");
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
            }
        return z;
    }

    public void printAvto(ActionEvent e) {
        if (getSelectedAvto() != null) {
            ZakrepWord zakrepWord = new ZakrepWord();
            XWPFDocument wb = zakrepWord.printAvto(getSelectedAvto(), getFromLyb(getSelectedAvto().getId_mark(), "MarkAvtModel"),
                    getFromLyb(getSelectedAvto().getId_region(), "RegionModel"),
                    getFromLyb(getSelectedAvto().getId_division(), "DivisModel"),
                    getFromLyb(getSelectedAvto().getId_depart(), "DepartModel"),
                    getFromLyb(getSelectedAvto().getId_preDepart(), "PreDepartModel"),
                    getFromLyb(getSelectedAvto().getId_serv(), "ServModel"),
                    getFromLyb(getSelectedAvto().getId_PreServ(), "PreServModel"),
                    getFromLyb(getSelectedAvto().getId_dislocation(), "DislocationvModel"),
                    getFromLyb(getSelectedAvto().getId_group(), "GroupModel"),
                    getFromLyb(getSelectedAvto().getId_type_body(), "TypeBodyModel"),
                    getFromLyb(getSelectedAvto().getId_tech(), "TechModel"),
                    getFromLyb(getSelectedAvto().getId_source_bay(), "SourceModel"),
                    getFromLyb(getSelectedAvto().getId_source_fin(), "SourceModel"),
                    getFromLyb(getSelectedAvto().getId_insure_company(), "InsureCompanyModel"), getLastZakrep(getSelectedAvto().getId())



                    );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"avto.docx\"");
            try {
                wb.write(externalContext.getResponseOutputStream());
                facesContext.responseComplete();
            } catch (Exception er) {
                logger.log(Level.ERROR, er.getMessage());
            }
        }
    }

    private void openNewWindows(String link, String name, int type, int w, int h) {
        if (type == 1)
            PrimeFaces.current().executeScript("window.open('" + link + "','" + name + "','scrollbars=yes,menubar=no,height=" + h + ",width=" + w + ",resizable=yes,toolbar=no,location=no,status=no');");
        else
            PrimeFaces.current().executeScript("window.open('" + link + "','" + name + "');");
    }

    private void openNewWindows(String link, String name) {
        openNewWindows(link, name, 2, 0, 0);
    }

    private boolean validMaskNumb(String numb) {
        if (numb.matches("\\d{4}\\S{3}")) return true;//0000ppp
        if (numb.matches("\\S{1}\\d{4}")) return true;//р0000
        if (numb.matches("\\S{1}\\d{3}\\S{2}")) return true;//р000рр
        if (numb.matches("\\S{2}\\d{4}")) return true;//рр0000

        if (numb.equalsIgnoreCase("бн")) return true;

        return false;
    }

}
