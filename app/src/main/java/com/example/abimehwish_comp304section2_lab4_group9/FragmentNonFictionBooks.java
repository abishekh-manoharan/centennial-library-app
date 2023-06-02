package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentNonFictionBooks extends Fragment {

    LiveData<List<Book>> booksLiveData;
    BookViewModel bookViewModel;
    StudentViewModel studentViewModel;

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private RecyclerView.LayoutManager layoutManager;

    int currentStudentId;
    Student currentStudent;

    private SharedPreferences myPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_librarian_module_non_fiction, container, false);

        myPreference = getActivity().getSharedPreferences("studentId",0);
        currentStudentId = myPreference.getInt("currentStudentId", -1);

        if(currentStudentId!=-1) {
            studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
            currentStudent = studentViewModel.getStudentById(currentStudentId);
            bookAdapter = new BookAdapter(getActivity(), currentStudent);
        }
        else {
            bookAdapter = new BookAdapter(getActivity());
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.librarian_non_fiction_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        booksLiveData = bookViewModel.getAllBooksOfCategory("Non-Fiction");

        booksLiveData.observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                bookAdapter.setBookList(books);
            }
        });

        recyclerView.setAdapter(bookAdapter);

        return view;
    }

}