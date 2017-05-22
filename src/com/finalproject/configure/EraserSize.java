package com.finalproject.configure;

/**
 * һ��������Ƥ���ߴ��ö���ࡣ
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
