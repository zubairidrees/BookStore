package com.bookstore.resource;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Book.
 */
@RestController
public class BookResource {

    private BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * POST /books : Create a new book.
     *
     * @param book the book to create
     * @return the ResponseEntity with status 201 (Created) and with body the new book,
     * or with status 400 (Bad Request) if the book has already an ID
     */
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (book.getBookId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book resultBook = bookService.saveBook(book);
        return new ResponseEntity<>(resultBook, HttpStatus.CREATED);
    }

    /**
     * PUT /books : Updates an existing book.
     *
     * @param book the book to update
     * @return the ResponseEntity with state 200 (OK) and with body the updated book,
     * or with status 400 (Bad Request) if the book is not valid
     */
    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if (book.getBookId() == null) {
            return createBook(book);
        }
        Book resultBook = bookService.updateBook(book);
        return new ResponseEntity<>(resultBook, HttpStatus.OK);
    }

    /**
     * GET /books : get all the books.
     *
     * @return the ResponseEntity with status 200 (OK) and the list ob books in body,
     * or with status 204 (NO CONTENT) if there no books in repository
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.findAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * GET /books/:id : get the "id" book.
     *
     * @param id the id of the book to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the book,
     * or with status 404 (NOT FOUND)
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Optional<Book> book = bookService.findBookById(id);
        if (book.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

    /**
     * DELETE /books/:id : delete the "id" book.
     *
     * @param id the id of the book to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Optional<Book> book = bookService.findBookById(id);
        if (book.isEmpty() ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookService.deleteBook(book.get());
        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }
}