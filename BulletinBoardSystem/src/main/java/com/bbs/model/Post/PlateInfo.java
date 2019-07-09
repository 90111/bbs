package com.bbs.model.Post;

import java.util.Date;
import java.util.List;

public class PlateInfo {

    private int id;
    private  int post_num;
    private String plate_name;
    private List<DistrictInfo> districtInfos;

    public List<DistrictInfo> getDistrictInfos() {
        return districtInfos;
    }

    public void setDistrictInfos(List<DistrictInfo> districtInfos) {
        this.districtInfos = districtInfos;
    }

    public int getId() {return id;}

    public void setId(int id){this.id=id;}

    public int getPost_num(){return post_num;}

    public void setPost_num(int post_num){this.post_num=post_num;}

    public String getPlate_name(){return plate_name;}

    public void setPlate_name(String plate_name){this.plate_name=plate_name;}

}
