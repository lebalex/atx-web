/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.lebalex.atx.controls;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import xyz.lebalex.atx.utils.FacesUtils;


/**
 *
 * @author ivc_lebedevav
 */
@Named
@ViewScoped
public class TestControl  implements Serializable{
    static Logger logger = Logger.getLogger(TestControl.class);

    private String username;




    /**
     * Creates a new instance of DareiledLogin
     */
    public TestControl() {
    }
    @PostConstruct
    public void initialize() {
        //this.username = null;


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void find(ActionEvent e) {

        logger.log(Level.WARN, this.username+" wrong password");

        /*String param = FacesUtils.getRequestParameter("myParam");

        if (param == null || param.length() == 0) {
            param = (String) e.getComponent().getAttributes().get("myParam");
        }*/





    }





}
