package com.example.asus.myapplication;

/**
 * Created by asus on 23.07.2017.
 */

public class Event {
    public long id;
    public String text;
    public long date;

    public Event(long id, String text, long date, boolean trash) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.trash = trash;
    }

    public boolean isTrash() {

        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    public boolean trash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event(){

    }

    public String getText() {
        return text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }
}
