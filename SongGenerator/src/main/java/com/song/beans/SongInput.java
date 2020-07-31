package com.song.beans;

public class SongInput {

	private String tonic;
	private String mode;
	private int noteLength;
	public SongInput(String tonic, String mode, int noteLength) {
		super();
		this.tonic = tonic;
		this.mode = mode;
		this.noteLength = noteLength;
	}
	public SongInput() {
		super();
	}
	public String getTonic() {
		return tonic;
	}
	public void setTonic(String tonic) {
		this.tonic = tonic;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getNoteLength() {
		return noteLength;
	}
	public void setNoteLength(int noteLength) {
		this.noteLength = noteLength;
	}
	@Override
	public String toString() {
		return "SongInput [tonic=" + tonic + ", mode=" + mode + ", noteLength=" + noteLength + "]";
	}

	
	
}
