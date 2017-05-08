package com.finalproject.command;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.finalproject.util.FloodFillAlgorithm;
import com.finalproject.util.FloodFillScanLineAlgorithm;

public class OilPaint extends DrawCommand{
	
	private int x;
	
	private int y;
	
	private int newRGB;
	
	public OilPaint(HashMap<String, Object> configure, int x, int y, int newRGB) {
		super(configure);
		this.x = x;
		this.y = y;
		this.newRGB = newRGB;
	}

	@Override
	public void execute(BufferedImage image) {
//		FloodFillAlgorithm fillAlgorithm = new FloodFillAlgorithm(image, x, y,newRGB);
		
		FloodFillScanLineAlgorithm fillAlgorithm = new FloodFillScanLineAlgorithm(image, x, y, newRGB);
		
		fillAlgorithm.fill();
	}

}
