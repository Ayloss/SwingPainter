package com.finalproject.command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;


public class Eraser extends DrawCommand {

	private ArrayList<Point> area;
	
	public Eraser(HashMap<String, Object> configure) {
		super(configure);
		area = new ArrayList<>();
	}

	@Override
	public void execute(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.white);
		
		for (Point point : area) {
			g2d.fillRect(point.x, point.y, (Integer)configure.get("eraserSize"),(Integer)configure.get("eraserSize"));
		}
	}
	
	public void addArea(int x, int y) {
		area.add(new Point(x, y));
	}

}
