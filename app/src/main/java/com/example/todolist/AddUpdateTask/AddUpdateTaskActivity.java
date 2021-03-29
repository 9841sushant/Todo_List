package com.example.todolist.AddUpdateTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.todolist.Fragments.AddUpdate;
import com.example.todolist.R;

public class AddUpdateTaskActivity extends AppCompatActivity {


    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Constant for logging
    private static final String TAG = AddUpdateTaskActivity.class.getSimpleName();
    Fragment mFragment;
    FragmentManager mFragmentManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_task);
        mFragment=new AddUpdate();
        mFragmentManager=getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.AddUpdateFrag,mFragment)
                .commit();

    }
}