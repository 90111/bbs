package com.bbs.model.Message;

import java.util.Date;

public class MessageInfo {
    private int id;
    private int send_user_id;
    private int receive_user_id;
    private Date send_time;
    private String content;

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getSend_user_id(){return send_user_id;}
    public void setSend_user_id(int send_user_id){
        this.send_user_id=send_user_id;
    }
    public int getReceive_user_id(){return receive_user_id;}
    public void setReceive_user_id(int receive_user_id){
        this.receive_user_id=receive_user_id;
    }
    public Date getSend_time(){return send_time;}
    public void setSend_time(Date send_time){
        this.send_time=send_time;
    }
    public String getContent(){return content;}
    public void setContent(String content){
        this.content=content;
    }
}
