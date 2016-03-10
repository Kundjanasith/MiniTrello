package com.example.exceed.minitrello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.views.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<Board> boards;
    private SearchAdapter searchAdapter;
    private EditText searchET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search");
        boards = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            setTitle("");
            init();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        RecyclerView recList = (RecyclerView) findViewById(R.id.board_cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        searchAdapter = new SearchAdapter(this);
        recList.setHasFixedSize(true);
        recList.setItemAnimator(new DefaultItemAnimator());
        recList.setAdapter(searchAdapter);
        searchAdapter.SetOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(SearchActivity.this, BoardActivity.class);
                intent.putExtra("board", boards.get(position));
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.search_board_bar);
        actionBar.setDisplayShowTitleEnabled(false);

        searchET = (EditText) actionBar.getCustomView().findViewById(R.id.search_board);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (searchET.getText().toString().equals("")) {
                    boards.clear();
                    searchAdapter.notifyDataSetChanged();
                } else {
                    filter(searchET.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filter(String text) {
        String query = text.toLowerCase();
        boards.clear();
        for (Board w : Storage.getInstance().loadBoard()) {
            if (w.getBoard_name().toLowerCase().contains(query)) {
                boards.add(w);
            }
        }
        searchAdapter.notifyDataSetChanged();
    }

    public List<Board> getBoards() {
        return this.boards;
    }

}
