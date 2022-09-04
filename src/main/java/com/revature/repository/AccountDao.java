package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.models.Member;
import com.revature.models.User;
import com.revature.repository.DTO.AccountDTO;
import com.revature.repository.DTO.UserDTO;
import com.revature.util.ConnectionFactory;

public class AccountDao implements AccountDaoInterface{
	Logger consoleLogger;
	Logger fileLogger;
	
	public AccountDao() {
		consoleLogger = LoggerFactory.getLogger("consoleLogger");
		fileLogger = LoggerFactory.getLogger("fileLogger");
	}
	
	public static void main(String[] args) {
		AccountDao aDao = new AccountDao();
		System.out.println(aDao.selectAccountByID(1));
	}

	@Override
	public AccountDTO selectAccountByID(Integer accountID) {
		consoleLogger.debug("Getting Account with ID: " + accountID);
		fileLogger.debug("Retrieved Account from Database with ID");
		
		AccountDTO account = null;
		
		final String sql = "SELECT * FROM accounts WHERE account_id = '"+accountID+"';"; 
		
		try (Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();)
		{
			
			ResultSet set = statement.executeQuery(sql);
			
			if(set.next()) {
				account = new AccountDTO(
						set.getInt(1),
						set.getInt(2),
						set.getString(3),
						set.getBoolean(4)
						);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
			fileLogger.error(e.toString());
		}
		
		return account;
	}

	@Override
	public void updateAccountOwner(Account account, UserDTO member) {
		// TODO Auto-generated method stub
		
		consoleLogger.debug("Updating Account Owner: " + account);
		fileLogger.debug("Account Owner Updated");
		
		final String SQL = "INSERT INTO user_role_bridge(user_id, account_id) values (?, ?);";	
	
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL);
				)
		{
			
			statement.setInt(1, account.getAccountID());
			statement.setInt(2, member.getUser_id());
			
			statement.execute();
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
			fileLogger.error(e.toString());
		}
		
	}

	@Override
	public void updateAccountOwner(AccountDTO convertAccountToAccountDTO, UserDTO convertUserToUserDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccountOwner(AccountDTO convertAccountToAccountDTO, User convertUserToUserDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccountOwner(Account account, Member member) {
		
		consoleLogger.debug("Updating Account Owner: " + account);
		fileLogger.debug("Account Owner Updated");
		
		final String SQL = "INSERT INTO user_account_bridge(user_id, account_id) values (?, ?);";	
	
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL);
				)
		{
			
			statement.setInt(1, account.getAccountID());
			statement.setInt(2, member.getUserID());
			
			statement.execute();
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			consoleLogger.error(e.getMessage());
			fileLogger.error(e.toString());
		}
		
	}

}
