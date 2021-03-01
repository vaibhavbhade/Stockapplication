package com.atyeti.StockApplication.service;

import java.util.List;

import com.atyeti.StockApplication.model.OrderHistory;
import com.atyeti.StockApplication.model.User;
import com.atyeti.StockApplication.model.UserOrder;

public interface OrderHistoryService {

	
	
List<OrderHistory> createOrderHistory(UserOrder userOrder, User user);	
	
}
