package xyz.lebalex.atx.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private int id;
    private String username;
    private String pwd;
    private String pwd_repeat;
    private String fio;
    private String email;
    private String phone;
    private String decsription;
    private Long region;
    private Long depart;

    public UserModel()
    {
        this.id = 0;
    }

    public UserModel(int id, String username, String fio, String email, String phone, String decsription) {
        this.id = id;
        this.username = username;
        this.fio = fio;
        this.email = email;
        this.phone = phone;
        this.decsription = decsription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd_repeat() {
        return pwd_repeat;
    }

    public void setPwd_repeat(String pwd_repeat) {
        this.pwd_repeat = pwd_repeat;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDecsription() {
        return decsription;
    }

    public void setDecsription(String decsription) {
        this.decsription = decsription;
    }

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public Long getDepart() {
        return depart;
    }

    public void setDepart(Long depart) {
        this.depart = depart;
    }
}
