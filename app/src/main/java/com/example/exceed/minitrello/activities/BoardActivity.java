package com.example.exceed.minitrello.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.views.TaskAdapter;

public class BoardActivity extends FragmentActivity implements TaskFragment.OnFragmentInteractionListener{

    ViewPager viewPager;
    TaskAdapter adapter;
    Toolbar toolbar;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        toolbar = (Toolbar) findViewById(R.id.bar_task);
        String t = ((Board) getIntent().getSerializableExtra("board")).getBoard_name();
        toolbar.setTitle("Board name : "+ t);
        Log.i("123okg", ((Board) getIntent().getSerializableExtra("board")).getReableCreatedTime() + "");
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new TaskAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        Log.i("1asasd23okg", ((Board) getIntent().getSerializableExtra("board")).getReableCreatedTime() + "");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//            super.onActivityResult(requestCode, resultCode, data);
//            if((resultCode==RESULT_OK)&&(requestCode==1)) {
//                date_text = (TextView) findViewById(R.id.note_date_edittext);
//                start_text = (TextView) findViewById(R.id.show_start_time);
//                date_text.setText(data.getStringExtra("date"));
//                start_text.setText(data.getStringExtra("start_time"));
//            }
//    }
}
