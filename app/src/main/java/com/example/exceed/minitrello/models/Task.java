package com.example.exceed.minitrello.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by exceed on 2/26/16 AD.
 */
public class Task implements Serializable{

    private String task_name;
    private List<Card> task_card;
    private long task_createTime;
    private Date task_date;

    public Task(String task_name){
        this.task_name = task_name;
        this.task_card = new ArrayList<Card>();
        this.task_createTime = System.currentTimeMillis();
        this.task_date = new Date();
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public List<Card> getTask_card() {
        return task_card;
    }

    public void setTask_card(List<Card> task_card) {
        this.task_card = task_card;
    }

    public long getTask_createTime() {
        return task_createTime;
    }

    public void setTask_createTime(long task_createTime) {
        this.task_createTime = task_createTime;
    }

    public String getReableCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return sdf.format(this.task_date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;
        return task_createTime == task.task_createTime;
    }

    @Override
    public int hashCode() {
        return (int) (task_createTime ^ (task_createTime >>> 32));
    }
}
