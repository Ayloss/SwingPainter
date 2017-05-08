package com.finalproject.component;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.finalproject.command.DrawCommand;
import com.finalproject.command.DrawLine;
import com.finalproject.command.DrawOval;
import com.finalproject.command.DrawRectangle;
import com.finalproject.command.Eraser;
import com.finalproject.command.OilPaint;
import com.finalproject.command.ResultQueue;
import com.finalproject.component.configurepanel.ExportConfigure;
import com.finalproject.command.Brush;
import com.finalproject.shape.Line;
import com.finalproject.shape.Oval;
import com.finalproject.shape.Rectangle;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Dimension;

/**
 * 画板
 * 
 * @author Yixin
 *
 */
public class MyCanvas extends JPanel {

	private ResultQueue resultQueue = new ResultQueue();

	private HashMap<String, ExportConfigure> configures;

	private int currentAction = DrawCommand.PENCIL;
	private Color lineColor = Color.BLACK;
	private Color fillColor = Color.WHITE;

	private ArrayList<MouseAdapter> mouseEvents = new ArrayList<>();

	// 绘图的结果会先绘制到该bufferedImage上,最后绘制到panel上
	private BufferedImage image;

	// 当前的操作
	private DrawCommand commandAtThisStep = null;

	// 鼠标移动停止标志
	private boolean isMouseMoveFinished = true;

	private MouseAdapter oilPaintEvent = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {

			OilPaint oilPaint = new OilPaint(configures.get("oilPaint").export(), e.getX(), e.getY(),
					fillColor.getRGB());

			commandAtThisStep = oilPaint;

			isMouseMoveFinished = true;

			repaint();
		}

	};

	private MouseAdapter eraserEvent = new MouseAdapter() {

		private int x;
		private int y;
		private Eraser eraser;

		@Override
		public void mousePressed(MouseEvent e) {
			isMouseMoveFinished = false;

			x = e.getX();
			y = e.getY();

			eraser = new Eraser(configures.get("eraser").export());
			eraser.addArea(x, y);

			commandAtThisStep = eraser;

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			isMouseMoveFinished = true;

			mouseDragged(e);

			eraser = null;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			eraser.addArea(x, y);

			repaint();
		}

	};
	private MouseAdapter brushEvent = new MouseAdapter() {

		private int x;
		private int y;
		private Brush brush;

		@Override
		public void mousePressed(MouseEvent e) {
			isMouseMoveFinished = false;

			x = e.getX();
			y = e.getY();

			brush = new Brush(configures.get("brush").export(), new Color(lineColor.getRGB()));
			brush.addPoint(x, y);

			commandAtThisStep = brush;

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			isMouseMoveFinished = true;

			mouseDragged(e);

			brush = null;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			brush.addPoint(x, y);

			repaint();
		}

	};
	private MouseAdapter drawLineEvent = new MouseAdapter() {

		private int x1, y1;
		private int x2, y2;
		private DrawLine drawLine;

		@Override
		public void mousePressed(MouseEvent e) {
			isMouseMoveFinished = false;

			x1 = e.getX();
			y1 = e.getY();
			x2 = e.getX();
			y2 = e.getY();

			drawLine = new DrawLine(configures.get("line").export(), new Line(x1, y1, x2, y2),
					new Color(lineColor.getRGB()));

			commandAtThisStep = drawLine;

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
			isMouseMoveFinished = true;

			mouseDragged(e);

			drawLine = null;
		}
	};

	private MouseAdapter drawOvalEvent = new MouseAdapter() {

		private int x1, y1;
		private int x2, y2;

		private DrawOval drawOval;

		@Override
		public void mousePressed(MouseEvent e) {
			isMouseMoveFinished = false;

			x1 = e.getX();
			y1 = e.getY();
			x2 = e.getX();
			y2 = e.getY();

			drawOval = new DrawOval(configures.get("oval").export(), new Oval(x1, y1, 0, 0),
					new Color(lineColor.getRGB()), new Color(fillColor.getRGB()));

			commandAtThisStep = drawOval;

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			isMouseMoveFinished = true;

			mouseDragged(e);

			drawOval = null;
		}

		@Override
		public void mouseDragged(MouseEvent e) {

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

	};

	private MouseAdapter drawRectangleEvent = new MouseAdapter() {

		private int x1, y1;
		private int x2, y2;

		private DrawRectangle drawRectangle;

		@Override
		public void mousePressed(MouseEvent e) {
			isMouseMoveFinished = false;

			x1 = e.getX();
			y1 = e.getY();
			x2 = e.getX();
			y2 = e.getY();

			drawRectangle = new DrawRectangle(configures.get("rectangle").export(), new Rectangle(x1, y1, 0, 0),
					new Color(lineColor.getRGB()), new Color(fillColor.getRGB()));

			commandAtThisStep = drawRectangle;

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			isMouseMoveFinished = true;

			mouseDragged(e);

			drawRectangle = null;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
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
		mouseEvents.add(oilPaintEvent);

		addMouseListener(brushEvent);
		addMouseMotionListener(brushEvent);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		super.paint(g);

		image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics gi = image.getGraphics();
		gi.setColor(Color.WHITE);
		gi.fillRect(0, 0, getWidth(), getHeight());

		// 先绘制上一次结果
		if (!resultQueue.isEmpty()) {
			BufferedImage imagePrev = resultQueue.getLastResult();
			gi.drawImage(imagePrev, 0, 0, this);
		}

		// 如果没有执行重做或撤回,执行当前命令
		// 执行了重做或者撤回,不执行当前操作
		if (commandAtThisStep != null) {
			commandAtThisStep.execute(image);
		}

		// 鼠标移动结束,把绘图结果存入结果队列
		if (isMouseMoveFinished) {
			resultQueue.addResult(image);
			isMouseMoveFinished = false;
			commandAtThisStep = null;
		}

		g.drawImage(image, 0, 0, this);

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
		case DrawCommand.OilPaint:
			addMouseListener(oilPaintEvent);
			break;
		}
	}

	public void redo() {
		if (resultQueue.redoable()) {
			resultQueue.redo();
			repaint();
		}
	}

	public void undo() {
		if (resultQueue.undoable()) {
			resultQueue.undo();
			repaint();
		}

	}

	public void clean() {
		resultQueue.clean();
		repaint();
	}

	public void resizeCanvas(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		// 设置大小后重绘
		revalidate();
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
	
	public void saveImage(String path) {
		
		if(!path.endsWith("jpg")) {
			path = path + ".jpg";
		}
		
		File file = new File(path);
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			ImageIO.write(image, "jpg", file);
		} catch (IOException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
