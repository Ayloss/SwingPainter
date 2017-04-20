package org.jfree;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CubicCurveMouse extends JFrame {
	DrawingCanvas canvas;

	JLabel label = new JLabel("Mouse Location (x, y):  ");
	JLabel coords = new JLabel("");

	public CubicCurveMouse() {
		super();
		Container container = getContentPane();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		panel.add(label);
		// panel.add(label);
		panel.setOpaque(false);

		panel.add(coords);

		container.add(panel, BorderLayout.SOUTH);

		canvas = new DrawingCanvas();
		JPanel jpanel = new JPanel();
		jpanel.add(canvas);
		container.add(jpanel);
		addWindowListener(new WindowEventHandler());
		pack();
		setSize(300, 300);
		setVisible(true);
	}

	class WindowEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public static void main(String arg[]) {

		new CubicCurveMouse();
	}

	class DrawingCanvas extends Canvas {
		// x1,y1为起始点坐标，xc1cur, yc1cur�&#65533;2次点击时点坐标，x4new,
		// y4new第一次松�&#65533;
		// 时纪录的点，
		float x1, y1, xc1cur, yc1cur, xc1new, yc1new, xc2cur, yc2cur, xc2new, yc2new, x4cur, y4cur, x4new, y4new;

		int pressNo = 0; // 点击鼠标次数

		int dragFlag1 = -1;

		int dragFlag2 = -1;

		boolean clearFlag = false;

		float dashes[] = { 5f, 5f };

		BasicStroke stroke;

		public DrawingCanvas() {
			setBackground(Color.white);
			addMouseListener(new MyMouseListener());
			addMouseMotionListener(new MyMouseListener());
			setSize(400, 400);
			stroke = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10f, dashes, 0f);
		}

		public void update(Graphics g) {
			paint(g);
		}

		public void paint(Graphics g) {
			Graphics2D g2D = (Graphics2D) g;

			if (pressNo == 1) {
				g2D.setXORMode(getBackground());
				g2D.setColor(Color.red);
				g2D.setStroke(stroke);

				// Erase the currently existing line
				g2D.draw(new Line2D.Float(x1, y1, x4cur, y4cur));
				// Draw the new line
				g2D.draw(new Line2D.Float(x1, y1, x4new, y4new));

				// Update the currently existing coordinate values
				x4cur = x4new;
				y4cur = y4new;

			} else if (pressNo == 2) {
				g2D.setXORMode(getBackground());
				g2D.setColor(Color.black);
				g2D.setStroke(stroke);

				if (dragFlag1 != -1) {
					g2D.draw(new QuadCurve2D.Float(x1, y1, xc1cur, yc1cur, x4new, y4new));
				}
				dragFlag1++; // Reset the drag-flag

				g2D.draw(new QuadCurve2D.Float(x1, y1, xc1new, yc1new, x4new, y4new));

				xc1cur = xc1new;
				yc1cur = yc1new;
			} else if (pressNo == 3) {
				g2D.setXORMode(getBackground());
				g2D.setColor(Color.black);

				if (dragFlag2 != -1) {
					g2D.draw(new CubicCurve2D.Float(x1, y1, xc1new, yc1new, xc2cur, yc2cur, x4new, y4new));
				}
				dragFlag2++; // Reset the drag flag
				g2D.draw(new CubicCurve2D.Float(x1, y1, xc1new, yc1new, xc2new, yc2new, x4new, y4new));
				xc2cur = xc2new;
				yc2cur = yc2new;
			}
			if (clearFlag) {
				g2D.setXORMode(getBackground());
				g2D.setColor(Color.black);
				g2D.setStroke(stroke);

				g2D.draw(new Line2D.Float(x1, y1, x4new, y4new));
				g2D.draw(new QuadCurve2D.Float(x1, y1, xc1new, yc1new, x4new, y4new));
				clearFlag = false;
			}
		}

		class MyMouseListener extends MouseAdapter implements MouseMotionListener {
			public void mousePressed(MouseEvent e) {
				if (pressNo == 0) {
					pressNo++;
					x1 = x4cur = e.getX();
					y1 = y4cur = e.getY();
				} else if (pressNo == 1) {
					pressNo++;
					xc1cur = e.getX();
					yc1cur = e.getY();
				} else if (pressNo == 2) {
					pressNo++;
					xc2cur = e.getX();
					yc2cur = e.getY();
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (pressNo == 1) {
					x4new = e.getX();
					y4new = e.getY();
					canvas.repaint();
				} else if (pressNo == 2) {
					xc1new = e.getX();
					yc1new = e.getY();
					canvas.repaint();
				} else if (pressNo == 3) {
					xc2new = e.getX();
					yc2new = e.getY();
					canvas.repaint();
					pressNo = 0;
					dragFlag1 = -1;
					dragFlag2 = -1;
					clearFlag = true;
				}
			}

			public void mouseDragged(MouseEvent e) {
				if (pressNo == 1) {
					x4new = e.getX();
					y4new = e.getY();

				} else if (pressNo == 2) {
					xc1new = e.getX();
					yc1new = e.getY();

				} else if (pressNo == 3) {
					xc2new = e.getX();
					yc2new = e.getY();

				}
				String string = "(" + Integer.toString(e.getX()) + ", " + Integer.toString(e.getY()) + ")";
				coords.setText(string);
				canvas.repaint();
			}

			public void mouseMoved(MouseEvent e) {
				String string = "(" + Integer.toString(e.getX()) + ", " + Integer.toString(e.getY()) + ")";
				coords.setText(string);
			}
		}
	}
}