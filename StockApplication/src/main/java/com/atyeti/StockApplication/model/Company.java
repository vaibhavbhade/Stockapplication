package com.atyeti.StockApplication.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import net.minidev.json.annotate.JsonIgnore;

@Entity
public class Company {

	@Id
	@GeneratedValue	
	private Long id;
	
	private String companyName;
	
	private String stockName;
	
	private int  availableStock;
	
	private Double pricePerStock;
	
	private String totalStocks;
	
	
	private Boolean isEnable=true;
	@JsonIgnore
	@OneToMany(mappedBy = "company")
	private List<UserOrder> userOrderList;
	
	@JsonIgnore

	@OneToMany(mappedBy = "company")
    private List<OrderHistory> orderHistoryList;
	


	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getStockName() {
		return stockName;
	}


	public void setStockName(String stockName) {
		this.stockName = stockName;
	}


	public int getAvailableStock() {
		return availableStock;
	}


	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}


	public Double getPricePerStock() {
		return pricePerStock;
	}


	public void setPricePerStock(Double pricePerStock) {
		this.pricePerStock = pricePerStock;
	}


	public String getTotalStocks() {
		return totalStocks;
	}


	public void setTotalStocks(String totalStocks) {
		this.totalStocks = totalStocks;
	}


	public Boolean getIsEnable() {
		return isEnable;
	}


	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}





	public List<UserOrder> getUserOrderList() {
		return userOrderList;
	}


	public void setUserOrderList(List<UserOrder> userOrderList) {
		this.userOrderList = userOrderList;
	}


	public List<OrderHistory> getOrderHistoryList() {
		return orderHistoryList;
	}


	public void setOrderHistoryList(List<OrderHistory> orderHistoryList) {
		this.orderHistoryList = orderHistoryList;
	}
	
	
	
	
	
	
	
	
	
}
