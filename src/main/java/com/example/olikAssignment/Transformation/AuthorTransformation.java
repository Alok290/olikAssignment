package com.example.olikAssignment.Transformation;

import com.example.olikAssignment.Dtos.RequestDto.AuthorRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.AuthorResponseDto;
import com.example.olikAssignment.Models.Author;

public class AuthorTransformation {


    public static Author convertEntity(AuthorRequestDto authorRequestDto){

        Author author = Author.builder()
                .name(authorRequestDto.getName())
                .biography(authorRequestDto.getBiography())
                .build();
        return author;
    }

    public static AuthorResponseDto convertIntoResponse(Author author) {
        AuthorResponseDto responseDto = AuthorResponseDto.builder()
                .biography(author.getBiography())
                .name(author.getName())
                .build();
        return responseDto;
    }

}

