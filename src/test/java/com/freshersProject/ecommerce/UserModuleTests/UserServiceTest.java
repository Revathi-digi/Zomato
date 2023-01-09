package com.freshersProject.ecommerce.UserModuleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.freshersProject.ecommerce.model.User;
import com.freshersProject.ecommerce.repository.UserRepository;
import com.freshersProject.ecommerce.serviceImpl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

//@WebMvcTest(UserServiceImpl.class)
@ActiveProfiles("junit")
@ExtendWith(MockitoExtension.class)
@Slf4j
public class UserServiceTest {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	public void findAllTest() {
		ArrayList<User> list = new ArrayList<>();
		User user = new User();
		user.setEmail("shukla");

		list.add(user);
		when(userRepository.findAll()).thenReturn(list);
		assertEquals(1, userRepository.findAll().size());
	}

	@Test
	public void updateUserTest() throws Exception {
		ArrayList<User> list = new ArrayList<>();
		User user = new User();

		user.setUserName("Digi");
		user.setUserPhoneNumber("123");
		user.setEmail("abc@gmail.com");
		user.setPassword("abc999");
		user.setUserAddress("USA");
		user.setUserGender("Male");
		user.setUserDOB("2020-12-12");

		list.add(user);

		when(userRepository.findByEmail("abc@gmail.com")).thenReturn(user);
		assertEquals("user  Updated", userServiceImpl.updateUser(user));

	}

	@Test
	public void addUser() throws Exception {
		User user = new User(1, "Modi", "1234561234", "lakhan@gmail.com", "lakhan999", "Pune", "Male", "1998-7-19");
		when(userRepository.save(user)).thenReturn(user);

		assertEquals("Data Saved", userServiceImpl.addUser(user));
	}

	@Test
	public void deleteUserTest() throws Exception {
		ArrayList<User> list = new ArrayList<>();
		User user = new User();
		user.setId(1);
		user.setUserName("Digi");
		user.setUserPhoneNumber("123");
		user.setEmail("abc@gmail.com");
		user.setPassword("abc999");
		user.setUserAddress("USA");
		user.setUserGender("Male");
		user.setUserDOB("2020-12-12");

		list.add(user);
		assertEquals("User Deleted", userServiceImpl.deleteUsers(1));

	}

	@Test
	public void userLoginTest() throws Exception {

		User user = new User();
		user.setEmail("saurabh@gmail.com");
		user.setPassword("saurabh999");

		when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

		assertEquals("Login Success", userServiceImpl.userLogin(user.getEmail(), user.getPassword()));

	}

}
