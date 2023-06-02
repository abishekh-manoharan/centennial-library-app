package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentStudentProfile extends Fragment {
    TextView studentIdTV;
    TextView studentFnameTV;
    TextView studentLnameTV;
    TextView borrowedBookTitleTV;

    int currentStudentId;

    StudentViewModel studentViewModel;
    BookViewModel bookViewModel;

    SharedPreferences myPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_profile, container, false);

        studentIdTV = view.findViewById(R.id.studentid_tv);
        studentFnameTV = view.findViewById(R.id.student_fname_tv);
        studentLnameTV = view.findViewById(R.id.student_lname_tv);
        borrowedBookTitleTV = view.findViewById(R.id.borrowed_book_tv);

        myPreference = getActivity().getSharedPreferences("studentId",0);
        currentStudentId = myPreference.getInt("currentStudentId", -1);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        Student student = studentViewModel.getStudentById(currentStudentId);

        studentIdTV.setText(String.valueOf(student.getStudentId()));
        studentFnameTV.setText(student.getFName());
        studentLnameTV.setText(student.getLName());

        if(student.getBookId() != -1) {
            bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
            Book book = bookViewModel.getBookById(student.getBookId());
            borrowedBookTitleTV.setText(book.getBookName());
        }
        else{
            borrowedBookTitleTV.setText("No book borrowed.");
        }

        return view;
    }
}