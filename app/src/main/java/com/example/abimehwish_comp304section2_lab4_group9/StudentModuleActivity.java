package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class StudentModuleActivity extends AppCompatActivity {
    public final static String EXTRA_STUDENT_ID = "com.example.abimehwish_comp304section2_lab4_group9.EXTRA_STUDENT_ID";

    private static StudentViewModel studentViewModel;
    private static BookViewModel bookViewModel;

    private SharedPreferences myPreference;
    SharedPreferences.Editor prefEditor;


    Intent intent;

    FragmentFictionBooks fictionFragment;
    FragmentNonFictionBooks nonFictionFragment;
    FragmentHistoryBooks historyFragment;
    FragmentEducationalBooks educationalFragment;
    FragmentAllBooks allBooksFragment;
    FragmentStudentProfile studentProfileFragment;

    private static int currentStudentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_module);

        fictionFragment = new FragmentFictionBooks();
        nonFictionFragment = new FragmentNonFictionBooks();
        historyFragment = new FragmentHistoryBooks();
        educationalFragment = new FragmentEducationalBooks();
        allBooksFragment = new FragmentAllBooks();
        studentProfileFragment = new FragmentStudentProfile();

        intent = getIntent();
        currentStudentId = intent.getIntExtra(EXTRA_STUDENT_ID,1);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        myPreference = getSharedPreferences("studentId",0);
        prefEditor = myPreference.edit();
        prefEditor.putInt("currentStudentId", currentStudentId);
        prefEditor.commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad, fictionFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_module_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.student_fiction_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,fictionFragment).commit();
                break;
            case R.id.student_nonfiction_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,nonFictionFragment).commit();
                break;
            case R.id.student_history_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,historyFragment).commit();
                break;
            case R.id.student_educational_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,educationalFragment).commit();
                break;
            case R.id.student_view_profile_option:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameToLoad,studentProfileFragment).commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public static String callStudentUpdate(Student student){ return studentViewModel.update(student); }
    public static String callBookUpdate(Book book){
        return bookViewModel.update(book);
    }
    public static Book callGetBookById(int id){
        return bookViewModel.getBookById(id);
    }
}