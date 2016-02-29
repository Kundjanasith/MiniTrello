package com.example.exceed.minitrello.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.Board;
import com.example.exceed.minitrello.models.Storage;
import com.example.exceed.minitrello.models.Task;
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
public class TaskFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Task task;
    private TaskAdapter taskAdapter;
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton add_task_button;
    private List<Task> listTask;
    private BoardActivity board;
    private TextView taskName;
    private static int num_task = 0;
    private String title;
    private int position;
    public TaskFragment(Task task,TaskAdapter taskAdapter,BoardActivity board,String title,int position) {
        // Required empty public constructor
        this.task = task;
        this.taskAdapter = taskAdapter;
        this.listTask = new ArrayList<Task>();
        this.board = board;
        this.title = title;
        this.position = position;
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
        TaskFragment fragment = new TaskFragment(this.task,this.taskAdapter,this.board,this.title,this.position);
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
        taskName.setTextColor(Color.WHITE);
        if(title.equals("Add Task")&&position==0&&Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()>=1)
            taskName.setText("Task name : "+Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).get(0).getTask_name());
//        Log.i("Poss",position+"/"+title);
        else if(!title.equals("Add Task"))taskName.setText("Task name : "+title);
        else taskName.setText(title);
        add_task_button = (FloatingActionButton) v.findViewById(R.id.add_task_button);
        add_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
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
                Log.i("edasdokg", board.getIntent().getSerializableExtra("board").hashCode() + "");
                Storage.getInstance().saveTask((Board) board.getIntent().getSerializableExtra("board"), new Task(editText.getText().toString()));
                Log.i("TH-BOARD", ((Board) board.getIntent().getSerializableExtra("board")).getBoard_name());
                for(Task s:Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board"))){
                    Log.i("TH-TASK", s.getTask_name());
                }
                if(Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).size()>=1&&position==0)
                  taskName.setText("Task name : "+Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board")).get(0).getTask_name());
//                if(taskName.getText().equals("Add Task"))
//                    taskName.setText(Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board")).get(0).getTask_name());
                refreshTasks();
//                taskName.setText(Storage.getInstance().loadTask((Board) board.getIntent().getSerializableExtra("board")).get(num_task).getTask_name());
                Log.i("Title", title);
                num_task++;
                taskAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(Task s:Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board"))){
                    Log.e("Task-S", s.getTask_name());
                }
                dialog.cancel();
                refreshTasks();
            }
        });
        builder.show();
    }

    public static int getNum_page(){
        return num_task+1;
    }

    private void refreshTasks(){
        listTask.clear();
        if(Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board"))!=null)
            for (Task task: Storage.getInstance().loadTask((Board)board.getIntent().getSerializableExtra("board"))) {
                listTask.add(task);
            }
        taskAdapter.notifyDataSetChanged();
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

    @Override
    public void onStart() {
        super.onStart();
        refreshTasks();
    }
}
