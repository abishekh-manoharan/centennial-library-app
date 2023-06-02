package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LibrarianAddNewBook extends AppCompatActivity {

    Spinner categories_spinner;
    BookViewModel bookViewModel;
    EditText bookId_et;
    EditText bookname_et;
    EditText author_et;
    EditText description_et;
    EditText quantity_et;
    Button submitBtn;
    Button clearBtn;

    int bookId;
    String bookname;
    String author;
    String description;
    String genre;
    int quantity;

    String status = "";
    Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_add_new_book);

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        categories_spinner = findViewById(R.id.book_genres_spinner);
        submitBtn = findViewById(R.id.add_book_submit_btn);
        clearBtn = findViewById(R.id.add_book_clear_btn);
        bookId_et = findViewById(R.id.bookid_et);
        bookname_et = findViewById(R.id.bookName_et);
        author_et = findViewById(R.id.author_et);
        description_et = findViewById(R.id.description_et);
        quantity_et = findViewById(R.id.quantity_et);

        book = new Book();

        setEventHandlers();
    }

    private void setEventHandlers(){
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(author_et.getText().toString().equals("") || description_et.getText().toString().equals("") || quantity_et.getText().toString().equals("") || bookname_et.getText().toString().equals("")|| bookId_et.getText().toString().equals("")){
                    Toast.makeText(LibrarianAddNewBook.this, "All Fields Required.", Toast.LENGTH_SHORT).show();
                }
                else {
                    bookId = Integer.parseInt(bookId_et.getText().toString());
                    bookname = bookname_et.getText().toString();
                    author = author_et.getText().toString();
                    description = description_et.getText().toString();
                    ;
                    genre = categories_spinner.getSelectedItem().toString();
                    quantity = Integer.parseInt(quantity_et.getText().toString());
                    ;

                    book.setBookId(bookId);
                    book.setBookName(bookname);
                    book.setAuthorName(author);
                    book.setBookDescription(description);
                    book.setCategory(genre);
                    book.setQuantity(quantity);

                    status = bookViewModel.insert(book);

                    Toast.makeText(LibrarianAddNewBook.this, "" + status, Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookId_et.setText("");
                bookname_et.setText("");
                quantity_et.setText("");
                description_et.setText("");
                author_et.setText("");

            }
        });
    }
}