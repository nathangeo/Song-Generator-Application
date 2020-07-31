package com.song.entity;

import java.io.File;

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
    
    @Column(name = "SONG_FILE")
    private byte[] songFile;
    
    @Column(name = "U_ID")
	private int userId;

    
    
    
	public Song() {
		super();
	}




	public Song(String title) {
		super();
		this.title = title;
	}




	public Song(int songId, String title, byte[] songFile, int userId) {
		super();
		this.songId = songId;
		this.title = title;
		this.songFile = songFile;
		this.userId = userId;
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




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	@Override
	public String toString() {
		return "Song [songId=" + songId + ", title=" + title + ", songFile=" + songFile + ", userId=" + userId + "]";
	}
    
    
    
    
	
}
