package com.song.controller;

import java.io.File;
import java.io.IOException;
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

	UserService userservice;
	
	@Autowired
	public UserController(UserService userservice) {
		this.userservice = userservice;
	}
	
	//BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
	
	
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
    	return userservice.getAllUsers();
    }
	
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User addUpdateUser(@RequestBody User user) throws IOException {
    	
    	boolean b = this.userservice.existsByUsername(user.getUsername());
    	if(b == false) {
        	User u = this.userservice.addUpdateUser(user);
    		u.setPassword("*****");
    		return u;
    	} else {
    		return null;
    	}	   	
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User loginUser(@RequestBody User user) throws IOException {
    	
    	boolean b = this.userservice.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
    	if(b == true) {
    		User u = this.userservice.findUserByUsername(user.getUsername());
    		u.setPassword("*****");
    		return u;
    	} else {
    		return null;
    	}
    }
}
