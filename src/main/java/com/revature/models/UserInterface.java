package com.revature.models;

import com.revature.repository.exceptions.UserNotFoundException;

public interface UserInterface {

	User login(String username, String password) throws UserNotFoundException;
	
	User createAccount(String firstName, String lastName, String username, String password, String email);
	
	boolean logout();
	
	boolean deleteAccount();
	
	boolean updateAccount(Account updatedAccount);
	
}
