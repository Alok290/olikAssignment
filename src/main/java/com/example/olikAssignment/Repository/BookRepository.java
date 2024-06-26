package com.example.olikAssignment.Repository;

import com.example.olikAssignment.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);
}
