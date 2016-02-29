package com.example.exceed.minitrello.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by exceed on 2/26/16 AD.
 */
public class Board implements Serializable{

    private String board_name;
    private List<Task> board_tasks;
    private long board_createTime;

    public Board(String board_name){
        this.board_name = board_name;
        this.board_tasks = new ArrayList<Task>();
        this.board_createTime = System.currentTimeMillis();
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public List<Task> getBoard_tasks() {
        return board_tasks;
    }

    public void setBoard_tasks(List<Task> board_tasks) {
        this.board_tasks = board_tasks;
    }

    public long getBoard_createTime() {
        return board_createTime;
    }

    public void setBoard_createTime(long board_createTime) {
        this.board_createTime = board_createTime;
    }

    public String getReableCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = new Date();
        return sdf.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;

        Board board = (Board) o;
        return board_createTime== board.board_createTime;
    }

    @Override
    public int hashCode() {
        return (int) (board_createTime ^ (board_createTime >>> 32));
    }
}
