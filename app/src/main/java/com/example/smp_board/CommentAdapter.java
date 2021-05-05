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

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //final long now = System.currentTimeMillis();
    long now;
    //Date date = new Date(time);
    //String time2 = ((WriteActivity)WriteActivity.context_main).stime;

    FirebaseDatabase database_c = FirebaseDatabase.getInstance();
    DatabaseReference myRef_c = database_c.getReference("Comment");

    private ArrayAdapter<String> adapter_c;
    private ArrayList<CommentInfo> CommentInfoArrayList;
    private Context context;

    public CommentAdapter(ArrayList<CommentInfo> CommentInfoArrayList, Context context) {
        this.CommentInfoArrayList = CommentInfoArrayList;
        this.context = context;
    }


    public static class MyViewHolder_c extends RecyclerView.ViewHolder {
        TextView name_c;
        TextView comment_c;
        TextView time_c;

        public MyViewHolder_c(@NonNull View view_c){
            super(view_c);
            name_c = view_c.findViewById(R.id.tv_name_c);
            comment_c = view_c.findViewById(R.id.tv_comment_c);
            time_c = view_c.findViewById(R.id.tv_time_c);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_c = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_c, parent, false);
        MyViewHolder_c myViewHolder_c = new MyViewHolder_c(view_c);
        return myViewHolder_c;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myViewholder_c, int position) {
        long time_c = CommentInfoArrayList.get(position).c_time;
        long now = System.currentTimeMillis();
        long a  = (now - time_c)/60000; // 60000으로 나누면 분이 뜸
        CommentAdapter.MyViewHolder_c myViewHolder_c = (CommentAdapter.MyViewHolder_c) myViewholder_c;
        myViewHolder_c.name_c.setText(CommentInfoArrayList.get(position).name);
        myViewHolder_c.comment_c.setText(CommentInfoArrayList.get(position).comment);
        if (a == 0){
            myViewHolder_c.time_c.setText("방금");
        }else {
            myViewHolder_c.time_c.setText(a+"분 전");
        }
        //myViewHolder.commentb.setText(BoardInfoArrayList.get(position).comment);
    }

    @Override
    public int getItemCount() {
        return (CommentInfoArrayList != null ? CommentInfoArrayList.size() : 0);
    }

}
