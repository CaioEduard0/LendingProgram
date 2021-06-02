package com.example.LendingProgram.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.LendingProgram.entities.Loan;
import com.example.LendingProgram.entities.User;
import com.example.LendingProgram.repositories.LoanRepository;

@Service
public class LoanService {
	
	private LoanRepository loanRepository;
	
	public LoanService(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}
	
	public Loan findLoanById(Long id) {
		Optional<Loan> item = loanRepository.findById(id);
		return item.get();
	}
	
	public void insertLoan(User user, Loan loan) {
		loan.setUser(user);
		user.getLoans().add(loan);
		loanRepository.save(loan);
	}
	
	public void updateLoan(Long id, LocalDate returnDate) {
		Loan loan = findLoanById(id);
		loan.setReturnDate(returnDate);
		loanRepository.save(loan);
	}
}
