package com.example.olikAssignment.Controller;


import com.example.olikAssignment.Dtos.RequestDto.RentalRequestDto;
import com.example.olikAssignment.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")
public class RentalController {


    // Autowiring the RentalService
    @Autowired
    private RentalService rentalService;

    // Endpoint to rent a book
    @PostMapping("/rent")
    public ResponseEntity rentBook(@RequestBody RentalRequestDto requestDto)throws Exception{
        try{
            // Call the rentBook method from the service and get the response
            String response = rentalService.rentBook(requestDto);
            // Return the response with HTTP status 202 (Accepted)
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to return a book
    @PostMapping("/return")
    public ResponseEntity returnBook(@RequestParam Integer bookId, @RequestParam Integer rentId)throws Exception{
        try{
            // Call the returnBook method from the service and get the response
            String response = rentalService.returnBook(bookId,rentId);
            // Return the response with HTTP status 200 (OK)
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            // If there's an exception, return the exception message with HTTP status 400 (Bad Request)
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
