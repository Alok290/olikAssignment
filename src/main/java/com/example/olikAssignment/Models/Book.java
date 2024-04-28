package com.example.olikAssignment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String title;

    private String isbn;

    private String publicationYear;

    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Author author;




    @OneToOne(mappedBy = "book",cascade = CascadeType.ALL)
    private Rental rental;



}
