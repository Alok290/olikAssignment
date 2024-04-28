package com.example.olikAssignment.Service;

import com.example.olikAssignment.Dtos.RequestDto.RentalRequestDto;

public interface RentalService {
    String rentBook(RentalRequestDto requestDto)throws Exception;

    String returnBook(Integer bookId, Integer rentId) throws Exception;
}
