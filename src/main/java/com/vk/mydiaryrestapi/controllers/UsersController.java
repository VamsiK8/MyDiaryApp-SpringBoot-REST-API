package com.vk.mydiaryrestapi.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.mydiaryrestapi.entities.User;
import com.vk.mydiaryrestapi.service.UserService;

//server.servlet.context-path=/mydiaryapi --> the 'context path' is used to set a base URL for all endpoints in the application.

@RestController// used when no 'views' or when 'REST' api used
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	// @GetMapping("/users/") not used here because @RequestMapping("/users") is mapped/set at class level, no need to mention at method, so("/") == ("/users/")
	@GetMapping("/")
	public List<User> findAllUser() {	
		return userService.findAll();
	}  
	
	// @GetMapping("/users/{id}") not used here because @RequestMapping("/users") is mapped/set at class level, no need to mention at method, so("/{id}") == ("/users/{id}")
	@GetMapping("/{id}")
	public User findUserById(@PathVariable("id") long id) {	
		User user = userService.findById(id);
		return user;
	}  
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {	// @RequestBody - used to get body(user) from request via postman.
		User saveduser = userService.saveUser(user);	
		return saveduser;		
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user) {	// @RequestBody - used to get body(user) from request via postman.
		User updateduser = userService.updateUser(user);	
		return updateduser;		
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") long id, @RequestBody User user) {	// @RequestBody - used to get body(user) from request via postman.
		
		User dbuser = userService.findById(id);// update only required field (request with all fields) -->burden to solve we use "Patch"
		dbuser.setUsername(user.getUsername());
		dbuser.setPassword(user.getPassword());
		
		User updateduser = userService.updateUser(dbuser);
		return updateduser;		
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		User user = userService.findById(id);
		userService.deleteUser(user);
	}
	
	@PatchMapping("/{id}") // update only required field (request with only required field)
    public User updateUserWithPatch(@PathVariable("id") long id, @RequestBody User user) {	// @RequestBody - used to get body(user) from request via postman.
		
		User dbuser = userService.findById(id);
		
		String uname = user.getUsername();
		String pass = user.getPassword(); 
		
		if(uname!=null && uname.length()>0)
			dbuser.setUsername(uname);
		if(pass!=null && pass.length()>0)
		    dbuser.setPassword(pass);
		
		User updateduser = userService.updateUser(dbuser);
		return updateduser;		
	}
	
}
