package com.atyeti.StockApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderHistory {

	@Id
	@GeneratedValue
	public Long id;
	
	
	public Date sellDate;
	
	public int saleStockQuntity;
	
	public Double Sellprice;
	
	public Double buyPrice;
	
	public Double investedPrice;



	public Double finalTotalSellprice;
	

	public Double profit;
	@ManyToOne
	public Company company;

	@ManyToOne
	private User user;

	public OrderHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return sellDate;
	}

	public void setDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public int getSaleStockQuntity() {
		return saleStockQuntity;
	}

	public void setSaleStockQuntity(int saleStockQuntity) {
		this.saleStockQuntity = saleStockQuntity;
	}

	public Double getSellprice() {
		return Sellprice;
	}

	public void setSellprice(Double sellprice) {
		Sellprice = sellprice;
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

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public Double getFinalTotalSellprice() {
		return finalTotalSellprice;
	}

	public void setFinalTotalSellprice(Double finalTotalSellprice) {
		this.finalTotalSellprice = finalTotalSellprice;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getInvestedPrice() {
		return investedPrice;
	}

	public void setInvestedPrice(Double investedPrice) {
		this.investedPrice = investedPrice;
	}

	
	
	
	
	
	
	
}
