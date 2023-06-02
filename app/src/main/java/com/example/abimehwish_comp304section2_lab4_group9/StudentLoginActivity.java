package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class StudentLoginActivity extends AppCompatActivity {
    Button submitBtn;
    Button clearBtn;
    EditText studentIdEt;
    EditText passwordEt;

    int studentId;
    String password;
    Boolean auth;

    StudentViewModel studentViewModel;
    LiveData<List<Student>> allStudents;
    List<Student> allSList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        submitBtn = findViewById(R.id.student_login_submit_btn);
        clearBtn = findViewById(R.id.student_login_clear_btn);
        studentIdEt = findViewById(R.id.student_login_studentid_et);
        passwordEt = findViewById(R.id.student_login_password_et);

        allStudents = studentViewModel.getAllStudents();

        allStudents.observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                try {
                    allSList = students;
                }
                catch (Exception e){
                    Log.d(" ", e.getMessage());
                }
            }
        });

        setEventHandlers();
    }

    private void setEventHandlers() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentId = Integer.parseInt(studentIdEt.getText().toString());
                password = passwordEt.getText().toString();

                auth = studentViewModel.authenticate(studentId, password, allSList);

                if(auth){
                    Intent intent = new Intent(StudentLoginActivity.this, StudentModuleActivity.class);
                    intent.putExtra(StudentModuleActivity.EXTRA_STUDENT_ID, studentId);
                    startActivity(intent);
                    Toast.makeText(StudentLoginActivity.this, "authenticated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(StudentLoginActivity.this, "invalid login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentIdEt.setText("");
                passwordEt.setText("");
            }
        });
    }

}