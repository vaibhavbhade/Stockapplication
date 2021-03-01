package com.atyeti.StockApplication.repo;

import org.springframework.data.repository.CrudRepository;

import com.atyeti.StockApplication.model.UserFunds;

public interface UserFundsRepository extends CrudRepository<UserFunds, Long>{

}
