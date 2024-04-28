package com.example.olikAssignment.Transformation;

import com.example.olikAssignment.Dtos.RequestDto.RentalRequestDto;
import com.example.olikAssignment.Models.Rental;

public class RentalTransformation {

    public static Rental convertEntity(RentalRequestDto requestDto){

        Rental rental = Rental.builder()
                .rentalDate(requestDto.getRentalDate())
                .renterName(requestDto.getRenterName())
                .returnDate(requestDto.getReturnDate())
                .build();
        return rental;
    }
}
