package com.viktor.backend.controller;

import com.viktor.backend.exception.*;
import com.viktor.backend.model.*;
import com.viktor.backend.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/logins")
	public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }
	
	@GetMapping("/logins/{id}")
	public ResponseEntity<Login> getLoginById(@PathVariable(value = "id") Long loginId)
			throws ResourceNotFoundException {
		Login login = loginRepository.findById(loginId)
				.orElseThrow(() -> new ResourceNotFoundException("Login not found for this id: " + loginId));
				return ResponseEntity.ok().body(login);
	}
	
	@PostMapping("/logins/{userId}")
	public Login createLogin(@PathVariable(value = "userId") Long userId,
			@Valid @RequestBody Login login) 
					throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
		login.setUser(user);
		return loginRepository.save(login);
}
	
	@PutMapping("/logins/{id}")
	public ResponseEntity<Login> updateLogin(@PathVariable(value = "id") Long loginId,
			@Valid @RequestBody Login loginDetails) throws ResourceNotFoundException {
		Login login = loginRepository.findById(loginId)
				.orElseThrow(() -> new ResourceNotFoundException("Login not found for this id: " + loginId));
		
		login.setUsername(loginDetails.getUsername());
		login.setPassword(loginDetails.getPassword());
	
		final Login updatedLogin = loginRepository.save(login);
		return ResponseEntity.ok(updatedLogin);
	}
	
	@DeleteMapping("/logins/{id}")
	public Map<String, Boolean> deleteLogin(@PathVariable(value = "id") Long loginId)
    		throws ResourceNotFoundException {
        Login login = loginRepository.findById(loginId)
       .orElseThrow(() -> new ResourceNotFoundException("Login not found for this id :: " + loginId));

        loginRepository.delete(login);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}