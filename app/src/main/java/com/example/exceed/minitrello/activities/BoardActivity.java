package com.example.exceed.minitrello.activities;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.views.TaskAdapter;

public class BoardActivity extends FragmentActivity implements TaskFragment.OnFragmentInteractionListener {

    ViewPager viewPager;
    TaskAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        toolbar = (Toolbar) findViewById(R.id.bar_task);
        String t = ((Board) getIntent().getSerializableExtra("board")).getBoard_name();
        toolbar.setTitle("Board name : " + t);
        toolbar.setTitleTextColor(Color.WHITE);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new TaskAdapter(getSupportFragmentManager(), (Board) getIntent().getSerializableExtra("board"));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
