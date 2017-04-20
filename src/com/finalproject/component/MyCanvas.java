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

	public void setDotted(boolean dotted) {
		this.dotted = dotted;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	private int currentAction = DrawCommand.DRAW_LINE;
	private Color lineColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private int lineThickness = LineThickness.THIN.getVal();
	private int eraserSize = EraserSize.SMALL.getVal();
	private boolean dotted = false;
	private boolean filled = false;
	
	private ArrayList<MouseAdapter> mouseEvents = new ArrayList<>();

	private MouseAdapter eraserEvent = new MouseAdapter() {

		private int x;
		private int y;
		private Eraser eraser;
		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			eraser = new Eraser();
			eraser.areaSize = eraserSize;
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
			
			brush = new Brush();
			brush.addPoint(x,y);
			drawCommandStack.addCommand(brush);
			
			brush.LineColor = new Color(lineColor.getRGB());
			brush.thickness = lineThickness;
			
			repaint(20);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			
			brush.addPoint(x,y);
			
			repaint(20);
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

			drawLine = new DrawLine();
			drawLine.shape = new Line();
			drawCommandStack.addCommand(drawLine);

			drawLine.shape.x1 = x1;
			drawLine.shape.y1 = y1;
			drawLine.shape.x2 = x1;
			drawLine.shape.y2 = y1;
			drawLine.thickness = lineThickness;
			drawLine.dotted = dotted;

			drawLine.color = new Color(lineColor.getRGB());

			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			x2 = e.getX();
			y2 = e.getY();

			drawLine.shape.x2 = x2;
			drawLine.shape.y2 = y2;

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

			drawOval = new DrawOval();
			drawOval.shape = new Oval();
			drawOval.shape.x = x1;
			drawOval.shape.x = y1;
			drawOval.shape.width = 0;
			drawOval.shape.height = 0;

			drawOval.lineThickness = lineThickness;
			drawOval.filled = filled;
			drawOval.lineColor = new Color(lineColor.getRGB());
			drawOval.filledColor = new Color(fillColor.getRGB());

			drawCommandStack.addCommand(drawOval);
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			x2 = e.getX();
			y2 = e.getY();

			if (x2 >= x1 && y2 >= y1) {
				drawOval.shape.x = x1;
				drawOval.shape.y = y1;

			} else if (x2 >= x1 && y2 <= y1) {
				drawOval.shape.x = x1;
				drawOval.shape.y = y2;
			} else if (x2 <= x1 && y2 >= y1) {
				drawOval.shape.x = x2;
				drawOval.shape.y = y1;
			} else if (x2 <= x1 && y2 <= y1) {
				drawOval.shape.x = x2;
				drawOval.shape.y = y2;
			}

			drawOval.shape.width = Math.abs(x1 - x2);
			drawOval.shape.height = Math.abs(y1 - y2);

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

			drawRectangle = new DrawRectangle();
			drawRectangle.shape = new Rectangle();
			drawRectangle.shape.x = x1;
			drawRectangle.shape.x = y1;
			drawRectangle.shape.width = 0;
			drawRectangle.shape.height = 0;

			drawRectangle.lineThickness = lineThickness;
			drawRectangle.filled = filled;
			drawRectangle.lineColor = new Color(lineColor.getRGB());
			drawRectangle.filledColor = new Color(fillColor.getRGB());

			drawCommandStack.addCommand(drawRectangle);

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			x2 = e.getX();
			y2 = e.getY();

			if (x2 >= x1 && y2 >= y1) {
				drawRectangle.shape.x = x1;
				drawRectangle.shape.y = y1;
			} else if (x2 >= x1 && y2 <= y1) {
				drawRectangle.shape.x = x1;
				drawRectangle.shape.y = y2;
			} else if (x2 <= x1 && y2 >= y1) {
				drawRectangle.shape.x = x2;
				drawRectangle.shape.y = y1;
			} else if (x2 <= x1 && y2 <= y1) {
				drawRectangle.shape.x = x2;
				drawRectangle.shape.y = y2;
			}

			drawRectangle.shape.width = Math.abs(x1 - x2);
			drawRectangle.shape.height = Math.abs(y1 - y2);

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
		
		addMouseListener(drawRectangleEvent);
		addMouseMotionListener(drawRectangleEvent);
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

	public void setLineThickness(int thickness) {
		this.lineThickness = thickness;
	}
	
	public void setEraserSize(int eraserSize) {
		this.eraserSize = eraserSize;
	}
	

	public static void main(String[] args) {

		JFrame jFrame = new JFrame();
		MyCanvas myCanvas = new MyCanvas();
		jFrame.setBounds(0, 0, 500, 500);
		jFrame.getContentPane().add(myCanvas);
		jFrame.setVisible(true);
	}
	
}
