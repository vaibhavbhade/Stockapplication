package com.atyeti.StockApplication.repo;

import org.springframework.data.repository.CrudRepository;

import com.atyeti.StockApplication.model.UserOrder;

public interface UserOrderRepository extends CrudRepository<UserOrder, Long> {

}
