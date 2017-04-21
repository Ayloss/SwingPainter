package com.finalproject.component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.finalproject.command.DrawCommand;
import com.finalproject.command.DrawCommandStack;
import com.finalproject.command.DrawLine;
import com.finalproject.command.DrawOval;
import com.finalproject.command.DrawRectangle;
import com.finalproject.command.Eraser;
import com.finalproject.component.configurepanel.ExportConfigure;
import com.finalproject.configure.EraserSize;
import com.finalproject.configure.LineThickness;
import com.finalproject.command.Brush;
import com.finalproject.shape.Line;
import com.finalproject.shape.Oval;
import com.finalproject.shape.Rectangle;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;

/**
 * »­°å
 * 
 * @author Yixin
 *
 */
public class MyCanvas extends JPanel {

	private DrawCommandStack drawCommandStack = new DrawCommandStack();

	private HashMap<String, ExportConfigure> configures;

	private int currentAction = DrawCommand.PENCIL;
	private Color lineColor = Color.BLACK;
	private Color fillColor = Color.WHITE;

	private ArrayList<MouseAdapter> mouseEvents = new ArrayList<>();

	private MouseAdapter eraserEvent = new MouseAdapter() {

		private int x;
		private int y;
		private Eraser eraser;

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			eraser = new Eraser(configures.get("eraser").export());
			eraser.addArea(x, y);

			drawCommandStack.addCommand(eraser);

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			eraser.addArea(x, y);

			repaint();

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseReleased(e);
		}

	};
	private MouseAdapter brushEvent = new MouseAdapter() {

		private int x;
		private int y;
		private Brush brush;

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			brush = new Brush(configures.get("brush").export(), new Color(lineColor.getRGB()));
			brush.addPoint(x, y);
			drawCommandStack.addCommand(brush);

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			brush.addPoint(x, y);

			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseReleased(e);
		}

	};
	private MouseAdapter drawLineEvent = new MouseAdapter() {

		private int x1, y1;
		private int x2, y2;
		private DrawLine drawLine;

		@Override
		public void mousePressed(MouseEvent e) {

			x1 = e.getX();
			y1 = e.getY();
			x2 = e.getX();
			y2 = e.getY();

			drawLine = new DrawLine(configures.get("line").export(), new Line(x1, y1, x2, y2),
					new Color(lineColor.getRGB()));
			drawCommandStack.addCommand(drawLine);

			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			x2 = e.getX();
			y2 = e.getY();

			drawLine.setShape(x1, y1, x2, y2);

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mouseDragged(e);
		}
	};

	private MouseAdapter drawOvalEvent = new MouseAdapter() {

		private int x1, y1;
		private int x2, y2;

		private DrawOval drawOval;

		@Override
		public void mousePressed(MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
			x2 = e.getX();
			y2 = e.getY();

			drawOval = new DrawOval(configures.get("oval").export(), new Oval(x1, y1, 0, 0),
					new Color(lineColor.getRGB()), new Color(fillColor.getRGB()));

			drawCommandStack.addCommand(drawOval);

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();

			if (x2 >= x1 && y2 >= y1) {
				drawOval.setPoint(x1, y1);
			} else if (x2 >= x1 && y2 <= y1) {
				drawOval.setPoint(x1, y2);
			} else if (x2 <= x1 && y2 >= y1) {
				drawOval.setPoint(x2, y1);
			} else if (x2 <= x1 && y2 <= y1) {
				drawOval.setPoint(x2, y2);
			}

			drawOval.setWidth(Math.abs(x1 - x2));
			drawOval.setHeight(Math.abs(y1 - y2));

			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseReleased(e);
		}

	};

	private MouseAdapter drawRectangleEvent = new MouseAdapter() {

		private int x1, y1;
		private int x2, y2;

		private DrawRectangle drawRectangle;

		@Override
		public void mousePressed(MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
			x2 = e.getX();
			y2 = e.getY();

			drawRectangle = new DrawRectangle(configures.get("rectangle").export(), new Rectangle(x1, y1, 0, 0),
					new Color(lineColor.getRGB()), new Color(fillColor.getRGB()));

			drawCommandStack.addCommand(drawRectangle);

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			x2 = e.getX();
			y2 = e.getY();

			if (x2 >= x1 && y2 >= y1) {
				drawRectangle.setPoint(x1, y1);
			} else if (x2 >= x1 && y2 <= y1) {
				drawRectangle.setPoint(x1, y2);
			} else if (x2 <= x1 && y2 >= y1) {
				drawRectangle.setPoint(x2, y1);
			} else if (x2 <= x1 && y2 <= y1) {
				drawRectangle.setPoint(x2, y2);
			}

			drawRectangle.setWidth(Math.abs(x1 - x2));
			drawRectangle.setHeight(Math.abs(y1 - y2));

			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseReleased(e);
		}

	};

	/**
	 * Create the panel.
	 */
	public MyCanvas() {

		setPreferredSize(new Dimension(400, 300));
		setBackground(Color.WHITE);
		setLayout(null);

		mouseEvents.add(drawLineEvent);
		mouseEvents.add(drawOvalEvent);
		mouseEvents.add(drawRectangleEvent);
		mouseEvents.add(brushEvent);
		mouseEvents.add(eraserEvent);

		addMouseListener(drawLineEvent);
		addMouseMotionListener(drawLineEvent);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		super.paint(g);

		drawCommandStack.executeAll(g);
	}

	public void setCurrentAction(int action) {
		currentAction = action;

		for (MouseAdapter mouseAdapter : mouseEvents) {
			removeMouseListener(mouseAdapter);
			removeMouseMotionListener(mouseAdapter);
		}

		switch (currentAction) {
		case DrawCommand.DRAW_LINE:
			addMouseListener(drawLineEvent);
			addMouseMotionListener(drawLineEvent);
			break;
		case DrawCommand.DRAW_OVAL:
			addMouseListener(drawOvalEvent);
			addMouseMotionListener(drawOvalEvent);
			break;
		case DrawCommand.DRAW_RECTANGLE:
			addMouseListener(drawRectangleEvent);
			addMouseMotionListener(drawRectangleEvent);
			break;
		case DrawCommand.PENCIL:
			addMouseListener(brushEvent);
			addMouseMotionListener(brushEvent);
			break;
		case DrawCommand.ERASER:
			addMouseListener(eraserEvent);
			addMouseMotionListener(eraserEvent);
			break;
		}
	}

	public void redo() {
		drawCommandStack.redo();
		repaint();
		System.out.println("redo!");
	}

	public void undo() {
		drawCommandStack.undo();
		repaint();
		System.out.println("undo!");
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}


	public void setConfigures(HashMap<String, ExportConfigure> configures) {
		this.configures = configures;
	}


}
