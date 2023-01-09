package com.freshersProject.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freshersProject.ecommerce.model.User;
import com.freshersProject.ecommerce.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/userRegistration")
	public String addUser(@RequestBody User user) {

		return userService.addUser(user);
		//return "Registration Successfull";
	}

	@GetMapping(value = "/userAllData")
	public List<User> getAllUser() {
		List<User> list = userService.getAllUser();

		return list;
	}

	@GetMapping(value = "/myProfile")
	private User getUserByEmail(@RequestParam String email) {
		return userService.getUserByEmail(email);

	}

	@PostMapping(value = "/userLogin")
	public String userLogin(@RequestParam String email, @RequestParam String password) throws Exception {

		return userService.userLogin(email, password);

	}

	@PutMapping("/changePassword")
	public String updatePassword(@RequestBody User user) {

		return userService.updatePassword(user);

	}

	@DeleteMapping("/deleteUsers")
	public String deleteUsers(@RequestParam int id) {
		return userService.deleteUsers(id);

	}

	@PutMapping("/updateUsers")
	public String updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

}
