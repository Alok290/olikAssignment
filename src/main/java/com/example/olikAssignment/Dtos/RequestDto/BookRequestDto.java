package com.example.olikAssignment.Dtos.RequestDto;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private Integer authorId;
    private String title;

    private String isbn;

    private String publicationYear;
}
