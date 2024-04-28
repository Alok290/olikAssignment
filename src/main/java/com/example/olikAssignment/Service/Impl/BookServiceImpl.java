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

        Book book = BookTransformation.convertEntity(bookRequestDto);
        Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        if(optionalBook.isPresent()){
            throw new bookAlreadyPresent("this book is already present");
        }

        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        book.setAuthor(author);
        author.getBookList().add(book);

        authorRepository.save(author);

        return "book successfully saved";
    }

    @Override
    public BookResponseDto getBookByTitle(String title) throws Exception {

        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        if(optionalBook.isEmpty()){
            throw new bookNotPresent("this book is not present");
        }

        Book book = optionalBook.get();
        BookResponseDto responseDto = BookTransformation.convertIntoResponse(book);

        return responseDto;
    }

    @Override
    public List<Book> getAll() throws Exception {
        return bookRepository.findAll();
    }

    @Override
    public String update(BookRequestDto requestDto) throws Exception {
        Book book = BookTransformation.convertEntity(requestDto);
        Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        if(optionalBook.isPresent()){
            throw new bookAlreadyPresent("this book is already present");
        }
        return "";
    }

    @Override
    public String delete(Integer id) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            throw new bookAlreadyPresent("this book is not present");
        }

        Book book = optionalBook.get();

        bookRepository.delete(book);

        return "book is deleted successfully";
    }
}
