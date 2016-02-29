package com.example.exceed.minitrello.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.Board;

import java.util.List;

/**
 * Created by exceed on 2/26/16 AD.
 */
public class BoardAdapter extends ArrayAdapter<Board>{


    public BoardAdapter(Context context, int resource, List<Board> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cell_board, null);
        }
        TextView board_name = (TextView) v.findViewById(R.id.board_name);
        Board board = getItem(position);
        board_name.setText("Board name : "+board.getBoard_name());
        return v;
    }

}
