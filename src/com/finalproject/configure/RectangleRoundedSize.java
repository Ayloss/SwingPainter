package com.finalproject.configure;

public enum RectangleRoundedSize {

	SMAll(2),
	MIDDLE(5),
	LARGE(10);
	
	private int val;
	
	private RectangleRoundedSize(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
