package com.freshersProject.ecommerce.service;

import java.util.List;

import com.freshersProject.ecommerce.model.User;

public interface UserService {

	String addUser(User user);

	List<User> getAllUser();

	User getUserByEmail(String email);

	String userLogin(String email, String password);

	String deleteUsers(int id);

	String updatePassword(User user);

	String updateUser(User user);

}
