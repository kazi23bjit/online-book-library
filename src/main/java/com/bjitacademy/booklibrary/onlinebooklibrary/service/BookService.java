package com.bjitacademy.booklibrary.onlinebooklibrary.service;

import com.bjitacademy.booklibrary.onlinebooklibrary.entity.Book;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.BookModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookModel> getAll();
    void addBook(BookModel bookModel);
    BookModel getBookById(Integer bookId);

    BookModel getBookByAuthorAndTitle(String author, String title);

    List<BookModel> getBooksByAuthorName(String author);

    BookModel updateBook(BookModel bookModel, Integer bookId);

    List<BookModel> deleteBook(Integer bookId);
}
