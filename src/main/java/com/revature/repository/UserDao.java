package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Member;
import com.revature.models.User;
import com.revature.repository.DTO.UserDTO;
import com.revature.repository.exceptions.UserNotFoundException;
import com.revature.util.ConnectionFactory;

public class UserDao implements UserDaoInterface{
	
	Logger consoleLogger;
	Logger fileLogger;
	private String username;
	
	public UserDao() {
		consoleLogger = LoggerFactory.getLogger("consoleLogger");
		fileLogger = LoggerFactory.getLogger("fileLogger");
	}

	public User createUser(User newUser) {
		User user = null;
		
		final String sql = "INSERT INTO users(user_id, first_name, last_name, username, password, email) VALUES (nextval (\'userSeq\'),?, ?, ?, ?, ?);";
		
		try (	Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://database-1.ccmfvntumpjm.us-east-1.rds.amazonaws.com:5432/postgres",
				"postgres",
				"6880Excelsior!");
				Statement statement = connection.createStatement();)
		{
		
			ResultSet set = statement.executeQuery(sql);
			
		if(set.next()) {
			user = new Member(
					set.getInt(1),
					set.getString(2),
					set.getString(3),
					set.getString(4),
					set.getString(5),
					set.getString(6)
					);
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public User getUser(String username, String password) throws UserNotFoundException{
		
		consoleLogger.debug("Getting User with username: " + username);
		fileLogger.debug("Retrieved username from Database");
		consoleLogger.debug("Getting User with password: " + password);
		fileLogger.debug("Retrieved password from Database");
		
		User user = null;
		
		final String sql = "SELECT * FROM users WHERE username = '"+username+"' AND password = '"+password+"';"; 
		
		try (Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();)
		{
			
			ResultSet set = statement.executeQuery(sql);
			
			if(set.next()) {
				user = new Member(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getString(5),
						set.getString(6)
						);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
			fileLogger.error(e.toString());
		}
		
		return user;

	}

	public User updateUser(User updatedUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public User deleteUser(User deletedUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectUserByID(Integer userID) {
		consoleLogger.debug("Getting User with user_id: " + userID);
		fileLogger.debug("Retrieved User from Database");
		
		
		User user = null;
		
		final String sql = "SELECT * FROM users WHERE user_id = '"+userID+"';"; 
		
		try (Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();)
		{
			
			ResultSet set = statement.executeQuery(sql);
			
			if(set.next()) {
				user = new Member(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getString(5),
						set.getString(6)
						);
			}else {
				throw new UserNotFoundException("User not found with username: " + username);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
			fileLogger.error(e.toString());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User createUser(String firstName, String lastName, String username, String password, String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
