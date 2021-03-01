package com.atyeti.StockApplication.repo;

import org.springframework.data.repository.CrudRepository;

import com.atyeti.StockApplication.model.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {

	
	
}
