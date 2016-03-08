package com.example.exceed.minitrello.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exceed on 2/26/16 AD.
 */
public class Storage {

    private List<Board> saveBoard;
    private static Storage instance;
    private static ColorAll color = new ColorAll(new int[]{0,0,0},new int[]{0,0,0},new int[]{0,0,0});

    public ColorAll getColor(){
        return color;
    }

    public void setColor(ColorAll c){
        color = c;
    }

    private Storage() {
        saveBoard = new ArrayList<Board>();
//        saveBoard.add(new Board("Awe"));
//        saveBoard.add(new Board("Some"));
//        saveBoard.add(new Board("Boards"));
    }

    public static Storage getInstance() {
        if (instance == null) return instance = new Storage();
        return instance;
    }

    public void saveBoard(Board board) {
        saveBoard.add(board);
    }

    public List<Board> loadBoard() {
        return saveBoard;
    }


    public void saveTask(Board board, Task task) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                x.getBoard_tasks().add(task);
            }
        }
    }

    public List<Task> loadTask(Board board) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                return x.getBoard_tasks();
            }
        }
        return null;
    }

    public void saveCard(Board board, Task task, Card card) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task)) {
                        y.getTask_card().add(card);
                    }
                }
            }
        }
    }

    public List<Card> loadCard(Board board, Task task) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task)) {
                        return y.getTask_card();
                    }
                }
            }
        }
        return null;
    }

    public void saveComment(Board board, Task task, Card card, Comment comment) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task)) {
                        for (Card c : y.getTask_card()) {
                            if (c.equals(card)) {
                                c.getCard_comments().add(comment);
                            }
                        }
                    }
                }
            }
        }
    }

    public List<Comment> loadComment(Board board, Task task, Card card) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task)) {
                        for (Card c : y.getTask_card()) {
                            if (c.equals(card)) {
                                return c.getCard_comments();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean removeBoard(Board board) {
        return this.loadBoard().remove(board);
    }

    public void removeTask(Board board, Task task) {

    }

    public boolean removeCard(Board board, Task task,Card card) {
        return loadCard(board,task).remove(card);
    }

    public boolean removeComment(Board board, Task task, Card card,Comment comment) {
        return loadComment(board,task,card).remove(comment);
    }

}
