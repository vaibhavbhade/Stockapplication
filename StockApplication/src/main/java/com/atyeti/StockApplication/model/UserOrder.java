package com.atyeti.StockApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_order")
public class UserOrder {

@Id
@GeneratedValue
public Long id;	
	

public Date orderDate;


public Double finalprice;

public Double stockBuyPrice;

public int stockQuntity;

@ManyToOne
public Company company;

@ManyToOne
private User user;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Date getOrderDate() {
	return orderDate;
}

public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}

public Double getFinalprice() {
	return finalprice;
}

public void setFinalprice(Double finalprice) {
	this.finalprice = finalprice;
}

public int getStockQuntity() {
	return stockQuntity;
}

public void setStockQuntity(int stockQuntity) {
	this.stockQuntity = stockQuntity;
}

public Company getCompany() {
	return company;
}

public void setCompany(Company company) {
	this.company = company;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Double getStockBuyPrice() {
	return stockBuyPrice;
}

public void setStockBuyPrice(Double stockBuyPrice) {
	this.stockBuyPrice = stockBuyPrice;
}

 


}
