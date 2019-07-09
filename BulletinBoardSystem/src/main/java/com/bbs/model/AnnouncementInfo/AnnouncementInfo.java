package com.bbs.model.AnnouncementInfo;

import java.util.Date;

public class AnnouncementInfo {
    private int id;
    private int owner;
    private Date post_time;
    private String content;

    public int getId(){return id;}
    public void setId(int id){
        this.id=id;
    }
    public int getOwner(){return owner;}
    public void setOwner(int owner){this.owner=owner;}
    public Date getPost_time(){return post_time;}
    public void setPost_time(Date post_time){
        this.post_time=post_time;
    }
    public String getContent(){return content;}
    public void setContent(String content) {
        this.content = content;
    }
}
