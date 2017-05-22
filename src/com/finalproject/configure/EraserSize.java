package com.finalproject.configure;

/**
 * 一个代表橡皮擦尺寸的枚举类。
 * 
 * @author Yixin
 *
 */
public enum EraserSize {

	SMALL(10),
	MIDDLE(20),
	LARGE(30);
	
	private int val;
	
	private EraserSize(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
