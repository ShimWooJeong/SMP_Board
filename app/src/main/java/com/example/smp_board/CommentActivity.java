package com.example.smp_board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class CommentActivity extends AppCompatActivity {

    private RecyclerView RecyclerView_c;
    private RecyclerView.LayoutManager LayoutManager_c;
    private RecyclerView.Adapter adapter_c;
    private ArrayList<CommentInfo> arrayList_c;
    FirebaseDatabase database_c = FirebaseDatabase.getInstance();
    DatabaseReference myRef_c = database_c.getReference().child("Comment");
    Button btn_c;
    TextView tv_comment_bc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        btn_c = (Button)findViewById(R.id.btn_c);
        tv_comment_bc = (TextView)findViewById(R.id.tv_comment_bc);


        RecyclerView_c = (RecyclerView) findViewById(R.id.recycler_comment);
        RecyclerView_c.setHasFixedSize(true);
        LayoutManager_c = new LinearLayoutManager(this);
        LinearLayoutManager LayoutManager_c = new LinearLayoutManager(this);
        RecyclerView_c.setLayoutManager(LayoutManager_c);
        arrayList_c = new ArrayList<>();

        myRef_c.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList_c.clear();
                for (DataSnapshot snapshot_c : datasnapshot.getChildren()) {
                    CommentInfo commentInfo = snapshot_c.getValue(CommentInfo.class);
                    arrayList_c.add(commentInfo);

                }
                adapter_c.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {
                Log.e("CommentActivity", String.valueOf(databaseerror.toException()));
            }
        });

        adapter_c = new CommentAdapter(arrayList_c, this);
        RecyclerView_c.setAdapter(adapter_c);

/*
        ArrayList<CommentInfo> CommentInfoArrayList = new ArrayList<>();
        CommentInfoArrayList.add(new CommentInfo("익명1", "서브밀 어쩌구 안서동 야곱집 최고 냠", 5));
        CommentInfoArrayList.add(new CommentInfo("익명1", "아나 한 강의에서만 과제 3개임 어쩌구", 5));
        CommentInfoArrayList.add(new CommentInfo("익", "어쩌구 저쩌구 지금 나갈 건데 단대 호수 걷자고 꼬셔~~", 5));

        CommentAdapter myAdapter_c = new CommentAdapter(CommentInfoArrayList);

        RecyclerView_c.setAdapter(myAdapter_c);
*/

        View.OnClickListener listener_c = new View.OnClickListener(){
            @Override
            public void onClick(View v){

                DatabaseReference database_c = FirebaseDatabase.getInstance().getReference();
                long time_c = System.currentTimeMillis();
                //long w_time = System.currentTimeMillis();
                //String sTimeStamp = new SimpleDateFormat("HH시mm분ss초", Locale.KOREA).format(time);
                CommentInfo commentInfo = new CommentInfo(tv_comment_bc.getText().toString(), tv_comment_bc.getText().toString(), time_c);
                database_c.child("Comment").push().setValue(commentInfo);
                Toast.makeText(CommentActivity.this, "작성을 완료했습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), com.example.smp_board.CommentActivity.class);
                //Intent Adapter = new Intent(getApplicationContext(), MyAdapter.class);
                //Adapter.putExtra("atime", sTimeStamp.toString());
                startActivity(intent);
                //startActivity(Adapter);
            }
        };
        btn_c.setOnClickListener(listener_c);

    }
}
