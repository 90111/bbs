package com.bbs.model.Post;

import java.util.Date;
public class ReplyInfo {
    private int id;
    private int post_title_id;
    private int user_id;
    private String content;
    private Date reply_time;
    private String nick_name;
    private String icon;

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getReply_time() {
        return reply_time;
    }

    public void setReply_time(Date reply_time) {
        this.reply_time = reply_time;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getPost_title_id(){return post_title_id;}
    public void setPost_title_id(int post_title_id){
        this.post_title_id=post_title_id;
    }
    public int getUser_id(){return user_id;}
    public void setUser_id(int user_id){
        this.user_id=user_id;
    }
    public String getContent(){return content;}
    public void setContent(String content){
        this.content=content;
    }
}
