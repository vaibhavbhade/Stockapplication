package com.atyeti.StockApplication.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User implements UserDetails{

@Id
@GeneratedValue
private Long id;

private String name;

private String username;

private String password;

private String email;

private String phoneNo;

@OneToMany(mappedBy = "user")
private List<UserPayment> userPaymentList;

@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JsonIgnore
private Set<UserRole> userRoles=new HashSet<>();


@OneToMany(mappedBy = "user")
private List<UserOrder> userOrderList;

@OneToMany(mappedBy = "user")
private List<OrderHistory> userOrderHistoryList;

@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
private UserFunds userFunds;


public User() {
	super();
	// TODO Auto-generated constructor stub
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}



public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhoneNo() {
	return phoneNo;
}

public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}

public List<UserPayment> getUserPaymentList() {
	return userPaymentList;
}

public void setUserPaymentList(List<UserPayment> userPaymentList) {
	this.userPaymentList = userPaymentList;
}

public List<UserOrder> getUserOrderList() {
	return userOrderList;
}

public void setUserOrderList(List<UserOrder> userOrderList) {
	this.userOrderList = userOrderList;
}

public List<OrderHistory> getUserOrderHistoryList() {
	return userOrderHistoryList;
}

public void setUserOrderHistoryList(List<OrderHistory> userOrderHistoryList) {
	this.userOrderHistoryList = userOrderHistoryList;
}

public Set<UserRole> getUserRoles() {
	return userRoles;
}

public void setUserRoles(Set<UserRole> userRoles) {
	this.userRoles = userRoles;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	Set<GrantedAuthority> authorites = new HashSet<>();
	userRoles.forEach(ur -> authorites.add(new Authority(ur.getRole().getName())));
	
	return authorites;
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}

public UserFunds getUserFunds() {
	return userFunds;
}

public void setUserFunds(UserFunds userFunds) {
	this.userFunds = userFunds;
}








	
	
	
	
	
}
