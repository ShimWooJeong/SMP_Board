package com.example.smp_board;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WriteActivity extends AppCompatActivity {
    //public static Context context_main;
    public  static Context sTimeStamp;
    TextView tv_title, tv_content;
    Button btn_writend;

    long time = System.currentTimeMillis();
    //Date date = new Date(time);
    //String stime;

    //Intent intent = new Intent(WriteActivity.this, MyAdapter.class);

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Board");

/*
    DatabaseReference mDBReference = null;
    HashMap<String, Object> boardUpdates = null;
    Map<String, Object> boardValue = null;
    BoardInfo boardInfo = null;

 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        //stime = new SimpleDateFormat("HH:mm", Locale.KOREA).format(time);
        //context_main = this;

        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_content = (TextView)findViewById(R.id.tv_content);
        btn_writend = (Button)findViewById(R.id.btn_writend);
/*
        mDBReference = FirebaseDatabase.getInstance().getReference();
        boardUpdates = new HashMap<>();
        if(){
            boardInfo = new BoardInfo(title, content);
        }
*/
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                long w_time = System.currentTimeMillis();
                //String sTimeStamp = new SimpleDateFormat("HH시mm분ss초", Locale.KOREA).format(time);
                BoardInfo boardInfo = new BoardInfo(tv_title.getText().toString(), tv_content.getText().toString(), w_time);
                database.child("Board").push().setValue(boardInfo);
                Toast.makeText(WriteActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //Intent Adapter = new Intent(getApplicationContext(), MyAdapter.class);
                //Adapter.putExtra("atime", sTimeStamp.toString());
                startActivity(intent);
                //startActivity(Adapter);
            }
        };
        btn_writend.setOnClickListener(listener);

    }
}
