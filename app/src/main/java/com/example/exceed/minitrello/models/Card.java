package com.example.exceed.minitrello.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Card implements Serializable {

    private String card_name;
    private String card_description;
    private List<Comment> card_comments;
    private long card_createTime;
    private Date card_date;

    public Card(String card_name, String card_description) {
        this.card_name = card_name;
        this.card_description = card_description;
        this.card_comments = new ArrayList<>();
        this.card_createTime = System.currentTimeMillis();
        this.card_date = new Date();
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_description() {
        return card_description;
    }

    public void setCard_description(String card_description) {
        this.card_description = card_description;
    }

    public List<Comment> getCard_comments() {
        return card_comments;
    }

    public void setCard_comments(List<Comment> card_comments) {
        this.card_comments = card_comments;
    }

    public long getCard_createTime() {
        return card_createTime;
    }

    public void setCard_createTime(long card_createTime) {
        this.card_createTime = card_createTime;
    }

    public String getReadableCreatedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
        return sdf.format(this.card_date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;
        return card_createTime == card.card_createTime;
    }

    @Override
    public int hashCode() {
        return (int) (card_createTime ^ (card_createTime >>> 32));
    }

}
