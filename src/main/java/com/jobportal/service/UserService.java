package com.jobportal.service;

import com.jobportal.dto.LoginDTO;
import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;

public interface UserService {
	User registerUser(UserDTO dto);
	String login(LoginDTO dto);
}
