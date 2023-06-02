package com.example.abimehwish_comp304section2_lab4_group9;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;
//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class BookViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private AppRepository appRepository;
    //    private LiveData<Integer> insertResult;
//    private LiveData<Integer> deleteResult;
    private LiveData<List<Book>> allBooks;
    //
    public BookViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
//        insertResult = bookRepository.getInsertResult();
//        deleteResult = bookRepository.getDeleteResult();
        allBooks = appRepository.getAllBooks();
    }

    //returns query results as live data object
    LiveData<List<Book>> getAllBooks() { return allBooks; }

    //getting books of specified category
    public LiveData<List<Book>> getAllBooksOfCategory(String category){
        return appRepository.getAllBooksOfCategory(category);
    }

    public Book getBookById(int id){
        return appRepository.getBookById(id);
    }

    //calls repository to insert a book
    public String insert(Book book) {
        return appRepository.insertBook(book);
    }
    //calls repository to update a book
    public String update(Book book) {
        return appRepository.updateBook(book);
    }
    //calls repository to delete a book
    public String delete(Book book) {
        return appRepository.deleteBook(book);
    }

    //calls repository to delete a book
    public String deleteByCategory(String category) {
        return appRepository.deleteBookByCategory(category);
    }


}
