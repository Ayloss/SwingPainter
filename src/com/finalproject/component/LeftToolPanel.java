package com.finalproject.component;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.EtchedBorder;

import com.finalproject.command.DrawCommand;
import java.awt.Cursor;

/**
 * 左侧工具栏，用来选择绘制的图案
 * @author Yixin
 *
 */
public class LeftToolPanel extends JPanel {
	private JButton eraser;
	private JButton oilPaint;
	private JButton line;
	private JButton oval;
	private JButton brush;
	private JButton polygon;
	private JButton rectangle;
	private JButton airbrush;

	private MyCanvas myCanvas;
	private DrawOptionPanel drawOptionPanel;
	private JButton colorPicker;
	private JButton curve;
	
	public void setMyCanvas(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
	}
	
	private void addButtonClickEvent() {
		
		line.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.DRAW_LINE);
				drawOptionPanel.switchOptionPanel("lineOptionPanel");
			}
			
		});
		
		rectangle.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.DRAW_RECTANGLE);
				drawOptionPanel.switchOptionPanel("rectangleOptionPanel");
			}
			
		});
		
		oval.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.DRAW_OVAL);
				drawOptionPanel.switchOptionPanel("ovalOptionPanel");
			}
			
		});
		
		brush.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.PENCIL);
				drawOptionPanel.switchOptionPanel("brushOptionPanel");
			}
			
		});
		
		eraser.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.ERASER);
				drawOptionPanel.switchOptionPanel("eraserOptionPanel");
			}
		});
		
		oilPaint.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.OilPaint);
				drawOptionPanel.switchOptionPanel("oilPaintOptionPanel");
			}
			
		});
	}
	/**
	 * Create the panel.
	 */
	public LeftToolPanel() {
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		brush = new JButton("");
		brush.setIcon(new ImageIcon("resource/icon/brush.png"));
		GridBagConstraints gbc_brush = new GridBagConstraints();
		gbc_brush.insets = new Insets(0, 0, 5, 5);
		gbc_brush.gridx = 0;
		gbc_brush.gridy = 0;
		add(brush, gbc_brush);
		
		eraser = new JButton("");
		eraser.setIcon(new ImageIcon("resource/icon/eraser.png"));
		GridBagConstraints gbc_eraser = new GridBagConstraints();
		gbc_eraser.insets = new Insets(0, 0, 5, 0);
		gbc_eraser.gridx = 1;
		gbc_eraser.gridy = 0;
		add(eraser, gbc_eraser);
		
		oval = new JButton("");
		oval.setIcon(new ImageIcon("resource/icon/oval.png"));
		GridBagConstraints gbc_oval = new GridBagConstraints();
		gbc_oval.insets = new Insets(0, 0, 5, 5);
		gbc_oval.gridx = 0;
		gbc_oval.gridy = 1;
		add(oval, gbc_oval);
		buttonGroup.add(oval);
		
		line = new JButton("");
		line.setIcon(new ImageIcon("resource/icon/line.png"));
		GridBagConstraints gbc_line = new GridBagConstraints();
		gbc_line.insets = new Insets(0, 0, 5, 0);
		gbc_line.gridx = 1;
		gbc_line.gridy = 1;
		add(line, gbc_line);
		
		rectangle = new JButton("");
		rectangle.setIcon(new ImageIcon("resource/icon/rectangle.png"));
		GridBagConstraints gbc_rectangle = new GridBagConstraints();
		gbc_rectangle.insets = new Insets(0, 0, 5, 5);
		gbc_rectangle.gridx = 0;
		gbc_rectangle.gridy = 2;
		add(rectangle, gbc_rectangle);

		
		oilPaint = new JButton("");
		oilPaint.setIcon(new ImageIcon("resource/icon/oilpaint.png"));
		oilPaint.setActionCommand("");
		GridBagConstraints gbc_oilPaint = new GridBagConstraints();
		gbc_oilPaint.insets = new Insets(0, 0, 5, 0);
		gbc_oilPaint.gridx = 1;
		gbc_oilPaint.gridy = 2;
		add(oilPaint, gbc_oilPaint);

		
		polygon = new JButton("");
		polygon.setEnabled(false);
		polygon.setIcon(new ImageIcon("resource/icon/polygon.png"));
		GridBagConstraints gbc_polygon = new GridBagConstraints();
		gbc_polygon.insets = new Insets(0, 0, 5, 5);
		gbc_polygon.gridx = 0;
		gbc_polygon.gridy = 3;
		add(polygon, gbc_polygon);
		
		colorPicker = new JButton("");
		colorPicker.setEnabled(false);
		colorPicker.setIcon(new ImageIcon("resource/icon/colorpicker.png"));
		GridBagConstraints gbc_colorPicker = new GridBagConstraints();
		gbc_colorPicker.insets = new Insets(0, 0, 5, 0);
		gbc_colorPicker.gridx = 1;
		gbc_colorPicker.gridy = 3;
		add(colorPicker, gbc_colorPicker);
		
		curve = new JButton("");
		curve.setEnabled(false);
		curve.setIcon(new ImageIcon("resource/icon/curve.png"));
		GridBagConstraints gbc_curve = new GridBagConstraints();
		gbc_curve.insets = new Insets(0, 0, 0, 5);
		gbc_curve.gridx = 0;
		gbc_curve.gridy = 4;
		add(curve, gbc_curve);
		
		airbrush = new JButton("");
		airbrush.setEnabled(false);
		airbrush.setIcon(new ImageIcon("resource/icon/airbrush.png"));
		airbrush.setActionCommand("");
		GridBagConstraints gbc_airbrush = new GridBagConstraints();
		gbc_airbrush.gridx = 1;
		gbc_airbrush.gridy = 4;
		add(airbrush, gbc_airbrush);

		buttonGroup.add(rectangle);
		buttonGroup.add(oilPaint);
		buttonGroup.add(eraser);
		buttonGroup.add(line);
		buttonGroup.add(polygon);
		buttonGroup.add(airbrush);
		buttonGroup.add(brush);
		
		addButtonClickEvent();
		
	}

	public void setDrawOptionPanel(DrawOptionPanel drawOptionPanel) {
		this.drawOptionPanel = drawOptionPanel;
	}

}
