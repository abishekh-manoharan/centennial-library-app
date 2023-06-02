package com.example.abimehwish_comp304section2_lab4_group9;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class StudentViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private AppRepository appRepository;

    private LiveData<List<Student>> allStudents;
    private List<Student> allStudentsList;
    //
    public StudentViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);

    }

    //returns query results as live data object
    LiveData<List<Student>> getAllStudents() {
        allStudents = appRepository.getAllStudents();
        return allStudents; }
    //returns student by id
    Student getStudentById(int studentId){
        return appRepository.getStudentById(studentId);
    }
    //calls repository to insert a student
    public String insert(Student student) {
        return appRepository.insertStudent(student);
    }
    //calls repository to update a student
    public String update(Student student) {
        return appRepository.updateStudent(student);
    }
    //calls repository to delete a student
    public String delete(Student student) {
        return appRepository.deleteStudent(student);
    }

    //authenticate login
    boolean authenticate(int studentid, String password, List<Student> studentList){
        try {
            for (Student s: studentList) {
                if (s.getStudentId()==studentid){
                    if(s.getPassword().equals(password)){
                        return true; // user inputs match existing entry
                    }
                }
            }
        }
        catch (Exception e) {
            Log.d(" ", e.getMessage());
        }

        return false; // user inputs dont match existing entry
    }
}
