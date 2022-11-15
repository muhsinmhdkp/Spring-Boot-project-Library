package com.student.ust.controller;

import com.student.ust.entity.Book;
import com.student.ust.entity.Student;
import com.student.ust.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * The type Book controller.
 */
@RestController
@Slf4j
public class BookController{
    /**
     * The Book service.
     */
    @Autowired
    BookService bookService;

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/Book/{id}")
    public ResponseEntity<Book> get(@PathVariable Integer id){
        try{
            log.debug("inside debug The Book Id passed as Pathvariable ="+id);
            Book book = bookService.getBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get response entity.
     *
     * @return the response entity
     */
    @GetMapping("/Book")
    public ResponseEntity<List<Book>>get(){
        try{
            List<Book> allBook = bookService.getAllBook();
            return new ResponseEntity<List<Book>>(allBook, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add.
     *
     * @param book the book
     */
    @PostMapping("/Book")
    public void add (@RequestBody Book book){
        bookService.saveBook(book);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping("/Book/{id}")
    public void delete(@PathVariable Integer id ){
        bookService.deleteBook(id);
    }

    /**
     * Put response entity.
     *
     * @param book the book
     * @return the response entity
     */
    @PutMapping("/Book")
    public ResponseEntity<Book> put(@RequestBody Book book){
        try{
            Book updateBook = bookService.updateBook(book);
            return new ResponseEntity<Book>(updateBook, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

}

