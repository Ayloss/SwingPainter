package com.finalproject.util;

import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * 泛洪填充算法.
 * 填充区域较大的时候会导致栈溢出.
 * 
 * @author Yixin
 *
 */
public class FloodFillAlgorithm {

	private BufferedImage image;

	//填充的颜色
	private int newRGB;

	//要替换的颜色
	private int targetRGB;
	
	//点击位置坐标
	private int x;
	private int y;
	
	private Stack<Integer> xs;
	private Stack<Integer> ys;
	
	public FloodFillAlgorithm(BufferedImage image, int x, int y, int newRGB) {
		this.image = image;

		this.x = x;
		this.y = y;
		
		//获取点击位置的颜色
		this.targetRGB = image.getRGB(x, y);
		
		//填充的颜色
		this.newRGB = newRGB;
	}

	public void fillUsingStack() {
		//填充的颜色和要替换的颜色一致,不做任何操作
		if(targetRGB == newRGB) {
			return;
		}
		
		xs = new Stack<>();
		ys = new Stack<>();
		
		xs.push(x);
		ys.push(y);
		
		while(!xs.empty() && !ys.empty()) {
			int x0,y0;
			x0 = xs.pop();
			y0 = ys.pop();
			
			int rgb = 0;
			try {
				rgb = image.getRGB(x0, y0);
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
			
			if(rgb == targetRGB) {
				image.setRGB(x0, y0, newRGB);
			} else {
				continue;
			}
			
			//左边
			xs.push(x0 - 1);
			ys.push(y0);
			
			//上边
			xs.push(x0);
			ys.push(y0 - 1);
			
			//右边
			xs.push(x0 + 1);
			ys.push(y0);
			
			//下边
			xs.push(x0);
			ys.push(y0 + 1);
		}
	}
	
	public void fill() {
		//填充的颜色和要替换的颜色一致,不做任何操作
		if(targetRGB == newRGB) {
			return;
		}
		
		fill(x,y);
	}
	
	public void fill(int x, int y) {
		
		int rgb = 0;
		
		//获取该位置的rgb值
		//越界则结束该次递归
		try {
			rgb = image.getRGB(x, y);
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
		
		//如果该位置的rgb值和填充的rgb值不一致
		//进行替换
		//否则结束该层递归
		if(rgb == targetRGB) {
			image.setRGB(x, y, newRGB);
		} else {
			return;
		}

		fill(x, y - 1);
		fill(x, y + 1);
		fill(x - 1, y);
		fill(x + 1, y);
	}

}
