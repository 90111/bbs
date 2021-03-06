package com.bbs.model.Post;

import java.util.Date;
public class PostTitleInfo {
    private int id;
    private int plate_id;
    private int districtInfo_id;
    private String title;
    private int owner;
    private String content;
    private Date post_time;
    private int reply_num;
    private Date reply_time;
    private int view_num;
    private int like_num;
    private int recommend_num;
    private String delete_reason;
    private Date delete_time;
    private String state;
    private String image;
    private String icon;
    private String nick_name;
    private boolean Liked = false;
    private boolean collected = false;

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isLiked() {
        return Liked;
    }

    public void setLiked(boolean liked) {
        Liked = liked;
    }

    public int getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(int plate_id) {
        this.plate_id = plate_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public int getDistrictInfo_id(){return districtInfo_id;}
    public void setDistrictInfo_id(int districtInfo_id){
        this.districtInfo_id=districtInfo_id;
    }

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}

    public int getOwner(){return owner;}
    public void setOwner(int owner){this.owner=owner;}

    public String getContent(){return content;}
    public void setContent(String content){this.content=content;}

    public Date getPost_time(){return post_time;}
    public void setPost_time(Date post_time){
        this.post_time=post_time;
    }

    public int getReply_num(){return reply_num;}
    public void setReply_num(int reply_num){this.reply_num=reply_num;}

    public Date getReply_time(){return reply_time;}
    public void setReply_time(Date reply_time){
        this.reply_time=reply_time;
    }

    public int getView_num(){return view_num;}
    public void setView_num(int view_num){
        this.view_num=view_num;
    }

    public int getLike_num(){return like_num;}
    public void setLike_num(int like_num){
        this.like_num=like_num;
    }

    public int getRecommend_num(){return recommend_num;}
    public void setRecommend_num(int recommend_num){
        this.recommend_num=recommend_num;
    }

    public String getDelete_reason(){return  delete_reason;}
    public void setDelete_reason(String delete_reason){
        this.delete_reason=delete_reason;
    }

    public Date getDelete_time(){return delete_time;}
    public void setDelete_time(Date delete_time){
        this.delete_time=delete_time;
    }

    public String getState(){return state;}
    public void setState(String state){this.state=state;}
}
