package com.bbs.model.User;

import java.util.List;

public class RoleInfo {

    private int id;
    private String code;
    private String name;
    private List<FunctionInfo> functionInfos;

    public List<FunctionInfo> getFunctionInfos() {
        return functionInfos;
    }

    public void setFunctionInfos(List<FunctionInfo> functionInfos) {
        this.functionInfos = functionInfos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
