package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FragmentAllBooks extends Fragment {

    LiveData<List<Book>> booksLiveData;
    List<Book> booksList;
    BookViewModel bookViewModel;

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_librarian_module_all_books, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.librarian_allbooks_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        bookAdapter = new BookAdapter(getActivity());

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        booksLiveData = bookViewModel.getAllBooks();

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