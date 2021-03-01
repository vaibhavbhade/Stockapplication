package com.atyeti.StockApplication.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atyeti.StockApplication.model.OrderHistory;
import com.atyeti.StockApplication.model.User;
import com.atyeti.StockApplication.model.UserFunds;
import com.atyeti.StockApplication.model.UserOrder;
import com.atyeti.StockApplication.repo.OrderHistoryRepository;
import com.atyeti.StockApplication.repo.UserFundsRepository;
import com.atyeti.StockApplication.repo.UserOrderRepository;
import com.atyeti.StockApplication.service.OrderHistoryService;


@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	private OrderHistoryRepository orderHistoryRepository;
	
	@Autowired
	private UserOrderRepository userOrderRepository;
	
	@Autowired
	private UserFundsRepository userFundsRepository;
	
	
	
	
	@Override
	public List<OrderHistory> createOrderHistory(UserOrder userOrder, User user) {
		OrderHistory orderHistory=new OrderHistory();
	    UserFunds userFunds=user.getUserFunds();
		orderHistory.setUser(user);
		orderHistory.setCompany(userOrder.getCompany());
		orderHistory.setFinalTotalSellprice(userOrder.getCompany().getPricePerStock() * userOrder.getStockQuntity());
		orderHistory.setSellprice(userOrder.getCompany().getPricePerStock());
		orderHistory.setSellDate(new Date());
		orderHistory.setBuyPrice(userOrder.getStockBuyPrice());
		orderHistory.setInvestedPrice(userOrder.getFinalprice());
		orderHistory.setSaleStockQuntity(userOrder.getStockQuntity());
		Double profit= 0.0;
		
		if(userOrder.getFinalprice() <= userOrder.getCompany().getPricePerStock() * userOrder.getStockQuntity())
		{
			profit=userOrder.getCompany().getPricePerStock() * userOrder.getStockQuntity()-userOrder.getFinalprice();
			System.out.println("profit");
			orderHistory.setProfit(profit);
		}
		else if(userOrder.getFinalprice() >= userOrder.getCompany().getPricePerStock() * userOrder.getStockQuntity())
		{
			profit=userOrder.getCompany().getPricePerStock() * userOrder.getStockQuntity()-userOrder.getFinalprice();
			System.out.println("loss");
			orderHistory.setProfit(profit);

		}
		userFunds.setAmmount(userFunds.getAmmount()+userOrder.getCompany().getPricePerStock()*userOrder.getStockQuntity());
		userFundsRepository.save(userFunds);
		orderHistoryRepository.save(orderHistory);
		userOrderRepository.delete(userOrder);
		return (List<OrderHistory>) orderHistoryRepository.findAll();
		
		
	}

}
