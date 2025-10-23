package com.example.book_api;

import com.example.book_api.model.Book;
import com.example.book_api.repository.BookRepository;
import com.example.book_api.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        Book book = new Book();
        book.setId("1");
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setPrice(39.99);


        when(repository.findAll()).thenReturn(List.of(book));

        List<Book> books = service.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("Clean Code", books.get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setId("1");
        book.setTitle("Effective Java");

        when(repository.findById("1")).thenReturn(Optional.of(book));

        Optional<Book> found = service.getBookById("1");

        assertTrue(found.isPresent());
        assertEquals("Effective Java", found.get().getTitle());
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setTitle("Spring Boot in Action");

        when(repository.save(book)).thenReturn(book);

        Book saved = service.addBook(book);

        assertEquals("Spring Boot in Action", saved.getTitle());
    }

    @Test
    void testDeleteBook() {
        String id = "1";
        doNothing().when(repository).deleteById(id);

        service.deleteBook(id);

        verify(repository, times(1)).deleteById(id);
    }
}
