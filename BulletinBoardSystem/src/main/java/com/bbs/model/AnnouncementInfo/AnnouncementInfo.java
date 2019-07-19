package com.bbs.model.AnnouncementInfo;

import com.bbs.model.User.UserBaseInfo;

import java.util.Date;

public class AnnouncementInfo {
    private int id;
    private int owner;
    private int plate_id=0;
    private int district_id=0;
    private String plate_name;
    private String district_name;
    private Date post_time;
    private String title;
    private String content;
    private UserBaseInfo userBaseInfo;

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

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
