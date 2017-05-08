package com.finalproject.util;

import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * ��������㷨.
 * �������ϴ��ʱ��ᵼ��ջ���.
 * 
 * @author Yixin
 *
 */
public class FloodFillAlgorithm {

	private BufferedImage image;

	//������ɫ
	private int newRGB;

	//Ҫ�滻����ɫ
	private int targetRGB;
	
	//���λ������
	private int x;
	private int y;
	
	private Stack<Integer> xs;
	private Stack<Integer> ys;
	
	public FloodFillAlgorithm(BufferedImage image, int x, int y, int newRGB) {
		this.image = image;

		this.x = x;
		this.y = y;
		
		//��ȡ���λ�õ���ɫ
		this.targetRGB = image.getRGB(x, y);
		
		//������ɫ
		this.newRGB = newRGB;
	}

	public void fillUsingStack() {
		//������ɫ��Ҫ�滻����ɫһ��,�����κβ���
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
			
			//���
			xs.push(x0 - 1);
			ys.push(y0);
			
			//�ϱ�
			xs.push(x0);
			ys.push(y0 - 1);
			
			//�ұ�
			xs.push(x0 + 1);
			ys.push(y0);
			
			//�±�
			xs.push(x0);
			ys.push(y0 + 1);
		}
	}
	
	public void fill() {
		//������ɫ��Ҫ�滻����ɫһ��,�����κβ���
		if(targetRGB == newRGB) {
			return;
		}
		
		fill(x,y);
	}
	
	public void fill(int x, int y) {
		
		int rgb = 0;
		
		//��ȡ��λ�õ�rgbֵ
		//Խ��������ôεݹ�
		try {
			rgb = image.getRGB(x, y);
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
		
		//�����λ�õ�rgbֵ������rgbֵ��һ��
		//�����滻
		//��������ò�ݹ�
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
