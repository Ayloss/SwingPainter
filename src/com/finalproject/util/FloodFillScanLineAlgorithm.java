package com.finalproject.util;

import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * 基于扫描线的泛洪填充算法。该算法能显著减少压栈的次数。
 * 基本思路是:
 * 从点击位置出发，先往上下两侧填充至边界。
 * 随后往左右侧移动一步，再往上下两个方向遍历，寻找可以填充的位置。
 * 重复以上过程。
 * @author Yixin
 *
 */
public class FloodFillScanLineAlgorithm {

	private BufferedImage image;

	//填充的颜色
	private int newRGB;

	//要替换的颜色
	private int targetRGB;
	
	//点击位置坐标
	private int x;
	private int y;
	
	private int width;
	private int height;
		
	public FloodFillScanLineAlgorithm(BufferedImage image, int x, int y, int newRGB) {
		this.image = image;

		this.x = x;
		this.y = y;
		
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		//获取点击位置的颜色
		this.targetRGB = image.getRGB(x, y);
		
		//填充的颜色
		this.newRGB = newRGB;
	}

//	public void fillUsingStack() {
//		//填充的颜色和要替换的颜色一致,不做任何操作
//		if(targetRGB == newRGB) {
//			return;
//		}
//		
//		xs = new Stack<>();
//		ys = new Stack<>();
//		
//		xs.push(x);
//		ys.push(y);
//		
//		
//		}
//	}
	
	public void fill() {
		//填充的颜色和要替换的颜色一致,不做任何操作
		if(targetRGB == newRGB) {
			return;
		}
		
		floodFillScanLine(x, y);
	}
	
	public void floodFillScanLine(int x, int y) {
		
		int y0;
		
		//往下填充
		y0 = y;
		while(y0 < height && image.getRGB(x, y0) == targetRGB) {
			image.setRGB(x, y0, newRGB);
			y0++;
		}
		
		//往上填充
		y0 = y - 1;
		while(y0 >= 0 && image.getRGB(x, y0) == targetRGB) {
			image.setRGB(x, y0, newRGB);
			y0--;
		}
		
		//往左寻找下一扫描线的出发点
		y0 = y;  
	    while(y0 < height && image.getRGB(x, y0) == newRGB)  
	    {  
	        if(x > 0 && image.getRGB(x - 1, y0) == targetRGB)   
	        {  
	            floodFillScanLine(x - 1, y0); 
	        }   
	        y0++;  
	    }
	    
	    y0 = y - 1;  
	    while(y0 >= 0 && image.getRGB(x, y0) == newRGB)  
	    {  
	        if(x > 0 && image.getRGB(x - 1, y0) == targetRGB)   
	        {  
	            floodFillScanLine(x - 1, y0);
	        }  
	        y0--;  
	    }   
	      
	    //往右寻找下一扫描线的出发点  
	    y0 = y;  
	    while(y0 < height && image.getRGB(x, y0) == newRGB)  
	    {  
	        if(x < width - 1 && image.getRGB(x + 1, y0) == targetRGB)   
	        {             
	            floodFillScanLine(x + 1, y0);
	        }   
	        y0++;  
	    }  
	    y0 = y - 1;  
	    while(y0 >= 0 && image.getRGB(x, y0) == newRGB)  
	    {  
	        if(x < width - 1 && image.getRGB(x + 1, y0) == targetRGB)   
	        {  
	            floodFillScanLine(x + 1, y0); 
	        }  
	        y0--;  
	    }  
	}
}
