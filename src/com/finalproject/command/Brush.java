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
import java.util.HashMap;


public class Brush extends DrawCommand{

	private ArrayList<Point> path;
	private Color LineColor;
	

	public Brush(HashMap<String, Object> configure, Color lineColor) {
		super(configure);
		LineColor = lineColor;
		path = new ArrayList<>();
	}

	@Override
	public void execute(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(LineColor);
		BasicStroke basicStroke = new BasicStroke((Integer)configure.get("lineThickness"),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
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
