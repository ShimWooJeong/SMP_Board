package com.example.smp_board;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoardInfo {
    //final long now = System.currentTimeMillis();
    public String title;
    public String content;
    public String time;
    //public String comment;
    //public Date date;

    public BoardInfo(){ // 지우면 안 됨

    }

    public BoardInfo(String title, String content, String time){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        this.title = title;
        this.content = content;
        this.time = time;
        //this.comment = comment;
        //this.date = date;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    //public Date getDate() { return date; }
    //public void setDate(Date date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

}


