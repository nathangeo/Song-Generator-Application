package com.song.beans;

public class FourChord {

	private String a;
	private String b;
	private String c;
	private String d;
	private int noteLength;
	public FourChord() {
		super();
	}
	public FourChord(String a, String b, String c, String d, int noteLength) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.noteLength = noteLength;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public int getNoteLength() {
		return noteLength;
	}
	public void setNoteLength(int noteLength) {
		this.noteLength = noteLength;
	}
	@Override
	public String toString() {
		return "FourChord [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", noteLength=" + noteLength + "]";
	}
	
	
	
}
