package com.bbs.model.AnnouncementInfo;

import java.util.Date;

public class AnnouncementInfo {
    private int id;
    private int owner;
    private int plate_id=-1;
    private int district_id=-1;
    private Date post_time;
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(int plate_id) {
        this.plate_id = plate_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

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
