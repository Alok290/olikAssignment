package com.example.olikAssignment.Services;

import com.example.olikAssignment.Dtos.RequestDto.RentalRequestDto;
import com.example.olikAssignment.Models.Book;
import com.example.olikAssignment.Models.Rental;
import com.example.olikAssignment.Repository.BookRepository;
import com.example.olikAssignment.Repository.RentalRepository;
import com.example.olikAssignment.Service.RentalServiceImpl;
import com.example.olikAssignment.Transformation.RentalTransformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RentalServiceImplTest {

    @InjectMocks
    private RentalServiceImpl rentalService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRentBook() throws Exception {
        RentalRequestDto requestDto = new RentalRequestDto();
        // set properties for requestDto
        Rental rental = RentalTransformation.convertEntity(requestDto);
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(new Book()));
        when(bookRepository.save(any(Book.class))).thenReturn(new Book());

        String result = rentalService.rentBook(requestDto);

        assertEquals("the book name is " + rental.getBook().getTitle() + " is rented by " + rental.getRenterName(), result);
        verify(bookRepository, times(1)).findById(requestDto.getBookId());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testReturnBook() throws Exception {
        Integer bookId = 1;
        Integer rentId = 1;
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(new Book()));
        when(rentalRepository.findById(rentId)).thenReturn(Optional.of(new Rental()));
        when(rentalRepository.save(any(Rental.class))).thenReturn(new Rental());
        when(bookRepository.save(any(Book.class))).thenReturn(new Book());

        String result = rentalService.returnBook(bookId, rentId);

        assertEquals("Book returned successfully. It was rented for 0 days.", result);
        verify(bookRepository, times(1)).findById(bookId);
        verify(rentalRepository, times(1)).findById(rentId);
        verify(rentalRepository, times(1)).save(any(Rental.class));
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}
