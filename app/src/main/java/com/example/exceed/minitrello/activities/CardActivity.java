package com.example.exceed.minitrello.activities;

import android.app.SharedElementCallback;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Card;
import com.example.exceed.minitrello.models.Comment;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.models.Task;
import com.example.exceed.minitrello.views.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {

    private List<Comment> comments;
    private CommentAdapter commentAdapter;
    //    private ListView listView;
    private RecyclerView recList;
    private ImageButton send_button;
    private TextView comment_name;
    private TextView comment_text;
    private TextView description;
    private Card ca;
    private Board bo;
    private Task ta;
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        init();
    }

    private void init() {
        ca = ((Card) getIntent().getSerializableExtra("card"));
        bo = ((Board) getIntent().getSerializableExtra("board"));
        ta = ((Task) getIntent().getSerializableExtra("task"));
        pos = (int) getIntent().getSerializableExtra("pos");
        Log.i("Z-card", ca.getReableCreatedTime());
        Log.i("Z-board", bo.getReableCreatedTime());
        Log.i("Z-task", ta.getReableCreatedTime());
        String t = ca.getCard_name();
        send_button = (ImageButton) findViewById(R.id.send_button);
        comment_name = (TextView) findViewById(R.id.mentor_text);
        comment_text = (TextView) findViewById(R.id.comment_text);
        description = (TextView) findViewById(R.id.card_description_edittext);
        setTitle("Card name : " + t);
        comments = new ArrayList<Comment>();
        recList = (RecyclerView) findViewById(R.id.comment_cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        commentAdapter = new CommentAdapter(this);
        recList.setHasFixedSize(true);
        recList.setItemAnimator(new DefaultItemAnimator());
        recList.setAdapter(commentAdapter);
        commentAdapter.SetOnItemClickListener(new CommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                commentAdapter.clickDelete(position);
            }
        });
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().saveComment(bo,ta, ca, new Comment(comment_name.getText().toString(), comment_text.getText().toString()));
                refreshComments();
                comment_name.setText("");
                comment_text.setText("");
            }
        });
    }

    public List<Comment> getComment() {
        return comments;
    }

    @Override
    public void setExitSharedElementCallback(SharedElementCallback callback) {
        super.setExitSharedElementCallback(callback);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("MiniTrello", "BackPressed");
        Storage.getInstance().loadCard(bo, ta).get(pos).setCard_description(description.getText().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshComments();
        refreshDescription();
    }

    private void refreshComments() {
        comments.clear();
        for (Comment m : Storage.getInstance().loadComment(bo, ta, ca)) {
            comments.add(m);
        }
        commentAdapter.notifyDataSetChanged();
    }

    private void refreshDescription() {
        description.setText(Storage.getInstance().loadCard(bo,ta).get(pos).getCard_description());
    }

}
