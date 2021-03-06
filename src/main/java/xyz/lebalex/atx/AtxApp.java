/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.lebalex.atx;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author ivc_lebedevav
 */
public class AtxApp implements Serializable{
    static Logger logger = Logger.getLogger(AtxApp.class);
    private static AtxApp instance;
    private String db_aliase;


    public String getDb_aliase() {
        if(db_aliase==null)
        {
            ResourceBundle dbBundle = ResourceBundle.getBundle("db");
            db_aliase = dbBundle.getString("atx.db");
            logger.log(Level.INFO, db_aliase);
        }
        return db_aliase;
    }

    public static AtxApp getInstance() {
        if (instance == null) {
            instance = new AtxApp();
        }

        return instance;
    }    
    public Connection getConnection()
    {
        try{
            return getAtxDB().getConnection();
        }catch(Exception e)
        {
            logger.log(Level.ERROR, "Exception: {0}", e);
            return null;
        }
    }

    private DataSource getAtxDB() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/jdbc/"+getDb_aliase());
    }
    
}
