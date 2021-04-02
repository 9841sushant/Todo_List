package com.example.todolist.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.todolist.AddUpdateTask.AddUpdateTaskActivity;
import com.example.todolist.Fragments.MainFragment;
import com.example.todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity  {

    // Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();
    // Member variables for the adapter and RecyclerView
    RecyclerView mRecyclerView;
   TaskAdapter mAdapter;
    MainActivityViewModel viewModel;
    Fragment mFragment;
    FragmentManager mFragmentManager;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment=new MainFragment();
        mFragmentManager=getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.Main_xml,mFragment)
                .commit();

        user_id=getIntent().getStringExtra("userId");
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);





        /*
         Set the Floating Action Button (FAB) to its corresponding View.
         Attach an OnClickListener to it, so that when it's clicked, a new intent will be created
         to launch the AddTaskActivity.
         */
        FloatingActionButton fabButton = findViewById(R.id.fab);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to start an AddTaskActivity
                Intent addTaskIntent = new Intent(MainActivity.this, AddUpdateTaskActivity.class);
                addTaskIntent.putExtra("userId",user_id);
                startActivity(addTaskIntent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate( R.menu.delete_menu, menu );

        inflater.inflate( R.menu.logout_menu, menu );


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_tasks:
                viewModel.deleteAllNotes();
                Toast.makeText(this, "Deleted all Tasks", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout_app:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(
                        "Do you really want to logout?")
                        // Setting Icon to Dialog

                        .setCancelable(false)
                        .setPositiveButton("Logout",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        //to perform on ok
                                        finish();


                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {

                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();


            default:
                return super.onOptionsItemSelected(item);
        }

    }

}


