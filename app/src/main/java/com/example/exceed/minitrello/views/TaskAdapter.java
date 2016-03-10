package com.example.exceed.minitrello.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.exceed.minitrello.activities.TaskFragment;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.models.Task;

import java.io.Serializable;
import java.util.List;

public class TaskAdapter extends FragmentPagerAdapter implements Serializable{

    private Task task;
//    private BoardActivity board;
    private Board board;
//    public TaskAdapter(FragmentManager fm,BoardActivity boardActivity) {
    public TaskAdapter(FragmentManager fm,Board board){
        super(fm);
        this.board = board;

    }

    @Override
    public Fragment getItem(int position) {
//        List<Task> temp = Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board"));
        List<Task> temp = Storage.getInstance().loadTask(board);
        if(temp.size()==0&&position==0) Log.i("yes","yrs");
        if(temp.size()==0){
            Log.i("yed", position+"");
            return new TaskFragment(this,board,"Add Task",position);
        }
        else{
            Log.i("yedk", position+"");
            return new TaskFragment(this,board,temp.get(position).getTask_name(),position);
        }
    }

    @Override
    public int getCount() {
//        List<Task> temp = Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board"));
        List<Task> temp = Storage.getInstance().loadTask(board);
        if(temp.size()==0) return 1;
        else return temp.size();
    }

}