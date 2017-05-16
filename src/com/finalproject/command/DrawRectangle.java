package com.finalproject.command;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.finalproject.shape.Rectangle;

public class DrawRectangle extends DrawCommand {

	private Rectangle shape;
	private Color lineColor;
	private Color filledColor;

	public DrawRectangle(HashMap<String, Object> configure, Rectangle shape, Color lineColor, Color filledColor) {
		super(configure);
		this.shape = shape;
		this.lineColor = lineColor;
		this.filledColor = filledColor;
	}

	@Override
	public void execute(BufferedImage image) {
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		BasicStroke basicStroke = new BasicStroke((Integer) configure.get("lineThickness"));
		g2d.setStroke(basicStroke);

		if (!(Boolean) configure.get("rounded")) {
			if ((Boolean) configure.get("filled")) {
				g2d.setColor(filledColor);
				g2d.fillRect(shape.x, shape.y, shape.width, shape.height);
				g2d.setColor(lineColor);
				g2d.drawRect(shape.x, shape.y, shape.width, shape.height);
			} else {
				g2d.setColor(lineColor);
				g2d.drawRect(shape.x, shape.y, shape.width, shape.height);
			}
		} else {
			int radius = (int) configure.get("radius");
			if ((Boolean) configure.get("filled")) {
				g2d.setColor(filledColor);
				g2d.fillRoundRect(shape.x, shape.y, shape.width, shape.height, radius, radius);
				g2d.setColor(lineColor);
				g2d.drawRoundRect(shape.x, shape.y, shape.width, shape.height, radius, radius);
			} else {
				g2d.setColor(lineColor);
				g2d.drawRoundRect(shape.x, shape.y, shape.width, shape.height, radius, radius);
			}
		}

	}

	public void setShape(int x, int y, int width, int height) {
		setPoint(x, y);
		setWidth(width);
		setHeight(height);
	}

	public void setPoint(int x, int y) {
		shape.x = x;
		shape.y = y;
	}

	public void setWidth(int width) {
		shape.width = width;
	}

	public void setHeight(int height) {
		shape.height = height;
	}

}
