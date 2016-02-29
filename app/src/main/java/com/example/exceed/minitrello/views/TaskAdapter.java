package com.example.exceed.minitrello.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.exceed.minitrello.activities.BoardActivity;
import com.example.exceed.minitrello.activities.TaskFragment;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.models.Task;

import java.util.List;

public class TaskAdapter extends FragmentPagerAdapter {

    private Task task;
    private BoardActivity board;
    public TaskAdapter(FragmentManager fm,BoardActivity boardActivity) {
        super(fm);
        this.board = boardActivity;

    }

    @Override
    public Fragment getItem(int position) {
        List<Task> temp = Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board"));
//        Log.i("temp", temp+"");
//        if(temp==null){
//            return new TaskFragment(null,this,board,"Add Task");
//        }
//        else {
//            Log.i("pos", position+"");
//            return new TaskFragment(temp.get(position),this,board,temp.get(position).getTask_name());
//        }
//        if(temp==null) return new TaskFragment(null,this,board,"Add Task");
//        else
//        if(position==0&&temp.size()!=0) {
//            Log.i("KUN", temp.get(0).getTask_name() + "");
//
//            return new TaskFragment(temp.get(0),this,board,temp.get(0).getTask_name());
//        }
        if(temp.size()==0&&position==0) Log.i("yes","yrs");
        if(temp.size()==0){
            Log.i("yed", position+"");
            return new TaskFragment(null,this,board,"Add Task",position);
        }
        else{
            Log.i("yedk", position+"");
            return new TaskFragment(temp.get(position),this,board,temp.get(position).getTask_name(),position);
        }
    }

    @Override
    public int getCount() {
        List<Task> temp = Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board"));
//        if(temp==null) return 1;
//        else
            if(temp.size()==0) return 1;
            else return temp.size();
//        Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()
//        if(Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()==0||
//                ){
//            return 1;
//        }
//        else{
//            return Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size();
//        }
    }

}