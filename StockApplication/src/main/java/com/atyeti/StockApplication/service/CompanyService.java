package com.atyeti.StockApplication.service;

import java.util.List;

import com.atyeti.StockApplication.model.Company;

public interface CompanyService {

	public List<Company> addCompanyDetails(Company company);
	public List<Company> getAll();
	public List<Company> findCompanyDetails();
	public Company findCompanyDetailsById(Company company);
}
