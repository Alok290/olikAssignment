package com.example.olikAssignment.Controller;


import com.example.olikAssignment.Dtos.RequestDto.BookRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.BookResponseDto;
import com.example.olikAssignment.Models.Book;
import com.example.olikAssignment.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    // Autowiring the BookService
    @Autowired
    private BookService bookService;

    // Endpoint to save a book
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        try {
            // Call the save method from the service and get the response
            String response = bookService.save(bookRequestDto);
            // Return the response with HTTP status 201 (Created)
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 409 (Conflict)
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // Endpoint to get a book by title
    @GetMapping("/get/{title}")
    public ResponseEntity getBook(@PathVariable String title) throws Exception {
        try {
            // Call the getBookByTitle method from the service and get the response
            BookResponseDto responseDto = bookService.getBookByTitle(title);
            // Return the response with HTTP status 200 (OK)
            return new ResponseEntity(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to get all books
    @GetMapping("/getAll")
    public ResponseEntity getAll() throws Exception {
        try {
            // Call the getAll method from the service and get the response
            List<Book> bookList = bookService.getAll();
            // Return the response with HTTP status 200 (OK)
            return new ResponseEntity(bookList, HttpStatus.OK);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to update a book
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody BookRequestDto requestDto) throws Exception {
        try {
            // Call the update method from the service and get the response
            String response = bookService.update(requestDto);
            // Return the response with HTTP status 200 (OK)
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to delete a book by id
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        try {
            // Call the delete method from the service and get the response
            String response = bookService.delete(id);
            // Return the response with HTTP status 202 (Accepted)
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}