package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LibrarianLoginActivity extends AppCompatActivity {
    
    Button submitBtn;
    Button clearBtn;
    EditText librarianIdEt;
    EditText passwordEt;

    int librarianId;
    String password;
    Boolean auth;

    LibrarianViewModel librarianViewModel;
    List<Librarian> allLList;

    Intent intent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);

        submitBtn = findViewById(R.id.librarian_login_submit_btn);
        clearBtn = findViewById(R.id.librarian_login_clear_btn);
        librarianIdEt = findViewById(R.id.librarian_login_librarianid_et);
        passwordEt = findViewById(R.id.librarian_login_password_et);


        librarianViewModel = ViewModelProviders.of(this).get(LibrarianViewModel.class);
        librarianViewModel.getAllLibrarians().observe(this, new Observer<List<Librarian>>() {
            @Override
            public void onChanged(@Nullable List<Librarian> librarians) {
                allLList = librarians;
            }
        });
        setEventHandlers();
    }

    private void setEventHandlers() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                librarianId = Integer.parseInt(librarianIdEt.getText().toString());
                password = passwordEt.getText().toString();

                auth = librarianViewModel.authenticate(librarianId, password, allLList);
                if(auth){
                    Toast.makeText(LibrarianLoginActivity.this, "authenticated", Toast.LENGTH_SHORT).show();
                    intent = new Intent(LibrarianLoginActivity.this, LibrarianModuleActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LibrarianLoginActivity.this, "invalid login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordEt.setText("");
                librarianIdEt.setText("");
            }
        });
    }

}