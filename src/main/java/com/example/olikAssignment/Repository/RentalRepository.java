package com.example.olikAssignment.Repository;

import com.example.olikAssignment.Models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
}
