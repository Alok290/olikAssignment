package com.example.olikAssignment.Dtos.RequestDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequestDto {

    private String  renterName;
    private Date rentalDate;
    private Date returnDate;
    private Integer bookId;
}
