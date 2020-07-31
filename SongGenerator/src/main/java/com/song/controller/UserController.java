package com.song.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.song.beans.SongInput;
import com.song.entity.User;
import com.song.service.UserService;



@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userservice;
	
	//BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
	
	
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
    	return userservice.getAllUsers();
    }
	
    
}
