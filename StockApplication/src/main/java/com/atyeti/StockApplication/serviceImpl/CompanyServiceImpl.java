package com.atyeti.StockApplication.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atyeti.StockApplication.model.Company;
import com.atyeti.StockApplication.repo.CompanyRepository;
import com.atyeti.StockApplication.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Company> addCompanyDetails(Company company) {
		// TODO Auto-generated method stub
		companyRepository.save(company);

		return (List<Company>) companyRepository.findAll();
	}

	@Override
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		return (List<Company>) companyRepository.findAll();
	}

	@Override
	public List<Company> findCompanyDetails() {
		// TODO Auto-generated method stub
		return (List<Company>) companyRepository.findAll();
	}

	@Override
	public Company findCompanyDetailsById(Company company) {
		// TODO Auto-generated method stub
		return companyRepository.findById(company.getId()).orElse(null);
	}

}
