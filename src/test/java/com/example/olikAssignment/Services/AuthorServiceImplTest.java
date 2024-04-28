package com.example.olikAssignment.Services;

import com.example.olikAssignment.Dtos.RequestDto.AuthorRequestDto;
import com.example.olikAssignment.Dtos.RequestDto.AuthorUpdateRequestDto;
import com.example.olikAssignment.Dtos.ResponseDto.AuthorResponseDto;
import com.example.olikAssignment.Models.Author;
import com.example.olikAssignment.Repository.AuthorRepository;
import com.example.olikAssignment.Service.Impl.AuthorServiceImpl;
import com.example.olikAssignment.Transformation.AuthorTransformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        AuthorRequestDto authorRequestDto = new AuthorRequestDto();
        // set properties for authorRequestDto
        Author author = AuthorTransformation.convertEntity(authorRequestDto);
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        String result = authorService.save(authorRequestDto);

        assertEquals("congrats author saved into db", result);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void testGetAuthor() throws Exception {
        Integer id = 1;
        Author author = new Author();
        // set properties for author
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        AuthorResponseDto result = authorService.getAuthor(id);

        assertEquals(AuthorTransformation.convertIntoResponse(author), result);
        verify(authorRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdate() throws Exception {
        AuthorUpdateRequestDto requestDto = new AuthorUpdateRequestDto();
        // set properties for requestDto
        Author author = new Author();
        // set properties for author
        when(authorRepository.findById(requestDto.getId())).thenReturn(Optional.of(author));

        String result = authorService.update(requestDto);

        assertEquals("your author is successfully updated", result);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;
        Author author = new Author();
        // set properties for author
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        String result = authorService.delete(id);

        assertEquals("successfully deleted", result);
        verify(authorRepository, times(1)).delete(author);
    }
}
