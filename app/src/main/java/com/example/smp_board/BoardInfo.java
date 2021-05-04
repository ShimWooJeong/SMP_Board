package com.example.smp_board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoardInfo {
    //final long now = System.currentTimeMillis();
    public String title;
    public String content;
    public long w_time;
    //public String comment;
    //public Date date;

    public BoardInfo(){ // 지우면 안 됨

    }

    public BoardInfo(String title, String content, long w_time){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        this.title = title;
        this.content = content;
        this.w_time = w_time;
        //this.comment = comment;
        //this.date = date;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    //public Date getDate() { return date; }
    //public void setDate(Date date) { this.date = date; }
    public long getTime() { return w_time; }
    public void setTime(long w_time) { this.w_time = w_time; }

}