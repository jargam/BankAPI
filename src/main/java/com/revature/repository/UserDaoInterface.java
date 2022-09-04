package com.revature.repository;

import com.revature.models.User;
import com.revature.repository.DTO.UserDTO;
import com.revature.repository.exceptions.UserNotFoundException;

public interface UserDaoInterface {

	//CRUD
	
//	CREATE
	
	User createUser(String firstName, String lastName, String username, String password, String email);
//	User createUser(User newUser);
	
//	READ
	
	User getUser(String username, String password) throws UserNotFoundException;
	
	
	
//	UPDATE
	
	User updateUser(User updatedUser);
	
//	DELETE
	
	User deleteUser(User deletedUser);

	User selectUserByID(Integer userID);

	
	
}
