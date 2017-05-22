package com.finalproject.util;

import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * ����ɨ���ߵķ�������㷨�����㷨����������ѹջ�Ĵ�����
 * ����˼·��:
 * �ӵ��λ�ó�����������������������߽硣
 * ��������Ҳ��ƶ�һ�������������������������Ѱ�ҿ�������λ�á�
 * �ظ����Ϲ��̡�
 * @author Yixin
 *
 */
public class FloodFillScanLineAlgorithm {

	private BufferedImage image;

	//������ɫ
	private int newRGB;

	//Ҫ�滻����ɫ
	private int targetRGB;
	
	//���λ������
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
		
		//��ȡ���λ�õ���ɫ
		this.targetRGB = image.getRGB(x, y);
		
		//������ɫ
		this.newRGB = newRGB;
	}

//	public void fillUsingStack() {
//		//������ɫ��Ҫ�滻����ɫһ��,�����κβ���
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
		//������ɫ��Ҫ�滻����ɫһ��,�����κβ���
		if(targetRGB == newRGB) {
			return;
		}
		
		floodFillScanLine(x, y);
	}
	
	public void floodFillScanLine(int x, int y) {
		
		int y0;
		
		//�������
		y0 = y;
		while(y0 < height && image.getRGB(x, y0) == targetRGB) {
			image.setRGB(x, y0, newRGB);
			y0++;
		}
		
		//�������
		y0 = y - 1;
		while(y0 >= 0 && image.getRGB(x, y0) == targetRGB) {
			image.setRGB(x, y0, newRGB);
			y0--;
		}
		
		//����Ѱ����һɨ���ߵĳ�����
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
	      
	    //����Ѱ����һɨ���ߵĳ�����  
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
