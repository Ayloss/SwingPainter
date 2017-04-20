package com.finalproject.command;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.finalproject.shape.Line;

public class DrawLine implements DrawCommand {

	public Line shape;
	public Color color;
	public boolean dotted;
	public int thickness;

	@Override
	public void execute(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		BasicStroke basicStroke;
		g2d.setColor(color);
		if (dotted) {
			basicStroke = new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 3.5f,
					new float[] { 5, 3 }, 0f);
		} else {
			basicStroke = new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		}
		g2d.setStroke(basicStroke);
		g2d.draw(new Line2D.Float(shape.x1, shape.y1, shape.x2, shape.y2));
	}

}
