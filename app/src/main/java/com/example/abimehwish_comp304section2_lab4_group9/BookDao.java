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
public interface BookDao {
    @Update
    void update(Book book);

    @Insert
    void insert(Book book);

    //Monitoring Query Result Changes with Live Data
    @Query("select * from Book order by category")
    LiveData<List<Book>> getAllBooks();

    //Monitoring Query Result Changes with Live Data
    @Query("select * from Book where bookId=:bookId")
    Book getBooksById(int bookId);

    //Get all books of a specific category
    @Query("select * from Book where category=:category")
    LiveData<List<Book>> getAllBooksOfCategory(String category);

    @Delete
    void delete(Book book);

    //Delete all books from a category
    @Query("DELETE FROM Book WHERE category=:category")
    void deleteByCategory(String category);
}