package com.song.beans;

public class PolyInput {
	
	private int left;
	private int right;
	private int beatLength;
	public PolyInput() {
		super();
	}
	public PolyInput(int left, int right, int beatLength) {
		super();
		this.left = left;
		this.right = right;
		this.beatLength = beatLength;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getBeatLength() {
		return beatLength;
	}
	public void setBeatLength(int beatLength) {
		this.beatLength = beatLength;
	}
	@Override
	public String toString() {
		return "PolyInput [left=" + left + ", right=" + right + ", beatLength=" + beatLength + "]";
	}
	
	

}
