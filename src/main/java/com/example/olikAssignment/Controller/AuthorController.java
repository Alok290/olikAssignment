package com.example.olikAssignment.Controller;


import com.example.olikAssignment.Dtos.RequestDto.AuthorRequestDto;
import com.example.olikAssignment.Dtos.RequestDto.AuthorUpdateRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.AuthorResponseDto;
import com.example.olikAssignment.Models.Author;
import com.example.olikAssignment.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    // Autowiring the AuthorService
    @Autowired
    private AuthorService authorService;

    // Endpoint to save an author
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody AuthorRequestDto authorRequestDto) throws Exception {
        try {
            // Call the save method from the service and get the response
            String response = authorService.save(authorRequestDto);
            // Return the response with HTTP status 201 (Created)
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 409 (Conflict)
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // Endpoint to get an author by id
    @GetMapping("/get{id}")
    public ResponseEntity getAuthor(@PathVariable Integer id) throws Exception {
        try {
            // Call the getAuthor method from the service and get the response
            AuthorResponseDto responseDto = authorService.getAuthor(id);
            // Return the response with HTTP status 202 (Accepted)
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to update an author
    @PutMapping("/update")
    public ResponseEntity update(AuthorUpdateRequestDto requestDto) throws Exception {
        try {
            // Call the update method from the service and get the response
            String response = authorService.update(requestDto);
            // Return the response with HTTP status 200 (OK)
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to delete an author by id
    @DeleteMapping("/delete{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        try {
            // Call the delete method from the service and get the response
            String response = authorService.delete(id);
            // Return the response with HTTP status 200 (OK)
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
