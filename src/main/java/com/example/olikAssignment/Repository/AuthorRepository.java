package com.example.olikAssignment.Repository;

import com.example.olikAssignment.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
