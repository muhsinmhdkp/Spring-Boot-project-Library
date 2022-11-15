package com.student.ust.service;

import com.student.ust.entity.Book;
import com.student.ust.entity.Student;
import com.student.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Book service.
 */
@Service
public class BookService {

    /**
     * The Book repository.
     */
    @Autowired
    BookRepository bookRepository;

    /**
     * Get book by id book.
     *
     * @param id the id
     * @return the book
     */
    public Book getBookById(Integer id){
        return bookRepository.findById(id).orElse(null);
    }


    /**
     * Save book.
     *
     * @param book the book
     */
    public void saveBook(Book book) {
        bookRepository.save(book);
    }


    /**
     * Gets all book.
     *
     * @return the all book
     */
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    /**
     * Delete book.
     *
     * @param id the id
     */
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    /**
     * Update book book.
     *
     * @param book the book
     * @return the book
     */
    public Book updateBook(Book book) {
        Book updateBook = bookRepository.findById(book.getBookId()).orElseThrow(() -> new NoSuchElementException());
        updateBook.setIsbn(book.getIsbn());
        updateBook.setBookName(book.getBookName());
        bookRepository.save(updateBook);
        return updateBook;
    }
}
