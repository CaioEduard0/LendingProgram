package com.example.LendingProgram.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.LendingProgram.entities.User;
import com.example.LendingProgram.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User findUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user.orElseThrow(() -> new UsernameNotFoundException("User with e-mail " + email + " not found!"));
	}
	
	public void insertUser(User user) {
		userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user.orElseThrow(() -> new UsernameNotFoundException("User with e-mail " + email + " not found!"));
	}
}
