package com.finalproject.command;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class Brush implements DrawCommand{

	private ArrayList<Point> path;
	public Color LineColor;
	public int thickness;
	
	
	public Brush() {
		path = new ArrayList<>();
	}

	@Override
	public void execute(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(LineColor);
		BasicStroke basicStroke = new BasicStroke(thickness,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2d.setStroke(basicStroke);
		
		Point pre = null;
		for (Point point : path) {
			g2d.draw(new Line2D.Float(point.x, point.y, point.x, point.y));
			
			try {
				g2d.draw(new Line2D.Float(pre.x, pre.y, point.x, point.y));
			} catch (NullPointerException e) {
				
			}
			pre = point;
		}
	}
	
	public void addPoint(int x,int y) {
		path.add(new Point(x, y));
	}

}
