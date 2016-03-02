package com.example.exceed.minitrello.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.views.BoardAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.exceed.minitrello.views.BoardAdapter.*;

public class MainActivity extends AppCompatActivity implements Serializable{

    private BoardAdapter boardAdapter;
    private List<Board> boards;
    private FloatingActionButton addBoardButton ;
    private Button button_sort_az;
    private Button button_sort_time;
    private RecyclerView recList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    private void init(){
        boards = new ArrayList<Board>();
        recList = (RecyclerView) findViewById(R.id.board_cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        boardAdapter = new BoardAdapter(this);
        recList.setHasFixedSize(true);
        recList.setItemAnimator(new DefaultItemAnimator());
        recList.setAdapter(boardAdapter);
        boardAdapter.SetOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Log.i("PosKU",position+"");
                Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                intent.putExtra("board",boards.get(position));
                startActivity(intent);
            }
        });
        addBoardButton = (FloatingActionButton) findViewById(R.id.add_board_button);
        addBoardButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
    }

    private void showInputDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.input_board, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        builder.setView(promptView);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Storage.getInstance().saveBoard(new Board(editText.getText().toString()));
                refreshBoards();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                refreshBoards();
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Log.i("Kundjanasith","TH");
            return true;
        }
        if (id == R.id.action_theme) {
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("MainActivity", this);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_sort){
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            final View promptView = layoutInflater.inflate(R.layout.input_sort, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(promptView);
            button_sort_az = (Button) promptView.findViewById(R.id.sort_az_button);
            button_sort_time = (Button) promptView.findViewById(R.id.sort_time_button);
            button_sort_az.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Collections.sort(boards, new Board.AlphabetComparator());
                    boardAdapter.notifyDataSetChanged();
                }
            });
            button_sort_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Collections.sort(boards,new Board.CreatedTimeComparator());
                    boardAdapter.notifyDataSetChanged();
                }
            });
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

     public void refreshBoards(){
        boards.clear();
        for(Board board : Storage.getInstance().loadBoard()){
            boards.add(board);
        }
        boardAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshBoards();
    }

    public List<Board> getBoard(){
        return this.boards;
    }

}
