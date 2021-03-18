package xyz.lebalex.atx.controls;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.AtxLogin;
import xyz.lebalex.atx.consts.Roles;
import xyz.lebalex.atx.consts.Txt;
import xyz.lebalex.atx.models.LibrModel;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Named
@ViewScoped
public class Libr extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(Libr.class);
    private List<LibrModel> listLibrModel = new ArrayList();
    private int what;
    private String tableName;


    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;


    public Libr() {
    }

    @PostConstruct
    public void initialize() {
        this.listLibrModel.clear();
    }

    public int getWhat() {
        this.what = managerPages.getWhatLibr();
        this.listLibrModel.clear();
        geyLibr(this.what);
        return what;
    }

    private void geyLibr(int what) {
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetLibr(?)}")) {
                    cStmt.setInt(1, what);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            int num = 1;
                            while (rs.next()) {
                                this.listLibrModel.add(new LibrModel(rs.getInt("id"), rs.getString("name"), num));
                                num++;
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, what);
            logger.log(Level.ERROR, ex);
        }
    }

    public void saveLibr(int what, LibrModel libr, int save_del) {
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.SetLibr(?,?,?,?,?)}")) {
                    cStmt.setInt(1, what);
                    cStmt.setInt(2, libr.getId());
                    cStmt.setString(3, libr.getName());
                    cStmt.setInt(4, save_del);
                    cStmt.registerOutParameter(5, java.sql.Types.INTEGER);
                    cStmt.execute();
                    libr.setId(cStmt.getInt("id_i"));
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, what);
            logger.log(Level.ERROR, ex);
        }
    }

    public void onRowEdit(RowEditEvent<LibrModel> event) {

        if (atxLogin.isSave(getRoleForLibr(this.what))) {
            saveLibr(this.what, (LibrModel) event.getObject(), 0);
            viewMessage("Успешно", Txt.DATA_SAVE);
        } else {
            viewMessage("Ошибка", Txt.ACCESS_ERROR);
        }
    }

    private String getRoleForLibr(int whatLibr) {
        switch (whatLibr) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return Roles.TECH_NSI;

            case 12:
            case 16:
            case 22:
            case 23:
                return Roles.GSM_NSI;

            case 13:
            case 14:
                return Roles.REM_NSI;
            default:
                return Roles.TECH_NSI;
        }
    }

    public List<LibrModel> getListLibrModel() {
        return listLibrModel;
    }

    public void setListLibrModel(List<LibrModel> listLibrModel) {
        this.listLibrModel = listLibrModel;
    }


    public void setWhat(int what) {
        this.what = what;
    }

    public void onAddNew() {
        LibrModel e = new LibrModel(0, "введите название", this.listLibrModel.size() + 1);
        this.listLibrModel.add(e);
    }

    public void onDelete(int num) {
        if (atxLogin.isSave(getRoleForLibr(this.what))) {
            Iterator<LibrModel> i = this.listLibrModel.iterator();
            while (i.hasNext()) {
                LibrModel agr = i.next();
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
