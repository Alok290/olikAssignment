package com.example.olikAssignment.Services;

import com.example.olikAssignment.Dtos.RequestDto.BookRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.BookResponseDto;
import com.example.olikAssignment.Models.Author;
import com.example.olikAssignment.Models.Book;
import com.example.olikAssignment.Repository.AuthorRepository;
import com.example.olikAssignment.Repository.BookRepository;
import com.example.olikAssignment.Service.Impl.BookServiceImpl;
import com.example.olikAssignment.Transformation.BookTransformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        BookRequestDto bookRequestDto = new BookRequestDto();
        // set properties for bookRequestDto
        Book book = BookTransformation.convertEntity(bookRequestDto);
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.empty());
        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(new Author()));
        when(authorRepository.save(any(Author.class))).thenReturn(new Author());

        String result = bookService.save(bookRequestDto);

        assertEquals("book successfully saved", result);
        verify(bookRepository, times(1)).findByIsbn(book.getIsbn());
        verify(authorRepository, times(1)).findById(bookRequestDto.getAuthorId());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void testGetBookByTitle() throws Exception {
        String title = "Book Title";
        Book book = new Book();
        // set properties for book
        when(bookRepository.findByTitle(title)).thenReturn(Optional.of(book));

        BookResponseDto result = bookService.getBookByTitle(title);

        assertEquals(BookTransformation.convertIntoResponse(book), result);
        verify(bookRepository, times(1)).findByTitle(title);
    }

    @Test
    public void testGetAll() throws Exception {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), bookService.getAll());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testUpdate() throws Exception {
        BookRequestDto bookRequestDto = new BookRequestDto();
        // set properties for bookRequestDto
        Book book = BookTransformation.convertEntity(bookRequestDto);
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.empty());

        String result = bookService.update(bookRequestDto);

        assertEquals("", result);
        verify(bookRepository, times(1)).findByIsbn(book.getIsbn());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;
        when(bookRepository.findById(id)).thenReturn(Optional.of(new Book()));

        String result = bookService.delete(id);

        assertEquals("book is deleted successfully", result);
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).delete(any(Book.class));
    }
}
