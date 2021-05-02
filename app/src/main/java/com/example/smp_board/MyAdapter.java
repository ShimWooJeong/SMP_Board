package com.example.smp_board;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //final long now = System.currentTimeMillis();
    //long now;
    //Date date = new Date(time);
    //String time2 = ((WriteActivity)WriteActivity.context_main).stime;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Board");

    private ArrayAdapter<String> adapter;
    private ArrayList<BoardInfo> BoardInfoArrayList;
    private Context context;

    //Bundle bundle = ((Activity) context).getIntent().getExtras();

    public MyAdapter(ArrayList<BoardInfo> BoardInfoarrayList, Context context) {
        this.BoardInfoArrayList = BoardInfoarrayList;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleb;
        TextView contentb;
        TextView timeb;
        TextView commentb;

        public MyViewHolder(@NonNull View view){
            super(view);
            titleb = view.findViewById(R.id.title_bo);
            contentb = view.findViewById(R.id.content_bo);
            timeb = view.findViewById(R.id.time_bo);
            commentb = view.findViewById(R.id.comment_bo);
        }
    }
    /*
    MyAdapter(ArrayList<BoardInfo> BoardInfoArrayList, Context context) {
        this.BoardInfoArrayList = BoardInfoArrayList;
    }
*/

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder myViewholder, int position) {
        //now = (System.currentTimeMillis() - time)/60000;
        MyViewHolder myViewHolder = (MyViewHolder) myViewholder;

        myViewHolder.titleb.setText(BoardInfoArrayList.get(position).title);
        myViewHolder.contentb.setText(BoardInfoArrayList.get(position).content);
        myViewHolder.timeb.setText(BoardInfoArrayList.get(position).time);
        /*
        if (now == 0){
            myViewHolder.timeb.setText("방금");
        }else {
            myViewHolder.timeb.setText(now+"전");
        }*/
        //myViewHolder.commentb.setText(BoardInfoArrayList.get(position).comment);

    }

    @Override
    public int getItemCount() {
        return (BoardInfoArrayList != null ? BoardInfoArrayList.size() : 0);
    }

}
