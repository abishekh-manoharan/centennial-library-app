package com.example.abimehwish_comp304section2_lab4_group9;

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

public class StudentSignUpActivity extends AppCompatActivity {
    StudentViewModel studentViewModel;

    private EditText studentId;
    private EditText fName;
    private EditText lName;
    private EditText password;

    private Button submitBtn;
    private Button clearBtn;

    Intent intent;
    String status;

    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        studentId = findViewById(R.id.studentid_et);
        fName = findViewById(R.id.student_fname_et);
        lName = findViewById(R.id.student_lname_et);
        password = findViewById(R.id.student_password_et);
        submitBtn = findViewById(R.id.student_signup_submit_btn);
        clearBtn = findViewById(R.id.student_signup_clear_btn);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        student = new Student();

        setEventHandlers();

    }

    void setEventHandlers(){
        //submit button click event
        submitBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(studentId.getText().toString().equals("") || fName.getText().toString().equals("") || lName.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(StudentSignUpActivity.this, "All Fields Required.", Toast.LENGTH_SHORT).show();
                }
                else {
                    student.setStudentId(Integer.parseInt(studentId.getText().toString()));
                    student.setFName(fName.getText().toString());
                    student.setLName(lName.getText().toString());
                    student.setPassword(password.getText().toString());
                    student.setBookId(-1);

                    status = studentViewModel.insert(student);
                    Toast.makeText(StudentSignUpActivity.this, " "+status, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //clear button on click
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentId.setText("");
                fName.setText("");
                lName.setText("");
                password.setText("");
            }
        });
    }

    }
