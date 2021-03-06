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
public class LibrGSMBaseNorm extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(LibrGSMBaseNorm.class);
    private List<LibrModel> listMarkModel = new ArrayList();
    private List<LibrModel> listModelModel = new ArrayList();
    private List<LibrModel> listGSMModel = new ArrayList();
    private int id_mark;
    private int id_model;
    private int id_gsm;
    private List<BaseNormModel> listBaseNormModel = new ArrayList();


    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;


    public LibrGSMBaseNorm() {
    }

    @PostConstruct
    public void initialize() {
        listGSMModel.clear();
        listMarkModel.clear();
        geyLibrMark();

    }

    private void geyLibrGsm() {
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetLibr(?)}")) {
                    cStmt.setInt(1, 12);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            while (rs.next()) {
                                this.listGSMModel.add(new LibrModel(rs.getInt("id"), rs.getString("name"), 0));
                            }
                            this.id_gsm = (this.listGSMModel.size() > 0) ? ((LibrModel) this.listGSMModel.get(0)).getId() : 0;
                            getBaseNorm();
                        }
                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    private void geyLibrMark() {
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetLibr(?)}")) {
                    cStmt.setInt(1, 6);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            while (rs.next()) {
                                this.listMarkModel.add(new LibrModel(rs.getInt("id"), rs.getString("name"), 0));
                            }
                            this.id_mark = (this.listMarkModel.size() > 0) ? ((LibrModel) this.listMarkModel.get(0)).getId() : 0;
                            geyLibrModel();
                        }
                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    private void geyLibrModel() {
        try {
            listModelModel.clear();
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetLibr2(?,?)}")) {
                    cStmt.setInt(1, 1);
                    cStmt.setInt(2, this.id_mark);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            while (rs.next()) {
                                this.listModelModel.add(new LibrModel(rs.getInt("id"), rs.getString("name"), 0));
                            }
                            this.id_model = (this.listModelModel.size() > 0) ? ((LibrModel) this.listModelModel.get(0)).getId() : 0;
                            if(this.listGSMModel.size()==0)
                                geyLibrGsm();
                        }
                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    private void getBaseNorm() {
        try {
            listBaseNormModel.clear();
            try (Connection con = getConnection()) {
                try (PreparedStatement cStmt = con.prepareStatement("select id, id_mark, id_model, id_gsm, norm  from ATX_UVD.dbo.tbasenormgsm where id_mark=? and id_model=? and id_gsm=? order by id")) {
                    cStmt.setInt(1, this.id_mark);
                    cStmt.setInt(2, this.id_model);
                    cStmt.setInt(3, this.id_gsm);
                    try (ResultSet rs = cStmt.executeQuery()) {
                        int num = 1;
                        while (rs.next()) {
                            this.listBaseNormModel.add(new BaseNormModel(rs.getInt("id"), rs.getInt("id_mark"), rs.getInt("id_model"), rs.getInt("id_gsm"), rs.getFloat("norm"), num));
                            num++;
                        }
                    }

                }
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public void handleChange() {
        logger.log(Level.ERROR, this.id_mark);
        geyLibrModel();
        logger.log(Level.ERROR, this.id_model);
        logger.log(Level.ERROR, this.id_gsm);
        getBaseNorm();

    }

    public void handleChange2() {
        //logger.log(Level.ERROR, this.id_model);
        //logger.log(Level.ERROR, this.id_gsm);
        getBaseNorm();

    }

    public List<LibrModel> getListMarkModel() {
        return listMarkModel;
    }

    public void setListMarkModel(List<LibrModel> listMarkModel) {
        this.listMarkModel = listMarkModel;
    }

    public List<LibrModel> getListModelModel() {
        return listModelModel;
    }

    public void setListModelModel(List<LibrModel> listModelModel) {
        this.listModelModel = listModelModel;
    }

    public List<LibrModel> getListGSMModel() {
        return listGSMModel;
    }

    public void setListGSMModel(List<LibrModel> listGSMModel) {
        this.listGSMModel = listGSMModel;
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

    public int getId_gsm() {
        return id_gsm;
    }

    public void setId_gsm(int id_gsm) {
        this.id_gsm = id_gsm;
    }

    public List<BaseNormModel> getListBaseNormModel() {
        return listBaseNormModel;
    }

    public void setListBaseNormModel(List<BaseNormModel> listBaseNormModel) {
        this.listBaseNormModel = listBaseNormModel;
    }

    public boolean saveLibr(BaseNormModel libr, int save_del) {
        try {
            try (Connection con = getConnection()) {
                if (libr.getId() == 0) {
                    String generatedColumns[] = {"id"};
                    try (PreparedStatement prst = con.prepareStatement("insert into ATX_UVD.dbo.tbasenormgsm (id_mark, id_model, id_gsm, norm) values (?,?,?,?)", generatedColumns)) {
                        prst.setInt(1, libr.getId_mark());
                        prst.setInt(2, libr.getId_model());
                        prst.setInt(3, libr.getId_gsm());
                        prst.setFloat(4, libr.getNorm());
                        prst.execute();
                        try (ResultSet rs = prst.getGeneratedKeys()) {

                            if (rs.next()) {
                                libr.setId(rs.getInt(1));
                            }
                        }
                    }
                } else {
                    try (PreparedStatement prst = con.prepareStatement("update ATX_UVD.dbo.tbasenormgsm set norm =? where id=?")) {
                        prst.setFloat(1, libr.getNorm());
                        prst.setInt(2, libr.getId());
                        prst.execute();
                    }
                }
                return true;
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
            return false;
        }
    }

    public void onRowEdit(RowEditEvent<BaseNormModel> event) {

        if (atxLogin.isSave(Roles.GSM_NSI)) {
            if(saveLibr((BaseNormModel) event.getObject(), 0))
                viewMessage("Успешно", Txt.DATA_SAVE);
            else
                viewMessage("Ошибка", Txt.DATA_SAVE_ERROR);
        } else {
            viewMessage("Ошибка", Txt.ACCESS_ERROR);
        }
    }

    public void onAddNew() {
        BaseNormModel e = new BaseNormModel(this.id_mark, this.id_model, this.id_gsm, this.listBaseNormModel.size() + 1);
        this.listBaseNormModel.add(e);
    }

    public void onDelete(int num) {
        Iterator<BaseNormModel> i = this.listBaseNormModel.iterator();
        while (i.hasNext()) {
            BaseNormModel agr = i.next();
            if (agr.getNum() == num) {
                //saveLibr(this.what, agr, 1);
                i.remove();
            }
        }
    }
}
