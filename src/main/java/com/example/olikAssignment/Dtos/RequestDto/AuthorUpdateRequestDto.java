package com.example.olikAssignment.Dtos.RequestDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequestDto {


    private Integer id;
    private String name;

    private String biography;
}
