package com.example.LendingProgram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LendingProgram.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {}
