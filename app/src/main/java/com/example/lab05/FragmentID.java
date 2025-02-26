package com.example.lab05;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Arrays;

public class FragmentID extends Fragment{

    MainActivity main; Context context = null; String message = "";
    String[] sid = {
            "Data-0", "Data-1", "Data-2", "Data-3", "Data-4",
            "Data-5", "Data-6", "Data-7", "Data-8", "Data-9",
            "Data-10", "Data-11", "Data-12", "Data-13", "Data-14"
    };

    String[] classID = {
            "Class-0", "Class-1", "Class-2", "Class-3", "Class-4",
            "Class-5", "Class-6", "Class-7", "Class-8", "Class-9",
            "Class-10", "Class-11", "Class-12", "Class-13", "Class-14"
    };

    Integer[] img = {
            R.drawable.pic01_small, R.drawable.pic01_small, R.drawable.pic01_small,
            R.drawable.pic01_small, R.drawable.pic01_small, R.drawable.pic01_small,
            R.drawable.pic01_small, R.drawable.pic01_small, R.drawable.pic01_small,
            R.drawable.pic01_small, R.drawable.pic01_small, R.drawable.pic01_small,
            R.drawable.pic01_small, R.drawable.pic01_small, R.drawable.pic01_small
    };

    String[] scores = {
            "9.0", "7.5", "6.8",
            "8.3", "5.5", "9.5",
            "3.0", "8.8", "4.3",
            "10.0", "9.8", "8.0",
            "8.0", "7.8", "5.0"
    };

    ListView myListView;
    TextView txtMsg;
    Button btnPage1, btnPage2, btnPage3;
    int pageSize = 5; // Each page contains 5 items
    int currentPage = 0; // Start from page 0

    int currentIndex = 0;
    public static FragmentID newInstance(String strArg) {
        FragmentID fragment = new FragmentID();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        if (context instanceof MainActivity) {
            main = (MainActivity) context;  // Safe casting
        } else {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }


    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studentid, container, false);

        txtMsg = view.findViewById(R.id.txtMsg);
        btnPage1 = view.findViewById(R.id.btnPage1);
        btnPage2 = view.findViewById(R.id.btnPage2);
        btnPage3 = view.findViewById(R.id.btnPage3);
        myListView = view.findViewById(R.id.my_list);
        loadPage(0);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                String name = sid[currentPage * pageSize + position];
                String classid = classID[currentPage * pageSize + position];
                String score = scores[currentPage * pageSize + position];
                main.onMsgFromFragToMain("ID-FLAG", name, classid, score, "");
            }
        });

        // Button click listeners
        btnPage1.setOnClickListener(v -> {
            loadPage(0);
            updateButtonFocus(btnPage1);
        });

        btnPage2.setOnClickListener(v -> {
            loadPage(1);
            updateButtonFocus(btnPage2);
        });

        btnPage3.setOnClickListener(v -> {
            loadPage(2);
            updateButtonFocus(btnPage3);
        });

        // Load first page by default and set button 1 as focused
        loadPage(0);
        updateButtonFocus(btnPage1);
        return view;
    }


    public void onNavigationCommand(String command) {
        switch (command) {
            case "FIRST":
                selectFirstItem();
                break;
            case "PREVIOUS":
                selectPreviousItem();
                break;
            case "NEXT":
                selectNextItem();
                break;
            case "LAST":
                selectLastItem();
                break;
            default:
                Log.d("FragmentID", "Invalid command: " + command);
        }
    }

    private void loadPage(int page) {
        currentPage = page;
        int start = page * pageSize;
        int end = Math.min(start + pageSize, sid.length);

        String[] pageItems = Arrays.copyOfRange(sid, start, end);
        Integer[] pageThumbnails = Arrays.copyOfRange(img, start, end);

        // Set adapter with new data
        CustomIconLabelAdapter adapter = new CustomIconLabelAdapter(context, R.layout.custom_icon, pageItems, pageThumbnails);
        myListView.setAdapter(adapter);

       // updateSelection();

    }
    private void updateButtonFocus(Button selectedButton) {
        // Reset all buttons to default color
        btnPage1.setBackgroundColor(Color.LTGRAY);
        btnPage2.setBackgroundColor(Color.LTGRAY);
        btnPage3.setBackgroundColor(Color.LTGRAY);

        // Highlight the selected button
        selectedButton.setBackgroundColor(Color.BLUE);
    }

    private void selectFirstItem() {
        currentIndex = 0;
        updateSelection();
    }

    private void selectPreviousItem() {
        if (currentIndex > 0) {
            currentIndex--;
            updateSelection();
        }
    }

    private void selectNextItem() {
        if (currentIndex < pageSize - 1 && (currentPage * pageSize + currentIndex + 1) < sid.length) {
            currentIndex++;
            updateSelection();
        }
    }

    private void selectLastItem() {
        int lastIndex = Math.min(pageSize - 1, sid.length - (currentPage * pageSize) - 1);
        currentIndex = lastIndex;
        updateSelection();
    }

    private void updateSelection() {
        main.onMsgFromFragToMain("ID-FLAG", sid[currentPage * pageSize + currentIndex], classID[currentPage * pageSize + currentIndex], scores[currentPage * pageSize + currentIndex], "");
    }
}
