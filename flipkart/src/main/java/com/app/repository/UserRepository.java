package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findUserByUsernameAndPassword(String username, String password);
	//select * from user where username="admin" and password="admin";
}
