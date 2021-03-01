package com.atyeti.StockApplication.repo;

import org.springframework.data.repository.CrudRepository;

import com.atyeti.StockApplication.model.OrderHistory;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {

}
