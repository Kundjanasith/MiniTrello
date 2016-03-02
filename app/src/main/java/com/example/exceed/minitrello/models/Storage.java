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
//                for (Task y : x.getBoard_tasks()) {
//                    temp.add(y);
//                }
                return x.getBoard_tasks();
            }
        }
        return null;
    }

    public void saveCard(Board board, Task task, Card card) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task))
                        y.getTask_card().add(card);
                }
//                break;
            }
//            break;
        }
    }

    public List<Card> loadCard(Board board, Task task) {
        List<Card> temp = new ArrayList<>();
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task)) {
//                        for (Card c : y.getTask_card()) {
//                            temp.add(c);
//                        }
                        return y.getTask_card();
                    }
                }
//                break;
            }
        }
        return null;
    }

    public void saveComment(Board board, Task task, Card card, Comment comment) {
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task))
                        for (Card c : y.getTask_card()) {
                            if (c.equals(card))
                                c.getCard_comments().add(comment);
                        }
                }
//                break;
            }
//            break;
        }
    }

    public List<Comment> loadComment(Board board, Task task, Card card) {
//        List<Comment> temp = new ArrayList<>();
        for (Board x : this.loadBoard()) {
            if (x.equals(board)) {
                for (Task y : x.getBoard_tasks()) {
                    if (y.equals(task)) {
                        for (Card c : y.getTask_card()) {
                            if (c.equals(card)) {
//                                for (Comment m : c.getCard_comments()) {
//                                    temp.add(m);
//                                }
                                return c.getCard_comments();
                            }
                        }
                    }
                }
//                break;
            }
        }
        return null;
    }

    public boolean removeBoard(Board board) {
        return this.loadBoard().remove(board);
    }

    public void removeTask(Board board, Task task) {
//        this.loadTask(board).remove(task);
    }

    public boolean removeCard(Board board, Task task,Card card) {
//        this.loadCard(board, task).remove(position);
//        Log.i("Card-A", card.getReableCreatedTime());
//        Log.i("Task-A", task.getReableCreatedTime());
//        Log.i("Board-A", board.getReableCreatedTime());
//        for(Board b:this.loadBoard()){
//            if(b.equals(board)) {
//                Log.i("Board-B", b.getReableCreatedTime());
//                for (Task t : b.getBoard_tasks()) {
//                    Log.i("Task-B", t.getReableCreatedTime());
//                    if (t.equals(task)) {
//                        Log.i("FINK", t.getTask_card().contains(card) + "");
//                        return t.getTask_card().remove(card);
//                    }
//                }
//            }
//        }
//        return false;
        return loadCard(board,task).remove(card);
    }

    public boolean removeComment(Board board, Task task, Card card,Comment comment) {
//        for(Board b:this.loadBoard()){
//            if(b.equals(board))
//                for(Task t:b.getBoard_tasks()){
//                    if(t.equals(task))
//                        for(Card c:t.getTask_card()){
//                            if(c.equals(card))
//                                return c.getCard_comments().remove(comment);
//                        }
//                }
//        }
//        return false;
        return loadComment(board,task,card).remove(comment);
    }

}
