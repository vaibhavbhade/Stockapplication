package com.atyeti.StockApplication.service;

import java.util.Set;

import com.atyeti.StockApplication.model.User;
import com.atyeti.StockApplication.model.UserRole;

public interface UserService {
	
	public User saveUserDetails(User user, Set<UserRole> userRoles);

	public User findByusername(String username);

	public User findByEmail(String email);

}
