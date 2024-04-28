package com.example.olikAssignment.Service.Impl;

import com.example.olikAssignment.Dtos.RequestDto.AuthorRequestDto;
import com.example.olikAssignment.Dtos.RequestDto.AuthorUpdateRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.AuthorResponseDto;
import com.example.olikAssignment.Exceptions.AuthorNotPresent;
import com.example.olikAssignment.Models.Author;
import com.example.olikAssignment.Repository.AuthorRepository;
import com.example.olikAssignment.Service.AuthorService;
import com.example.olikAssignment.Transformation.AuthorTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public String save(AuthorRequestDto authorRequestDto) {

        Author author = AuthorTransformation.convertEntity(authorRequestDto);
        authorRepository.save(author);
        return "congrats author saved into db";
    }

    @Override
    public AuthorResponseDto getAuthor(Integer id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if(optionalAuthor.isEmpty()){
            throw new AuthorNotPresent("this author is not present");
        }

        Author author = optionalAuthor.get();

        AuthorResponseDto response = AuthorTransformation.convertIntoResponse(author);

        return response;
    }

    @Override
    public String update(AuthorUpdateRequestDto requestDto) throws Exception {

        Optional<Author> optionalAuthor = authorRepository.findById(requestDto.getId());

        if(optionalAuthor.isEmpty()){
            throw new AuthorNotPresent("this author is not valid");
        }

        Author author = optionalAuthor.get();

        if(requestDto.getBiography()!=null){
            author.setBiography(requestDto.getBiography());
        }

        if(requestDto.getName()!=null){
            author.setName(requestDto.getName());
        }

        authorRepository.save(author);
        return "your author is successfully updated";
    }

    @Override
    public String delete(Integer id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if(optionalAuthor.isEmpty()){
            throw new AuthorNotPresent("this author is not valid");
        }

        Author author = optionalAuthor.get();

        authorRepository.delete(author);
        return "successfully deleted";
    }
}
