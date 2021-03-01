package com.atyeti.StockApplication.service;

import com.atyeti.StockApplication.model.Company;
import com.atyeti.StockApplication.model.User;
import com.atyeti.StockApplication.model.UserFunds;
import com.atyeti.StockApplication.model.UserOrder;

public interface UserOrderService {


	UserOrder createUserOrder(UserOrder userOrder, User user, Company company, int quantity, UserFunds userFunds);


	UserOrder updateUserOrder(User user, UserOrder userOrder, Company company, int quantity, UserFunds userFunds);

	
	UserOrder  findUserOrder(Long id);
}
