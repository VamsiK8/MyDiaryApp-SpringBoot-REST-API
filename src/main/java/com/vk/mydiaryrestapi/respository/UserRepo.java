package com.vk.mydiaryrestapi.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.vk.mydiaryrestapi.entities.User;


public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query(value = "select * from users where username=:username LIMIT 1", nativeQuery = true)// :username -> named parameter takes func paramete here
	public User findByUsername(String username);
}
