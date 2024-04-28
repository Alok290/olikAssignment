package com.example.olikAssignment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;



@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Rental {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer rentId;

     private String  renterName;
     private Date rentalDate;
     private Date returnDate;


     @OneToOne
     @JoinColumn
     @JsonIgnore
     private Book book;
}
