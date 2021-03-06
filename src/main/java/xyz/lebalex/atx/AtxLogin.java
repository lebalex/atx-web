/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.lebalex.atx;

import java.io.Serializable;

import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.event.ActionEvent;

import com.google.common.hash.Hashing;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import xyz.lebalex.atx.consts.Txt;
import xyz.lebalex.atx.utils.FacesUtils;


/**
 *
 * @author ivc_lebedevav
 */
@Named
@SessionScoped
public class AtxLogin extends AtxBase implements Serializable{
    static Logger logger = Logger.getLogger(AtxLogin.class);
    private boolean loginBool=false;
    private String username;
    private String password;
    private String fio;
    private int user_id;
    private int region_id;
    private int depart_id;
    private List<String> roles = new ArrayList();



    /**
     * Creates a new instance of DareiledLogin
     */
    public AtxLogin() {
    }
    @PostConstruct
    public void initialize() {
        this.user_id = 0;
        this.username = null;
        this.password = null;
        this.fio = null;
        this.roles.clear();

    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public int getUser_id() {
        return user_id;
    }

    public List<String> getRoles() {
        return roles;
    }
    public boolean getAccess(String role_code)
    {
        return roles.contains(role_code);
    }

    public void login(ActionEvent e) {

        /*String param = FacesUtils.getRequestParameter("myParam");

        if (param == null || param.length() == 0) {
            param = (String) e.getComponent().getAttributes().get("myParam");
        }*/
        //logger.log(Level.INFO, this.password);
        FacesContext ctx = FacesContext.getCurrentInstance();
        String myConstantValue =
                ctx.getExternalContext().getInitParameter("javax.faces.PROJECT_STAGE");
        if(myConstantValue.equalsIgnoreCase("Development"))
            logger.log(Level.INFO, Hashing.sha512().hashString(this.password, StandardCharsets.UTF_8).toString());

        try(Connection conn=getConnection();
            PreparedStatement prst = conn.prepareStatement("select id, username, fio from ATX_UVD.dbo.tWebUsers where username=? and pwd=?");
        )
        {
            prst.setString(1, this.username);
            prst.setString(2, Hashing.sha512().hashString(this.password, StandardCharsets.UTF_8).toString());
            //prst.setString(2, this.password);
            try (ResultSet rs = prst.executeQuery()) {

                if (rs.next())
                {
                    this.user_id = rs.getInt("id");
                    this.username = rs.getString("username");
                    this.fio = rs.getString("fio");
                    /*тут возьмем его роли*/
                    try (PreparedStatement role_prst = conn.prepareStatement("select role_code from ATX_UVD.dbo.tUsersRole inner join ATX_UVD.dbo.tWebRoles on role_id=id  where user_id=?")) {
                        role_prst.setInt(1, this.user_id);
                        try (ResultSet role_rs = role_prst.executeQuery()) {
                            while(role_rs.next()) {
                                this.roles.add(role_rs.getString("role_code"));
                            }
                        }
                    }
                    /*тут возьмем его регион*/
                    try (PreparedStatement role_prst = conn.prepareStatement("select region_id from ATX_UVD.dbo.tUsersRegion where user_id=?")) {
                        role_prst.setInt(1, this.user_id);
                        try (ResultSet s = role_prst.executeQuery()) {
                            if(rs.next()) {
                                this.region_id = rs.getInt("region_id");
                            }else
                                this.region_id = 0;
                        }
                    }
                    /*тут возьмем его отдел*/
                    try (PreparedStatement role_prst = conn.prepareStatement("select depart_id from ATX_UVD.dbo.tUsersDepart where user_id=?")) {
                        role_prst.setInt(1, this.user_id);
                        try (ResultSet s = role_prst.executeQuery()) {
                            if(rs.next()) {
                                this.depart_id = rs.getInt("region_id");
                            }else
                                this.depart_id = 0;
                        }
                    }
                    logger.log(Level.INFO, this.user_id);
                    logger.log(Level.INFO, this.username);
                    logger.log(Level.INFO, this.fio);
                    logger.log(Level.INFO, this.roles);
                    FacesUtils.Redirect("index.jsf");
                }else
                {
                    logger.log(Level.WARN, this.username+" wrong password");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Ошибка", Txt.ACCESS_ERROR));
                }
            }
        }
        catch (Exception ex) {
            logger.log(Level.ERROR, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Ошибка", ex.getMessage()));
        }



    }

    public void exit(ActionEvent e) {
        this.user_id=0;
        this.username=null;
        this.roles.clear();
        FacesUtils.Redirect("login.jsf");
    }


    public boolean isLoginBool() {
        return loginBool;
    }

    public void setLoginBool(boolean loginBool) {
        this.loginBool = loginBool;
    }




    public int getRegion_id() {
        return region_id;
    }

    public int getDepart_id() {
        return depart_id;
    }


    public boolean isSave(String role, int r_id, int d_id)
    {
        if(this.roles.contains("USER")) return false;
        if(!this.roles.contains(role)) return false;
        if(this.region_id!=0 && r_id!=this.region_id) return false;
        if(this.depart_id!=0 && d_id!=this.depart_id) return false;
        return true;
    }
    public boolean isSave(String role)
    {
        if(this.roles.contains("USER")) return false;
        if(!this.roles.contains(role)) return false;
        return true;
    }
    


}
