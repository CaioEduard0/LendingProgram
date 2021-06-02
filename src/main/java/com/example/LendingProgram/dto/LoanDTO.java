package com.example.LendingProgram.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.LendingProgram.entities.Loan;

public class LoanDTO {
	
	@NotBlank(message = "{item.not.blank}")
	@Size(min = 2, max = 30, message = "{item.size}")
	private String item;
	
	@NotBlank(message = "{name.not.blank}")
	@Size(min = 2, max = 50, message = "{name.size}")
	private String name;
	
	@NotBlank(message = "{phone.not.blank}")
	@Size(min = 8, max = 15, message = "{phone.size}")
	private String phone;
	
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "{loanDate.not.null}")
	@PastOrPresent(message = "{loanDate.present}")
	private LocalDate loanDate;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}
	
	public Loan dtoToItem(LoanDTO loanDto) {
		Loan loan = new Loan();
		loan.setItem(loanDto.getItem());
		loan.setName(loanDto.getName());
		loan.setPhone(loanDto.getPhone());
		loan.setLoanDate(loanDto.getLoanDate());
		return loan;
	}
}
