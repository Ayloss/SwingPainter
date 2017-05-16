package com.finalproject.configure;

public enum RectangleRoundedRadius {

	SMAll(5),
	MIDDLE(10),
	LARGE(20);
	
	private int val;
	
	private RectangleRoundedRadius(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
