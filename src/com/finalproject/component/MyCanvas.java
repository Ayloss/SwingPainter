package com.finalproject.component;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.finalproject.command.DrawCommand;
import com.finalproject.command.DrawLine;
import com.finalproject.command.DrawOval;
import com.finalproject.command.DrawRectangle;
import com.finalproject.command.Eraser;
import com.finalproject.command.OilPaint;
import com.finalproject.command.ResultQueue;
import com.finalproject.component.optionPanel.OptionPanel;
import com.finalproject.configure.EraserSize;
import com.finalproject.command.Brush;
import com.finalproject.shape.Line;
import com.finalproject.shape.Oval;
import com.finalproject.shape.Rectangle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

/**
 * 画板
 * 
 * @author Yixin
 *
 */
public class MyCanvas extends JPanel {

	// 结果队列,用于存储每次绘图的结果
	private ResultQueue resultQueue = new ResultQueue();

	// 选项栏
	private HashMap<String, OptionPanel> configures;

	// 当前的操作
	private int currentAction = DrawCommand.PENCIL;
	// 线条颜色
	private Color lineColor = Color.BLACK;
	// 填充颜色
	private Color fillColor = Color.WHITE;

	// 将所有的鼠标事件加到数组里,便于更换事件
	private ArrayList<MouseAdapter> mouseEvents = new ArrayList<>();

	// 绘图的结果会先绘制到该bufferedImage上,最后绘制到panel上
	private BufferedImage image;

	// 当前的操作
	private DrawCommand commandAtThisStep = null;

	// 鼠标移动停止标志
	private boolean isMouseMoveFinished = true;

	//油漆桶事件
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

	//橡皮擦事件
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
	
	//笔刷事件
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
	
	//画线事件
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

	//画圆事件
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

	//画矩形事件
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

	/**
	 * 设置当前的行为. 实际上是移除其他事件,添加入当前的事件.
	 * 
	 * @param action 当前事件的编号
	 */
	public void setCurrentAction(int action) {
		currentAction = action;

		for (MouseAdapter mouseAdapter : mouseEvents) {
			removeMouseListener(mouseAdapter);
			removeMouseMotionListener(mouseAdapter);
		}

		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
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
			setEraserCursor();
			break;
		case DrawCommand.OilPaint:
			addMouseListener(oilPaintEvent);
			break;
		}
	}

	/**
	 * 重做.调用ResultQueue的重做方法,然后重绘.
	 */
	public void redo() {
		if (resultQueue.redoable()) {
			resultQueue.redo();
			repaint();
		}
	}

	/**
	 * 撤销.调用ResultQueue的撤销方法,然后重绘.
	 */
	public void undo() {
		if (resultQueue.undoable()) {
			resultQueue.undo();
			repaint();
		}

	}

	/**
	 * 清空画板.
	 */
	public void clean() {
		resultQueue.clean();
		repaint();
	}

	/**
	 * 调整画板大小
	 * 
	 * @param width
	 * @param height
	 */
	public void resizeCanvas(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		// 设置大小后重绘
		revalidate();
	}

	/**
	 * 设置线条颜色
	 * @param lineColor
	 */
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * 设置填充颜色
	 * 
	 * @param fillColor
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	/**
	 * 添加选项卡,用于导出参数选项.
	 * 
	 * @param configures
	 */
	public void setConfigures(HashMap<String, OptionPanel> configures) {
		this.configures = configures;
	}

	/**
	 * 保存当前图像.目前只能保存jpg格式.
	 * 
	 * @param path
	 */
	public void saveImage(String path) {

		//如果不是以jpg结尾,添加jpg后缀
		if (!path.endsWith("jpg")) {
			path = path + ".jpg";
		}

		File file = new File(path);

		if (!file.exists()) {
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

	/**
	 * 打开图像.
	 * 
	 * @param path
	 */
	public void openImage(File path) {

		try {
			BufferedImage readedImage = ImageIO.read(path);

			resizeCanvas(readedImage.getWidth(), readedImage.getHeight());

			clean();

			resultQueue.addResult(readedImage);

			repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置橡皮擦光标.
	 * swing光标最大尺寸为32x32,如果图像小于该尺寸会被拉伸至32x32.
	 * 这里使用ARGB，将不要的区域设置为透明,这样就不会显示.
	 * @see http://stackoverflow.com/questions/12558887/changing-cursor-size
	 */
	public void setEraserCursor() {
		
		int eraserSize = (int) configures.get("eraser").export().get("eraserSize");

		BufferedImage cursor =  new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = (Graphics2D) cursor.getGraphics();
		//将图像填充透明
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(new Color(0, 0, 0,0));
		g2d.fillRect(0, 0, 32, 32);
		
		//填充橡皮擦的区域
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, eraserSize, eraserSize);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, eraserSize - 1, eraserSize - 1);
		
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "eraser"));
	}
}
