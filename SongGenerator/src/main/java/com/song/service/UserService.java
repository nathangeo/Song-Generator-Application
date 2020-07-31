package com.song.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.dao.UserDAO;
import com.song.entity.User;

@Service
public class UserService {

	private UserDAO userdao;
	
	@Autowired
	public UserService(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	
	public User addUpdateUser(User user) {
		return this.userdao.save(user);
	}
	
	public List<User> getAllUsers() {
		return this.userdao.findAll();
	}
	
	public User findUserByUsername(String username) {
		return this.userdao.findUserByUsername(username);
	}
}
