package com.finalproject.component;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.ButtonGroup;
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
	private JButton roundedRectangle;
	private JButton airbrush;

	private MyCanvas myCanvas;
	
	public void setMyCanvas(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
	}
	
	public void addButtonClickEvent() {
		line.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.DRAW_LINE);
			}
			
		});
		
		rectangle.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.DRAW_RECTANGLE);

			}
			
		});
		
		oval.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.DRAW_OVAL);

			}
			
		});
		
		brush.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.PENCIL);
			}
			
		});
		
		eraser.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myCanvas.setCurrentAction(DrawCommand.ERASER);
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
		
		brush = new JButton("\u7B14\u5237");
		GridBagConstraints gbc_brush = new GridBagConstraints();
		gbc_brush.insets = new Insets(0, 0, 5, 5);
		gbc_brush.gridx = 0;
		gbc_brush.gridy = 0;
		add(brush, gbc_brush);
		
		eraser = new JButton("\u6A61\u76AE");
		GridBagConstraints gbc_eraser = new GridBagConstraints();
		gbc_eraser.insets = new Insets(0, 0, 5, 0);
		gbc_eraser.gridx = 1;
		gbc_eraser.gridy = 0;
		add(eraser, gbc_eraser);
		
		oval = new JButton("\u692D\u5706");
		GridBagConstraints gbc_oval = new GridBagConstraints();
		gbc_oval.insets = new Insets(0, 0, 5, 5);
		gbc_oval.gridx = 0;
		gbc_oval.gridy = 1;
		add(oval, gbc_oval);
		buttonGroup.add(oval);
		
		line = new JButton("\u76F4\u7EBF");
		GridBagConstraints gbc_line = new GridBagConstraints();
		gbc_line.insets = new Insets(0, 0, 5, 0);
		gbc_line.gridx = 1;
		gbc_line.gridy = 1;
		add(line, gbc_line);
		
		rectangle = new JButton("\u77E9\u5F62");
		GridBagConstraints gbc_rectangle = new GridBagConstraints();
		gbc_rectangle.insets = new Insets(0, 0, 5, 5);
		gbc_rectangle.gridx = 0;
		gbc_rectangle.gridy = 2;
		add(rectangle, gbc_rectangle);

		
		oilPaint = new JButton("\u6CB9\u6F06\u6876");
		oilPaint.setActionCommand("");
		GridBagConstraints gbc_oilPaint = new GridBagConstraints();
		gbc_oilPaint.insets = new Insets(0, 0, 5, 0);
		gbc_oilPaint.gridx = 1;
		gbc_oilPaint.gridy = 2;
		add(oilPaint, gbc_oilPaint);

		
		polygon = new JButton("\u591A\u8FB9\u5F62");
		GridBagConstraints gbc_polygon = new GridBagConstraints();
		gbc_polygon.insets = new Insets(0, 0, 5, 5);
		gbc_polygon.gridx = 0;
		gbc_polygon.gridy = 3;
		add(polygon, gbc_polygon);
		
		roundedRectangle = new JButton("\u5706\u89D2\u77E9\u5F62");
		GridBagConstraints gbc_roundedRectangle = new GridBagConstraints();
		gbc_roundedRectangle.insets = new Insets(0, 0, 0, 5);
		gbc_roundedRectangle.gridx = 0;
		gbc_roundedRectangle.gridy = 4;
		add(roundedRectangle, gbc_roundedRectangle);
		
		airbrush = new JButton("\u55B7\u67AA");
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
		buttonGroup.add(roundedRectangle);
		buttonGroup.add(airbrush);
		buttonGroup.add(brush);
	}

}
