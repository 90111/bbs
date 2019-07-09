package com.bbs.model.User;


public class ResponseModel {

    String msg;
    UserLoginInfo userLoginInfo;

    public ResponseModel(){}

    public ResponseModel(String msg, UserLoginInfo userLoginInfo){
        this.msg = msg;
        this.userLoginInfo = userLoginInfo;
    }
}
