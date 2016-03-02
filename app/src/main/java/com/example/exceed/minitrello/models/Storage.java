package com.example.exceed.minitrello.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exceed on 2/26/16 AD.
 */
public class Storage {

    private List<Board> saveBoard;
    private static Storage instance;

    private Storage() {
        saveBoard = new ArrayList<Board>();
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

    public void clearBoard() {
        saveBoard.clear();
    }

    public void deleteNote(Board board) {
        saveBoard.remove(board);
    }

    public void saveTask(Board board, Task task) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                x.getBoard_tasks().add(task);
            }
        }

    }

    public List<Task> loadTask(Board board) {
        List<Task> temp = new ArrayList<>();
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    temp.add(y);
                }
                break;
            }
        }
        return temp;
    }

    public void saveCard(Board board, Task task, Card card) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : this.loadTask(board)) {
                    if (y.equals(task))
                        y.getTask_card().add(card);
                }
                break;
            }
            break;
        }
    }

    public List<Card> loadCard(Board board, Task task) {
        List<Card> temp = new ArrayList<>();
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task)) {
                        for (Card c : y.getTask_card()) {
                            temp.add(c);
                        }
                    }
                }
                break;
            }
        }
        return temp;
    }

    public void saveComment(Board board, Task task, Card card, Comment comment) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : this.loadTask(board)) {
                    if (y.equals(task))
                        for (Card c : this.loadCard(board, task)) {
                            if (c.equals(card))
                                c.getCard_comments().add(comment);
                        }
                }
                break;
            }
            break;
        }
    }

    public List<Comment> loadComment(Board board, Task task, Card card) {
        List<Comment> temp = new ArrayList<>();
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task)) {
                        for (Card c : y.getTask_card()) {
                            if (c.equals(card)) {
                                for (Comment m : c.getCard_comments()) {
                                    temp.add(m);
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        return temp;
    }

    public void removeBoard(int position) {
        this.loadBoard().remove(position);
    }

    public void removeTask(Board board, Task task) {
//        this.loadTask(board).remove(task);
    }

    public void removeCard(Board board, Task task, int position) {
//        this.loadCard(board, task).remove(position);
        for(Board b:this.loadBoard()){
            if(b.equals(board))
            for(Task t:b.getBoard_tasks()){
                if(t.equals(task))
                    t.getTask_card().remove(position);
            }
        }
    }

    public void removeComment(Board board, Task task, Card card, int position) {
//        this.loadComment(board, task, card).remove(position);
        for(Board b:this.loadBoard()){
            if(b.equals(board))
                for(Task t:b.getBoard_tasks()){
                    if(t.equals(task))
                        for(Card c:t.getTask_card()){
                            if(c.equals(card))
                                c.getCard_comments().remove(position);
                        }
                }
        }
    }

}
