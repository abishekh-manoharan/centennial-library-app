package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private LibrarianViewModel librarianViewModel;
    private BookViewModel bookViewModel;

    private Button btnStudentLogin;
    private Button btnStudentSignup;
    private Button btnLibrarianLogin;
    private Button btnLibrarianSignup;

    private Intent intent;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiating view objects
        btnStudentLogin = findViewById(R.id.studentLoginBtn);
        btnStudentSignup = findViewById(R.id.studentSignupBtn);
        btnLibrarianLogin = findViewById(R.id.librarianLoginBtn);
        btnLibrarianSignup = findViewById(R.id.librarianSignUpBtn);

        //instantiating Viewmodel objects
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        librarianViewModel = ViewModelProviders.of(this).get(LibrarianViewModel.class);

        setEventHandlers();
    }

    void setEventHandlers(){

        //Student signup button click event
        btnStudentSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, StudentSignUpActivity.class);
                startActivity(intent);
            }
        });

        //Student login button click event
        btnStudentLogin.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   intent = new Intent(MainActivity.this, StudentLoginActivity.class);
                   startActivity(intent);
               }
           }
        );
        //librarian login button click event

        btnLibrarianSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, LibrarianSignUpActivity.class);
                startActivity(intent);
            }
        });

        //Student login button click event
        btnLibrarianLogin.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   intent = new Intent(MainActivity.this, LibrarianLoginActivity.class);
                   startActivity(intent);
               }
           }
        );

    }

}

