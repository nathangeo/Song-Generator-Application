package com.song.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.song.entity.Song;

@Repository
public interface SongDAO extends JpaRepository<Song, Integer>{

	public List<Song> findSongByUsername(String username);
	
}
