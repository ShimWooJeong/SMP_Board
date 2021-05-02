package com.example.smp_board;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoardView extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<BoardInfo> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Board");

    TextView bv_title, bv_content;

    //private ArrayAdapter<String> adapter;
    private ArrayList<BoardInfo> BoardInfoArrayList;
    private Context context;

    //Bundle bundle = ((Activity) context).getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_boardview);

        bv_title = findViewById(R.id.bv_title);
        bv_content = findViewById(R.id.bv_content);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_board);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        arrayList = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    BoardInfo boardInfo = snapshot.getValue(BoardInfo.class);
                    arrayList.add(boardInfo);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {
                Log.e("BoardView", String.valueOf(databaseerror.toException()));
            }
        });
        adapter = new MyAdapter(arrayList, this);
        mRecyclerView.setAdapter(adapter);

        //bv_title.setText(BoardInfoArrayList.title);
    }
}
