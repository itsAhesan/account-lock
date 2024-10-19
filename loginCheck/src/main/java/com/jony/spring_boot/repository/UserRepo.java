package com.jony.spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jony.spring_boot.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	 @Query("SELECT e FROM User e WHERE e.name = :name") 
	User findByUser(String name);

}
