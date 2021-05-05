package com.example.smp_board;

public class CommentInfo {

    public String name;
    public String comment;
    public long c_time;

    public CommentInfo(){ // 지우면 안 됨

    }

    public CommentInfo(String name, String comment, long c_time){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        this.name = name;
        this.comment = comment;
        this.c_time = c_time;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    //public Date getDate() { return date; }
    //public void setDate(Date date) { this.date = date; }
    public long getTime_c() { return c_time; }
    public void setTime_c(long c_time) { this.c_time = c_time; }

}
