package com.example.olikAssignment.Service.Impl;

import com.example.olikAssignment.Dtos.RequestDto.BookRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.BookResponseDto;
import com.example.olikAssignment.Exceptions.bookAlreadyPresent;
import com.example.olikAssignment.Exceptions.bookNotPresent;
import com.example.olikAssignment.Models.Author;
import com.example.olikAssignment.Models.Book;
import com.example.olikAssignment.Repository.AuthorRepository;
import com.example.olikAssignment.Repository.BookRepository;
import com.example.olikAssignment.Service.BookService;
import com.example.olikAssignment.Transformation.BookTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public String save(BookRequestDto bookRequestDto) throws Exception {
        // Convert the request DTO to a Book entity
        Book book = BookTransformation.convertEntity(bookRequestDto);

        // Check if the book with the same ISBN already exists
        Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        if (optionalBook.isPresent()) {
            throw new bookAlreadyPresent("This book is already present");
        }

        // Retrieve the author based on the author ID from the request DTO
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        book.setAuthor(author);
        author.getBookList().add(book);

        // Save the author (and the associated book) to the database
        authorRepository.save(author);

        return "Book successfully saved";
    }

    @Override
    public BookResponseDto getBookByTitle(String title) throws Exception {
        // Find the book by its title
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        if (optionalBook.isEmpty()) {
            throw new bookNotPresent("This book is not present");
        }

        // Convert the Book entity to a response DTO
        Book book = optionalBook.get();
        BookResponseDto responseDto = BookTransformation.convertIntoResponse(book);

        return responseDto;
    }

    @Override
    public List<Book> getAll() throws Exception {
        // Retrieve all books from the repository
        return bookRepository.findAll();
    }

    @Override
    public String update(BookRequestDto requestDto) throws Exception {
        // Find the book by its ISBN
        Optional<Book> optionalBook = bookRepository.findByIsbn(requestDto.getIsbn());
        if (optionalBook.isEmpty()) {
            throw new bookNotPresent("This book is not present");
        }

        // Update the book title if provided in the request DTO
        Book book = optionalBook.get();
        if (requestDto.getTitle() != null) {
            book.setTitle(requestDto.getTitle());
        }

        return "Congratulations! You've updated the book.";
    }

    @Override
    public String delete(Integer id) throws Exception {
        // Find the book by its ID
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new bookAlreadyPresent("This book is not present");
        }

        // Delete the book from the repository
        Book book = optionalBook.get();
        bookRepository.delete(book);

        return "Book is deleted successfully";
    }
}