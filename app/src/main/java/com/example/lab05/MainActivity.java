package com.example.lab05;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements MainCallbacks {
    FragmentTransaction ft; FragmentInfo redFragment; FragmentID blueFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        ft = getSupportFragmentManager().beginTransaction(); blueFragment = FragmentID.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment); ft.commit();

        ft = getSupportFragmentManager().beginTransaction(); redFragment = FragmentInfo.newInstance("first-red");
        ft.replace(R.id.main_holder_red, redFragment); ft.commit();
    }

    @Override
    public void onMsgFromFragToMain(String sender, String name, String classid, String score,String command) {
        if(sender.equals("INFO-FLAG")) {
            blueFragment.onNavigationCommand(command);
        }

        if(sender.equals("ID-FLAG")) {
            redFragment.onMsgFromMainToFragment(name, classid, score);
        }

    }

}