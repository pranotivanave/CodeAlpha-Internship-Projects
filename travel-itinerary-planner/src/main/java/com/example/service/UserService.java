package com.example.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;


	@Service
	public class UserService {

	    @Autowired
	    private UserRepository userRepository;

	    public User registerUser(User user) {
	        
	        String hashedPassword = hashPassword(user.getPassword());
	        user.setPassword(hashedPassword);
	        return userRepository.save(user);
	    }

	    public boolean authenticateUser(String email, String password) {
	        User user = userRepository.findByEmail(email);
	        if (user != null) {
	            
	            String hashedPassword = hashPassword(password);
	            return user.getPassword().equals(hashedPassword);
	        }
	        return false;
	    }

	  
	    public User authenticateUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    private String hashPassword(String password) {
	        try {
	            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
	            byte[] hashedBytes = messageDigest.digest(password.getBytes());
	            StringBuilder hexString = new StringBuilder();
	            for (byte b : hashedBytes) {
	                hexString.append(String.format("%02x", b));
	            }
	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException("Error hashing password", e);
	        }
	    }
	    
	    public User findUserById(Long id) {
	        return userRepository.findById(id).orElse(null); // Ensure that userRepository is injected correctly
	    }

	}
