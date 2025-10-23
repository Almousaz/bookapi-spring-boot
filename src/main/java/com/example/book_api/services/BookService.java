package com.example.book_api.services;


import com.example.book_api.model.Book;
import com.example.book_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return repository.findById(id);
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(String id, Book book) {
        book.setId(id);
        return repository.save(book);
    }

    public void deleteBook(String id) {
        repository.deleteById(id);
    }

}
