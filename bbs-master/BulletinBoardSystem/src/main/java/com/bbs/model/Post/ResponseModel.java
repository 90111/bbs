package com.bbs.model.Post;


import com.bbs.model.User.UserLoginInfo;

public class ResponseModel {

    String msg;
    UserLoginInfo userLoginInfo;

    public ResponseModel(){}

    public ResponseModel(String msg, UserLoginInfo userLoginInfo){
        this.msg = msg;
        this.userLoginInfo = userLoginInfo;
    }
}
