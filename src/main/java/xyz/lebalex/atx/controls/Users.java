package xyz.lebalex.atx.controls;

import com.google.common.hash.Hashing;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.models.AvtoModel;
import xyz.lebalex.atx.models.RegionModel;
import xyz.lebalex.atx.models.RoleModel;
import xyz.lebalex.atx.models.UserModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.*;
import java.util.*;

@Named
@ViewScoped
public class Users extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(Users.class);
    private List<UserModel> listUser = new ArrayList();
    private List<RoleModel> listRoles = new ArrayList();
    private UserModel user;
    private String pwd;
    private String user_find;
    private Map<Long, String> options_region = new LinkedHashMap<Long, String>();
    ;
    private Map<Long, String> options_depart = new LinkedHashMap<Long, String>();
    ;

    @Inject
    ManagerPages managerPages;


    public Users() {
    }

    @PostConstruct
    public void initialize() {
        getAllUsers(null);
    }

    public List<UserModel> getListUser() {
        return listUser;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<RoleModel> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<RoleModel> listRoles) {
        this.listRoles = listRoles;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser_find() {
        return user_find;
    }

    public void setUser_find(String user_find) {
        this.user_find = user_find;
    }

    public Map<Long, String> getOptions_region() {
        return options_region;
    }


    public Map<Long, String> getOptions_depart() {
        return options_depart;
    }

    private void getAllUsers(String user_find) {
        try {
            try (Connection con = getConnection()) {
                try (Statement st = con.createStatement()) {
                    String sql = "select * from ATX_UVD.dbo.tWebUsers";
                    if (user_find != null) {
                        sql += " where username like '%" + user_find + "%' or fio like '%" + user_find + "%'";
                    }
                    logger.log(Level.ERROR, sql);
                    try (ResultSet rs = st.executeQuery(sql + " order by fio")) {
                        listUser.clear();
                        while (rs.next()) {
                            UserModel lu = new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("fio")
                                    , rs.getString("email")
                                    , rs.getString("phone")
                                    , rs.getString("description"));
                            listUser.add(lu);
                        }
                    }
                    options_region.clear();
                    options_region.put((long) -1, "Все");
                    try (ResultSet rs_r = con.createStatement().executeQuery("select id, name from ATX_UVD.dbo.tRegion")) {
                        while (rs_r.next()) {
                            options_region.put(rs_r.getLong(1), rs_r.getString(2));
                        }
                    }
                    options_depart.clear();
                    options_depart.put((long) -1, "Все");
                    try (ResultSet rs_d = con.createStatement().executeQuery("select id, name from ATX_UVD.dbo.tDepart")) {
                        while (rs_d.next()) {
                            options_depart.put(rs_d.getLong(1), rs_d.getString(2));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }


    }

    public void getInfo(UserModel user) {
        this.user = user;
        try {
            try (Connection con = getConnection()) {
                try (PreparedStatement role_prst = con.prepareStatement("select id, role, role_code, case when user_id IS null then 0 else 1 end as selected from ATX_UVD.dbo.tUsersRole  right join ATX_UVD.dbo.tWebRoles on role_id=id and user_id=?")) {
                    role_prst.setInt(1, this.user.getId());
                    try (ResultSet rs = role_prst.executeQuery()) {
                        listRoles.clear();
                        while (rs.next()) {
                            listRoles.add(new RoleModel(rs.getInt("id"), rs.getString("role_code"), rs.getString("role"), rs.getBoolean("selected")));
                        }
                    }
                }
                try (PreparedStatement role_prst = con.prepareStatement("select region_id from ATX_UVD.dbo.tUsersRegion where user_id=?")) {
                    role_prst.setInt(1, this.user.getId());
                    try (ResultSet rs = role_prst.executeQuery()) {
                        if (rs.next()) {
                            user.setRegion(rs.getLong("region_id"));
                        } else user.setRegion((long) -1);
                    }
                }
                try (PreparedStatement role_prst = con.prepareStatement("select depart_id from ATX_UVD.dbo.tUsersDepart where user_id=?")) {
                    role_prst.setInt(1, this.user.getId());
                    try (ResultSet rs = role_prst.executeQuery()) {
                        if (rs.next()) {
                            user.setDepart(rs.getLong("depart_id"));
                        } else user.setDepart((long) -1);
                    }
                }
            }
            managerPages.setPage("userinfo.xhtml");
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }


    }


    public void generate_pwd(ActionEvent e) {
        this.pwd = generate(8);
        this.user.setPwd(this.pwd);
    }

    public void newUser(ActionEvent e) {
        this.user = new UserModel();
        this.pwd = generate(8);
        this.user.setPwd(this.pwd);
        try {
            try (Connection con = getConnection()) {
                try (PreparedStatement role_prst = con.prepareStatement("select id, role, role_code, 0 as selected from ATX_UVD.dbo.tWebRoles")) {
                    try (ResultSet rs = role_prst.executeQuery()) {
                        listRoles.clear();
                        while (rs.next()) {
                            RoleModel rm = new RoleModel(rs.getInt("id"), rs.getString("role_code"), rs.getString("role"), rs.getBoolean("selected"));
                            if (rs.getString("role_code").equalsIgnoreCase("USER"))
                                rm.setSelected(true);
                            listRoles.add(rm);
                        }
                        managerPages.setPage("userinfo.xhtml");
                    }
                }

            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public void searchUser(ActionEvent e) {
        getAllUsers(this.user_find);

    }

    public void save(ActionEvent e) {
        boolean result = true;
        //logger.log(Level.ERROR, this.user.getRegion());
        //logger.log(Level.ERROR, this.user.getDepart());
        logger.log(Level.INFO, this.user.getId());
        /*logger.log(Level.INFO, this.user.getUsername());
        logger.log(Level.INFO, this.user.getFio());
        logger.log(Level.INFO, this.user.getEmail());
        logger.log(Level.INFO, this.user.getPhone());
        logger.log(Level.INFO, this.user.getDecsription());
        if (this.user.getPwd().length() > 0)
            logger.log(Level.INFO, this.user.getPwd());

        for (RoleModel a : listRoles
        ) {
            logger.log(Level.INFO, a.isSelected());
            logger.log(Level.INFO, a.getRole_code());
            logger.log(Level.INFO, a.getId());
        }*/
        String generatedColumns[] = {"id"};

        if (this.user.getId() == 0) {
            /*новый*/
            try {
                try (Connection con = getConnection()) {
                    try (PreparedStatement prst = con.prepareStatement("insert into ATX_UVD.dbo.tWebUsers (username, pwd, fio, email, phone, description) values (?,?,?,?,?,?)", generatedColumns)) {
                        prst.setString(1, this.user.getUsername());
                        prst.setString(2, Hashing.sha512().hashString(this.user.getPwd(), StandardCharsets.UTF_8).toString());
                        prst.setString(3, this.user.getFio());
                        prst.setString(4, this.user.getEmail());
                        prst.setString(5, this.user.getPhone());
                        prst.setString(6, this.user.getDecsription());
                        prst.execute();
                        try (ResultSet rs = prst.getGeneratedKeys()) {

                            if (rs.next()) {
                                int id = rs.getInt(1);
                                this.user.setId(id);
                            }
                        }


                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
                result = false;
            }
        } else {
            /*обновляем*/
            //this.user.setPwd("56892701");
            try {
                try (Connection con = getConnection()) {
                    String sql_with_pwd = "update ATX_UVD.dbo.tWebUsers set username=?, fio=?, email=?, phone=?, description=?, pwd=? where id=?";
                    String sql_without_pwd = "update ATX_UVD.dbo.tWebUsers set username=?, fio=?, email=?, phone=?, description=? where id=?";
                    try (PreparedStatement prst = con.prepareStatement((this.user.getPwd()!=null)?sql_with_pwd:sql_without_pwd)) {
                        prst.setString(1, this.user.getUsername());
                        prst.setString(2, this.user.getFio());
                        prst.setString(3, this.user.getEmail());
                        prst.setString(4, this.user.getPhone());
                        prst.setString(5, this.user.getDecsription());
                        if(this.user.getPwd()!=null)
                        {
                            prst.setString(6, Hashing.sha512().hashString(this.user.getPwd(), StandardCharsets.UTF_8).toString());
                            prst.setInt(7, this.user.getId());
                        }else
                            prst.setInt(6, this.user.getId());

                        prst.execute();
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
                result = false;
            }
        }
        /*перезапишем роли*/
        try {
            try (Connection con = getConnection()) {
                try (PreparedStatement prst = con.prepareStatement("delete from ATX_UVD.dbo.tUsersRole where user_id=?")) {
                    prst.setInt(1, this.user.getId());
                    prst.execute();
                    try (PreparedStatement prst_in = con.prepareStatement("insert into ATX_UVD.dbo.tUsersRole values (?,?)")) {
                        for (RoleModel r : listRoles) {
                            if (r.isSelected()) {
                                prst_in.setInt(1, this.user.getId());
                                prst_in.setInt(2, r.getId());
                                prst_in.execute();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
            result = false;
        }
        /*перезапишем регионы если не -1*/
        if(this.user.getRegion()!=-1) {
            try {
                try (Connection con = getConnection()) {
                    try (PreparedStatement prst = con.prepareStatement("delete from ATX_UVD.dbo.tUsersRegion where user_id=?")) {
                        prst.setInt(1, this.user.getId());
                        prst.execute();
                        try (PreparedStatement prst_in = con.prepareStatement("insert into ATX_UVD.dbo.tUsersRegion values (?,?)")) {
                            prst_in.setInt(1, this.user.getId());
                            prst_in.setLong(2, this.user.getRegion());
                            prst_in.execute();
                        }
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
                result = false;
            }
        }
        /*перезапишем Отдел если не -1*/
        if(this.user.getDepart()!=-1) {
            try {
                try (Connection con = getConnection()) {
                    try (PreparedStatement prst = con.prepareStatement("delete from ATX_UVD.dbo.tUsersDepart where user_id=?")) {
                        prst.setInt(1, this.user.getId());
                        prst.execute();
                        try (PreparedStatement prst_in = con.prepareStatement("insert into ATX_UVD.dbo.tUsersDepart values (?,?)")) {
                            prst_in.setInt(1, this.user.getId());
                            prst_in.setLong(2, this.user.getDepart());
                            prst_in.execute();
                        }
                    }
                }
            } catch (Exception ex) {
                logger.log(Level.ERROR, ex);
                result = false;
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        if (result) {
            getAllUsers(null);
            context.addMessage(null, new FacesMessage("Успешно", "Данные сохранены"));
        } else
            context.addMessage(null, new FacesMessage("Ошибка", "Произошла ошибка"));

    }


    private String generate(int length) {
        String[] charCategories = new String[]{
                "abcdefghijklmnopqrstuvwxyz",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "0123456789"
        };
        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        for (int i = 0; i < length; i++) {
            String charCategory = charCategories[random.nextInt(charCategories.length)];
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }

        return new String(password);
    }


}
