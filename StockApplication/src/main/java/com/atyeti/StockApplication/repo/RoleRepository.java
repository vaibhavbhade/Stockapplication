package com.atyeti.StockApplication.repo;

import org.springframework.data.repository.CrudRepository;

import com.atyeti.StockApplication.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByname(String name);

}
