package com.bbs.model.FriendlyLinkInfo;

public class FriendlyLinkInfo {
    private int id;
    private String link_name;
    private String link_url;

    public int getId(){return id;}
    public void setId(int id){
        this.id=id;
    }
    public String getLink_name(){return link_name;}
    public void setLink_name(String link_name){
        this.link_name=link_name;
    }
    public String getLink_url(){return link_url;}
    public void setLink_url(String link_url){
        this.link_url=link_url;
    }
}
