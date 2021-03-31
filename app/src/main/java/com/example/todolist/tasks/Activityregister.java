package com.example.todolist.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.database.User;
import com.example.todolist.database.AppDatabase;
import com.example.todolist.database.TaskDao;
import com.example.todolist.database.repoUser;

public class Activityregister extends AppCompatActivity {
    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText password;

    private Button registerButton;
    private Button cancelButton;
    private AppDatabase database;
    private TaskDao taskDaoO;
    private repoUser repouser;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityregister);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering Now");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        name = findViewById(R.id.register_name);
        database= AppDatabase.getInstance(this);
        repouser=new repoUser(database);
        lastName = findViewById(R.id.register_lastName);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.register_user);




        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty())
                {
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User(name.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString());
                            repouser.insertUser(user);
                            progressDialog.dismiss();
                            startActivity(new Intent(Activityregister.this, ActivityLogin.class));
                        }
                    },1000);
                }
                else{
                    Toast.makeText(Activityregister.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isEmpty()
    {
        if(TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(lastName.getText().toString())||TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
            return  true;
        }
        else
        {
            return  false;
        }
    }
}