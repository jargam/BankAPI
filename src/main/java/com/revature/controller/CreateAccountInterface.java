package com.revature.controller;

import com.revature.models.User;

public interface CreateAccountInterface {

//	Validate User input
	
	void createAccount();
	
	User ValidateCreatedAccount(String firstName, String lastName, String username, String password, String email);

	String getUserInput3();
	
//	Insert info into database
	
	
	
//	Display info on create account status
	
}
