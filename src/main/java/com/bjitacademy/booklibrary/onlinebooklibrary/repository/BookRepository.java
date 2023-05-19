package com.bjitacademy.booklibrary.onlinebooklibrary.repository;

import com.bjitacademy.booklibrary.onlinebooklibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    public Optional<Book> findByAuthorAndTitle(String author, String title);
    public List<Book> findByAuthor(String author);
}
