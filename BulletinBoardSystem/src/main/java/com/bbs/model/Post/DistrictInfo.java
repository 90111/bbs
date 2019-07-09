package com.bbs.model.Post;

import java.util.Date;
public class DistrictInfo {

    private int id;
    private int plate_id;
    private int post_num;
    private String district_name;

    public int getId(){return id;}
    public void setId(int id){
        this.id=id;
    }
    public int getPlate_id(){
        return plate_id;
    }
    public void setPlate_id(int plate_id){
        this.plate_id=plate_id;
    }
    public int getPost_num(){
        return post_num;
    }
    public void setPost_num(int post_num){
        this.post_num=post_num;
    }
    public String getDistrict_name(){
        return district_name;
    }
    public void setDistrict_name(String district_name){
        this.district_name=district_name;
    }
}
