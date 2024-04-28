package com.example.olikAssignment.Transformation;

import com.example.olikAssignment.Dtos.RequestDto.BookRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.BookResponseDto;
import com.example.olikAssignment.Models.Book;

public class BookTransformation {

    public static Book convertEntity(BookRequestDto bookRequestDto){
        Book book = Book.builder()
                .isbn(bookRequestDto.getIsbn())
                .title(bookRequestDto.getTitle())
                .publicationYear(bookRequestDto.getPublicationYear())
                .build();

        return book;
    }

    public static BookResponseDto convertIntoResponse(Book book) {

        BookResponseDto responseDto = BookResponseDto.builder()
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .title(book.getTitle())
                .build();

        return responseDto;


    }
}
