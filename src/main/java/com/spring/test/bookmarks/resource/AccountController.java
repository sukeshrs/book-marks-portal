package com.spring.test.bookmarks.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.bookmarks.dao.AccountRepository;
import com.spring.test.bookmarks.dao.CustomerRepository;
import com.spring.test.bookmarks.model.Account;
import com.spring.test.bookmarks.model.Customer;

@RestController
@RequestMapping(path="/account")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createAccount(@RequestBody Account account){
		
		Account newAccount =null;
		ResponseEntity<Object> response= null;
		
		if(account !=null) {
			newAccount = accountRepository.save(account);
			response = ResponseEntity.ok().body(newAccount);
		}else {
			response =  ResponseEntity.unprocessableEntity().body("Request Not Good");
		}
		
	 return response;
	
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAccounts(){
		
		Iterable<Account> accounts =accountRepository.findAll();
		ResponseEntity<Object> response= null;
		if (accounts != null) {
			response = ResponseEntity.ok().body(accounts);
		}else{
			response = ResponseEntity.badRequest().body("There are no accounts");
		}
		return response;
	}
	

}
