package com.example.abimehwish_comp304section2_lab4_group9;


import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Database;
import android.content.Context;
//Room database class
@Database(entities = {Student.class, Librarian.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "LibraryDB";
    public abstract StudentDao studentDao();
    public abstract LibrarianDao librarianDao();
    public abstract BookDao bookDao();

    //Singleton Pattern to have one instance of DB
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}
