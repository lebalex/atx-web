package xyz.lebalex.atx.controls;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.AtxLogin;
import xyz.lebalex.atx.consts.Roles;
import xyz.lebalex.atx.consts.Txt;
import xyz.lebalex.atx.models.BaseNormModel;
import xyz.lebalex.atx.models.LibrGSMYearExplModel;
import xyz.lebalex.atx.models.LibrModel;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Named
@ViewScoped
public class LibrGSMYearExpl extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(LibrGSMYearExpl.class);
    private List<LibrGSMYearExplModel> listYearExplModel = new ArrayList();
    private float valYearExplBig;
    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;


    public LibrGSMYearExpl() {
    }

    @PostConstruct
    public void initialize() {
        getLibr();
    }

    private void getLibr() {
        try {
            listYearExplModel.clear();
            try (Connection con = getConnection()) {
                try (PreparedStatement cStmt = con.prepareStatement("select year, val from ATX_UVD.dbo.tYearExpl order by year")) {
                    try (ResultSet rs = cStmt.executeQuery()) {
                        int num = 1;
                        while (rs.next()) {
                            this.listYearExplModel.add(new LibrGSMYearExplModel(rs.getInt("year"), rs.getFloat("val"), num));
                            num++;
                        }
                    }
                }
                try (PreparedStatement cStmt = con.prepareStatement("select val from ATX_UVD.dbo.tYearExplBig")) {
                    try (ResultSet rs = cStmt.executeQuery()) {
                        if (rs.next()) {
                            this.valYearExplBig = rs.getFloat("val");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public List<LibrGSMYearExplModel> getListYearExplModel() {
        return listYearExplModel;
    }

    public void setListYearExplModel(List<LibrGSMYearExplModel> listYearExplModel) {
        this.listYearExplModel = listYearExplModel;
    }

    public float getValYearExplBig() {
        return valYearExplBig;
    }

    public void setValYearExplBig(float valYearExplBig) {
        this.valYearExplBig = valYearExplBig;
    }

    public void onRowEdit(RowEditEvent<LibrGSMYearExplModel> event) {

        if (atxLogin.isSave(Roles.GSM_NSI)) {
            if (saveLibr((LibrGSMYearExplModel) event.getObject(), 0))
                viewMessage("Успешно", Txt.DATA_SAVE);
            else
                viewMessage("Ошибка", Txt.DATA_SAVE_ERROR);
        } else {
            viewMessage("Ошибка", Txt.ACCESS_ERROR);
        }
    }

    public void onAddNew() {
        LibrGSMYearExplModel e = new LibrGSMYearExplModel(0, 0, this.listYearExplModel.size() + 1);
        this.listYearExplModel.add(e);
    }

    public void onDelete(int num) {
        if (atxLogin.isSave(Roles.GSM_NSI)) {
        Iterator<LibrGSMYearExplModel> i = this.listYearExplModel.iterator();
        while (i.hasNext()) {
            LibrGSMYearExplModel agr = i.next();
            if (agr.getNum() == num) {
                //saveLibr(this.what, agr, 1);
                i.remove();
            }
        }
        } else {
            viewMessage("Ошибка", Txt.ACCESS_ERROR);
        }
    }

    public void saveYearExplBig(ActionEvent e) {
        if (atxLogin.isSave(Roles.GSM_NSI)) {
            try {
                try (Connection con = getConnection()) {
                    try (PreparedStatement prst2 = con.prepareStatement("update ATX_UVD.dbo.tYearExplBig set val =?")) {
                        prst2.setFloat(1, getValYearExplBig());
                        prst2.execute();
                        viewMessage("Успешно", Txt.DATA_SAVE);
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
                viewMessage("Ошибка", Txt.DATA_SAVE_ERROR);
            }
        } else {
            viewMessage("Ошибка", Txt.ACCESS_ERROR);
        }
    }

    public boolean saveLibr(LibrGSMYearExplModel libr, int save_del) {
        try {
            try (Connection con = getConnection()) {
                try (PreparedStatement prst = con.prepareStatement("select year from ATX_UVD.dbo.tYearExpl where year=?")) {
                    prst.setInt(1, libr.getYear());
                    try (ResultSet rs = prst.executeQuery()) {
                        if (rs.next()) {
                            try (PreparedStatement prst2 = con.prepareStatement("update ATX_UVD.dbo.tYearExpl set val =? where year=?")) {
                                prst2.setFloat(1, libr.getVal());
                                prst2.setInt(2, libr.getYear());
                                prst2.execute();
                            }
                        } else {
                            try (PreparedStatement prst2 = con.prepareStatement("insert into ATX_UVD.dbo.tYearExpl values(?,?)")) {
                                prst2.setInt(1, libr.getYear());
                                prst2.setFloat(2, libr.getVal());
                                prst2.execute();
                            }
                        }
                    }
                }

                return true;
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
            return false;
        }
    }


}
