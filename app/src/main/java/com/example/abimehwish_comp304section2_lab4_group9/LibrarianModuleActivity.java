package com.example.abimehwish_comp304section2_lab4_group9;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class LibrarianModuleActivity extends AppCompatActivity {
    private SharedPreferences myPreference;
    SharedPreferences.Editor prefEditor;


    Intent intent;

    FragmentFictionBooks fictionFragment;
    FragmentNonFictionBooks nonFictionFragment;
    FragmentHistoryBooks historyFragment;
    FragmentEducationalBooks educationalFragment;
    FragmentAllBooks allBooksFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_module);

        fictionFragment = new FragmentFictionBooks();
        nonFictionFragment = new FragmentNonFictionBooks();
        historyFragment = new FragmentHistoryBooks();
        educationalFragment = new FragmentEducationalBooks();
        allBooksFragment = new FragmentAllBooks();

        myPreference = getSharedPreferences("studentId",0);
        prefEditor = myPreference.edit();
        prefEditor.putInt("currentStudentId", -1);
        prefEditor.commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad, fictionFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.librarian_module_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.lib_add_book_option:
                intent = new Intent(LibrarianModuleActivity.this, LibrarianAddNewBook.class);
                startActivity(intent);
                break;
            case R.id.lib_fiction_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,fictionFragment).commit();
                break;
            case R.id.lib_nonfiction_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,nonFictionFragment).commit();
                break;
            case R.id.lib_history_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,historyFragment).commit();
                break;
            case R.id.lib_educational_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,educationalFragment).commit();
                break;
            case R.id.lib_all_books_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,allBooksFragment).commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}