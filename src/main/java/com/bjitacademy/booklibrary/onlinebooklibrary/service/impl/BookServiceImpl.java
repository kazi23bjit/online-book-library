package com.bjitacademy.booklibrary.onlinebooklibrary.service.impl;

import com.bjitacademy.booklibrary.onlinebooklibrary.entity.Book;
import com.bjitacademy.booklibrary.onlinebooklibrary.exception.BookNotFoundException;
import com.bjitacademy.booklibrary.onlinebooklibrary.model.BookModel;
import com.bjitacademy.booklibrary.onlinebooklibrary.repository.BookRepository;
import com.bjitacademy.booklibrary.onlinebooklibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Override
    public List<BookModel> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookModel> bookModels = new ArrayList<>();
        for(Book book:books){
            BookModel bookModel = BookModel.builder()
                                .title(book.getTitle())
                                .author(book.getAuthor())
                                .genre(book.getGenre())
                                .price(book.getPrice())
                                .build();
            bookModels.add(bookModel);
        }
        return bookModels;
    }

    @Override
    public void addBook(BookModel bookModel) {
        Book book = Book.builder()
                .title(bookModel.getTitle())
                .author(bookModel.getAuthor())
                .genre(bookModel.getGenre())
                .price(bookModel.getPrice())
                .build();
        bookRepository.save(book);
    }

    @Override
    public BookModel getBookById(Integer bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            Book requiredBook = book.get();
            BookModel bookModel = BookModel.builder()
                    .title(requiredBook.getTitle())
                    .author(requiredBook.getAuthor())
                    .genre(requiredBook.getGenre())
                    .price(requiredBook.getPrice()).build();
            return bookModel;
        }
        else{
            throw new BookNotFoundException("Book not found");
        }
    }

    @Override
    public BookModel getBookByAuthorAndTitle(String author, String title){
        Optional<Book> book = bookRepository.findByAuthorAndTitle(author, title);
        if(book.isPresent()){
            Book requiredBook = book.get();
            BookModel bookModel = BookModel.builder()
                    .title(requiredBook.getTitle())
                    .author(requiredBook.getAuthor())
                    .genre(requiredBook.getGenre())
                    .price(requiredBook.getPrice()).build();
            return bookModel;
        }else{
            throw new BookNotFoundException("Required book is not found");
        }
    }

    @Override
    public List<BookModel> getBooksByAuthorName(String author){
        List<Book> books = bookRepository.findByAuthor(author);
        if(books.isEmpty()){
            throw new BookNotFoundException("NO SUCH BOOK EXISTS");
        }
        List<BookModel> bookModels = new ArrayList<>();
        for(Book book:books){
            BookModel bookModel = BookModel.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .genre(book.getGenre())
                    .price(book.getPrice())
                    .build();
            bookModels.add(bookModel);
        }
        return bookModels;
    }

    @Override
    public BookModel updateBook(BookModel bookModel, Integer bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            Book requiredBook = book.get();

//            requiredBook.setTitle(bookModel.getTitle());
//            requiredBook.setAuthor(bookModel.getAuthor());
            requiredBook.setGenre(bookModel.getGenre());
            requiredBook.setPrice(bookModel.getPrice());

            bookRepository.save(requiredBook);

            BookModel requiredUpdatedBook = BookModel.builder()
                    .title(requiredBook.getTitle())
                    .author(requiredBook.getAuthor())
                    .genre(requiredBook.getGenre())
                    .price(requiredBook.getPrice())
                    .build();
            return requiredUpdatedBook;
        }
        else{
            throw new BookNotFoundException("Book is not found to be updated");
        }
    }

    @Override
    public List<BookModel> deleteBook(Integer bookId){
        bookRepository.deleteById(bookId);
        List<Book> books = bookRepository.findAll();
        List<BookModel> bookModels = new ArrayList<>();
        for(Book book: books){
            BookModel bookModel = BookModel.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .genre(book.getGenre())
                    .price(book.getPrice()).build();
            bookModels.add(bookModel);

        }
        return bookModels;

    }
}
