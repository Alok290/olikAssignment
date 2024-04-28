package com.example.olikAssignment.Service;

import com.example.olikAssignment.Dtos.RequestDto.AuthorRequestDto;
import com.example.olikAssignment.Dtos.RequestDto.AuthorUpdateRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.AuthorResponseDto;

public interface AuthorService {
    String save(AuthorRequestDto authorRequestDto) throws Exception;

    AuthorResponseDto getAuthor(Integer id) throws Exception;

    String update(AuthorUpdateRequestDto requestDto) throws Exception;

    String delete(Integer id) throws Exception;
}
