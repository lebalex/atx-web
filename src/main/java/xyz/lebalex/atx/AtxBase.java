/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.lebalex.atx;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import xyz.lebalex.atx.models.BaseNormModel;
import xyz.lebalex.atx.utils.FacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ivc_lebedevav
 */
public class AtxBase {
    private static Logger logger2 = Logger.getLogger(AtxBase.class);
    private static AtxApp atxApp = AtxApp.getInstance();
    private boolean saveButtonVisible=true;
    private int status=0;


    public Connection getConnection()
    {
        return atxApp.getConnection();
    }
    public AtxLogin getAtxLogin()
    {
        return (AtxLogin)FacesUtils.getAtxLogin();
    }

    public String getSettingData(String key)
    {
        try{
            try (Connection con = getConnection()) {
                try (PreparedStatement prst = con.prepareStatement("select value_p from ATX_UVD.dbo.tSettingProgramm where param_p=?")) {
                    prst.setString(1, key);
                    try (ResultSet rs = prst.executeQuery()) {
                        if (rs.next()) {
                            return rs.getString(1);
                        }else
                            return null;
                    }
                }
            }
        }catch(Exception ee)
        {
        return null;
        }
    }
    public String getLitter(int idx)
    {
        switch(idx)
        {
            case 1:return "A";
            case 2:return "B";
            case 3:return "C";
            case 4:return "D";
            case 5:return "E";
            case 6:return "F";
            case 7:return "G";
            case 8:return "H";
            default:return "A";
        }
    }


    /*public boolean isSaveButtonVisible() {
        if(!getSaveB(getId_Razdel(this.getClass().getName()))) saveButtonVisible=false;
        return saveButtonVisible;
    }*/

    /*public void setSaveButtonVisible(boolean saveButtonVisible) {
        //if(getSaveB(getId_Razdel(this.getClass().getName())))
            this.saveButtonVisible = saveButtonVisible;
        //else
        //    this.saveButtonVisible=false;
    }
    public void setSaveButtonVisible(int status) {
        if (status == 1) 
                        setSaveButtonVisible(false);
                     else 
                        setSaveButtonVisible(true);
    }*/
    /*public boolean getSaveB(String id_razdel)
    {
        return getAtxLogin().isSaveButton(id_razdel);
    }*/
    public String getId_Razdel(String name_razdel)
    {
        String n="";
        String a = name_razdel.replace("_", ".");
        Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher=pat.matcher(a);
        while (matcher.find()) {
            n=matcher.group();
        }
        return n;
    }

    public void viewMessage(String title, String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title, message));
    }


}
