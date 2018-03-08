package com.spring.test.bookmarks.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.bookmarks.dao.CustomerRepository;
import com.spring.test.bookmarks.model.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> addCustomer(@RequestBody Customer newCustomer){
		
		Customer addedCustomer = customerRepository.save(newCustomer);
		if(addedCustomer!=null && addedCustomer.getFirstName()!=null) {
			return ResponseEntity.ok().body(addedCustomer);
		}else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
		}
			
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllCustomers(){
		
		Iterable<Customer> customerList = customerRepository.findAll();
		if (customerList !=null && customerList.iterator().hasNext()) {
			return ResponseEntity.ok().body(customerList);
		}else {
			return ResponseEntity.ok().body("No customers Found");
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET , path="/name")
	public ResponseEntity<Object> getCustomerByName(@RequestParam String name){
		
		List<Customer> customerList = customerRepository.findByLastName(name);
		if (customerList !=null && customerList.size()>0) {
			return ResponseEntity.ok().body(customerList);
		}else {
			return ResponseEntity.ok().body("No customers Found");
		}
		
	}
	
}
