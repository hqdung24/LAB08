package com.example.lab05;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentInfo extends Fragment implements FragmentCallbacks{
    MainActivity main; TextView txtRed; Button btnRedClock; Context context;

    TextView txtName;
    TextView txtClass;
    TextView txtScore;

    Button first;
    Button prev;
    Button next;
    Button last;
    public static FragmentInfo newInstance(String strArg1) {
        FragmentInfo fragment = new FragmentInfo();
        Bundle bundle = new Bundle(); bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity(); // Get the context
        if (!(context instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity) context; // Now it's safe to cast
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view_layout_red = (LinearLayout) inflater.inflate(R.layout.fragment_studentinfo, null);
        txtName = (TextView) view_layout_red.findViewById(R.id.textName);
        txtClass = (TextView) view_layout_red.findViewById(R.id.textClass);
        txtScore = (TextView) view_layout_red.findViewById(R.id.textScore);

        first = (Button) view_layout_red.findViewById(R.id.first);
        prev = (Button) view_layout_red.findViewById(R.id.previous);
        next = (Button) view_layout_red.findViewById(R.id.next);
        last = (Button) view_layout_red.findViewById(R.id.last);

        first.setOnClickListener(v -> main.onMsgFromFragToMain("INFO-FLAG","","","","FIRST"));
        prev.setOnClickListener(v -> main.onMsgFromFragToMain("INFO-FLAG","","","","PREVIOUS"));
        next.setOnClickListener(v -> main.onMsgFromFragToMain("INFO-FLAG","","","","NEXT"));
        last.setOnClickListener(v -> main.onMsgFromFragToMain("INFO-FLAG","","","","LAST"));


        return view_layout_red;
    }
    @Override
    public void onMsgFromMainToFragment(String name, String classid, String score) {
        txtName.setText("Name: " + name);
        txtClass.setText("Class: " + classid);
        txtScore.setText("Average score: "+ score);
    }

}
