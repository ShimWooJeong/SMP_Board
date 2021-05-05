package com.example.smp_board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BoardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    long now;
    //Date date = new Date(time);

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Board");

    private ArrayAdapter<String> adapter;
    private ArrayList<BoardInfo> BoardInfoArrayList;
    private Context context;

    public BoardViewAdapter(ArrayList<BoardInfo> BoardInfoarrayList, Context context) {
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder myViewholder, int position) {

        long time_d = BoardInfoArrayList.get(position).w_time;
        long now = System.currentTimeMillis();
        long a  = (now - time_d)/60000; // 60000으로 나누면 분이 뜸
        MyViewHolder myViewHolder = (MyViewHolder) myViewholder;
        myViewHolder.titleb.setText(BoardInfoArrayList.get(position).title);
        myViewHolder.contentb.setText(BoardInfoArrayList.get(position).content);

        if (a == 0){
            myViewHolder.timeb.setText("방금");
        }else {
            myViewHolder.timeb.setText(a+"분 전");
        }
        //myViewHolder.commentb.setText(BoardInfoArrayList.get(position).comment);

    }

    @Override
    public int getItemCount() {
        return (BoardInfoArrayList != null ? BoardInfoArrayList.size() : 0);
    }

}
