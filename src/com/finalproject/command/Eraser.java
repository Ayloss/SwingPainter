package com.finalproject.command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


public class Eraser implements DrawCommand {

	private ArrayList<Point> area;
	public int areaSize;
	
	public Eraser() {
		area = new ArrayList<>();
	}
	
	@Override
	public void execute(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.white);
		
		for (Point point : area) {
			g2d.fillRect(point.x, point.y, areaSize,areaSize);
		}
	}
	
	public void addArea(int x, int y) {
		area.add(new Point(x, y));
	}

}
