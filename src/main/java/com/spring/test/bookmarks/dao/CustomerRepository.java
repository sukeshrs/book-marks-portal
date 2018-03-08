package com.spring.test.bookmarks.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.test.bookmarks.model.Customer;

public interface CustomerRepository extends  CrudRepository<Customer, Long>{
	
	List<Customer> findByLastName(String lastName);

}
