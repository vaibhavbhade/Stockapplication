package com.atyeti.StockApplication.serviceImpl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atyeti.StockApplication.model.Company;
import com.atyeti.StockApplication.model.User;
import com.atyeti.StockApplication.model.UserFunds;
import com.atyeti.StockApplication.model.UserOrder;
import com.atyeti.StockApplication.repo.CompanyRepository;
import com.atyeti.StockApplication.repo.UserFundsRepository;
import com.atyeti.StockApplication.repo.UserOrderRepository;
import com.atyeti.StockApplication.service.UserOrderService;

@Service
public class UserOrderServiceIpml implements UserOrderService {

	@Autowired
	private UserOrderRepository userOrderRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserFundsRepository userFundsRepository;

	@Transactional
	@Override
	public UserOrder createUserOrder(UserOrder userOrder, User user, Company company, int quantity,
			UserFunds userFunds) {
		// TODO Auto-generated method stub

		userOrder.setCompany(company);
		userOrder.setUser(user);
		userOrder.setStockQuntity(quantity);
		userOrder.setOrderDate(new Date());
		userOrder.setStockBuyPrice(company.getPricePerStock());
		userOrder.setFinalprice(company.getPricePerStock() * quantity);
		company.setAvailableStock(company.getAvailableStock() - quantity);
	//	(value*(percentage/100.0f));
		Double upPrice=company.getPricePerStock() * quantity * (0.5/100.0f);
		System.out.println("upPrice::"+Math.round(upPrice));
		company.setPricePerStock(company.getPricePerStock()+Math.round(upPrice));
		companyRepository.save(company);
		userFunds.setAmmount(userFunds.getAmmount() - company.getPricePerStock() * quantity);
		userFundsRepository.save(userFunds);
		return userOrderRepository.save(userOrder);

	}

	@Override
	public UserOrder updateUserOrder(User user, UserOrder userOrder, Company company, int quantity, UserFunds userFunds) {
		// TODO Auto-generated method stub
		System.out.println("quantity  :: "+quantity);
		System.out.println("quantity  :: " +company.getAvailableStock());
		userOrder.setStockQuntity(quantity);
		userOrder.setOrderDate(new Date());
		userOrder.setStockBuyPrice(company.getPricePerStock());
		userOrder.setFinalprice(company.getPricePerStock() * quantity);
		company.setAvailableStock(company.getAvailableStock() - quantity);

		companyRepository.save(company);
		userFunds.setAmmount(userFunds.getAmmount() - company.getPricePerStock() * quantity);
		userFundsRepository.save(userFunds);
		return userOrderRepository.save(userOrder);
	}

	@Override
	public UserOrder findUserOrder(Long id) {
		// TODO Auto-generated method stub
		return userOrderRepository.findById(id).orElse(null);
	}

}
