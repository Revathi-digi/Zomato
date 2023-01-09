package com.freshersProject.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshersProject.ecommerce.model.User;
import com.freshersProject.ecommerce.repository.UserRepository;
import com.freshersProject.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public String addUser(User user) {
		
		try {
			userRepository.save(user); 
		}catch (Exception e) { 
			return "User Email or Phone Number already exist..";
			} 
		return "Data Saved";
		 
		/*
		 * try { User result = userRepository.findByEmail(user.getEmail()); User
		 * resultNew = userRepository.findByUserPhoneNumber(user.getUserPhoneNumber());
		 * 
		 * if (user.getEmail().equals(result.getEmail())) { return
		 * "Email already Exist, please use different Email"; }
		 * 
		 * if(user.getUserPhoneNumber().equals(resultNew.getUserPhoneNumber())) { return
		 * "Phone Number already Exist"; } }
		 * 
		 * catch (Exception e) { userRepository.save(user); } return "Data Saved";
		 * 
		 */
	}

	@Override
	public List<User> getAllUser() {
		List<User> user = new ArrayList<>();
		userRepository.findAll().forEach(users -> user.add(users));
		return user;
	}

	//@Override
	public User getUserById(int id) {
		return userRepository.findById(id);
	}
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public String userLogin(String email, String password) {
		User user;
		user = userRepository.findByEmail(email);
		if (user != null) {
			if (user.getPassword().equals(password) && user.getEmail().equals(email)) {
				return "Login Success";
			} else {
				return "Login Faild";
			}

		} else {
			return "Login Faild";
		}
	
	}

	@Override
	public String deleteUsers(int id) {
		userRepository.deleteById(id);
		return "User Deleted";

	}

	@Override
	public String updatePassword(User user) {
		User users = userRepository.findByEmail(user.getEmail());
		users.setPassword(user.getPassword());
		this.userRepository.save(users);
		return "password change";
	}

	@Override
	public String updateUser(User user) {
		User userUpdate = userRepository.findByEmail(user.getEmail());

		
		if (user.getUserName().isBlank()) {
			userUpdate.setUserName(user.getUserName());
		}
		if (user.getUserPhoneNumber().isBlank()) {
			userUpdate.setUserPhoneNumber(user.getUserPhoneNumber());
		}
		if (user.getEmail().isBlank()) {
			userUpdate.setEmail(user.getEmail());
		}
		if (user.getPassword().isBlank()) {
			userUpdate.setPassword(user.getPassword());
		}
		if (user.getUserAddress().isBlank()) {
			userUpdate.setUserAddress(user.getUserAddress());
		}
		if (user.getUserGender().isBlank()) {
			userUpdate.setUserGender(user.getUserGender());
		}
		if (user.getUserDOB().isBlank()) {
			userUpdate.setUserDOB(user.getUserDOB());
		}

		userRepository.save(userUpdate);
		return "user  Updated";
	}

}
