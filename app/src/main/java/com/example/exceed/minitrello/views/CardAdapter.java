package com.example.exceed.minitrello.views;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.activities.TaskFragment;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Card;
import com.example.exceed.minitrello.models.Storage;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final List<Card> cards;
    OnItemClickListener mItemClickListener;
    private Board board;
    private int position;
    private ViewHolder viewHolder;

    public CardAdapter(TaskFragment taskFragment, Board board, int position) {
        this.cards = taskFragment.getCard();
        this.board = board;
        this.position = position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_card, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (cards.size() > 0) {
            viewHolder.card_name.setText("Card name : " + cards.get(i).getCard_name());
            viewHolder.card_time.setText("Created time : " + cards.get(i).getReadableCreatedTime());
            clickDelete(i);
            clickEdit(i);
        }
    }

    private void clickEdit(final int i) {
        viewHolder.edit_card_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
                final View promptView = layoutInflater.inflate(R.layout.do_card_rename, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final TextView editText = (TextView) promptView.findViewById(R.id.edittext);
                builder.setView(promptView);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Storage.getInstance().loadTask(board).get(position).getTask_card().get(i).setCard_name(editText.getText().toString());
                        cards.get(i).setCard_name(editText.getText().toString());
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

    private void clickDelete(final int i) {
        viewHolder.delete_card_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
                final View promptView = layoutInflater.inflate(R.layout.do_card_delete, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final TextView editText = (TextView) promptView.findViewById(R.id.delete_card_name);
                editText.setText("Card name : " + cards.get(i).getCard_name());
                builder.setView(promptView);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Storage.getInstance().removeCard(board, Storage.getInstance().loadTask(board).get(position), cards.get(i)))
                            cards.remove(i);
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
        return cards.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView card_name;
        private TextView card_time;
        private ImageButton delete_card_button;
        private ImageButton edit_card_button;

        public ViewHolder(View itemView) {
            super(itemView);
            card_name = (TextView) itemView.findViewById(R.id.card_name);
            card_time = (TextView) itemView.findViewById(R.id.card_time);
            delete_card_button = (ImageButton) itemView.findViewById(R.id.delete_card_button);
            edit_card_button = (ImageButton) itemView.findViewById(R.id.edit_card_button);
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
