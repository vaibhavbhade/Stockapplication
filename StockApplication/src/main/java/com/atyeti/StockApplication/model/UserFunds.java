package com.atyeti.StockApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_funds")
public class UserFunds {

@Id
@GeneratedValue
private Long id;

private Double ammount;


@OneToOne(cascade = CascadeType.ALL)
private User user;


public UserFunds() {
	super();
	// TODO Auto-generated constructor stub
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public Double getAmmount() {
	return ammount;
}


public void setAmmount(Double ammount) {
	this.ammount = ammount;
}


public User getUser() {
	return user;
}


public void setUser(User user) {
	this.user = user;
}


 



	
	
	
}
