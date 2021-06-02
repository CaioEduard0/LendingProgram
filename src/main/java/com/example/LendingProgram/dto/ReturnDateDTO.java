package com.example.LendingProgram.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ReturnDateDTO {
	
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "{returnDate.not.null}")
	@FutureOrPresent(message = "{returnDate.future}")
	private LocalDate returnDate;

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
}
