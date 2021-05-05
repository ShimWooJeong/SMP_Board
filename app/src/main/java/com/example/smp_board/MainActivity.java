package com.example.smp_board;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<BoardInfo> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Board");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btn_write = findViewById(R.id.btn_write);
        btn_write.setOnClickListener(new FABClickListener());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_board);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true); // 역순 (게시글은 역순, 댓글은 X)
        mLayoutManager.setStackFromEnd(true); // 역순 (게시글은 역순, 댓글은 X)
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
                Log.e("MainActivity", String.valueOf(databaseerror.toException()));
            }
        });

        /*
        ArrayList<BoardInfo> BoardInfoArrayList = new ArrayList<>();
        BoardInfoArrayList.add(new BoardInfo("안서동 맛집 추천 리스트", "서브밀 어쩌구 안서동 야곱집 최고 냠"));
        BoardInfoArrayList.add(new BoardInfo("과제 진짜 개 많다", "아나 한 강의에서만 과제 3개임 어쩌구"));
        BoardInfoArrayList.add(new BoardInfo("나랑 단대호수 걸을 사람", "어쩌구 저쩌구 지금 나갈 건데 단대 호수 걷자고 꼬셔~~"));

        MyAdapter myAdapter = new MyAdapter(BoardInfoArrayList);

        mRecyclerView.setAdapter(myAdapter);
*/
        adapter = new MyAdapter(arrayList, this);
        mRecyclerView.setAdapter(adapter);

    }

    class FABClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
            startActivity(intent);
        }
    }

}