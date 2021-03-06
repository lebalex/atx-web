package xyz.lebalex.atx.controls;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import xyz.lebalex.atx.AtxBase;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ManagerPages extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ManagerPages.class);
    private String page="news.xhtml";
    private int whatLibr=-1;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    public void menu_action(ActionEvent event) {
        if(((String) event.getComponent().getAttributes().get("page")).equalsIgnoreCase("libr")){
            try {
                whatLibr = Integer.parseInt((String) event.getComponent().getAttributes().get("what"));
            }catch(Exception e){}
        }

        page = (String) event.getComponent().getAttributes().get("page")+".xhtml";
    }

    public int getWhatLibr() {
        return whatLibr;
    }
}
