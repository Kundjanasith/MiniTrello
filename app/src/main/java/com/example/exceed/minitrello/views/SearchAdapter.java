package com.example.exceed.minitrello.views;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.activities.SearchActivity;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Storage;

import java.io.Serializable;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Serializable {

    private final List<Board> boards;
    OnItemClickListener mItemClickListener;
    private ViewHolder viewHolder;

    public SearchAdapter(SearchActivity searchActivity){
        this.boards = searchActivity.getBoards();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_board, viewGroup, false);
        Log.i("SERV", i + "");
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.board_name.setText("Board name : " + boards.get(i).getBoard_name());
        viewHolder.board_time.setText("Created time : " + boards.get(i).getReableCreatedTime());
        clickDelete(i);
        clickEdit(i);
    }

    public void clickEdit(final int i){
        viewHolder.edit_board_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
                final View promptView = layoutInflater.inflate(R.layout.do_board_rename, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final TextView editText = (TextView) promptView.findViewById(R.id.edittext);
                builder.setView(promptView);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Storage.getInstance().loadBoard().get(i).setBoard_name(editText.getText().toString());
                        boards.get(i).setBoard_name(editText.getText().toString());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Board b : Storage.getInstance().loadBoard()) {
                            Log.i("SE", b.getBoard_name());
                        }
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    public void clickDelete(final int i){
        viewHolder.delete_board_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
                final View promptView = layoutInflater.inflate(R.layout.do_board_delete, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final TextView editText = (TextView) promptView.findViewById(R.id.delete_board_name);
                editText.setText("Board name : "+boards.get(i).getBoard_name());
                builder.setView(promptView);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Storage.getInstance().removeBoard(boards.get(i));
                        boards.remove(i);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Board b : Storage.getInstance().loadBoard()) {
                            Log.i("SE",b.getBoard_name());
                        }
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return boards.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView board_name;
        private TextView board_time;
        private ImageButton edit_board_button;
        private ImageButton delete_board_button;

        public ViewHolder(View itemView) {
            super(itemView);
            board_name = (TextView) itemView.findViewById(R.id.board_name);
            board_time = (TextView) itemView.findViewById(R.id.board_time);
            edit_board_button = (ImageButton) itemView.findViewById(R.id.edit_board_button);
            delete_board_button = (ImageButton) itemView.findViewById(R.id.delete_board_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}