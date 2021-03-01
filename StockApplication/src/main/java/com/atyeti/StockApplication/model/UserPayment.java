package com.atyeti.StockApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserPayment {

	
	@Id
    @GeneratedValue
    public Long id;
	
	
	public String cardUserName;
	
	public String cardNumber;
	
	public String expDate;
	
	public String cvvNo;
	
	@ManyToOne
	public User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardUserName() {
		return cardUserName;
	}

	public void setCardUserName(String cardUserName) {
		this.cardUserName = cardUserName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCvvNo() {
		return cvvNo;
	}

	public void setCvvNo(String cvvNo) {
		this.cvvNo = cvvNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
}
