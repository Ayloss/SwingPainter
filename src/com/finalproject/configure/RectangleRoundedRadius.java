package com.finalproject.configure;

/**
 * 一个代表矩形圆角半径的枚举类。
 * 
 * @author Yixin
 *
 */
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
