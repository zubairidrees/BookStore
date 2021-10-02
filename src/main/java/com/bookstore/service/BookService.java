package com.bookstore.service;

import com.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAllBooks();

    Optional<Book> findBookById(Long bookId);

    Book updateBook(Book book);

    void deleteBook(Book book);

    Book saveBook(Book book);
}
