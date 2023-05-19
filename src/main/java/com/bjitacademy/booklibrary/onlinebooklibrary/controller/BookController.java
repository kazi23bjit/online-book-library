package com.bjitacademy.booklibrary.onlinebooklibrary.controller;

import com.bjitacademy.booklibrary.onlinebooklibrary.model.BookModel;
import com.bjitacademy.booklibrary.onlinebooklibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    @GetMapping("/all")
    public ResponseEntity<List<BookModel>> getAll(){
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> addBook(@RequestBody BookModel bookModel){
        bookService.addBook(bookModel);
        return new ResponseEntity<>("Book created", HttpStatus.CREATED);
    }
    @GetMapping("/id/{bookId}")
    public BookModel getById(@PathVariable Integer bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/author-title/{author}/{title}")
    public BookModel getBookByAuthorAndTitle(@PathVariable String author, @PathVariable String title){
        return bookService.getBookByAuthorAndTitle(author, title);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookModel>> getBooksByAuthor(@PathVariable String author){
        return new ResponseEntity<>(bookService.getBooksByAuthorName(author), HttpStatus.OK);
    }
    @PutMapping("/update/{bookId}")
    public BookModel updateBook(@RequestBody BookModel bookModel,@PathVariable Integer bookId){
        return bookService.updateBook(bookModel, bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public List<BookModel> deleteBook(@PathVariable Integer bookId){
        return bookService.deleteBook(bookId);
    }

}
