package com.example.olikAssignment.Service;

import com.example.olikAssignment.Dtos.RequestDto.RentalRequestDto;
import com.example.olikAssignment.Exceptions.bookNotPresent;
import com.example.olikAssignment.Models.Book;
import com.example.olikAssignment.Models.Rental;
import com.example.olikAssignment.Repository.BookRepository;
import com.example.olikAssignment.Repository.RentalRepository;
import com.example.olikAssignment.Service.RentalService;
import com.example.olikAssignment.Transformation.RentalTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    // Autowiring the repositories
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BookRepository bookRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    // Method to rent a book
    @Override
    public String rentBook(RentalRequestDto requestDto) throws Exception {

        // Transforming the request DTO to a Rental entity
        Rental rental = RentalTransformation.convertEntity(requestDto);

        // Checking if the book is present in the repository
        Optional<Book>optionalBook = bookRepository.findById(requestDto.getBookId());

        // If the book is not present, throw an exception
        if(optionalBook.isEmpty()){
            throw new bookNotPresent("This book is not present");
        }

        // Get the book from the optional
        Book book = optionalBook.get();

        // If the book is not available, throw an exception
        if(book.getIsAvailable()==false){
            throw new bookNotPresent("this book is already rented");
        }

        book.setIsAvailable(false);

        // Set the rental to the book and vice versa
        book.setRental(rental);
        rental.setBook(book);

        // Save the book to the repository
        bookRepository.save(book);



        // Return a success message
        return "the book name is "+book.getTitle()+" is rented by "+rental.getRenterName();
    }

    // Method to return a book
    @Override
    public String returnBook(Integer bookId, Integer rentId) throws Exception {

        // Checking if the book is present in the repository
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new bookNotPresent("this book id is not valid");
        }

        // Get the book from the optional
        Book book = optionalBook.get();

        // Checking if the rental is present in the repository
        Optional<Rental> optionalRental = rentalRepository.findById(rentId);
        if(optionalBook.isEmpty()){
            throw new RuntimeException("not valid ");
        }

        // Get the rental from the optional
        Rental rental = optionalRental.get();

        // Calculate the number of days between renting and returning
        long diffInMillies = Math.abs(rental.getReturnDate().getTime() - rental.getRentalDate().getTime());
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);

        book.setIsAvailable(true);

        // Save the updated entities
        rentalRepository.save(rental);
        bookRepository.save(book);

        // If the book is overdue, throw an exception
        if(diffInDays>14){
            throw new RuntimeException("your returning date is overdue");
        }

        // Return a success message
        return "Book returned successfully. It was rented for " + diffInDays + " days.";
    }
}
