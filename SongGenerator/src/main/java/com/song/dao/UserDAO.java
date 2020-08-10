package com.song.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.song.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	public boolean existsByUsernameAndPassword(String username, String password);
	public User findUserByUsername(String username);
	public boolean existsByUsername(String username);
	
	
	
}
