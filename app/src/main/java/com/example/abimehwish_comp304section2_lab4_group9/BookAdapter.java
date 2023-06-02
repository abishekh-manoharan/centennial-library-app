package com.example.abimehwish_comp304section2_lab4_group9;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    private List<Book> books = new ArrayList<>();
    FragmentActivity activity;
    Student student;

    public BookAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    public BookAdapter(FragmentActivity activity, Student student) {
        this.activity = activity;
        this.student = student;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item, viewGroup, false);
        return new BookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {
        Book currBook = books.get(i);
        //bookHolder.bookIdTv.setText(String.valueOf(currBook.getBookId()));
        bookHolder.bookNameTv.setText(currBook.getBookName());
        bookHolder.authorTv.setText(currBook.getAuthorName());
        bookHolder.descriptionTv.setText(currBook.getBookDescription());
        bookHolder.quantityTv.setText(String.valueOf(currBook.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBookList(List<Book> books){
        this.books = books;
        notifyDataSetChanged();
    }

    class BookHolder extends RecyclerView.ViewHolder {
        private TextView bookIdTv;
        private TextView bookNameTv;
        private TextView authorTv;
        private TextView descriptionTv;
        private TextView quantityTv;

        int position;

        public BookHolder(View itemView){
            super(itemView);
            //bookIdTv = itemView.findViewById(R.id.bookid_tv);
            bookNameTv = itemView.findViewById(R.id.title_tv);
            authorTv = itemView.findViewById(R.id.author_tv);
            descriptionTv = itemView.findViewById(R.id.description_tv);
            quantityTv = itemView.findViewById(R.id.quantity_tv);
            //case where student is the one signed in
            if(student!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    position = getAdapterPosition();
                        //case where student has no books borrowed
                        if(student.getBookId() == -1){
                            //updating student's bookId value
                            student.setBookId(books.get(position).getBookId());
                            String status = StudentModuleActivity.callStudentUpdate(student);
                            books.get(position).setQuantity(books.get(position).getQuantity()-1);
                            String status2 = StudentModuleActivity.callBookUpdate(books.get(position));
                            Toast.makeText(activity, status+"! Book with ID: " + student.getBookId() + " borrowed.", Toast.LENGTH_SHORT).show();
                        }
                        //case where student is trying to borrow the same book twice
                        else if(student.getBookId() == books.get(position).getBookId()){
                            Toast.makeText(activity, "Book already borrowed "+student.getBookId(), Toast.LENGTH_SHORT).show();
                        }
                        //case where student is borrowing a new book while currently borrowing a different book
                        else{
                            //updating old book by increasing quantity
                            Book book = StudentModuleActivity.callGetBookById(student.getBookId());
                            book.setQuantity(book.getQuantity()+1);
                            StudentModuleActivity.callBookUpdate(book);

                            //borrowing new book
                            book = books.get(position);
                            book.setQuantity(book.getQuantity()-1);
                            StudentModuleActivity.callBookUpdate(book);

                            student.setBookId(books.get(position).getBookId());
                            StudentModuleActivity.callStudentUpdate(student);

                            Toast.makeText(activity, "Book with ID: " + student.getBookId() + " borrowed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            //case where librarian is the one signed in
            else {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    position = getAdapterPosition();
                    if (position != -1) {
                        Intent intent = new Intent(activity, LibrarianEditExistingBook.class);
                        intent.putExtra(LibrarianEditExistingBook.EXTRA_ID, books.get(position).getBookId());
                        intent.putExtra(LibrarianEditExistingBook.EXTRA_BOOK_NAME, books.get(position).getBookName());
                        intent.putExtra(LibrarianEditExistingBook.EXTRA_AUTHOR, books.get(position).getAuthorName());
                        intent.putExtra(LibrarianEditExistingBook.EXTRA_DESCRIPTION, books.get(position).getBookDescription());
                        intent.putExtra(LibrarianEditExistingBook.EXTRA_CATEGORY, books.get(position).getCategory());
                        intent.putExtra(LibrarianEditExistingBook.EXTRA_QUANTITY, books.get(position).getQuantity());
                        activity.startActivity(intent);
                    }
                    }
                });
            }
        }
    }

}
