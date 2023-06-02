package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
// this is a Room entity class to describe
// a database table Structured Data
//SQL - NoSQL (Firebase, MongoDB)
@Entity
public class Librarian {
    @PrimaryKey(autoGenerate = true)
    private int librarianId;
    private String fName;
    private String lName;
    private String password;


    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
