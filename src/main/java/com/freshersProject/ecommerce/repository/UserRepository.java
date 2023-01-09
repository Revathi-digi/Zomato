package com.freshersProject.ecommerce.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.freshersProject.ecommerce.model.User;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {

	User findById(int id);

	User findByuserName(@NotNull(message = "Name cannot be null") String userName);

	User findByEmail(String email);

	User findByUserPhoneNumber(@NotNull(message = "Phone Number cannot be null") String userPhoneNumber);

}
