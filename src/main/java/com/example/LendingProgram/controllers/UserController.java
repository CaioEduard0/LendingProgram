package com.example.LendingProgram.controllers;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.LendingProgram.dto.UserDTO;
import com.example.LendingProgram.entities.User;
import com.example.LendingProgram.services.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String loginPage() {	
		return "login";
	}
	
	@GetMapping("/sign-up")
	public String signUpPage() {
		return "signup";
	}
	
	@PostMapping("/sign-up")
	public ModelAndView insertUser(@Valid UserDTO user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("signup");
			mv.addObject("errors", bindingResult.getAllErrors());
			return mv;
		}
		userService.insertUser(user.dtoToUser(user));
		return new ModelAndView("login");
	}
	
	@GetMapping("/")
	public ModelAndView indexPage(@AuthenticationPrincipal UserDetails userDetails) {
		ModelAndView mv = new ModelAndView("index");
		User user = userService.findUserByEmail(userDetails.getUsername());
		mv.addObject("user", user);
		mv.addObject("loans", user.getLoans());
		return mv;
	}
}
