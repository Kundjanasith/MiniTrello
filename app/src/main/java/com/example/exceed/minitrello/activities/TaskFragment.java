package com.example.exceed.minitrello.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Card;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.models.Task;
import com.example.exceed.minitrello.views.CardAdapter;
import com.example.exceed.minitrello.views.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int num_task = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private Task task;
    private TaskAdapter taskAdapter;
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton add_task_button;
    private List<Task> listTask;
    private List<Card> cards;
    private Board board;
    private TextView taskName;
    private String title;
    private int position;
    private Button add_card_button;
//    private ListView listView;
    private CardAdapter cardAdapter;
    private RecyclerView recList;
    private ImageButton delete_task_button;
    private ImageButton edit_task_button;

    public TaskFragment(TaskAdapter taskAdapter,Board board,String title,int position) {
        // Required empty public constructor
//        this.task = task;
        this.taskAdapter = taskAdapter;
        this.listTask = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.board = board;
        this.title = title;
        this.position = position;
    }

    public static int getNum_page() {
        return num_task + 1;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public TaskFragment newInstance(String param1, String param2) {
        TaskFragment fragment = new TaskFragment(this.taskAdapter,this.board,this.title,this.position);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_task, container, false);
        init(v);
        return v;
    }

    private void init(View v){
        taskName = (TextView) v.findViewById(R.id.task_name);
        delete_task_button = (ImageButton) v.findViewById(R.id.delete_task_button);
        edit_task_button = (ImageButton) v.findViewById(R.id.edit_task_button);
        delete_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog2();
            }
        });
        edit_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog3();
            }
        });
//        if(title.equals("Add Task")&&position==0&&Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()>=1)
//            taskName.setText("Task name : "+Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).get(0).getTask_name());
        if(title.equals("Add Task")&&position==0&&Storage.getInstance().loadTask(board).size()>=1)
            taskName.setText("Task name : "+Storage.getInstance().loadTask(board).get(0).getTask_name());
        else if(!title.equals("Add Task"))taskName.setText("Task name : "+title);
        else taskName.setText(title);
        add_task_button = (FloatingActionButton) v.findViewById(R.id.add_task_button);
        add_card_button = (Button) v.findViewById(R.id.add_card_button);
//        if(Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()==0) {
        if(Storage.getInstance().loadTask(board).size()==0) {
            add_task_button.setVisibility(View.VISIBLE);
            add_card_button.setVisibility(View.INVISIBLE);
        }
        else{
//            if(position==Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()-1) {
            if(position==Storage.getInstance().loadTask(board).size()-1) {
                add_task_button.setVisibility(View.VISIBLE);
            }
            else {
                add_task_button.setVisibility(View.INVISIBLE);
            }
        }
//        cardAdapter = new CardAdapter( getContext(), R.layout.cell_card, cards);
//        listView = (ListView) v.findViewById(R.id.card_listView);
//        listView.setAdapter(cardAdapter);
        recList = (RecyclerView) v.findViewById(R.id.card_cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
//        if(Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()>0)
//            cardAdapter = new CardAdapter(this,(Board)board.getIntent().getSerializableExtra("board"),Storage.getInstance().loadTask(((Board) board.getIntent().getSerializableExtra("board"))).get(position));
//        else cardAdapter = new CardAdapter(this,(Board)board.getIntent().getSerializableExtra("board"),task);
//        cardAdapter = new CardAdapter(this,(Board)board.getIntent().getSerializableExtra("board"),position);
        cardAdapter = new CardAdapter(this,board,position);
//        cardAdapter = new CardAdapter(this,board);
        recList.setHasFixedSize(true);
        recList.setItemAnimator(new DefaultItemAnimator());
        recList.setAdapter(cardAdapter);
        cardAdapter.SetOnItemClickListener(new CardAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(view.getContext(), CardActivity.class);
                Log.i("!!!!!!!!!!!!!!!!!!!",position+"");
                intent.putExtra("card",Storage.getInstance().loadCard(board,Storage.getInstance().loadTask(board).get(position)).get(i));
//                Board temp = (Board) board.getIntent().getSerializableExtra("board");
//                intent.putExtra("task", Storage.getInstance().loadTask(temp).get(position));
//                cardAdapter.setTask(Storage.getInstance().loadTask(temp).get(position));
//                intent.putExtra("board", temp);
                intent.putExtra("board",board);
                intent.putExtra("task",Storage.getInstance().loadTask(board).get(position));
                intent.putExtra("pos", i);
                startActivity(intent);
            }
        });
        add_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        add_card_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showInputDialog1();
            }
        });
    }

    private void showInputDialog1(){
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.input_card, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        builder.setView(promptView);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Storage.getInstance().saveCard(board, Storage.getInstance().loadTask(board).get(position), new Card(editText.getText().toString(), ""));
                refreshCards();
                cardAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("XJ","PO");
                dialog.cancel();
                refreshCards();
            }
        });
        builder.show();
    }

    private void showInputDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.input_task, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        builder.setView(promptView);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Storage.getInstance().saveTask(board,new Task(editText.getText().toString()));
                if(Storage.getInstance().loadTask(board).size()>=1&&position==0)
                    taskName.setText("Task name : "+Storage.getInstance().loadTask(board).get(0).getTask_name());
//
                refreshTasks();
                Log.i("Title", title);
//                if(Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()==0) {
                if(Storage.getInstance().loadTask(board).size()==0) {
                    add_task_button.setVisibility(View.VISIBLE);
                }
                else{
//                    if(position==Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()-1) {
                    if(position==Storage.getInstance().loadTask(board).size()-1) {
                        add_task_button.setVisibility(View.VISIBLE);
                    }
                    else {
                        add_task_button.setVisibility(View.INVISIBLE);
                    }
                }
                add_card_button.setVisibility(View.VISIBLE);
                num_task++;
                taskAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                for(Task s:Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board"))){
//                    Log.e("Task-S", s.getTask_name());
//                }
                dialog.cancel();
                refreshTasks();
            }
        });
        builder.show();
    }

    private void showInputDialog2() {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.do_task_delete, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        final TextView editText = (TextView) promptView.findViewById(R.id.delete_task_name);
        final TextView x = (TextView) promptView.findViewById(R.id.xxxx);
        if (Storage.getInstance().loadTask(board).size() >= 1)
            editText.setText("Task name : " + Storage.getInstance().loadTask(board).get(position).getTask_name());
        else {
            editText.setText("No task to delete");
            x.setText("");
        }
        builder.setView(promptView);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Storage.getInstance().loadTask(board).size() >= 1) {
                    Storage.getInstance().loadTask(board).remove(position);
                    listTask.remove(position);
                    taskAdapter.notifyDataSetChanged();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
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

    private void showInputDialog3() {
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        final View promptView = layoutInflater.inflate(R.layout.do_task_rename, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        final TextView editText = (TextView) promptView.findViewById(R.id.edittext);
//        final TextView x = (TextView) promptView.findViewById(R.id.xxxx);
        if (Storage.getInstance().loadTask(board).size() >= 1)
            editText.setHint("Plese enter your task name");
        else {
            editText.setHint("No task to rename");
            editText.setEnabled(false);
        }
        builder.setView(promptView);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Storage.getInstance().loadTask(board).size() >= 1) {
                    Storage.getInstance().loadTask(board).get(position).setTask_name(editText.getText().toString());
                    listTask.get(position).setTask_name(editText.getText().toString());
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
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

    public List<Card> getCard(){
        return this.cards;
    }

    private void refreshTasks(){
        listTask.clear();
        if(Storage.getInstance().loadTask(board)!=null) {
            for (Task task : Storage.getInstance().loadTask(board)) {
                listTask.add(task);
            }
        }
        taskAdapter.notifyDataSetChanged();
    }

    private void refreshCards(){
        cards.clear();
//        if(Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board")).size()>=1) {
        if(Storage.getInstance().loadTask(board).size()>=1) {

//            Log.i("KUNDJDJ", "V");
//            if (Storage.getInstance().loadCard((Board) board.getIntent().getSerializableExtra("board"), Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board")).get(position)) != null) {
//                Log.i("KUNDJDJ", "@");
            if (Storage.getInstance().loadCard(board,Storage.getInstance().loadTask(board).get(position)) != null) {

//                Log.i("KUNDJDJ-Board", ((Board) board.getIntent().getSerializableExtra("board")).getBoard_name()+":"+((Board) board.getIntent().getSerializableExtra("board")).getReableCreatedTime());
//                Log.i("KUNDJDJ-Task", Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board")).get(position).getTask_name()+":"+Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board")).get(position).getReableCreatedTime());
//                Log.i("KUNDJDJ",Storage.getInstance().loadCard((Board) board.getIntent().getSerializableExtra("board"), Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board")).get(position)).size()+"");
                for (Card c : Storage.getInstance().loadCard(board, Storage.getInstance().loadTask(board).get(position))) {
                    Log.i("KUNDJDJ", c.getCard_name());
                    cards.add(c);
                }
            }
        }
        cardAdapter.notifyDataSetChanged();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshTasks();
        refreshCards();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
