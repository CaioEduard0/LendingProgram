package com.example.LendingProgram.controllers;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.LendingProgram.dto.LoanDTO;
import com.example.LendingProgram.dto.ReturnDateDTO;
import com.example.LendingProgram.entities.User;
import com.example.LendingProgram.services.LoanService;
import com.example.LendingProgram.services.UserService;

@Controller
public class LoanController {
	
	private UserService userService;	
	private LoanService loanService;
	
	public LoanController(UserService userService, LoanService loanService) {
		this.userService = userService;
		this.loanService = loanService;
	}
	
	@GetMapping("/lend")
	public String lendPage() {
		return "lend";
	}
	
	@PostMapping("/lend")
	public ModelAndView newLoan(@AuthenticationPrincipal UserDetails userDetails , @Valid LoanDTO loan, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("lend");
			mv.addObject("errors", bindingResult.getAllErrors());
			return mv;
		}
		loanService.insertLoan(userService.findUserByEmail(userDetails.getUsername()), loan.dtoToItem(loan));
		return findingLoans(userDetails);
	}
	
	@PostMapping("/return/{id}")
	public ModelAndView newReturn(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @Valid ReturnDateDTO returnDate, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = findingLoans(userDetails);
			mv.addObject("errors", bindingResult.getAllErrors());
			return mv;
		}
		loanService.updateLoan(id, returnDate.getReturnDate());
		return findingLoans(userDetails);
	}
	
	public ModelAndView findingLoans(UserDetails userDetails) {
		ModelAndView mv = new ModelAndView("index");
		User user = userService.findUserByEmail(userDetails.getUsername());
		mv.addObject("user", user);
		mv.addObject("loans", user.getLoans());
		return mv;
	}
}
