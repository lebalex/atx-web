package xyz.lebalex.atx.models;

import java.io.Serializable;

public class RoleModel implements Serializable {
    private int id;
    private String role_code;
    private String role_name;
    private boolean selected;

    public RoleModel(int id, String role_code, String role_name, boolean selected) {
        this.id = id;
        this.role_code = role_code;
        this.role_name = role_name;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean exec) {
        this.selected = exec;
    }
}
