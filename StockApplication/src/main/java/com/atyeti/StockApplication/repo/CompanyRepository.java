package com.atyeti.StockApplication.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atyeti.StockApplication.model.Company;
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
