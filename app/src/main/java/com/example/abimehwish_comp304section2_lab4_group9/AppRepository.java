package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class AppRepository {
    private static final String TAG = "AppRepository";

    private final StudentDao studentDao;
    private final LibrarianDao librarianDao;
    private final BookDao bookDao;

    private LiveData<List<Student>> studentList;
    private LiveData<List<Librarian>> librarianList;
    private LiveData<List<Book>> bookList;
    private LiveData<List<Book>> bookListOfCategory;

    String statusMsg;

    public AppRepository(Context context){
        AppDatabase appDatabase = AppDatabase.getInstance(context);

        studentDao = appDatabase.studentDao();
        librarianDao = appDatabase.librarianDao();
        bookDao = appDatabase.bookDao();


        librarianList = librarianDao.getAllLibrarians();
        bookList = bookDao.getAllBooks();

    }


    //----------------Student data functions----------------
    //get all
    public LiveData<List<Student>> getAllStudents(){
        studentList = studentDao.getAllStudents();
        return studentList;
    }

    public Student getStudentById(int id){
       return getStudentByIdAsync(id);
    }
    public Student getStudentByIdAsync(final int id){
        final Student[] student = {new Student()};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    student[0] = studentDao.getStudentById(id);
                } catch (Exception e) {
                    Log.d(TAG, "Insert Student Error. "+e.getMessage());
                }
            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return student[0];
    }
    //insert student
    public String insertStudent(Student student) {
        return insertStudentAsync(student);
    }
    private String insertStudentAsync(final Student student) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentDao.insert(student);
                    statusMsg = "success";
                } catch (Exception e) {
                    Log.d(TAG, "Insert Student Error. "+e.getMessage());
                    statusMsg = "Insert Student Error. "+e.getMessage();
                }
            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return statusMsg;
    }
    //delete student
    public String deleteStudent(Student student){
        return deleteStudentAsync(student);
    }
    public String deleteStudentAsync(final Student student){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    studentDao.delete(student);
                    statusMsg = "success";
                }
                catch (Exception e){
                    Log.d(TAG, "Delete Student Error. "+e.getMessage());
                    statusMsg = "Delete Student Error. "+e.getMessage();
                }

            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return statusMsg;
    }

    //update student
    public String updateStudent(Student student){
        return updateStudentAsync(student);
    }
    private String updateStudentAsync(final Student student){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    studentDao.update(student);
                    statusMsg = "success";
                }
                catch (Exception e){
                    Log.d(TAG, "Update Student Error "+e.getMessage());
                    statusMsg = "Update Student Error. "+e.getMessage();
                }
            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return statusMsg;
    }



    //----------------Book data functions----------------
    //get all
    public LiveData<List<Book>> getAllBooks(){
        return bookList;
    }
    public LiveData<List<Book>> getAllBooksOfCategory(String category){
        return bookDao.getAllBooksOfCategory(category);
    }
    //get book by id
    public Book getBookById(int id){
        return getBookByIdAsync(id);
    }
    public Book getBookByIdAsync(final int id){
        final Book[] book = {new Book()};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    book[0] = bookDao.getBooksById(id);
                } catch (Exception e) {
                    Log.d(TAG, "Get Book Error. "+e.getMessage());
                }
            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return book[0];
    }
    //insert book
    public String insertBook(Book book) {
        return insertBookAsync(book);
    }
    private String insertBookAsync(final Book book) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDao.insert(book);
                    statusMsg = "success";
                } catch (Exception e) {
                    //insertResult.postValue(0);
                    Log.d(TAG, "Insert Book Error "+e.getMessage());
                    statusMsg = "Insert Book Error. "+e.getMessage();
                }
            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return statusMsg;
    }
    //delete book
    public String deleteBook(Book book){
        return deleteBookAsync(book);
    }
    public String deleteBookAsync(final Book book){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    bookDao.delete(book);
                    statusMsg = "success";
                }
                catch (Exception e){
                    Log.d(TAG, "Delete Book Error "+e.getMessage());
                    statusMsg = "Delete Book Error. "+e.getMessage();
                }

            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return statusMsg;
    }

    //delete book
    public String deleteBookByCategory(String category){
        return deleteBookByCategoryAsync(category);
    }
    public String deleteBookByCategoryAsync(final String category){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    bookDao.deleteByCategory(category);
                    statusMsg = "success";
                }
                catch (Exception e){
                    Log.d(TAG, "Delete Books By Category Error "+e.getMessage());
                    statusMsg = "Delete Books By Category Error. "+e.getMessage();
                }

            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return statusMsg;
    }

    //update book
    public String updateBook(Book book){
        return updateBookAsync(book);
    }
    private String updateBookAsync(final Book book){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    bookDao.update(book);
                    statusMsg = "success";
                }
                catch (Exception e){
                    Log.d(TAG, "Update Book Error "+e.getMessage());
                    statusMsg = "Update Book Error. "+e.getMessage();

                }
            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return statusMsg;
    }

    //----------------Librarian data functions----------------
    //get all
    public LiveData<List<Librarian>> getAllLibrarians(){
        return librarianList;
    }
    //insert librarian
    public String insertLibrarian(Librarian librarian) {
        return insertLibrarianAsync(librarian);
    }
    private String insertLibrarianAsync(final Librarian librarian) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    librarianDao.insert(librarian);
                    statusMsg = "success";
                } catch (Exception e) {
                    //insertResult.postValue(0);
                    Log.d(TAG, "Insert Librarian Error "+e.getMessage());
                    statusMsg = "Insert Librarian Error. "+e.getMessage();
                }
            }
        });
        thread.start();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return statusMsg;
    }
}