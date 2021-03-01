package com.atyeti.StockApplication.repo;

import org.springframework.data.repository.CrudRepository;

import com.atyeti.StockApplication.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByusername(String username);
	
	public User findByemail(String email);

}
