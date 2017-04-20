package com.finalproject.command;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.finalproject.shape.Rectangle;

public class DrawRectangle implements DrawCommand {

	public Rectangle shape;
	public Color lineColor; 
	public Color filledColor;
	public boolean filled;
	public int lineThickness;
	
	@Override
	public void execute(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		BasicStroke basicStroke = new BasicStroke(lineThickness);
		g2d.setStroke(basicStroke);
		
		if(filled) {
			g2d.setColor(filledColor);
			g2d.fillRect(shape.x, shape.y, shape.width, shape.height);
			g2d.setColor(lineColor);
			g2d.drawRect(shape.x, shape.y, shape.width, shape.height);
		} else {
			g2d.setColor(lineColor);
			g2d.drawRect(shape.x, shape.y, shape.width, shape.height);
		}
	}

}
