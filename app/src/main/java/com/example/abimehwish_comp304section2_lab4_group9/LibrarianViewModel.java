package com.example.abimehwish_comp304section2_lab4_group9;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class LibrarianViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private AppRepository appRepository;
    //    private LiveData<Integer> insertResult;
//    private LiveData<Integer> deleteResult;
    private LiveData<List<Librarian>> allLibrarians;
    //
    public LibrarianViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allLibrarians = appRepository.getAllLibrarians();
    }

    //returns query results as live data object
    LiveData<List<Librarian>> getAllLibrarians() { return allLibrarians; }
    //calls repository to insert a librarian
    public String insert(Librarian librarian) { return appRepository.insertLibrarian(librarian); }

    //authenticate login
    boolean authenticate(int librarianid, String password, List<Librarian> librarianList){
        try {
            for (Librarian s: librarianList) {
                if (s.getLibrarianId()==librarianid){
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
