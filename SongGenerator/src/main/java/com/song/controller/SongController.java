package com.song.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.song.beans.SongInput;
import com.song.service.SongService;

@CrossOrigin
@RestController
@RequestMapping("/song")
public class SongController {

	private SongService songservice;
	
	@Autowired
	public SongController(SongService songservice) {
		this.songservice = songservice;
	}
	
    @RequestMapping(value = "/cofordered", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<String> cofOrdered(@RequestBody SongInput input) throws IOException {
    	
    	List<String> s = new ArrayList<String>();
    	File f = this.songservice.cofOrdered(input.getTonic(), input.getMode(), input.getNoteLength());
    	byte[] byteformat = new byte[(int) f.length()];
    	FileInputStream fis = new FileInputStream(f);
    	fis.read(byteformat);
    	fis.close();
    	byte[] encoded = Base64.encodeBase64(byteformat);
    	String encodedString = new String(encoded);
    	s.add(encodedString);
    	return s;
    	
    }
}
