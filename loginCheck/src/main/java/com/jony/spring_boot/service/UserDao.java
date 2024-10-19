package com.jony.spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jony.spring_boot.entity.User;
import com.jony.spring_boot.repository.UserRepo;

@Component
public class UserDao {
	
	@Autowired
	UserRepo repo;

	public void saveAll(User user) {
		repo.save(user);
		
		
	}

	public User checkUser(String name) {
		User user = repo.findByUser(name);
		
		return user;
		
	}

}
