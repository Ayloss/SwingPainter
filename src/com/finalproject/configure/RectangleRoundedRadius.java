package com.finalproject.configure;

public enum RectangleRoundedRadius {

	SMAll(2),
	MIDDLE(5),
	LARGE(10);
	
	private int val;
	
	private RectangleRoundedRadius(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
