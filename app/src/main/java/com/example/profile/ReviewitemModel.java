package com.example.profile;

public class ReviewitemModel {

    private int profile_image;
    private int num_of_like;
    private int like_buttom;
    private float rate;
    private String user_name, comment, comment_time;
    private String categoryid ;

    public ReviewitemModel() {
    }

    public ReviewitemModel(int profile_image, int num_of_like, int like_buttom, float rate, String user_name, String comment, String comment_time) {
        this.profile_image = profile_image;
        this.num_of_like = num_of_like;
        this.like_buttom = like_buttom;
        this.rate = rate;
        this.user_name = user_name;
        this.comment = comment;
        this.comment_time = comment_time;
    }

    public ReviewitemModel(int profile_image, int num_of_like, int like_buttom,
                           float rate, String user_name, String comment, String comment_time, String categoryid) {
        this.profile_image = profile_image;
        this.num_of_like = num_of_like;
        this.like_buttom = like_buttom;
        this.rate = rate;
        this.user_name = user_name;
        this.comment = comment;
        this.comment_time = comment_time;
        this.categoryid = categoryid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public int getNum_of_like() {
        return num_of_like;
    }

    public void setNum_of_like(int num_of_like) {
        this.num_of_like = num_of_like;
    }

    public int getLike_buttom() {
        return like_buttom;
    }

    public void setLike_buttom(int like_buttom) {
        this.like_buttom = like_buttom;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }
}
