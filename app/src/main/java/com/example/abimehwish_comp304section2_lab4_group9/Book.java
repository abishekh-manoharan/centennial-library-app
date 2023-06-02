package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
// this is a Room entity class to describe
// a database table Structured Data
//SQL - NoSQL (Firebase, MongoDB)
@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String category;
    private int quantity;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
