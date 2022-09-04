package com.revature;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.controller.UserController;
import com.revature.repository.UserDao;
import com.revature.repository.UserDaoInterface;
import com.revature.services.CreateAccountService;
import com.revature.services.LoginService;

public class Driver {

	public static void main(String[] args) throws SQLException {
		UserDaoInterface uDao = new UserDao();
		LoginService loginService = new LoginService(uDao);
		CreateAccountService createAccountService = new CreateAccountService(uDao);
		UserController userController = new UserController(new Scanner(System.in), loginService, createAccountService);
	
//		userController.login();
		userController.verifyUser();
//		userController.createAccount();
	}
	
}
