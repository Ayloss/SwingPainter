package com.finalproject.command;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.finalproject.shape.Line;

public class DrawLine extends DrawCommand {

	private Line shape;
	private Color lineColor;

	@Override
	public void execute(BufferedImage image) {
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		BasicStroke basicStroke;
		g2d.setColor(lineColor);
		if ((Boolean)configure.get("dotted")) {
			basicStroke = new BasicStroke((Integer)configure.get("lineThickness"), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 3.5f,
					new float[] { 5, 3 }, 0f);
		} else {
			basicStroke = new BasicStroke((Integer)configure.get("lineThickness"), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		}
		g2d.setStroke(basicStroke);
		g2d.draw(new Line2D.Float(shape.x1, shape.y1, shape.x2, shape.y2));
	}

	public DrawLine(HashMap<String, Object> configure, Line shape, Color lineColor) {
		super(configure);
		this.shape = shape;
		this.lineColor = lineColor;
	}

	public void setShape(int x1, int y1, int x2, int y2) {
		shape.x1 = x1;
		shape.x2 = x2;
		shape.y1 = y1;
		shape.y2 = y2;
	}

	
}
