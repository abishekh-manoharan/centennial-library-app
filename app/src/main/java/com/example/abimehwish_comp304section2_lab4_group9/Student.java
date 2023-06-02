package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
// this is a Room entity class to describe
// a database table Structured Data
//SQL - NoSQL (Firebase, MongoDB)
@Entity
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int studentId;
    private String fName;
    private String lName;
    private String password;
    private int bookId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

}
