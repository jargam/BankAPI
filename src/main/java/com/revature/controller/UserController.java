package com.revature.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.models.User;
import com.revature.repository.exceptions.UserNotFoundException;
import com.revature.services.CreateAccountService;
import com.revature.services.LoginService;
import com.revature.util.ConnectionFactory;

public class UserController implements UserInputInterface, LoginInterface, CreateAccountInterface{

	private Scanner sc;
	private LoginService loginService;
	private CreateAccountService createAccountService;
	private User user;
	
	
	
	public UserController(Scanner sc, LoginService loginService, CreateAccountService createAccountService) {
		super();
		this.sc = sc;
		this.loginService = loginService;
		this.createAccountService = createAccountService;
	}

	public User validateLogin(String username, String password) {
		
		if(username == null && password == null) {
			return null;
		}
		
		User user = null;
		try {
			user = loginService.login(username, password);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public String getUserInput() {
		System.out.println("Please enter your username");
		return sc.nextLine();
	}
	public String getUserInput1() {
		System.out.println("Please enter your password");
		return sc.nextLine();
	}

	public void login() {
		System.out.println("Please login below \n");
		String username = getUserInput();
		String password = getUserInput1();
		User user = validateLogin(username, password);
		
		if(user != null) {
			System.out.println("Login successful!");
			System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName());
		}else {
			System.out.println("Login failed! :(");
		}
	}

	
	public void verifyUser() throws SQLException {

		int choice;
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to your Bank \n");
		System.out.println("1.) Sign up for a new user account");
		System.out.println("2.) Log in to an exisiting account");
		System.out.println("3.) Admin login");
		System.out.println("4.) Employee login");
		System.out.println("5.) Exit");

		do {
			choice = scan.nextInt();

			switch (choice) {
			case 1:
				createAccount();
				System.out.println("Select 1 to sign up for a new user account");
				System.out.println("Select 2 to log in to your newly created account: ");
				break;
			case 2:
					login();
				break;
			case 3:
				Scanner in = new Scanner(System.in);
//				com.revature.controller.AdminController.adminSession(in);
				break;
			case 4:
				Scanner in1 = new Scanner(System.in);
//				com.revature.controller.AdminController.adminSession(in);
				break;
			case 5:
				System.out.println("Thank you and See you soon");
				break;
			default:
				System.out.println("enter a choice from 1 to 3");
			}
		} while (choice != 5);
	}

	@Override
	public User ValidateCreatedAccount(String firstName, String lastName, String username, String password,
			String email) {
		if(firstName == null && lastName == null && username == null && password == null && email == null) {
			return null; 
			}
		User user = createAccountService.createAccount(firstName, lastName, username, password, email);
		return user;
	}
	
	@Override
	public String getUserInput3() {
//		System.out.println("Create a new account below");
		return sc.nextLine();
		
	}

	@Override
	public void createAccount() {
		
		System.out.println("Please create your account \n");
//		System.out.println("Enter your First Name, Last Name, Username, Password, and Email \n");
	
		
		
		
	
		
		

		System.out.println("Enter your First Name \n");
		String firstName = getUserInput3();
		System.out.println("Enter your Last Name \n");
		String lastName = getUserInput3();
		System.out.println("Enter your username \n");
		String username = getUserInput3();
		System.out.println("Enter your password \n");
		String password = getUserInput3();
		System.out.println("Enter your email \n");
		String email = getUserInput3();
//		User user = ValidateCreatedAccount(firstName, lastName, username, password, email);
		try {
			createUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null) {
			System.out.println("Account created!");
			System.out.println(user);
		}else {
			System.out.println("Please try again");
		}
	}
	
	public int createUser(User users) throws SQLException {
		ConnectionFactory cf = ConnectionFactory.getInstance();
		
		int usersCreated = 0;
		
		String sql = "INSERT INTO users(first_name, last_name, username, password, email) VALUES (?, ?, ?, ?, ?)";
		
		Connection conn= cf.getConnection();
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
//		ps.setInt(1, user.getUserID());
		ps.setString(2, users.getFirstName());
		ps.setString(3, users.getLastName());
		ps.setString(4, users.getUsername());
		ps.setString(5, users.getPassword());
		ps.setString(6, users.getEmail());
		
		ps.executeUpdate(sql);

		return usersCreated;
	}

	
}
