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
import com.example.exceed.minitrello.activities.CardActivity;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Card;
import com.example.exceed.minitrello.models.Comment;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.models.Task;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private final List<Comment> comments;
    OnItemClickListener mItemClickListener;
    private Board board;
    private Task task;
    private Card card;
    private ViewHolder viewHolder;

    public CommentAdapter(CardActivity cardActivity) {
        this.comments = cardActivity.getComment();
        this.board = (Board) cardActivity.getIntent().getSerializableExtra("board");
        this.task = (Task) cardActivity.getIntent().getSerializableExtra("task");
        this.card = (Card) cardActivity.getIntent().getSerializableExtra("card");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_comment, viewGroup, false);
        Log.i("SERV", i + "");
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.comment_name.setText("By : " + comments.get(i).getComment_name());
            viewHolder.comment_text.setText("Comment : "+comments.get(i).getComment_content());
            viewHolder.comment_time.setText("Created time : " + comments.get(i).getReableCreatedTime());
            clickDelete(i);
        clickEdit(i);
    }

    public void clickEdit(final int i) {
        viewHolder.edit_comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
                final View promptView = layoutInflater.inflate(R.layout.do_comment_rename, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final TextView editText = (TextView) promptView.findViewById(R.id.edittext);
                builder.setView(promptView);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Storage.getInstance().loadComment(board, task, card).get(i).setComment_content(editText.getText().toString());
                        comments.get(i).setComment_content(editText.getText().toString());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    public void clickDelete(final int i){
        viewHolder.delete_comment_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
                final View promptView = layoutInflater.inflate(R.layout.do_comment_delete, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final TextView editText = (TextView) promptView.findViewById(R.id.delete_board_name);
                editText.setText("This comment :"+comments.get(i).getComment_content());
                builder.setView(promptView);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Storage.getInstance().removeComment(board, task, card, comments.get(i));
                        comments.remove(i);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView comment_name;
        private TextView comment_text;
        private TextView comment_time;
        private ImageButton delete_comment_button;
        private ImageButton edit_comment_button;

        public ViewHolder(View itemView) {
            super(itemView);
            comment_name = (TextView) itemView.findViewById(R.id.comment_name);
            comment_text = (TextView) itemView.findViewById(R.id.comment_text);
            comment_time = (TextView) itemView.findViewById(R.id.comment_time);
            delete_comment_button = (ImageButton) itemView.findViewById(R.id.delete_comment_button);
            edit_comment_button = (ImageButton) itemView.findViewById(R.id.edit_comment_button);
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

