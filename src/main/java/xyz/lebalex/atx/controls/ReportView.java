package xyz.lebalex.atx.controls;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.AtxLogin;
import xyz.lebalex.atx.modelReport.InventarModel;
import xyz.lebalex.atx.models.AvtoModel;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ReportView extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportView.class);
    private List<InventarModel> listInventarModel = new ArrayList();
    private List<Boolean> filds = new ArrayList();

    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;

    public List<InventarModel> getListInventarModel() {
        return listInventarModel;
    }

    public void setListInventarModel(List<InventarModel> listInventarModel) {
        this.listInventarModel = listInventarModel;
    }

    public List<Boolean> getFilds() {
        return filds;
    }

    public void inventar(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                         int id_source_fin, int id_group,
                         String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur,
                         String inAc1, String inAc2, int state_p, int uotAc_p, int life_p, String outD1, String outD2, int id_typetc, int arenda, int spisat, int fuel_p,
                         int notATX_p, String volumDVC, int typeDVC, int manufactureTC, int spisat_next, List<Boolean> filds) {
        this.filds=filds;
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportInventar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
                    cStmt.setInt(1, id_region);
                    cStmt.setInt(2, id_division);
                    cStmt.setInt(3, id_depart);
                    cStmt.setInt(4, id_preDepart);
                    cStmt.setInt(5, id_serv);
                    cStmt.setInt(6, id_mark);
                    cStmt.setString(7, modelAvt);
                    cStmt.setInt(8, id_source_bay);
                    cStmt.setInt(9, id_source_fin);
                    cStmt.setInt(10, id_group);
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
                    cStmt.setString(21, inAc1);//InAc1_p;
                    cStmt.setString(22, inAc2);//InAc2_p;
                    cStmt.setInt(23, state_p);//State_p;
                    cStmt.setInt(24, uotAc_p);//UotAc_p;
                    cStmt.setInt(25, life_p);//life_p;
                    cStmt.setString(26, outD1);//outD1;
                    cStmt.setString(27, outD2);//outD2;
                    cStmt.setString(28, insur);
                    cStmt.setString(29, "-1");//d_insur1;
                    cStmt.setString(30, "-1");//d_insur2;
                    cStmt.setInt(31, id_dislocation);
                    cStmt.setInt(32, id_typetc);
                    cStmt.setInt(33, arenda);
                    cStmt.setInt(34, spisat);
                    cStmt.setInt(35, fuel_p);
                    cStmt.setInt(36, id_PreServ);
                    cStmt.setInt(37, notATX_p);//notATX_p;
                    cStmt.setInt(38, -1);//State_priz
                    cStmt.setString(39, "-1");//modelDVC;
                    cStmt.setString(40, volumDVC);
                    cStmt.setInt(41, typeDVC);
                    cStmt.setInt(42, manufactureTC);
                    cStmt.setInt(43, spisat_next);
                    cStmt.setString(44, "-1");//color



                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listInventarModel.clear();
                            while (rs.next()) {
                                listInventarModel.add(new InventarModel(rs.getString(1),rs.getString(2),
                                        rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                                        rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
                                        rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),
                                        rs.getFloat(23),rs.getString(24),rs.getString(25),rs.getString(26),rs.getInt(27),rs.getString(28),rs.getString(29),
                                        rs.getString(30),rs.getString(31),rs.getString(32)));
                            }
                            managerPages.setPage("reports/reportInventar.xhtml");
                            //FacesContext.getCurrentInstance().getExternalContext().redirect("listavto2.jsf");
                        }
                    } else logger.log(Level.ERROR, "false");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }
}
