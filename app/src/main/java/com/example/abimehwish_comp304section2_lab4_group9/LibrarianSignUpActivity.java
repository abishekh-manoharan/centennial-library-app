package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LibrarianSignUpActivity extends AppCompatActivity {
    LibrarianViewModel librarianViewModel;

    private EditText librarianId;
    private EditText fName;
    private EditText lName;
    private EditText password;

    private Button submitBtn;
    private Button clearBtn;

    String status;

    Librarian librarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_sign_up);

        librarianViewModel = ViewModelProviders.of(this).get(LibrarianViewModel.class);

        librarianId = findViewById(R.id.librarianid_et);
        fName = findViewById(R.id.librarian_fname_et);
        lName = findViewById(R.id.librarian_lname_et);
        password = findViewById(R.id.librarian_password_et);
        submitBtn = findViewById(R.id.librarian_signup_submit_btn);
        clearBtn = findViewById(R.id.librarian_signup_clear_btn);

        librarian = new Librarian();
        
        setEventhandlers();
        
    }
    
    private void setEventhandlers(){
        //submit button click event
        submitBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(librarianId.getText().toString().equals("") || fName.getText().toString().equals("") || lName.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(LibrarianSignUpActivity.this, "All Fields Required.", Toast.LENGTH_SHORT).show();
                }
                else {
                    librarian.setLibrarianId(Integer.parseInt(librarianId.getText().toString()));
                    librarian.setFName(fName.getText().toString());
                    librarian.setLName(lName.getText().toString());
                    librarian.setPassword(password.getText().toString());

                    status = librarianViewModel.insert(librarian);
                    Toast.makeText(LibrarianSignUpActivity.this, " "+status, Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                librarianId.setText("");
                fName.setText("");
                lName.setText("");
                password.setText("");
            }
        });
    }
}