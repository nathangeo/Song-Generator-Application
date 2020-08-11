package com.song.entity;

import java.io.File;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SONG_TABLE")
public class Song {

    @Column(name = "SONG_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SONG_PK_SEQ")
    private int songId;
    
    @Column(name = "SONG_TITLE")
	private String title;
    
    @Column(name = "SONG_FILE", length = 500)
    private byte[] songFile;
    
    @Column(name = "USERNAME")
	private String username;

    
    
    
	public Song() {
		super();
	}




	public Song(String title, String username) {
		super();
		this.title = title;
		this.username = username;
	}



	public Song(int songId, String title, byte[] songFile, String username) {
		super();
		this.songId = songId;
		this.title = title;
		this.songFile = songFile;
		this.username = username;
	}




	public int getSongId() {
		return songId;
	}




	public void setSongId(int songId) {
		this.songId = songId;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public byte[] getSongFile() {
		return songFile;
	}




	public void setSongFile(byte[] songFile) {
		this.songFile = songFile;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	@Override
	public String toString() {
		return "Song [songId=" + songId + ", title=" + title + ", songFile=" + Arrays.toString(songFile) + ", username="
				+ username + "]";
	}

	
    
	
}
