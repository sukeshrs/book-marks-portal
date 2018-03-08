package com.spring.test.bookmarks.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.test.bookmarks.model.Account;

public interface AccountRepository extends  CrudRepository<Account, Long> {

}
