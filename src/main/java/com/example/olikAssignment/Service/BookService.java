package com.example.olikAssignment.Service;

import com.example.olikAssignment.Dtos.RequestDto.BookRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.BookResponseDto;
import com.example.olikAssignment.Models.Book;

import java.util.List;

public interface BookService {
    String save(BookRequestDto bookRequestDto) throws Exception;

    BookResponseDto getBookByTitle(String title) throws Exception;

    List<Book> getAll() throws Exception;

    String update(BookRequestDto requestDto)throws Exception;

    String delete(Integer id) throws Exception;
}
