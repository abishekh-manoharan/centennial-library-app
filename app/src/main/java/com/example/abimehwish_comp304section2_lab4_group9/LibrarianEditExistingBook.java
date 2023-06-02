package com.example.abimehwish_comp304section2_lab4_group9;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LibrarianEditExistingBook extends AppCompatActivity {
    public final static String EXTRA_ID = "com.example.abimehwish_comp304section2_lab4_group9.EXTRA_ID";
    public final static String EXTRA_BOOK_NAME =  "com.example.abimehwish_comp304section2_lab4_group9.EXTRA_BOOK_NAME";
    public final static String EXTRA_AUTHOR =  "com.example.abimehwish_comp304section2_lab4_group9.EXTRA_AUTHOR";
    public final static String EXTRA_DESCRIPTION =  "com.example.abimehwish_comp304section2_lab4_group9.EXTRA_DESCRIPTION";
    public final static String EXTRA_CATEGORY =  "com.example.abimehwish_comp304section2_lab4_group9.EXTRA_CATEGORY";
    public final static String EXTRA_QUANTITY =  "com.example.abimehwish_comp304section2_lab4_group9.EXTRA_QUANTITY";

    BookViewModel bookViewModel;

    TextView bookId_tv;
    EditText bookname_et;
    EditText author_et;
    EditText description_et;
    Spinner categories_spinner;
    EditText quantity_et;

    Button submitBtn;
    Button deleteBtn;
    Button clearBtn;

    int bookId;
    String bookName;
    String author;
    String description;
    String genre;
    int quantity;

    String status = "";
    Book book;

    Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_edit_existing_book);

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        book = new Book();

        intent = getIntent();

        //getting initial values from intent
        bookId = intent.getIntExtra(EXTRA_ID,1);
        bookName = intent.getStringExtra(EXTRA_BOOK_NAME);
        author = intent.getStringExtra(EXTRA_AUTHOR);
        description = intent.getStringExtra(EXTRA_DESCRIPTION);
        genre = intent.getStringExtra(EXTRA_CATEGORY);
        quantity = intent.getIntExtra(EXTRA_QUANTITY,1);

        //initializing field values
        categories_spinner = findViewById(R.id.book_genres_edit_spinner);
        submitBtn = findViewById(R.id.edit_book_submit_btn);
        clearBtn = findViewById(R.id.edit_book_clear_btn);
        deleteBtn = findViewById(R.id.edit_book_delete_btn);
        bookId_tv = findViewById(R.id.bookid_edit_tv);
        bookname_et = findViewById(R.id.bookName_edit_et);
        author_et = findViewById(R.id.author_edit_et);
        description_et = findViewById(R.id.description_edit_et);
        quantity_et = findViewById(R.id.quantity_edit_et);

        //setting initial field values
        bookId_tv.setText(String.valueOf(bookId));
        bookname_et.setText(bookName);
        author_et.setText(author);
        description_et.setText(description);
        quantity_et.setText(String.valueOf(quantity));

        switch (genre) {
            case "Fiction" :
                categories_spinner.setSelection(0);
                break;
            case "Non-Fiction" :
                categories_spinner.setSelection(1);
                break;
            case "Educational" :
                categories_spinner.setSelection(2);
                break;
            case "History" :
                categories_spinner.setSelection(3);
                break;
        }
        setEventHandlers();
    }
    private void setEventHandlers(){

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(author_et.getText().toString().equals("") || description_et.getText().toString().equals("") || quantity_et.getText().toString().equals("") || bookname_et.getText().toString().equals("")){
                    Toast.makeText(LibrarianEditExistingBook.this, "All Fields Required.", Toast.LENGTH_SHORT).show();
                }
                else {
                book.setBookId(Integer.parseInt(bookId_tv.getText().toString()));
                book.setAuthorName(author_et.getText().toString());
                book.setBookDescription(description_et.getText().toString());
                book.setCategory(categories_spinner.getSelectedItem().toString());
                book.setQuantity(Integer.parseInt(quantity_et.getText().toString()));
                book.setBookName(bookname_et.getText().toString());

                status = bookViewModel.update(book);

                Toast.makeText(LibrarianEditExistingBook.this, ""+status, Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book.setBookId(Integer.parseInt(bookId_tv.getText().toString()));
                status = bookViewModel.delete(book);
                Toast.makeText(LibrarianEditExistingBook.this, ""+status, Toast.LENGTH_SHORT).show();
                intent = new Intent(LibrarianEditExistingBook.this, LibrarianModuleActivity.class);
                startActivity(intent);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                author_et.setText("");
                bookname_et.setText("");
                description_et.setText("");
                quantity_et.setText("");
                categories_spinner.setSelection(0);
            }
        });
    }
}