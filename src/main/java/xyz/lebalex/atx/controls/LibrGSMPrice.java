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
public class LibrGSMPrice extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(LibrGSMPrice.class);
    private List<LibrModel> listParentModel = new ArrayList();
    private List<LibrGSMPriceModel> listChildModel = new ArrayList();
    private int id_parent;


    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;


    public LibrGSMPrice() {
    }

    @PostConstruct
    public void initialize() {
        this.listParentModel.clear();this.listChildModel.clear();geyLibrParent();
    }


    private void geyLibrParent() {
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetLibr(?)}")) {
                    cStmt.setInt(1, 12);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            int num = 1;
                            while (rs.next()) {
                                this.listParentModel.add(new LibrModel(rs.getInt("id"), rs.getString("name"), num));
                                num++;
                            }
                            this.id_parent = (this.listParentModel.size()>0)?((LibrModel)this.listParentModel.get(0)).getId():0;
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
                    cStmt.setInt(1, 2);
                    cStmt.setInt(2, this.id_parent);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            int num = 1;
                            while (rs.next()) {
                                this.listChildModel.add(new LibrGSMPriceModel(rs.getInt("id_mark_gsm"), rs.getDate("date"), rs.getFloat("val"), num));
                                num++;
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public void saveLibr(LibrGSMPriceModel libr, int save_del) {
        try {
            try (Connection con = getConnection()) {

                    try (PreparedStatement prst = con.prepareStatement("insert into ATX_UVD.dbo.tCoastGsm (id_mark_gsm, date, val) values (?,?,?)")) {
                        prst.setInt(1, libr.getId_mark_gsm());
                        prst.setDate(2, new java.sql.Date(libr.getDat().getTime()));
                        prst.setFloat(3, libr.getVal());
                        prst.execute();
                    }


            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public void onRowEdit(RowEditEvent<LibrGSMPriceModel> event) {

        if (atxLogin.isSave(Roles.GSM_NSI)) {
            saveLibr((LibrGSMPriceModel) event.getObject(), 0);
            viewMessage("Успешно", Txt.DATA_SAVE);
        }else {
            viewMessage("Ошибка", Txt.ACCESS_ERROR);
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
    public void getChild()
    {
        listChildModel.clear();
        geyLibrChild();
    }

    public List<LibrGSMPriceModel> getListChildModel() {
        return listChildModel;
    }

    public void setListChildModel(List<LibrGSMPriceModel> listChildModel) {
        this.listChildModel = listChildModel;
    }

    public void onAddNew() {
        LibrGSMPriceModel e = new LibrGSMPriceModel( this.id_parent, new Date(), 0, this.listChildModel.size() + 1);
        this.listChildModel.add(e);
    }

    public void onDelete(int num) {
        if (atxLogin.isSave(Roles.GSM_NSI)) {
        Iterator<LibrGSMPriceModel> i = this.listChildModel.iterator();
        while (i.hasNext()) {
            LibrGSMPriceModel agr = i.next();
            if (agr.getNum() == num) {
                //saveLibr(this.what, agr, 1);
                i.remove();
            }
        }
        } else {
            viewMessage("Ошибка", Txt.ACCESS_ERROR);
        }
    }

}
