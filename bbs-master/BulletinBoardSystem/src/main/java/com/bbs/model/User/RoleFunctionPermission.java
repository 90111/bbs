package com.bbs.model.User;

public class RoleFunctionPermission {

    private int id;
    private int role_info_id;
    private int function_info_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_info_id() {
        return role_info_id;
    }

    public void setRole_info_id(int role_info_id) {
        this.role_info_id = role_info_id;
    }

    public int getFunction_info_id() {
        return function_info_id;
    }

    public void setFunction_info_id(int function_info_id) {
        this.function_info_id = function_info_id;
    }
}
