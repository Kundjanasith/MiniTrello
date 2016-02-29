package com.example.exceed.minitrello.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by exceed on 2/26/16 AD.
 */
public class Comment implements Serializable{

    private String comment_name;
    private String comment_content;
    private long comment_createTime;

    public Comment(String comment_name,String comment_content){
        this.comment_name = comment_name;
        this.comment_content = comment_content;
        comment_createTime = System.currentTimeMillis();
    }

    public String getComment_name() {
        return comment_name;
    }

    public void setComment_name(String comment_name) {
        this.comment_name = comment_name;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public long getComment_createTime() {
        return comment_createTime;
    }

    public void setComment_createTime(long comment_createTime) {
        this.comment_createTime = comment_createTime;
    }

    public String getReableCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = new Date();
        return sdf.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;
        return comment_createTime == comment.comment_createTime;
    }

    @Override
    public int hashCode() {
        return (int) (comment_createTime ^ (comment_createTime >>> 32));
    }
}
