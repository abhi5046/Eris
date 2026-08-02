package com.jobportal.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.config.JwtUtil;
import com.jobportal.dto.LoginDTO;
import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public User registerUser(UserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		user.setRole(dto.getRole());

		return userRepository.save(user);
	}

	@Override
	public String login(LoginDTO dto) {
		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() ->
				new RuntimeException("Invalid email"));
		boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());
		
		if(!isMatch) {
			throw new RuntimeException("Invalid Password");
		}
		return jwtUtil.genrateToken(user.getEmail());
	}

}
