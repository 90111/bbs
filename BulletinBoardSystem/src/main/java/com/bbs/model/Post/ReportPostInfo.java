package com.bbs.model.Post;

import java.util.Date;

public class ReportPostInfo {
    private int id;
    private int report_title_id;
    private int owner;
    private Date report_time;
    private String report_reason;

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public int getReport_title_id(){return report_title_id;}
    public void setReport_title_id(int report_title_id){
        this.report_title_id=report_title_id;
    }

    public  int getOwner(){return owner;}
    public void setOwner(int owner){
        this.owner=owner;
    }

    public Date getReport_time(){return report_time;}
    public void setReport_time(Date report_time){
        this.report_time=report_time;
    }

    public String getReport_reason(){return report_reason;}
    public void setReport_reason(String report_reason){
        this.report_reason=report_reason;
    }
}
