package com.revature.services;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.models.UserInterface;
import com.revature.repository.UserDaoInterface;
import com.revature.repository.exceptions.UserNotFoundException;

public class CreateAccountService implements UserInterface{
	
	private UserDaoInterface userDao;
	
	

	public CreateAccountService(UserDaoInterface userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public User login(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createAccount(String firstName, String lastName, String username, String password, String email) {
		return userDao.createUser(firstName, lastName, username, password, email);
		
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(Account updatedAccount) {
		// TODO Auto-generated method stub
		return false;
	}

}
