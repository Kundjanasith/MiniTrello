package com.example.exceed.minitrello.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.exceed.minitrello.activities.TaskFragment;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.models.Task;

import java.io.Serializable;
import java.util.List;

public class TaskAdapter extends FragmentPagerAdapter implements Serializable {
    private Board board;

    public TaskAdapter(FragmentManager fm, Board board) {
        super(fm);
        this.board = board;

    }

    @Override
    public Fragment getItem(int position) {
        List<Task> temp = Storage.getInstance().loadTask(board);
        if (temp.size() == 0) {
            return new TaskFragment(this, board, "Add Task", position);
        } else {
            return new TaskFragment(this, board, temp.get(position).getTask_name(), position);
        }
    }

    @Override
    public int getCount() {
        List<Task> temp = Storage.getInstance().loadTask(board);
        if (temp.size() == 0) return 1;
        else return temp.size();
    }

}