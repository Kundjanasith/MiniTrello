package com.example.exceed.minitrello.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exceed on 2/26/16 AD.
 */
public class Storage {

    private List<Board> saveBoard;
    private static Storage instance;

    private Storage(){
        saveBoard = new ArrayList<Board>();
    }

    public static Storage getInstance(){
        if(instance==null) return instance = new Storage();
        return instance;
    }

    public void saveBoard(Board board){
        saveBoard.add(board);
    }

    public List<Board> loadBoard(){
        return saveBoard;
    }

    public void clearBoard() {
        saveBoard.clear();
    }

    public void deleteNote(Board board){
        saveBoard.remove(board);
    }

    public void saveTask(Board board,Task task){
        for(Board x:this.loadBoard()){
            if(x.equals(board)){
                x.getBoard_tasks().add(task);
            }
        }

    }

    public List<Task> loadTask(Board board){
        List<Task> temp = new ArrayList<Task>();
        for(Board x:this.loadBoard()){
            if(x.equals(board)){
//                temp = new ArrayList<>();
                for(Task y:x.getBoard_tasks()){
                    temp.add(y);
                }
                break;
            }
        }
//        if(temp.size()==0) return null;
        return temp;
    }

}
