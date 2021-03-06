package xyz.lebalex.atx.controls;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.AtxLogin;
import xyz.lebalex.atx.consts.Roles;
import xyz.lebalex.atx.consts.Txt;
import xyz.lebalex.atx.models.*;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Named
@ViewScoped
public class LibrGSMAddPr extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(LibrGSMPrice.class);
    private List<LibrModel> listParentModel = new ArrayList();
    private GSMAddPrModel listChildModel;
    private int id_parent;


    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;


    public LibrGSMAddPr() {
    }

    @PostConstruct
    public void initialize() {
        this.listParentModel.clear();
        geyLibrParent();
    }


    private void geyLibrParent() {
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetLibr(?)}")) {
                    cStmt.setInt(1, 11);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            int num = 1;
                            while (rs.next()) {
                                this.listParentModel.add(new LibrModel(rs.getInt("id"), rs.getString("name"), num));
                                num++;
                            }
                            this.id_parent = (this.listParentModel.size() > 0) ? ((LibrModel) this.listParentModel.get(0)).getId() : 0;
                            geyLibrChild();
                        }
                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    private void geyLibrChild() {
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetLibr2(?,?)}")) {
                    cStmt.setInt(1, 3);
                    cStmt.setInt(2, this.id_parent);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            if (rs.next()) {
                                listChildModel = new GSMAddPrModel(rs.getInt("id_dislok"),
                                        rs.getFloat("pr1"),
                                        rs.getFloat("pr2"),
                                        rs.getFloat("klimat0"),
                                        rs.getFloat("klimat1"),
                                        rs.getFloat("klimat2"),
                                        rs.getFloat("klimat3"),
                                        rs.getFloat("klimat4"),
                                        rs.getFloat("klimat5"));
                            } else {
                                listChildModel = new GSMAddPrModel(this.id_parent);
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public void saveLibr(GSMAddPrModel libr) {
        try {
            if (atxLogin.isSave(Roles.TECH_NSI)) {
            try (Connection con = getConnection()) {
                if (libr.isNewRec()) {
                    try (PreparedStatement prst = con.prepareStatement("insert into ATX_UVD.dbo.tProcentGsm values (?,?,?,?,?,?,?,?,?)")) {
                        prst.setInt(1, libr.getId_dislok());
                        prst.setFloat(2, libr.getPr1());
                        prst.setFloat(3, libr.getPr1());
                        prst.setFloat(4, libr.getKlimat0());
                        prst.setFloat(5, libr.getKlimat1());
                        prst.setFloat(6, libr.getKlimat2());
                        prst.setFloat(7, libr.getKlimat3());
                        prst.setFloat(8, libr.getKlimat4());
                        prst.setFloat(9, libr.getKlimat5());
                        prst.execute();
                        libr.setNewRec(false);
                        viewMessage("Успешно", Txt.DATA_SAVE);
                    }
                } else {
                    {
                        try (PreparedStatement prst = con.prepareStatement("update ATX_UVD.dbo.tProcentGsm set pr1=?,pr2=?,klimat0=?,klimat1=?,klimat2=?,klimat3=?,klimat4=?,klimat5=? where id_dislok=?")) {
                            prst.setFloat(1, libr.getPr1());
                            prst.setFloat(2, libr.getPr1());
                            prst.setFloat(3, libr.getKlimat0());
                            prst.setFloat(4, libr.getKlimat1());
                            prst.setFloat(5, libr.getKlimat2());
                            prst.setFloat(6, libr.getKlimat3());
                            prst.setFloat(7, libr.getKlimat4());
                            prst.setFloat(8, libr.getKlimat5());
                            prst.setInt(9, libr.getId_dislok());
                            prst.execute();
                            viewMessage("Успешно", Txt.DATA_SAVE);
                        }
                    }
                }


            }
            }else {
                viewMessage("Ошибка", Txt.ACCESS_ERROR);
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
            viewMessage("Ошибка", ex.getMessage());
        }
    }

    public List<LibrModel> getListParentModel() {
        return listParentModel;
    }

    public void setListParentModel(List<LibrModel> listParentModel) {
        this.listParentModel = listParentModel;
    }

    public int getId_parent() {
        return id_parent;
    }

    public void setId_parent(int id_parent) {
        this.id_parent = id_parent;
    }

    public void getChild() {
        geyLibrChild();
    }

    public GSMAddPrModel getListChildModel() {
        return listChildModel;
    }

    public void setListChildModel(GSMAddPrModel listChildModel) {
        this.listChildModel = listChildModel;
    }


}
