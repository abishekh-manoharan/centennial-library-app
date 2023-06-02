package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.LiveData;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

// this interface declares database functions
// and does the mapping of SQL queries to functions
@Dao
public interface StudentDao {
    @Update
    void update(Student student);

    @Insert
    void insert(Student student);

    //Monitoring Query Result Changes with Live Data
    @Query("select * from Student order by lName")
    LiveData<List<Student>> getAllStudents();

    //Monitoring Query Result Changes with Live Data
    @Query("select * from Student where studentId=:studentId")
    Student getStudentById(int studentId);

    @Delete
    void delete(Student student);
}