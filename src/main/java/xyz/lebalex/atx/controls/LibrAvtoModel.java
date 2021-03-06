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
import java.util.Iterator;
import java.util.List;

@Named
@ViewScoped
public class LibrAvtoModel extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(LibrAvtoModel.class);
    private List<LibrModel> listParentModel = new ArrayList();
    private List<ModelAvtLibrModel> listChildModel = new ArrayList();
    private int id_parent;


    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;


    public LibrAvtoModel() {
    }

    @PostConstruct
    public void initialize() {
        this.listParentModel.clear();this.listChildModel.clear();geyLibrParent();
    }


    private void geyLibrParent() {
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetLibr(?)}")) {
                    cStmt.setInt(1, 6);
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
                    cStmt.setInt(1, 1);
                    cStmt.setInt(2, this.id_parent);
                    boolean hadResults = cStmt.execute();
                    if (hadResults) {
                        try (ResultSet rs = cStmt.getResultSet()) {
                            int num = 1;
                            while (rs.next()) {
                                this.listChildModel.add(new ModelAvtLibrModel(rs.getInt("id"), this.id_parent, rs.getString("name"), rs.getInt("expl_year"), rs.getInt("expl_year"), num));
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

    public void saveLibr(ModelAvtLibrModel libr, int save_del) {
        try {
            try (Connection con = getConnection()) {
                String generatedColumns[] = {"id"};
                if(libr.getId()==0) {
                    try (PreparedStatement prst = con.prepareStatement("insert into ATX_UVD.dbo.tModel_avt (id_mark, name, expl_year) values (?,?,?)", generatedColumns)) {
                        prst.setInt(1, libr.getId_mark());
                        prst.setString(2, libr.getName());
                        prst.setInt(3, libr.getExpl_year());
                        prst.execute();
                        try (ResultSet rs = prst.getGeneratedKeys()) {

                            if (rs.next()) {
                                int id = rs.getInt(1);
                                libr.setId(id);
                            }
                        }
                    }
                }else
                {
                    try (PreparedStatement prst = con.prepareStatement("update ATX_UVD.dbo.tModel_avt set expl_year=? where id=?")) {
                        prst.setInt(1, libr.getExpl_year());
                        prst.setInt(2, libr.getId());
                        prst.execute();
                    }
                }
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.UpdateExplYear(?,?,?,?)}")) {
                    cStmt.setInt(1, libr.getId_mark());
                    cStmt.setString(2, libr.getName());
                    cStmt.setInt(3, libr.getExpl_year());
                    cStmt.setInt(4, libr.getOld_expl_year());
                    cStmt.execute();
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public void onRowEdit(RowEditEvent<ModelAvtLibrModel> event) {

        if (atxLogin.isSave(Roles.TECH_NSI)) {
            saveLibr((ModelAvtLibrModel) event.getObject(), 0);
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

    public List<ModelAvtLibrModel> getListChildModel() {
        return listChildModel;
    }

    public void setListChildModel(List<ModelAvtLibrModel> listChildModel) {
        this.listChildModel = listChildModel;
    }

    public void onAddNew() {
        ModelAvtLibrModel e = new ModelAvtLibrModel(0, this.id_parent, "введите название", 0, 0,this.listChildModel.size() + 1);
        this.listChildModel.add(e);
    }

    public void onDelete(int num) {
        Iterator<ModelAvtLibrModel> i = this.listChildModel.iterator();
        while (i.hasNext()) {
            ModelAvtLibrModel agr = i.next();
            if (agr.getNum() == num) {
                //saveLibr(this.what, agr, 1);
                i.remove();
            }
        }
    }

}
